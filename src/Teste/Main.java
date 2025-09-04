package Teste;

import Dominio.ListaDeProcessos;
import Dominio.*;

public class Main{
    public static void main(String[] args) {
        ListaDeProcessos listaDeProcessos = new ListaDeProcessos();
        Processo processo = new Processo(1, "Spotify", 1, 4, "");
        Processo processo1 = new Processo(2, "Google", 2, 3, "");
        Processo processo2 = new Processo(3, "Brave", 1, 4, "");
        Scheduler scheduler = new Scheduler();
        scheduler.addProcesso(processo);
        scheduler.addProcesso(processo1);
        scheduler.addProcesso(processo2);
        System.out.println(scheduler.toString());
    }
}