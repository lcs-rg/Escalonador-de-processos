package Dominio;

public class FilaCircularDeProcessos {
    private Node tail;
    private int size;

    public FilaCircularDeProcessos() {
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Lista Vazia";
        }
        StringBuilder sb = new StringBuilder();
        Node atual = tail.getNext();
        do {
            sb.append(atual.getProcesso().toString()).append("\n");
            atual = atual.getNext();
        } while (atual != tail.getNext());
        return sb.toString();
    }

    public void addLast(Processo processo) {
        Node novo = new Node(processo);
        if (isEmpty()) {
            tail = novo;
            tail.setNext(tail);
        } else {
            novo.setNext(tail.getNext());
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    public Processo removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node head = tail.getNext();
        if (tail == head) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getProcesso();
    }

    public boolean removeid(int id) {
        if (isEmpty()) {
            return false;
        }
        Node atual = tail.getNext();
        Node anterior = tail;
        do {
            if (atual.getProcesso().getId() == id) {
                if (atual == tail && atual.getNext() == tail) {
                    tail = null;
                } else {
                    anterior.setNext(atual.getNext());
                    if (atual == tail) tail = anterior;
                }
                size--;
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        } while (atual != tail.getNext());
        return false;
    }

    public void iterar() {
        if (isEmpty()) {
            return;
        }
        Node atual = tail.getNext();
        do {
            System.out.println(atual.getProcesso().toString());
            atual = atual.getNext();
        } while (atual != tail.getNext());
    }

    public Processo buscar(int id) {
        if (isEmpty()) {
            return null;
        }
        Node atual = tail.getNext();
        do {
            if (atual.getProcesso().getId() == id) {
                return atual.getProcesso();
            }
            atual = atual.getNext();
        } while (atual != tail.getNext());
        return null;
    }

    public Processo peek() {
        return tail.getNext().getProcesso();
    }

    public void printList() {
        Node atual = tail.getNext();
        if (isEmpty()) {
            System.out.println("Lista vazia");
            return;
        }
        do {
            System.out.println(atual.getProcesso().toString());
            atual = atual.getNext();
        } while (atual != tail.getNext());
    }

    public String exportar() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Node atual = tail.getNext();
        do {
            sb.append(atual.getProcesso().getId()).append(";");
            sb.append(atual.getProcesso().getNome()).append(";");
            sb.append(atual.getProcesso().getPrioridade()).append(";");
            sb.append(atual.getProcesso().getCiclos_necessarios()).append(";");
            sb.append(atual.getProcesso().getRecurso_necessario()).append("\n");
            atual = atual.getNext();
        } while (atual != tail.getNext());
        return sb.toString();
    }
}