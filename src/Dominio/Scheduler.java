package Dominio;

public class Scheduler {
    FilaCircularDeProcessos filaAltaP;
    FilaCircularDeProcessos filaMediaP;
    FilaCircularDeProcessos filaBaixaP;
    FilaCircularDeProcessos filaBloqueados;
    public int contador_ciclos_alta_prioridade;
    public Scheduler(){
        this.filaAltaP = new FilaCircularDeProcessos();
        this.filaMediaP = new FilaCircularDeProcessos();
        this.filaBaixaP = new FilaCircularDeProcessos();
        this.filaBloqueados = new FilaCircularDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }
    public FilaCircularDeProcessos getFilaMediaP(){
        return filaMediaP;
    }
    public FilaCircularDeProcessos getFilaBaixaP(){
        return filaBaixaP;
    }
    public FilaCircularDeProcessos getFilaAltaP(){
        return filaAltaP;
    }
    public FilaCircularDeProcessos getFilaBloqueados(){
        return filaBloqueados;
    }
    private boolean ListasVazias(){
        return filaAltaP.isEmpty() && filaMediaP.isEmpty() &&
                filaBaixaP.isEmpty() && filaBloqueados.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Prioridade Alta:").append(filaAltaP.toString()).append("\n");
        sb.append("Prioridade Media:").append(filaMediaP.toString()).append("\n");
        sb.append("Prioridade Baixa:").append(filaBaixaP.toString()).append("\n");
        sb.append("Lista de bloqueados:").append(filaBloqueados.toString());
        return sb.toString();
    }
    public String exportarTudo(){
        return filaAltaP.exportar()
                + filaMediaP.exportar()
                + filaBaixaP.exportar()
                + filaBloqueados.exportar();
    }
    public void addProcesso(Processo processo) {
            switch (processo.getPrioridade()){
                case 1: filaAltaP.addLast(processo);
                break;
                case 2: filaMediaP.addLast(processo);
                break;
                case 3: filaBaixaP.addLast(processo);
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
            System.out.println("\n=== Ciclo:" + ciclo + " ===");
            System.out.println();
            execCiclo();
            ciclo++;
        }
        System.out.println("\nTodos ciclos foram concluídos");
    }

    private void desbloquearProcesso(){
        if(!filaBloqueados.isEmpty()) {
            Processo desbloqueado = filaBloqueados.removeFirst();
            System.out.println("Processo desbloquado:" + desbloqueado);
            switch (desbloqueado.prioridade) {
                case 1:
                    filaAltaP.addLast(desbloqueado);
                    break;
                case 2:
                    filaMediaP.addLast(desbloqueado);
                    break;
                case 3:
                    filaBaixaP.addLast(desbloqueado);
            }
        }
    }
    private Processo selecionarProcesso(){
        Processo atual = null;

        // controle de fatia de tempo da prioridade alta
        if(contador_ciclos_alta_prioridade >= 5){
             if(!filaMediaP.isEmpty()){
                atual = filaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if(!filaBaixaP.isEmpty()){
                atual = filaBaixaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            }
        }

        if(atual == null){
            if(!filaAltaP.isEmpty()){
                atual = filaAltaP.removeFirst();
                contador_ciclos_alta_prioridade++;
            } else if(!filaMediaP.isEmpty()){
                atual = filaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if(!filaBaixaP.isEmpty()){
                atual = filaBaixaP.removeFirst();
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
            filaBloqueados.addLast(atual);
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
        if(!filaAltaP.isEmpty()) return filaAltaP.peek();
        if(!filaMediaP.isEmpty()) return filaMediaP.peek();
        if(!filaBaixaP.isEmpty()) return filaBaixaP.peek();
        if(!filaBloqueados.isEmpty()) return filaBloqueados.peek();
        return null;
    }
    public Processo buscarProcesso(int id){
        Processo processo = filaAltaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = filaMediaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = filaBaixaP.buscar(id);

        if (processo != null) {
            return processo;
        }
        processo = filaBloqueados.buscar(id);
        if (processo != null) {
            return processo;
        }
        return null;
    }public int buscarId(int id){
        Processo processo = filaAltaP.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        processo = filaMediaP.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        processo = filaBaixaP.buscar(id);

        if (processo != null) {
            return processo.id;
        }
        processo = filaBloqueados.buscar(id);
        if (processo != null) {
            return processo.id;
        }
        return 0;
    }
    public boolean removerProcesso(int id) {
        Processo p = buscarProcesso(id);
        if (p != null) {
            if (filaAltaP.buscar(id) != null) filaAltaP.removeid(id);
            else if (filaMediaP.buscar(id) != null) filaMediaP.removeid(id);
            else if (filaBaixaP.buscar(id) != null) filaBaixaP.removeid(id);
            else if (filaBloqueados.buscar(id) != null) filaBloqueados.removeid(id);
            System.out.println("Processo removido com sucesso");
            return true;
        }
        System.out.println("Nenhum processo com id:" + id + " a ser removido, tente novamente...");
        return false;
    }
}
