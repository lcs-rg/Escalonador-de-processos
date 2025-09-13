package Dominio;

public class Processo {
    private int id;
    private String nome;
    private int prioridade;
    private int ciclos_necessarios;
    private String recurso_necessario;

    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getCiclos_necessarios() {
        return ciclos_necessarios;
    }

    public void setCiclos_necessarios(int ciclos_necessarios) {
        this.ciclos_necessarios = ciclos_necessarios;
    }

    public String getRecurso_necessario() {
        return recurso_necessario;
    }

    public void setRecurso_necessario(String recurso_necessario) {
        this.recurso_necessario = recurso_necessario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ID:").append(id);
        sb.append(", Nome:").append(nome);
        sb.append(", Prioridade:").append(prioridade);
        sb.append(", Ciclos necessários:").append(ciclos_necessarios);
        sb.append(", Recurso necessário:").append(recurso_necessario);
        sb.append("]");
        return sb.toString();
    }
}