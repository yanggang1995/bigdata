package com.reptile.proxy.yg.entity;

/**
 * 循环链表
 *
 * @author Y.G
 * @date 2020/4/10 14:17
 **/
public class LoopList {
    private Node currentNode;

    private LoopList(){}

    public LoopList add(String data){
        return add(new LoopNode(data));
    }

    public LoopList add(Node node) {
        if (currentNode == null) {
            currentNode = node;
        }
        currentNode.setNext(node);
        currentNode = node;
        return this;
    }

    public Node current() {
        currentNode = currentNode.next();
        return currentNode;
    }

    public static LoopList of(){
        return new LoopList();
    }

}
