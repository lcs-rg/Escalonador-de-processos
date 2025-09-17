package Dominio;

public class Scheduler {
    private FilaCircularDeProcessos filaAltaP;
    private FilaCircularDeProcessos filaMediaP;
    private FilaCircularDeProcessos filaBaixaP;
    private FilaCircularDeProcessos filaBloqueados;
    private int contador_ciclos_alta_prioridade;
    /** construtor */
    public Scheduler() {
        this.filaAltaP = new FilaCircularDeProcessos();
        this.filaMediaP = new FilaCircularDeProcessos();
        this.filaBaixaP = new FilaCircularDeProcessos();
        this.filaBloqueados = new FilaCircularDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }
/** getters e setters */
    public FilaCircularDeProcessos getFilaAltaP() {
        return filaAltaP;
    }

    public void setFilaAltaP(FilaCircularDeProcessos filaAltaP) {
        this.filaAltaP = filaAltaP;
    }

    public FilaCircularDeProcessos getFilaMediaP() {
        return filaMediaP;
    }

    public void setFilaMediaP(FilaCircularDeProcessos filaMediaP) {
        this.filaMediaP = filaMediaP;
    }

    public FilaCircularDeProcessos getFilaBaixaP() {
        return filaBaixaP;
    }

    public void setFilaBaixaP(FilaCircularDeProcessos filaBaixaP) {
        this.filaBaixaP = filaBaixaP;
    }

    public FilaCircularDeProcessos getFilaBloqueados() {
        return filaBloqueados;
    }

    public void setFilaBloqueados(FilaCircularDeProcessos filaBloqueados) {
        this.filaBloqueados = filaBloqueados;
    }

    public int getContadorCiclosAltaPrioridade() {
        return contador_ciclos_alta_prioridade;
    }

    public void setContadorCiclosAltaPrioridade(int contador_ciclos_alta_prioridade) {
        this.contador_ciclos_alta_prioridade = contador_ciclos_alta_prioridade;
    }
/** toString */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prioridade Alta:").append(filaAltaP.toString()).append("\n");
        sb.append("Prioridade Media:").append(filaMediaP.toString()).append("\n");
        sb.append("Prioridade Baixa:").append(filaBaixaP.toString()).append("\n");
        sb.append("Lista de bloqueados:").append(filaBloqueados.toString());
        return sb.toString();
    }
/** metodo que retorna um booleano para listas vazias */
    private boolean ListasVazias() {
        return filaAltaP.isEmpty() && filaMediaP.isEmpty() &&
                filaBaixaP.isEmpty() && filaBloqueados.isEmpty();
    }
/** metodo utilizado para salvar os processos dentro de cada fila circular */
    public String exportarTudo() {
        return filaAltaP.exportar()
                + filaMediaP.exportar()
                + filaBaixaP.exportar()
                + filaBloqueados.exportar();
    }
/** metodo para adicionar um processo a sua respectiva lista de prioridade */
    public void addProcesso(Processo processo) {
        switch (processo.getPrioridade()) {
            case 1:
                filaAltaP.addLast(processo);
                break;
            case 2:
                filaMediaP.addLast(processo);
                break;
            case 3:
                filaBaixaP.addLast(processo);
                break;
            default:
                throw new IndexOutOfBoundsException("Crie um processo com prioridade permitida");
        }
    }
    /** metodo que executa apenas 1 ciclo a partir da junção de outros metodos da mesma classe */
    public void execCiclo() {
        System.out.println("=== Iniciando Ciclo... ===");
        desbloquearProcesso();
        Processo atual = selecionarProcesso();
        if (atual == null) {
            System.out.println("Nenhum processo disponível nesse ciclo");
            return;
        }
        execProcesso(atual);

        System.out.println(this.toString());
    }
/** metodo que finaliza todos os ciclos possíveis para processos em ciclo */
    public void execTodosCiclos() {
        int ciclo = 1;
        if (ListasVazias()) {
            System.out.println("Nenhum ciclo a ser concluído...");
            return;
        }
        while (!ListasVazias()) {
            System.out.println("\n=== Ciclo:" + ciclo + " ===");
            System.out.println();
            execCiclo();
            ciclo++;
        }
        System.out.println("\nTodos ciclos foram concluídos");
    }
/** metodo que desbloqueia um processo, retirando da fila de bloquados */
    private void desbloquearProcesso() {
        if (!filaBloqueados.isEmpty()) {
            Processo desbloqueado = filaBloqueados.removeFirst();
            System.out.println("Processo desbloqueado:" + desbloqueado);
            switch (desbloqueado.getPrioridade()) {
                case 1:
                    filaAltaP.addLast(desbloqueado);
                    break;
                case 2:
                    filaMediaP.addLast(desbloqueado);
                    break;
                case 3:
                    filaBaixaP.addLast(desbloqueado);
            }
        }
    }
/** metodo que analisa o proximo processo a ser executado no ciclo */
    private Processo selecionarProcesso() {
        Processo atual = null;

        // controle de fatia de tempo da prioridade alta
        if (contador_ciclos_alta_prioridade >= 5) {
            if (!filaMediaP.isEmpty()) {
                atual = filaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if (!filaBaixaP.isEmpty()) {
                atual = filaBaixaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            }
        }

        if (atual == null) {
            if (!filaAltaP.isEmpty()) {
                atual = filaAltaP.removeFirst();
                contador_ciclos_alta_prioridade++;
            } else if (!filaMediaP.isEmpty()) {
                atual = filaMediaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            } else if (!filaBaixaP.isEmpty()) {
                atual = filaBaixaP.removeFirst();
                contador_ciclos_alta_prioridade = 0;
            }
        }
        if (atual == null) {
            System.out.println("Nenhum processo a executar");
            return null;
        }
        return atual;
    }
 /** metodo que executa o processo selecionado anteriormente */
    private void execProcesso(Processo atual) {
        if ("DISCO".equalsIgnoreCase(atual.getRecurso_necessario())) {
            System.out.println("Processo " + atual.getNome() + " foi bloqueado aguardando DISCO");
            atual.setRecurso_necessario(null);
            filaBloqueados.addLast(atual);
            return;
        }
        atual.setCiclos_necessarios(atual.getCiclos_necessarios() - 1);
        System.out.println("Executando: " + atual.getNome() + " | Ciclos restantes: " + atual.getCiclos_necessarios());

        if (atual.getCiclos_necessarios() <= 0) {
            System.out.println("Processo " + atual.getNome() + " foi finalizado com sucesso");
        } else {
            addProcesso(atual);
        }
    }
/** metodo que retorna o processo em primeira preferência */
    public Processo verProximo() {
        if (!filaAltaP.isEmpty()) return filaAltaP.peek();
        if (!filaMediaP.isEmpty()) return filaMediaP.peek();
        if (!filaBaixaP.isEmpty()) return filaBaixaP.peek();
        if (!filaBloqueados.isEmpty()) return filaBloqueados.peek();
        return null;
    }
/** metodo que retorna um processo a partir da busca pelo seu id */
    public Processo buscarProcesso(int id) {
        Processo processo = filaAltaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = filaMediaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = filaBaixaP.buscar(id);
        if (processo != null) {
            return processo;
        }
        processo = filaBloqueados.buscar(id);
        if (processo != null) {
            return processo;
        }
        return null;
    }
/** metodo que retorna o id de um processo a ser buscado */
    public int buscarId(int id) {
        Processo processo = filaAltaP.buscar(id);
        if (processo != null) {
            return processo.getId();
        }
        processo = filaMediaP.buscar(id);
        if (processo != null) {
            return processo.getId();
        }
        processo = filaBaixaP.buscar(id);
        if (processo != null) {
            return processo.getId();
        }
        processo = filaBloqueados.buscar(id);
        if (processo != null) {
            return processo.getId();
        }
        return 0;
    }
/** metodo que remove um processo a partir da busca pelo seu id */
    public boolean removerProcesso(int id) {
        Processo p = buscarProcesso(id);
        if (p != null) {
            if (filaAltaP.buscar(id) != null) filaAltaP.removeid(id);
            else if (filaMediaP.buscar(id) != null) filaMediaP.removeid(id);
            else if (filaBaixaP.buscar(id) != null) filaBaixaP.removeid(id);
            else if (filaBloqueados.buscar(id) != null) filaBloqueados.removeid(id);
            System.out.println("Processo removido com sucesso");
            return true;
        }
        System.out.println("Nenhum processo com id:" + id + " a ser removido, tente novamente...");
        return false;
    }
}