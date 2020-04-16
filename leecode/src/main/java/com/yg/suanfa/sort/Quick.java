package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 1、按照基准数，把数组分成两份，左边的永远不大于基准值，右边永远不小于基准值
 * 2、递归左右
 * 3、剩余元素小于2时退出
 *
 * @author Y.G
 * @date 2020/3/29 18:10
 **/
public class Quick {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        quick(arr, 0, arr.length - 1);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int stand = arr[start];
        int low = start;
        int high = end;

        while (low < high) {
            while (low < high && arr[high] >= stand) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= stand) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = stand;
        quick(arr, start, low - 1);
        quick(arr, high + 1, end);
    }
}
