package Dominio;

import Dominio.Node;
public class ListaDeProcessos{
    public Node head;
    public Node tail;
    public int size;
    public ListaDeProcessos(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public void addLast(Processo processo){
        Node novo = new Node(processo);
        if(head == null){
            head = novo;
            tail = head;
        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = novo;
        tail = temp.next;
        size++;
    }
    public void addFirst(Processo processo) {
        Node novo = new Node(processo);
        novo.next = head;
        head = novo;
        size++;
    }
    public void removeLast(){
        if(head == null){
            System.out.println("Lista já vazia");
            return;
        }
        Node temp = head;
        while (temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
    }
    public void removeFirst(){
        if(head == null){
            System.out.println("Lista já vazia");
            return;
        }
        head = head.next;
        size--;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public int size(){
        return size;
    }
    public Processo getFirst(){
        return head.processo;
    }
    public Processo getLast(){
        return tail.processo;
    }
    public void clear(){
        head = null;
    }
    public void printList(){
        if(head == null){
            System.out.println("Lista Vazia");
        }
        Node temp = head;
        while (temp != null){
            System.out.println(temp.processo);
            temp = temp.next;
        }
    }
}