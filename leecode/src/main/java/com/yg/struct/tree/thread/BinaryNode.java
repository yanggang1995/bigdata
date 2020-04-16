package com.yg.struct.tree.thread;

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
    private boolean leftType;
    private boolean rightType;
    private BinaryNode parent;

    public BinaryNode(int data) {
        this.data = data;
    }

    public void setLeftNode(BinaryNode lNode) {
        this.leftNode = lNode;
    }

    public void setRightNode(BinaryNode rNode) {
        this.rightNode = rNode;
    }

    public boolean getLeftType() {
        return leftType;
    }

    public void setLeftType(boolean leftType) {
        this.leftType = leftType;
    }

    public boolean getRightType() {
        return rightType;
    }

    public void setRightType(boolean rightType) {
        this.rightType = rightType;
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

    public BinaryNode getParent() {
        return parent;
    }

    public BinaryNode setParent(BinaryNode parent) {
        this.parent = parent;
        return this;
    }
}
