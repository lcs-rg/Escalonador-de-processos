package Teste;

import Dominio.ListaDeProcessos;
import Dominio.*;

public class Main{
    public static void main(String[] args) {
        ListaDeProcessos listaDeProcessos = new ListaDeProcessos();
        Processo processo = new Processo(1, "Spotify", 1, 5, "");
        Processo processo1 = new Processo(2, "Google", 2, 3, "DISCO");
        Processo processo2 = new Processo(3, "Brave", 1, 4, "");
        Processo processo3 = new Processo(4, "Opera", 2, 4, "DISCO");
        Processo processo4 = new Processo(5, "Fortnite", 3, 4, "");
        Scheduler scheduler = new Scheduler();
        scheduler.addProcesso(processo1);
        scheduler.addProcesso(processo);
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        scheduler.execCiclo();
        System.out.println(scheduler.getLista_bloqueados());
    }
}