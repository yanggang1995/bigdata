package com.yg.struct.arraytree;

import java.util.Objects;

/**
 * 顺序存储的二叉树
 * 1、必须满足是完全二叉树（所有叶子节点在最后一层和倒数第二层，倒数第二层叶子节点在右边连续，最后一层叶子节点在左边连续）
 * 2、性质：
 *      1） 下标 n 的左子节点下标：2*n + 1
 *      2） 下标 n 的右子节点下标：2*n + 2
 *      3） 下标 n 的父节点下标：(n - 1) / 2
 *
 * @author Y.G
 * @date 2020/4/2 11:42
 **/
public class BinaryTree {
    private int[] data;

    public BinaryTree(int[] data) {
        this.data = data;
    }



    /**
     * 遍历
     *
     * @param printType 遍历类型
     */
    public void print(PrintType printType) {
        if (Objects.isNull(data) || data.length == 0) {
            return;
        }
        System.out.print(printType + ": ");
        switch (printType) {
            case FRONT:
                front(0);
                break;
            case MIDDLE:
                middle(0);
                break;
            case BACK:
                back(0);
                break;
            default:
        }
        System.out.println();
    }


    /**
     * 前序遍历
     * 1、根节点 =》左节点 =》右节点
     */
    private void front(int index) {
        System.out.print(data[index] + " ");
        int leftIndex = 2 * index + 1;
        if (leftIndex < data.length) {
            front(leftIndex);
        }
        int rightIndex = 2 * index + 2;
        if (rightIndex < data.length) {
            front(rightIndex);
        }
    }

    /**
     * 中序遍历
     * 1、左节点 =》根节点 =》右节点
     *
     * @param index 下标
     */
    private void middle(int index) {
        int leftIndex = 2 * index + 1;
        if (leftIndex < data.length) {
            middle(leftIndex);
        }
        System.out.print(data[index] + " ");
        int rightIndex = 2 * index + 2;
        if (rightIndex < data.length) {
            middle(rightIndex);
        }
    }

    /**
     * 后序遍历
     * 1、左节点 =》右节点 =》根节点
     *
     * @param index 节点下标
     */
    private void back(int index) {
        int leftIndex = 2 * index + 1;
        if (leftIndex < data.length) {
            back(leftIndex);
        }
        int rightIndex = 2 * index + 2;
        if (rightIndex < data.length) {
            back(rightIndex);
        }
        System.out.print(data[index] + " ");
    }

    enum PrintType {
        /**
         * 遍历类型
         */
        FRONT, MIDDLE, BACK
    }
}
