package Dominio;

public class Node {//classe Node
    private Processo processo;
    private Node next;

    public Node(Processo processo) {
        this.processo = processo;
        this.next = null;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}