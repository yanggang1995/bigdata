package com.yg.struct.tree.avl;

/**
 * 平衡二叉树节点
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

    public int getData() {
        return this.data;
    }

    public BinaryNode leftNode() {
        return this.leftNode;
    }

    public BinaryNode rightNode() {
        return this.rightNode;
    }

    /**
     * 节点高度
     *
     * @return 高度
     */
    public int height() {
        int leftHeight = leftNode == null ? 0 : leftNode.height();
        int rightHeight = rightNode == null ? 0 : rightNode.height();
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int leftHeight(){
        if(leftNode == null){
            return 0;
        }
        return leftNode.height();
    }

    public int rightHeight(){
        if(rightNode == null){
            return 0;
        }
        return rightNode.height();
    }


    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                '}';
    }
}
