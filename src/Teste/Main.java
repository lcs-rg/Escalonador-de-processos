package Teste;
import java.util.Scanner;

import Dominio.*;

public class Main{
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("===|=========================================|===");
            System.out.println("===|         ESCALONADOR DE PROCESSOS        |===");
            System.out.println("===| Digite a opção abaixo que você  deseja: |===");
            System.out.println("===|      Adicionar um novo processo: 1      |===");
            System.out.println("===|         Remover um  processo: 2         |===");
            System.out.println("===|            Ver um processo: 3           |===");
            System.out.println("===|             Ver uma lista: 4            |===");
            System.out.println("===|           Executar um ciclo: 5          |===");
            System.out.println("===|       Executar todos os ciclos: 6       |===");
            System.out.println("===|=========================================|===");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao){
                case 1:
                System.out.println("Digite o ID do processo: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite o nome do processo: ");
                String nome = sc.nextLine();
                System.out.println("Digite a prioridade do processo: ");
                int prioridade = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite a quantidde de ciclos necessários do processo: ");
                int ciclos = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite se necessário o recurso do processo: ");
                String recurso = sc.nextLine();
                Processo processo = new Processo(id, nome, prioridade, ciclos, recurso);
                scheduler.addProcesso(processo);
            break;
                case 2 :
                    System.out.println("Digite o ID do processo a ser removido");
                    int removerid = sc.nextInt();
                    break;
                case 3:

                    break;
                case 4:
                    int verlista;
                    System.out.println("Qual lista você deseja ver?:");
                    System.out.println("Lista de Alta prioridade: 1");
                    System.out.println("Lista de Media prioridade: 2");
                    System.out.println("Lista de Baixa prioridade: 3");
                    System.out.println("Lista de Bloqueados: 4");
                    verlista = sc.nextInt();
                    sc.nextLine();
                    if(verlista == 1){

                    } else if(verlista == 2)
                    break;
                case 5:
                    scheduler.execCiclo();
                    break;
                case 6:
                    scheduler.execTodosCiclos();
                    break;
            }
            System.out.println(scheduler.getListaAltaP());
            System.out.println(scheduler.getListaMediaP());
        } while(opcao != 5);
    }
}