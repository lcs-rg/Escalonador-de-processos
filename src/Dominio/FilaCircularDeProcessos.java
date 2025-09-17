package Dominio;
/** classe da estrutura de dados criada */
public class FilaCircularDeProcessos {
    private Node tail;
    private int size;
    /** construtor */
    public FilaCircularDeProcessos() {
        this.tail = null;
        this.size = 0;
    }
/** metodo que retorna tamanho da estrutura */
    public int size() {
        return this.size;
    }
/** metodos que retorna true para lista vazia, caso contrário, false */
    public boolean isEmpty() {
        return tail == null;
    }
/** getters e setters */
    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }
/** toString */
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
    /** metodo que adiciona um novo nó ao final da ADT */
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
/** metodo que remove o primeiro nó da ADT */
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
/** metodo que remove um nó pelo id de processo, retornando boolean para exito ou não */
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
/** metodo para iterar na ADT */
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
/** metodo que retorna processo na ADT a partir do id de processo */
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
    /** metodo que retorna o primeiro elemento da ADT */
    public Processo peek() {
        return tail.getNext().getProcesso();
    }
    /** metodo para iterar sobre a lista printando todos os elementos presentes */
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
/** metodo para salvar as mudanças feitas na ADT, utilizado para armazenar em processo.txt */
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