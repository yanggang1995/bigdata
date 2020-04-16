package com.yg.struct.tree.binary;

import com.yg.struct.enums.PrintType;

import java.util.Objects;

/**
 * 二叉树
 *
 * @author Y.G
 * @date 2020/4/2 11:42
 **/
public class BinaryTree {
    private BinaryNode root;

    public BinaryTree(BinaryNode root) {
        this.root = root;
    }

    /**
     * 遍历
     *
     * @param printType 遍历类型
     */
    public void print(PrintType printType) {
        if (Objects.isNull(root)) {
            return;
        }
        System.out.print(printType + ": ");
        switch (printType) {
            case FRONT:
                front(root);
                break;
            case MIDDLE:
                middle(root);
                break;
            case BACK:
                back(root);
                break;
            default:
        }
        System.out.println();
    }

    /**
     * 查找
     *
     * @param data      数据
     * @param printType 查找类型
     * @return 节点
     */
    public BinaryNode findNode(int data, PrintType printType) {
        if (Objects.isNull(root)) {
            return null;
        }
        switch (printType) {
            case FRONT:
                return frontFind(data, root);
            case MIDDLE:
                return middleFind(data, root);
            case BACK:
                return backFind(data, root);
            default:
                return null;
        }
    }

    public boolean deleteNode(int data) {
        if (root.getData() == data) {
            root = null;
            return true;
        }
        return delete(data, root);
    }

    public boolean delete(int data, BinaryNode node) {
        if (Objects.nonNull(node.leftNode()) && node.leftNode().getData() == data) {
            node.setLeftNode(null);
            return true;
        } else if (Objects.nonNull(node.rightNode()) && node.rightNode().getData() == data) {
            node.setRightNode(null);
            return true;
        } else if (Objects.nonNull(node.leftNode()) && delete(data, node.leftNode())) {
            return true;
        } else if (Objects.nonNull(node.rightNode()) && delete(data, node.rightNode())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 前序查找
     *
     * @param data 数据
     * @param node 节点
     * @return 返回的节点
     */
    private BinaryNode frontFind(int data, BinaryNode node) {
        BinaryNode result = null;
        if (node.getData() == data) {
            result = node;
        }
        if (Objects.isNull(result) && Objects.nonNull(node.leftNode())) {
            result = frontFind(data, node.leftNode());
        }
        if (Objects.isNull(result) && Objects.nonNull(node.rightNode())) {
            result = frontFind(data, node.rightNode());
        }
        return result;
    }

    /**
     * 中序查找
     *
     * @param data 数据
     * @param node 节点
     * @return 返回的节点
     */
    private BinaryNode middleFind(int data, BinaryNode node) {
        BinaryNode result = null;
        if (Objects.nonNull(node.leftNode())) {
            result = middleFind(data, node.leftNode());
        }
        if (Objects.isNull(result) && node.getData() == data) {
            result = node;
        }
        if (Objects.isNull(result) && Objects.nonNull(node.rightNode())) {
            result = middleFind(data, node.rightNode());
        }
        return result;
    }

    /**
     * 后序查找
     *
     * @param data 数据
     * @param node 节点
     * @return 返回的节点
     */
    private BinaryNode backFind(int data, BinaryNode node) {
        BinaryNode result = null;
        if (Objects.nonNull(node.leftNode())) {
            result = backFind(data, node.leftNode());
        }
        if (Objects.isNull(result) && Objects.nonNull(node.rightNode())) {
            result = backFind(data, node.rightNode());
        }
        if (Objects.isNull(result) && node.getData() == data) {
            result = node;
        }
        return result;
    }

    /**
     * 前序遍历
     * 1、根节点 =》左节点 =》右节点
     *
     * @param node 节点
     */
    private void front(BinaryNode node) {
        System.out.print(node.getData() + " ");
        if (Objects.nonNull(node.leftNode())) {
            front(node.leftNode());
        }
        if (Objects.nonNull(node.rightNode())) {
            front(node.rightNode());
        }
    }

    /**
     * 中序遍历
     * 1、左节点 =》根节点 =》右节点
     *
     * @param node
     */
    private void middle(BinaryNode node) {
        if (Objects.nonNull(node.leftNode())) {
            middle(node.leftNode());
        }
        System.out.print(node.getData() + " ");
        if (Objects.nonNull(node.rightNode())) {
            middle(node.rightNode());
        }
    }

    /**
     * 后序遍历
     * 1、左节点 =》右节点 =》根节点
     *
     * @param node 节点
     */
    private void back(BinaryNode node) {
        if (Objects.nonNull(node.leftNode())) {
            back(node.leftNode());
        }
        if (Objects.nonNull(node.rightNode())) {
            back(node.rightNode());
        }
        System.out.print(node.getData() + " ");
    }
}
