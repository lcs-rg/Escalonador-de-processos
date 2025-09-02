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
}
