package Dominio;
public class Processo{
    public int id;
    public String nome;
    public int prioridade;
    public int ciclos_necessarios;
    public String recurso_necessario;
    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario){
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Processo [");
        sb.append(id);
        sb.append(id);
        sb.append(id);
        return "Processo [ID:" + id + ", Nome:" + nome + ", Prioridade:" + prioridade +
                "Ciclos Necessários:" + ciclos_necessarios + "Recurso Necessário:" + recurso_necessario + "]";
    }
}