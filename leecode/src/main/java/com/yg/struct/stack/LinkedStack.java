package com.yg.struct.stack;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/21 9:28
 **/
public class LinkedStack<T> {
    private Node<T> top;

    public LinkedStack<T> push(T data){
        Node<T> node = new Node<>(data);
        return this.push(node);
    }

    private LinkedStack<T> push(Node<T> node){
        if(top != null){
            node.next = top;
        }
        top = node;
        return this;
    }

    public T pop(){
        if(top == null){
            return null;
        }
        Node<T> popNode = top;
        top = top.next;
        return popNode.data;
    }

    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }
}
