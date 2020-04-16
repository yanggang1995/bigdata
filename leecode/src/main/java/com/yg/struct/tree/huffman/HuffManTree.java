package com.yg.struct.tree.huffman;

import com.yg.util.DataUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 赫夫曼树
 * 1、最优二叉树：它是n个带权叶子节点构成的所有二叉树中，带权路径长度最小的二叉树
 * 2、叶节点的带权路径：路径长度 * 权值
 * 3、树的带权路径长度WPL：树中所有叶子节点的带权路径之和
 *
 * @author Y.G
 * @date 2020/4/7 11:21
 **/
public class HuffManTree {
    private BinaryNode root;
    private Map<Byte, String> huffmanCode;
    private Map<String, Byte> huffmanDecode;
    /**
     * 树的带权路径
     */
    private AtomicInteger pwl = new AtomicInteger(0);
    /**
     * 编码时最后不足8位的补位长度
     */
    private int fixLen;

    public HuffManTree() {
    }

    public HuffManTree(BinaryNode root) {
        this.root = root;
    }

    public HuffManTree(List<BinaryNode> nodes) {
        this.root = parseRoot(nodes);
    }

    public HuffManTree(byte[] arr) {
        this.root = parseRoot(arr);
    }

    /**
     * 格式化字节数组
     *
     * @param arr 数组
     * @return 根节点
     */
    private BinaryNode parseRoot(byte[] arr) {
        Map<Byte, Integer> keyCounts = new HashMap<>(16);
        for (byte b : arr) {
            if (keyCounts.containsKey(b)) {
                keyCounts.put(b, keyCounts.get(b) + 1);
            } else {
                keyCounts.put(b, 1);
            }
        }
        List<BinaryNode> nodes = keyCounts.entrySet().stream().map(v -> new BinaryNode(v.getValue()).setData(v.getKey())).collect(Collectors.toList());
        return parseRoot(nodes);
    }

    /**
     * 构建哈夫曼树的根节点
     *
     * @param nodes 节点数组
     * @return 根节点
     */
    private BinaryNode parseRoot(List<BinaryNode> nodes) {
        // 通过数组构建赫夫曼树
        int index = 0;
        // 每次对权值进行排序，取最小两个，将其和作为父节点，小的为左节点，大的为右节点，重新放入父节点
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);
            BinaryNode left = nodes.get(nodes.size() - 1);
            BinaryNode right = nodes.get(nodes.size() - 2);
            // 构建二叉树
            BinaryNode parent = new BinaryNode(left.getWeight() + right.getWeight());
            parent.setLeftNode(left);
            parent.setRightNode(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        huffmanCode = new HashMap<>(nodes.size());
        huffmanDecode = new HashMap<>(nodes.size());
        // 数组中最后一个节点为父节点
        return nodes.get(0);
    }

    public void frontPrint() {
        front(root);
        System.out.println();
    }

    /**
     * 前序遍历
     * 1、根节点 =》左节点 =》右节点
     *
     * @param node 节点
     */
    private void front(BinaryNode node) {
        System.out.print("[" + node.getWeight() + " : " + node.getData() + "] ");
        if (Objects.nonNull(node.leftNode())) {
            front(node.leftNode());
        }
        if (Objects.nonNull(node.rightNode())) {
            front(node.rightNode());
        }
    }

    /**
     * 获取huffman编码表
     *
     * @return 编码表
     */
    static StringBuilder sb = new StringBuilder();

    public Map<Byte, String> huffmanCode() {
        if (root != null && huffmanCode.size() == 0) {
            loadHuffmanCode(root.leftNode(), "0", sb);
            loadHuffmanCode(root.rightNode(), "1", sb);
        }
        return huffmanCode;
    }

    /**
     * 返回解码表
     *
     * @return 解码表
     */
    public Map<String, Byte> huffmanDecode() {
        return huffmanDecode;
    }

    /**
     * 生成huffman编码表
     * 1、前序的方式
     *
     * @param parent 父节点
     * @param code   编码
     */
    private void loadHuffmanCode(BinaryNode parent, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (parent.getData() == null) {
            loadHuffmanCode(parent.leftNode(), "0", sb2);
            loadHuffmanCode(parent.rightNode(), "1", sb2);
        } else {
            huffmanCode.put(parent.getData(), sb2.toString());
            huffmanDecode.put(sb2.toString(), parent.getData());
        }
    }

    /**
     * 编码
     *
     * @param arr 需要编码的byte数组
     * @return 编码后的数组
     */
    public byte[] encode(byte[] arr) {
        this.root = parseRoot(arr);
        this.huffmanCode();
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(huffmanCode.get(b));
        }
        int len;
        int remainLen = sb.length() % 8;
        if (remainLen == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
            fixLen = 8 - remainLen;
            for (int fix = 0; fix < fixLen; fix++) {
                sb.append("0");
            }
        }
        huffmanDecode.put("fixLen", (byte) fixLen);
        byte[] result = new byte[len];
        int rsIndex = 0;
        for (int index = 0; index < sb.length(); index += 8) {
            String sByte = sb.substring(index, index + 8);
            result[rsIndex] = (byte) Integer.parseInt(sByte, 2);
            rsIndex++;
        }
        return result;
    }

    /**
     * 解码
     *
     * @param encodeArr 编码的byte数组
     * @return 解码后的byte数组
     */
    public byte[] decode(byte[] encodeArr) {
        return decode(encodeArr, this.huffmanDecode);
    }

    /**
     * 解码
     *
     * @param encodeArr     编码的byte数组
     * @param huffmanDecode 编码表
     * @return 解码后的byte数组
     */
    public static byte[] decode(byte[] encodeArr, Map<String, Byte> huffmanDecode) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < encodeArr.length; index++) {
            String encodeStr = DataUtil.byteToBitStr(encodeArr[index]);
            if (index == encodeArr.length - 1) {
                encodeStr = encodeStr.substring(0, 8 - huffmanDecode.get("fixLen"));
            }
            sb.append(encodeStr);
        }
        List<Byte> decode = new ArrayList<>();
        for (int index = 1, start = index - 1; index <= sb.length(); index++) {
            String decodeStr = sb.substring(start, index);
            if (huffmanDecode.containsKey(decodeStr)) {
                decode.add(huffmanDecode.get(decodeStr));
                start = index;
            }
        }
        byte[] decodeArr = new byte[decode.size()];
        for (int index = 0; index < decode.size(); index++) {
            decodeArr[index] = decode.get(index);
        }
        return decodeArr;
    }
}
