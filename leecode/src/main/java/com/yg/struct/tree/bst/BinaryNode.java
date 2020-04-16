package com.yg.struct.tree.bst;

/**
 * 二叉树节点
 *
 * @author Y.G
 * @date 2020/4/2 11:43
 **/
public class BinaryNode {
    private int data;
    private BinaryNode leftNode;
    private BinaryNode rightNode;

    public BinaryNode(int data) {
        this.data = data;
    }

    public void setLeftNode(BinaryNode lNode) {
        this.leftNode = lNode;
    }

    public void setRightNode(BinaryNode rNode) {
        this.rightNode = rNode;
    }

    public int getData(){
        return  this.data;
    }

    public BinaryNode leftNode() {
        return this.leftNode;
    }

    public BinaryNode rightNode() {
        return this.rightNode;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                '}';
    }
}
