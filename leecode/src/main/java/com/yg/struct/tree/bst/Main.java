package com.yg.struct.tree.bst;

/**
 * 测试
 *
 * @author Y.G
 * @date 2020/4/9 22:43
 **/
public class Main {
    public static void main(String[] args) {
        // 7 3 10 12 5 1 9
        BinarySortTree bst = new BinarySortTree();
        bst.addNode(7).addNode(3).addNode(10).addNode(13).addNode(5).addNode(1).addNode(9).addNode(11).addNode(12);
        bst.middlePrint();
        System.out.println("找到节点：" + bst.search(12));
        System.out.println("找到节点：" + bst.search(9));
        System.out.println("===========");
        System.out.println("删除结果：" + bst.delete(10));
        bst.middlePrint();
    }
}
