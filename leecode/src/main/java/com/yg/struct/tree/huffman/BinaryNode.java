package com.yg.struct.tree.huffman;

/**
 * 二叉树节点
 *
 * @author Y.G
 * @date 2020/4/2 11:43
 **/
public class BinaryNode implements Comparable<BinaryNode> {
    private int weight;
    private Byte data;
    private BinaryNode leftNode;
    private BinaryNode rightNode;

    public BinaryNode(int weight) {
        this.weight = weight;
    }

    public void setLeftNode(BinaryNode lNode) {
        this.leftNode = lNode;
    }

    public void setRightNode(BinaryNode rNode) {
        this.rightNode = rNode;
    }

    public int getWeight(){
        return  this.weight;
    }

    public BinaryNode leftNode() {
        return this.leftNode;
    }

    public BinaryNode rightNode() {
        return this.rightNode;
    }

    public Byte getData() {
        return data;
    }

    public BinaryNode setData(Byte data) {
        this.data = data;
        return this;
    }

    @Override
    public int compareTo(BinaryNode node) {
        return node.weight - this.weight;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }
}
