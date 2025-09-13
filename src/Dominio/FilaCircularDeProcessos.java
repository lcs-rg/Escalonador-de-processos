package Dominio;

public class FilaCircularDeProcessos {
    public Node tail;
    private int size;
    public FilaCircularDeProcessos(){
        this.tail = null;
        this.size = 0;
    }
    public int size(){
        return this.size;
    }
    public Boolean isEmpty(){
        return tail == null;
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
    public boolean removeid(int id){
        if(isEmpty()) {
            return false;
        }
            Node atual = tail.next;
        Node anterior = tail;
        do {
        if(atual.processo.id == id){
            if(atual == tail && atual.next == tail){
                tail = null;
            } else {
                anterior.next = atual.next;
                if (atual == tail) tail = anterior;
            }
            size--;
            return true;
        }
        anterior = atual;
        atual = atual.next;
        }while (atual != tail.next);
        return false;
    }
    public void iterar(){
        if(isEmpty()){
            return;
        }
        Node atual = tail.next;
                do{
                    System.out.println(atual.processo.toString());
                    atual = atual.next;
                } while (atual != tail.next);
    }
    public Processo buscar(int id){
        if(isEmpty()){
            return null;
        }
        Node atual = tail.next;
        do {
            if (atual.processo.id == id){
                return atual.processo;
        }

            atual = atual.next;
        }while (atual != tail.next);
            return null;
    }
    public Processo peek(){
        return tail.next.processo;
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
    public String exportar(){
        if(isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Node atual = tail.next;
        do {
            sb.append(atual.processo.getId()).append(";");
            sb.append(atual.processo.getNome()).append(";");
            sb.append(atual.processo.getPrioridade()).append(";");
            sb.append(atual.processo.getCiclos_necessarios()).append(";");
            sb.append(atual.processo.getRecurso_necessario()).append("\n");
            atual = atual.next;
        } while (atual != tail.next);
        return sb.toString();
    }
}