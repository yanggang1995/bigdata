package com.yg.struct.arraytree;


/**
 * 树结构
 *
 * @author Y.G
 * @date 2020/4/2 13:50
 **/
public class TreeMain {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        BinaryTree bTree = new BinaryTree(arr);
        bTree.print(BinaryTree.PrintType.FRONT);
        bTree.print(BinaryTree.PrintType.MIDDLE);
        bTree.print(BinaryTree.PrintType.BACK);
    }
}
