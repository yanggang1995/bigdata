package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 归并排序，不拷贝数组，合并时通过缓存数组回写原数组实现
 *
 * @author Y.G
 * @date 2020/4/1 11:03
 **/
public class MergeSort_NoCopy {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] sort, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int half = (start + end + 1) / 2;
        mergeSort(sort, start, half - 1);
        mergeSort(sort, half, end);
        merge(sort, start, half, end);
    }

    private static void merge(int[] arr, int start, int half, int end) {
        int[] mergeArr = new int[end - start + 1];
        int leftIndex = start;
        int rightIndex = half;
        int mergeIndex = 0;
        while (leftIndex < half || rightIndex < end) {
            while (leftIndex < half && (rightIndex > end || arr[leftIndex] <= arr[rightIndex])) {
                mergeArr[mergeIndex++] = arr[leftIndex++];
            }
            while (rightIndex <= end && (leftIndex >= half || arr[rightIndex] <= arr[leftIndex])) {
                mergeArr[mergeIndex++] = arr[rightIndex++];
            }
        }
        for (int var1 = 0; var1 < mergeArr.length; var1++) {
            arr[start + var1] = mergeArr[var1];
        }
    }
}
