package Dominio;

public class Scheduler {
    FilaCircularDeProcessos listaAltaP;
    FilaCircularDeProcessos listaMediaP;
    FilaCircularDeProcessos listaBaixaP;
    FilaCircularDeProcessos listaBloqueados;
    public int contador_ciclos_alta_prioridade;
    public Scheduler(){
        this.listaAltaP = new FilaCircularDeProcessos();
        this.listaMediaP = new FilaCircularDeProcessos();
        this.listaBaixaP = new FilaCircularDeProcessos();
        this.listaBloqueados = new FilaCircularDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }
    public FilaCircularDeProcessos getListaMediaP(){
        return listaMediaP;
    }
    public FilaCircularDeProcessos getListaBaixaP(){
        return listaBaixaP;
    }
    public FilaCircularDeProcessos getListaAltaP(){
        return listaAltaP;
    }
    public FilaCircularDeProcessos getListaBloqueados(){
        return listaBloqueados;
    }
    private boolean ListasVazias(){
        return listaAltaP.isEmpty() && listaMediaP.isEmpty() &&
                listaBaixaP.isEmpty() && listaBloqueados.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Prioridade Alta:").append(listaAltaP.toString()).append("\n");
        sb.append("Prioridade Media:").append(listaMediaP.toString()).append("\n");
        sb.append("Prioridade Baixa:").append(listaBaixaP.toString()).append("\n");
        sb.append("Lista de bloqueados:").append(listaBloqueados.toString());
        return sb.toString();
    }
    public String exportarTudo(){
        return listaAltaP.exportar()
                + listaMediaP.exportar()
                + listaBaixaP.exportar()
                + listaBloqueados.exportar();
    }
    public void addProcesso(Processo processo) {
            switch (processo.getPrioridade()){
                case 1: listaAltaP.addLast(processo);
                break;
                case 2: listaMediaP.addLast(processo);
                break;
                case 3: listaBaixaP.addLast(processo);
                break;
                default:
                    throw new IndexOutOfBoundsException("Crie um processo com prioridade permitida");
        }
    }
    public void execCiclo() {
        System.out.println("=== Iniciando Ciclo... ===");
        desbloquearProcesso();
        Processo atual = selecionarProcesso();
        if(atual == null){
            System.out.println("Nenhum processo disponível nesse ciclo");
            return;
        }
        execProcesso(atual);

        System.out.println(this.toString());
    }
    public void execTodosCiclos(){
        int ciclo = 1;
        if(ListasVazias()){
            System.out.println("Nenhum ciclo a ser concluído...");
            return;
        }
        while (!ListasVazias()){
            System.out.println("\n=== Ciclo:" + ciclo + "===");
            System.out.println();
            execCiclo();
            ciclo++;
        }
        System.out.println("\nTodos ciclos foram concluídos");
    }

    private void desbloquearProcesso(){
        if(!listaBloqueados.isEmpty()) {
            Processo desbloqueado = listaBloqueados.removeFirst();
            System.out.println("Processo desbloquado:" + desbloqueado);
            switch (desbloqueado.prioridade) {
                case 1:
                    listaAltaP.addLast(desbloqueado);
                    break;
                case 2:
                    listaMediaP.addLast(desbloqueado);
                    break;
                case 3:
                    listaBaixaP.addLast(desbloqueado);
            }
        }
    }
    private Processo selecionarProcesso(){
        Processo atual = null;

        // controle de fatia de tempo da prioridade alta
        if(contador_ciclos_alta_prioridade >= 5){
             if(!listaMediaP.isEmpty()){
                atual = listaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if(!listaBaixaP.isEmpty()){
                atual = listaBaixaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            }
        }

        if(atual == null){
            if(!listaAltaP.isEmpty()){
                atual = listaAltaP.removeFirst();
                contador_ciclos_alta_prioridade++;
            } else if(!listaMediaP.isEmpty()){
                atual = listaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if(!listaBaixaP.isEmpty()){
                atual = listaBaixaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            }
        }
            if(atual == null){
                System.out.println("Nenhum processo a executar");
                return null;
            }
            return atual;
    }
    private void execProcesso(Processo atual){
        if("DISCO".equalsIgnoreCase(atual.recurso_necessario)){
            System.out.println("Processo " + atual.nome + " foi bloqueado aguardando DISCO");
            atual.recurso_necessario = null;
            listaBloqueados.addLast(atual);
            return;
        }
        atual.ciclos_necessarios--;
        System.out.println("Executando: " + atual.nome + " | Ciclos restantes: " + atual.ciclos_necessarios);

        if(atual.ciclos_necessarios <= 0){
            System.out.println("Processo " + atual.nome + " foi finalizado com sucesso");
        } else {
            addProcesso(atual);
        }
    }
    public Processo verProximo(){
        if(!listaAltaP.isEmpty()) return listaAltaP.peek();
        if(!listaMediaP.isEmpty()) return listaMediaP.peek();
        if(!listaBaixaP.isEmpty()) return listaBaixaP.peek();
        if(!listaBloqueados.isEmpty()) return listaBloqueados.peek();
        return null;
    }
    public Processo buscarProcesso(int id){
        Processo processo = listaAltaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = listaMediaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = listaBaixaP.buscar(id);

        if (processo != null) {
            return processo;
        }
        processo = listaBloqueados.buscar(id);
        if (processo != null) {
            return processo;
        }
        return null;
    }public int buscarId(int id){
        Processo processo = listaAltaP.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        processo = listaMediaP.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        processo = listaBaixaP.buscar(id);

        if (processo != null) {
            return processo.id;
        }
        processo = listaBloqueados.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        return 0;
    }
    public boolean removerProcesso(int id) {
        Processo p = buscarProcesso(id);
        if (p != null) {
            if (listaAltaP.buscar(id) != null) listaAltaP.removeid(id);
            else if (listaMediaP.buscar(id) != null) listaMediaP.removeid(id);
            else if (listaBaixaP.buscar(id) != null) listaBaixaP.removeid(id);
            else if (listaBloqueados.buscar(id) != null) listaBloqueados.removeid(id);
            System.out.println("Processo removido com sucesso");
            return true;
        }
        System.out.println("Nenhum processo com id:" + id + " a ser removido, tente novamente...");
        return false;
    }
}
