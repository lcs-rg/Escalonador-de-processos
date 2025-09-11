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
            System.out.println("===|         Atualizar um processo: 3        |===");
            System.out.println("===|            Ver um processo: 4           |===");
            System.out.println("===|               Ver listas: 5             |===");
            System.out.println("===|          Ver proximo processo: 6        |===");
            System.out.println("===|           Executar um ciclo: 7          |===");
            System.out.println("===|       Executar todos os ciclos: 8       |===");
            System.out.println("===|           Executar N ciclos: 9          |===");
            System.out.println("===|          Resetar escalonador: 10        |===");
            System.out.println("===|                  Sair: 0                |===");
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
                    sc.nextLine();
                    //if(removerid == )
                    break;
                case 3:
                    System.out.println("Digite o ID do processo:");
                    int verprocesso = sc.nextInt();
                    sc.nextLine();
                    //if(verprocesso == processo.id)
                    break;
                case 4:
                    System.out.println("Digite o Id do processo");
                    int buscar = sc.nextInt();
                    sc.nextLine();
                    if(scheduler.getListaAltaP().buscar(buscar) == null){
                        System.out.println();
                    };

                    break;
                case 5:
                    int verlista;
                    do {
                        System.out.println("Qual lista você deseja ver?:");
                        System.out.println("Lista de Alta prioridade: 1");
                        System.out.println("Lista de Media prioridade: 2");
                        System.out.println("Lista de Baixa prioridade: 3");
                        System.out.println("Lista de Bloqueados: 4");
                        System.out.println("Todas as listas: 5");
                        System.out.println("Sair: 0");
                        verlista = sc.nextInt();
                        sc.nextLine();
                        if (verlista == 1) {
                            System.out.println(scheduler.getListaAltaP());
                        } else if (verlista == 2) {
                            System.out.println(scheduler.getListaMediaP());
                        } else if (verlista == 3) {
                            System.out.println(scheduler.getListaBaixaP());
                        } else if (verlista == 4) {
                            System.out.println(scheduler.getListaBloqueados());
                        } else if (verlista == 5) {
                            System.out.println(scheduler.toString());
                        } else{
                            System.out.println("Número não permitido");
                        }
                    } while (verlista != 0);
                    break;
                case 6:
                    scheduler.verProximo();
                    break;
                case 7:
                    scheduler.execCiclo();
                    break;
                case 8:
                    scheduler.execTodosCiclos();
                    break;
                case 9:
                    System.out.println("Deseja executar quantos ciclos?");
                    int qntd = sc.nextInt();
                    sc.nextLine();
                    for(int i = 0; i < qntd; i++){
                        scheduler.execCiclo();
                    }
                    break;
                case 10:
                    scheduler = new Scheduler();
                    System.out.println("Escalonador resetado");
                    break;
                default:
                    System.out.println("Número não permitido");
            }
        } while(opcao != 0);
                    System.out.println("Saindo...");
    }
}
