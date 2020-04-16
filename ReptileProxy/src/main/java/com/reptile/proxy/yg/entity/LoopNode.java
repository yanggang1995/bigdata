package com.reptile.proxy.yg.entity;

/**
 * 循环节点
 *
 * @author Y.G
 * @date 2020/4/10 14:46
 **/
public class LoopNode<T> extends Node<T> {

    public LoopNode(T data){
        this.data = data;
        this.next = this;
    }

    @Override
    public Node next() {
        return next;
    }

    @Override
    public void setNext(Node node) {
        Node tmpNode = this.next;
        this.next = node;
        node.next = tmpNode;
    }

    @Override
    public T getData() {
        return this.data;
    }
}
