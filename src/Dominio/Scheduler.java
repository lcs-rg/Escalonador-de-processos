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
        return "[" + lista_alta_prioridade + "]" + "\n" +
                "[" + lista_media_prioridade + "]" + "\n" +
                "[" + lista_baixa_prioridade + "]" + "\n" +
                "[" + lista_bloqueados + "]";
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
}
