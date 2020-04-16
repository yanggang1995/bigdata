package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Y.G
 * @date 2020/4/1 10:15
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        System.out.println("-----------");
        System.out.println(Arrays.toString(mergeSort(arr)));
    }

    /**
     * 划分以及归并
     *
     * @param sort 数组
     * @return 归并后的数组
     */
    private static int[] mergeSort(int[] sort) {
        if (sort.length < 2) {
            return sort;
        }
        // 划分
        int half = sort.length / 2;
        int[] left = new int[half];
        int[] right = new int[sort.length - half];
        System.arraycopy(sort, 0, left, 0, left.length);
        System.arraycopy(sort, left.length, right, 0, right.length);
        // 合并
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 2路-归并
     *
     * @param left  左有序数组
     * @param right 右有序数组
     * @return 归并后的数组
     */
    private static int[] merge(int[] left, int[] right) {
        int[] mergeArr = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int mergeIndex = 0;
        while (leftIndex < left.length || rightIndex < right.length) {
            while (leftIndex < left.length && (rightIndex >= right.length || left[leftIndex] <= right[rightIndex])) {
                mergeArr[mergeIndex++] = left[leftIndex++];
            }
            while (rightIndex < right.length && (leftIndex >= left.length || right[rightIndex] <= left[leftIndex])) {
                mergeArr[mergeIndex++] = right[rightIndex++];
            }
        }
        return mergeArr;
    }
}
