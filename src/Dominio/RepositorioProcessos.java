package Dominio;

import java.io.*;

public class RepositorioProcessos {
    private static final String arquivo = "Processo.txt";

    public static void salvar(Scheduler scheduler) {
        try (FileWriter fw = new FileWriter(arquivo)) {
            String conteudo = scheduler.exportarTudo();
            fw.write(conteudo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar processo" + e.getMessage());
        }
    }

    public static Scheduler carregar() {
        Scheduler scheduler = new Scheduler();
        File file = new File(arquivo);
        System.out.println("Tentando carregar de:" + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Arquivo não existe, iniciando vazio.");
            return scheduler;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int linhascarregadas = 0;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";");
                if (partes.length < 5) {
                    System.out.println("Linhas inválida(menos de 5 partes): " + linha);
                    continue;
                }
                try {
                    int id = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    int prioridade = Integer.parseInt(partes[2].trim());
                    int ciclos = Integer.parseInt(partes[3].trim());
                    String recurso = partes[4].trim();

                    Processo processo = new Processo(id, nome, prioridade, ciclos, recurso);
                    scheduler.addProcesso(processo);
                    linhascarregadas++;
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao ler linha: " + linha + " | " + e.getMessage());
                }
            }
            System.out.println("Carregadas " + linhascarregadas + " processos com sucesso");
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, iniciando..." + e.getMessage());
        }
        return scheduler;
    }
}