package com.ZohoIncubation.Task3;

public class Node<T> {

    private T value;
    private Node prev;
    private Node next;

    public Node(T value, Node next, Node prev){
        this.value = value;
        this.prev = prev;
        this.next = next;
    }


    public Node(T value){
        this.value = value;
    }

    public void setValue(T value){
        this.value = null;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getPrev(){
        return this.prev;
    }

    public Object getValue(){
        return this.value;
    }

    public Node getNext(){
       return this.next;
    }
}
