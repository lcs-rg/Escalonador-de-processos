package Dominio;
import Dominio.Processo;
public class Node{
    Processo processo;
    public Node next;
    public Node(Processo processo){
        this.processo = processo;
        this.next = null;
    }
}
