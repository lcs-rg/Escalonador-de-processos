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
    public String getListaMediaP(){
        return listaMediaP.toString();
    }
    public String getListaBaixaP(){
        return listaBaixaP.toString();
    }
    public String getListaAltaP(){
        return listaAltaP.toString();
    }
    public String getListaBloqueados(){
        return listaBloqueados.toString();
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
    public void addProcesso(Processo processo) {
            switch (processo.getPrioridade()){
                case 1: listaAltaP.addLast(processo);
                break;
                case 2: listaMediaP.addLast(processo);
                break;
                case 3: listaBaixaP.addLast(processo);
                break;
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
}
