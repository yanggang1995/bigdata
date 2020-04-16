package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 1、按照步长将数据划分为各个分区
 * 2、在各个分区内使用直接插入排序
 *
 * @author Y.G
 * @date 2020/3/31 18:02
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        shell(arr);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    private static void shell(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int var1 = gap; var1 < arr.length; var1++) {
                int stand = arr[var1];
                int var2 = var1 - gap;
                for (; var2 >= 0 && arr[var2] > stand; var2 -= gap) {
                    arr[var2 + gap] = arr[var2];
                }
                arr[var2 + gap] = stand;
            }
        }
    }
}
