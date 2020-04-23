package com.yg.struct.queue;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/21 10:37
 **/
public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedQueue<T> offer(T data){
        Node<T> offerNode = new Node(data);
        if(head == null){
            head = offerNode;
        }else {
            tail.next = offerNode;
        }
        tail = offerNode;
        return this;
    }

    public T take(){
        if(head == null){
            return null;
        }
        Node<T> takeNode = head;
        head = takeNode.next;
        return takeNode.data;
    }

    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }
}
