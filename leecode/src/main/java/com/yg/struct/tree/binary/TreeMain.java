package com.yg.struct.tree.binary;

import com.yg.struct.enums.PrintType;

/**
 * 树结构
 *
 * @author Y.G
 * @date 2020/4/2 13:50
 **/
public class TreeMain {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(1);
        BinaryTree bTree = new BinaryTree(root);

        BinaryNode left = new BinaryNode(2);
        BinaryNode right = new BinaryNode(3);
        root.setLeftNode(left);
        root.setRightNode(right);
        left.setLeftNode(new BinaryNode(4));
        left.setRightNode(new BinaryNode(5));
        right.setLeftNode(new BinaryNode(6));
        right.setRightNode(new BinaryNode(7));
        bTree.print(PrintType.FRONT);
        bTree.print(PrintType.MIDDLE);
        bTree.print(PrintType.BACK);
        System.out.println(bTree.findNode(7, PrintType.FRONT).getData());
        System.out.println(bTree.findNode(7, PrintType.MIDDLE).getData());
        System.out.println(bTree.findNode(7, PrintType.BACK).getData());
        bTree.deleteNode(5);
        bTree.print(PrintType.FRONT);
    }
}
