package Dominio;
//Classe Processo que modela atributos relevantes para o escalonador
public class Processo {
    private int id; //identificador específico de cada processo
    private String nome; // nome do processo
    private int prioridade; // prioridade a ser implementada em uma lista seja ela Alta:1 , Média:2, Baixa:3
    private int ciclos_necessarios;// ciclos necessarios para o termino do processo
    private String recurso_necessario;// recurso necessário para saber se haverá o bloqueio do processo

    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {//construtor de Processo que inicializa os atributos
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }
    //getters e setters
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
// toString utilizado para fornecer um objeto de Processo em forma de String
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