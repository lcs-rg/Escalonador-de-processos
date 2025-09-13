package Teste;
import java.util.Scanner;

import Dominio.*;

public class Main{
    public static void main(String[] args) {
        Scheduler scheduler = RepositorioProcessos.carregar();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println();
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
            System.out.println("===|           Executar N ciclos: 8          |===");
            System.out.println("===|       Executar todos os ciclos: 9       |===");
            System.out.println("===|          Resetar escalonador: 10        |===");
            System.out.println("===|                  Sair: 0                |===");
            System.out.println("===|=========================================|===");
            System.out.println();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao){
                case 1:
                System.out.println("Digite o ID do processo: ");
                int id = sc.nextInt();
                sc.nextLine();
                if(id == scheduler.buscarId(id)){
                    throw new IndexOutOfBoundsException("[ERRO] Id:"+ id + " já existente, tente novamente");
                }
                System.out.println("Digite o nome do processo: ");
                String nome = sc.nextLine();
                System.out.println("Digite a prioridade do processo: ");
                int prioridade = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite a quantidade de ciclos necessários do processo: ");
                int ciclos = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite o recurso do processo(caso não haja, digite \"null\"): ");
                String recurso = sc.nextLine();
                if(recurso.isEmpty()){
                    throw new IndexOutOfBoundsException("[ERRO] Nenhum caractere informado.");
                }
                Processo processo = new Processo(id, nome, prioridade, ciclos, recurso);
                scheduler.addProcesso(processo);
                RepositorioProcessos.salvar(scheduler);
            break;
                case 2 :
                    System.out.println("Digite o ID do processo a ser removido");
                    int removerid = sc.nextInt();
                    sc.nextLine();
                    scheduler.removerProcesso(removerid);
                RepositorioProcessos.salvar(scheduler);
                    break;
                case 3:
                    System.out.println("Digite o ID do processo:");
                    int atualizar = sc.nextInt();
                    sc.nextLine();
                    Processo processoAtual = scheduler.buscarProcesso(atualizar);
                    if (processoAtual == null){
                        System.out.println("[ERRO] Processo com id:" + atualizar + " Não encontrado.");
                    } else {

                        System.out.println("Digite o novo nome do processo (ou Enter para manter: " + processoAtual.getNome() + "):");
                        String novoNome = sc.nextLine();
                        if(!novoNome.isBlank()) {
                            processoAtual.nome = novoNome;
                        }

                        System.out.println("Digite a nova prioridade (1,2,3) ou Enter para manter: " + processoAtual.getPrioridade());
                        String novaPrioridade = sc.nextLine();
                        if(!novaPrioridade.isBlank()){
                            int prioridadeInt = Integer.parseInt(novaPrioridade);
                            if(prioridadeInt >= 1 && prioridadeInt <= 3){
                                processoAtual.prioridade = prioridadeInt;
                            } else {
                                System.out.println("[ERRO] Prioridade inválida, mantida a anterior.");
                            }
                        }

                        System.out.println("Digite a nova quantidade de ciclos ou Enter para manter: " + processoAtual.getCiclos_necessarios());
                        String novosCiclos = sc.nextLine();
                        if(!novosCiclos.isBlank()){
                            processoAtual.ciclos_necessarios = Integer.parseInt(novosCiclos);
                        }

                        System.out.println("Digite o novo recurso ou Enter para manter: " + processoAtual.getRecurso_necessario());
                        String novoRecurso = sc.nextLine();
                        if(!novoRecurso.isBlank()){
                            processoAtual.recurso_necessario = novoRecurso;
                        }

                        System.out.println("Processo atualizado com sucesso!");
                    }
                RepositorioProcessos.salvar(scheduler);
                    break;
                case 4:
                    System.out.println("Digite o Id do processo");
                    int buscar = sc.nextInt();
                    sc.nextLine();
                    if(scheduler.buscarProcesso(buscar) == null) {
                        throw new NullPointerException("[ERRO] Processo com id:" + buscar + " inexistente");
                    } else {
                        System.out.println(scheduler.buscarProcesso(buscar).toString());
                    }
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
                            System.out.println("[ERRO] Número não permitido");
                        }
                    } while (verlista != 0);
                    break;
                case 6:
                    if (scheduler.verProximo().toString() == null){
                        System.out.println("[ERRO] Nenhum processo disponível");
                    } else {
                        System.out.println(scheduler.verProximo().toString());
                    }
                    break;
                case 7:
                    scheduler.execCiclo();
                    RepositorioProcessos.salvar(scheduler);
                    break;
                case 8:
                    System.out.println("Deseja executar quantos ciclos?");
                    int qntd = sc.nextInt();
                    sc.nextLine();
                    for(int i = 0; i < qntd; i++){
                        System.out.println("=== Ciclo:" + (1 + i) + " ===");
                        scheduler.execCiclo();
                    }
                    RepositorioProcessos.salvar(scheduler);
                    break;
                case 9:
                    scheduler.execTodosCiclos();
                    RepositorioProcessos.salvar(scheduler);
                    break;
                case 10:
                    scheduler = new Scheduler();
                    System.out.println("Escalonador resetado");
                    RepositorioProcessos.salvar(scheduler);
                    break;
                case 0:
                    RepositorioProcessos.salvar(scheduler);
                    break;
                default:
                    System.out.println("[ERRO] Número não permitido");
            }
        } while(opcao != 0);
        sc.close();
        RepositorioProcessos.salvar(scheduler);
                    System.out.println("Saindo...");
    }
}
