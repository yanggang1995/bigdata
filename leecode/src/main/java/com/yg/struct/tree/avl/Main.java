package com.yg.struct.tree.avl;

/**
 * 测试
 *
 * @author Y.G
 * @date 2020/4/9 22:43
 **/
public class Main {
    public static void main(String[] args) {
        // 7 3 10 12 5 1 9
        AVLTree bst = new AVLTree();
//        bst.addNode(5).addNode(4).addNode(3).addNode(2).addNode(1).addNode(0);
        bst.addNode(5).addNode(6).addNode(2).addNode(1).addNode(3).addNode(4);
        bst.middlePrint();
    }
}
