package Dominio;

public class ListaDeProcessos {
    public Node tail;
    private int size;
    public ListaDeProcessos(){
        this.tail = null;
        this.size = 0;
    }
    public int size(){
        return this.size;
    }
    public Boolean isEmpty(){
        return tail == null;
    }

    public void addLast(Processo processo){
        Node novo = new Node(processo);
        if(isEmpty()){
            tail = novo;
            tail.next = tail;
        } else {
            novo.next = tail.next;
            tail.next = novo;
            tail = novo;
        }
        size++;
    }
    public Processo removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node head = tail.next;
        if(tail == head){
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return head.processo;
    }
    public void printList(){
        Node atual = tail.next;
        if(isEmpty()){
            System.out.println("Lista vazia");
            return;
        }
        do{
            System.out.println(atual.processo.toString());
            atual = atual.next;
        }while(atual != tail.next);
    }
    @Override
    public String toString(){
        if(isEmpty()){
            return "Lista Vazia";
        }
        StringBuilder sb = new StringBuilder();
        Node atual = tail.next;
        do{
            sb.append(atual.processo.toString()).append("\n");
            atual = atual.next;
        }while(atual != tail.next);
        return sb.toString();
    }
}