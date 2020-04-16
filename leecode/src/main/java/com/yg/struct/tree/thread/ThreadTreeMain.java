package com.yg.struct.tree.thread;

import com.yg.struct.enums.PrintType;

/**
 * 树结构
 *
 * @author Y.G
 * @date 2020/4/2 13:50
 **/
public class ThreadTreeMain {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(0);
        ThreadTree tTree = new ThreadTree(root);
        // 设置节点
        BinaryNode left = new BinaryNode(1).setParent(root);
        BinaryNode right = new BinaryNode(2).setParent(root);
        root.setLeftNode(left);
        root.setRightNode(right);
        left.setLeftNode(new BinaryNode(3).setParent(left));
        left.setRightNode(new BinaryNode(4).setParent(left));
        right.setLeftNode(new BinaryNode(5).setParent(right));
        right.setRightNode(new BinaryNode(6).setParent(right));
        // 中序线索化二叉树
//        tTree.threadNode(PrintType.MIDDLE);
//        tTree.print(PrintType.MIDDLE);
        // 后序线索化二叉树
        tTree.threadNode(PrintType.BACK);
        tTree.print(PrintType.BACK);
    }
}
