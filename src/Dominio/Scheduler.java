package Dominio;

public class Scheduler {
    ListaDeProcessos lista_alta_prioridade;
    ListaDeProcessos lista_media_prioridade;
    ListaDeProcessos lista_baixa_prioridade;
    ListaDeProcessos lista_bloqueados;
    public int contador_ciclos_alta_prioridade;
    public Scheduler(){
        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }
    public String getLista_alta_prioridade(){
        return lista_alta_prioridade.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Prioridade Alta:").append(lista_alta_prioridade).append("\n");
        sb.append("Prioridade Media:").append(lista_media_prioridade).append("\n");
        sb.append("Prioridade Baixa:").append(lista_baixa_prioridade).append("\n");
        sb.append("Lista de bloqueados:").append(lista_bloqueados);
        return sb.toString();
    }
    public void addProcesso(Processo processo){
        if (processo.prioridade == 1){
    lista_alta_prioridade.addLast(processo);
        } else if (processo.prioridade == 2){
    lista_media_prioridade.addLast(processo);
        } else if (processo.prioridade == 3){
    lista_baixa_prioridade.addLast(processo);
        }
    }
    public void execCiclo() {
        System.out.println("== Iniciando Ciclo... ==");
        desbloquearProcesso();
        Processo atual = selecionarProcesso();
        if(atual == null){
            System.out.println("Nenhum processo disponível nesse ciclo");
            return;
        }
        execProcesso(atual);

        System.out.println(this.toString());
    }

    private void desbloquearProcesso(){
        if(!lista_bloqueados.isEmpty()){
            Processo desbloqueado = lista_bloqueados.removeFirst();
                desbloqueado.bloqueado = false;
                addProcesso(desbloqueado);
            System.out.println("Processo desbloquado:" + desbloqueado);
        }
    }
    private Processo selecionarProcesso(){
        Processo atual = null;

        // controle de fatia de tempo da prioridade alta
        if(contador_ciclos_alta_prioridade >= 5){
            if(!lista_media_prioridade.isEmpty()){
                atual = lista_media_prioridade.removeFirst();
            } else if(!lista_baixa_prioridade.isEmpty()){
                atual = lista_baixa_prioridade.removeFirst();
            }
            contador_ciclos_alta_prioridade = 0;
        }

        if(atual == null){
            if(!lista_alta_prioridade.isEmpty()){
                atual = lista_alta_prioridade.removeFirst();
                contador_ciclos_alta_prioridade++;
            } else if(!lista_media_prioridade.isEmpty()){
                atual = lista_media_prioridade.removeFirst();
            } else if(!lista_baixa_prioridade.isEmpty()){
                atual = lista_baixa_prioridade.removeFirst();
            }
        }

        return atual;
    }
    private void execProcesso(Processo atual){
        if("DISCO".equalsIgnoreCase(atual.recurso_necessario) && !atual.bloqueado){
            atual.bloqueado = true;
            lista_bloqueados.addLast(atual);
            System.out.println("Processo " + atual.nome + " foi bloqueado aguardando DISCO");
            return;
        }

        atual.ciclos_necessarios--;
        System.out.println("Executando: " + atual.nome + " | Ciclos restantes: " + atual.ciclos_necessarios);

        if(atual.ciclos_necessarios > 0){
            addProcesso(atual);
        } else {
            System.out.println("Processo " + atual.nome + " foi finalizado com sucesso");
        }
    }
}
