package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 1、内层循环每次寻找剩余未排序的最小值
 * 2、找到后和第一个值交换
 *
 * @author Y.G
 * @date 2020/4/1 9:56
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        select(arr);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    private static void select(int[] arr) {
        for (int var1 = 0; var1 < arr.length; var1++) {
            int min = var1;
            for (int var2 = var1 + 1; var2 < arr.length; var2++) {
                if (arr[var2] < arr[min]) {
                    min = var2;
                }
            }
            if (min == var1) {
                continue;
            }
            int tmpData = arr[min];
            arr[min] = arr[var1];
            arr[var1] = tmpData;
        }
    }
}
