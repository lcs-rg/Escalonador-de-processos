package Dominio;

import Dominio.Node;

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
    public void addFirst(Processo processo){
        Node novo = new Node(processo);
        if(isEmpty()){
            tail = novo;
        }
            novo.next = tail.next;
            tail.next = novo;

        size++;
    }
    public void addLast(Processo processo){
        Node novo = new Node(processo);
        if(isEmpty()){
            tail = novo;
            tail.next = tail;
        }
        novo.next = tail.next;
        tail.next = novo;
        tail = novo;
        size++;
    }
    public Processo removeFirst(){
        if(isEmpty()){
            return null;
        }
        tail.next = tail.next.next;
        return tail.next.processo;
    }
    public void printList(){
        Node atual = tail.next;
        if(isEmpty()){
            System.out.println("Lista vazia");
            return;
        }
        do{
            System.out.println(atual.processo);
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