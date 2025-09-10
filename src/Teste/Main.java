package Teste;

import Dominio.FilaCircularDeProcessos;
import Dominio.*;

public class Main{
    public static void main(String[] args) {
        FilaCircularDeProcessos listaDeProcessos = new FilaCircularDeProcessos();
        Processo processo = new Processo(1, "Spotify", 1, 5, "");
        Processo processo1 = new Processo(2, "Google", 2, 3, "DISCO");
        Processo processo2 = new Processo(3, "Brave", 1, 4, "");
        Processo processo3 = new Processo(4, "Opera", 2, 4, "DISCO");
        Processo processo4 = new Processo(5, "Fortnite", 3, 4, "");
        Processo processo5 = new Processo(6, "CS-GO", 1, 5, "DISCO");
        Scheduler scheduler = new Scheduler();
        scheduler.addProcesso(processo2);
        scheduler.addProcesso(processo);
        scheduler.addProcesso(processo5);
        scheduler.execTodosCiclos();
    }
}