package Teste;

import Dominio.ListaDeProcessos;
import Dominio.*;

public class Main{
    public static void main(String[] args) {
        ListaDeProcessos listaDeProcessos = new ListaDeProcessos();
        Processo processo = new Processo(1, "Spotify", 1, 4, "");
        Processo processo1 = new Processo(2, "Google", 2, 3, "");
        Scheduler scheduler = new Scheduler();
        listaDeProcessos.addFirst(processo);
        listaDeProcessos.addFirst(processo1);
        listaDeProcessos.printList();
    }
}