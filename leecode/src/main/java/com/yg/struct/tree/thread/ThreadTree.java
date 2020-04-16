package com.yg.struct.tree.thread;

import com.yg.struct.enums.PrintType;

import java.util.Objects;

/**
 * 线索化二叉树
 *
 * @author Y.G
 * @date 2020/4/3 16:53
 **/
public class ThreadTree {
    BinaryNode root;
    BinaryNode pre;

    public ThreadTree(BinaryNode root) {
        this.root = root;
    }


    public void threadNode(PrintType printType) {
        switch (printType) {
            case FRONT:
                frontThread(root);
                break;
            case MIDDLE:
                middleThread(root);
                break;
            case BACK:
                backThread(root);
                break;
            default:
        }
    }

    public void print(PrintType printType) {
        switch (printType) {
            case FRONT:
                frontPrint();
                break;
            case MIDDLE:
                middlePrint();
                break;
            case BACK:
                backPrint();
                break;
            default:
        }
    }


    /**
     * 前序线索化二叉树
     *
     * @param parent 父节点
     */
    public void frontThread(BinaryNode parent) {
        if (parent == null) {
            return;
        }
        if (parent.leftNode() == null) {
            parent.setLeftNode(pre);
            parent.setLeftType(true);
        }
        if (pre != null && pre.rightNode() == null) {
            pre.setRightNode(parent);
            pre.setRightType(true);
        }
        pre = parent;
        if (parent.getLeftType() || parent.getRightType()) {
            return;
        }
        frontThread(parent.leftNode());
        frontThread(parent.rightNode());
    }

    /**
     * 遍历前序线索化二叉树
     */
    private void frontPrint() {
        BinaryNode parent = root;
        while (parent != null) {
            // 打印父节点，一直到起始线索化叶子节点
            while (!parent.getLeftType()) {
                System.out.println(parent.getData());
                parent = parent.leftNode();
            }
            // 打印线索化右节点，直到最后一个不满足，重新寻找子树中起始线索化叶子节点
            while (parent != null && (parent.getRightType() || parent.rightNode() == null)) {
                System.out.println(parent.getData());
                parent = parent.rightNode();
            }
        }
    }

    /**
     * 遍历中序线索化二叉树
     */
    public void middlePrint() {
        BinaryNode farLeft = root;
        while (farLeft != null) {
            while (!farLeft.getLeftType() && farLeft.leftNode() != null) {
                farLeft = farLeft.leftNode();
            }
            System.out.println(farLeft.getData());
            while (farLeft.getRightType()) {
                farLeft = farLeft.rightNode();
                System.out.println(farLeft.getData());
            }
            farLeft = farLeft.rightNode();
        }
    }

    /**
     * 中序线索化二叉树
     *
     * @param parent 父节点
     */
    public void middleThread(BinaryNode parent) {
        // 处理左子节点
        if (Objects.nonNull(parent.leftNode())) {
            middleThread(parent.leftNode());
        }
        // 处理当前节点
        if (parent.leftNode() == null && pre != null) {
            parent.setLeftNode(pre);
            parent.setLeftType(true);
        }
        if (Objects.nonNull(pre) && Objects.isNull(pre.rightNode())) {
            pre.setRightNode(parent);
            pre.setRightType(true);
        }
        pre = parent;
        // 处理右子节点
        if (Objects.nonNull(parent.rightNode())) {
            middleThread(parent.rightNode());
        }
    }

    /**
     * 递归后序线索化二叉树
     *
     * @param parent 父节点
     */
    private void backThread(BinaryNode parent) {
        if (parent == null) {
            return;
        }
        backThread(parent.leftNode());
        backThread(parent.rightNode());
        if (parent.leftNode() == null) {
            parent.setLeftNode(pre);
            parent.setLeftType(true);
        }
        if (pre != null && pre.rightNode() == null) {
            pre.setRightNode(parent);
            pre.setRightType(true);
        }
        pre = parent;
    }

    /**
     * 递归后序线索化二叉树
     */
    private void backPrint() {
        BinaryNode parent = root;
        BinaryNode preNode = null;
        while (parent != null) {
            // 寻找起始线索化叶子节点
            while (parent.leftNode() != null && !parent.getLeftType()) {
                parent = parent.leftNode();
            }
            // 打印具备右线索化的节点
            while (parent.getRightType()) {
                System.out.println(parent.getData());
                preNode = parent;
                parent = parent.rightNode();
            }
            // 后序：回溯父节点数据
            while (parent != null && preNode == parent.rightNode()) {
                System.out.println(parent.getData());
                preNode = parent;
                parent = parent.getParent();
            }
            // 选择后序父节点后的父节点对应的右子树父节点，重新寻找起始线索叶子节点
            if (parent != null) {
                preNode = parent;
                parent = parent.rightNode();
            }
        }
    }
}
