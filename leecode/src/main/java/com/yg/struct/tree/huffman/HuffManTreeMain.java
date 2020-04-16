package com.yg.struct.tree.huffman;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/7 13:50
 **/
public class HuffManTreeMain {
    public static void main(String[] args) {
        /*BinaryNode[] arr =  new BinaryNode[]{new BinaryNode(2), new BinaryNode(5), new BinaryNode(3), new BinaryNode(1), new BinaryNode(4), new BinaryNode(6)};
        HuffManTree huffManTree = new HuffManTree(arr);
        huffManTree.frontPrint();
        System.out.println(huffManTree.huffmanCode());*/
        String str = "can you can a can as a caner can?";
//        String str = "can you can a can as a can canner can a can.";
        HuffManTree huffManTree  = new HuffManTree(str.getBytes());
        System.out.println(str.getBytes().length);
        byte[] encodeArr = huffManTree.encode(str.getBytes());
        System.out.println(encodeArr.length);
        byte[] decodeArr = huffManTree.decode(encodeArr);
        System.out.println(new String(decodeArr));
    }
}
