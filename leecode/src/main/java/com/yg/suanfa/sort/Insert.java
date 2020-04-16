package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 1、每次排序指定下标之前的所有数据
 *
 * @author Y.G
 * @date 2020/3/30 20:51
 **/
public class Insert {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        insert(arr);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    private static void insert(int[] arr) {
        for (int var1 = 1; var1 < arr.length; var1++) {
            int tmpData = arr[var1];
            int var2;
            for (var2 = var1 - 1; var2 >= 0 && arr[var2] > tmpData; var2--) {
                arr[var2 + 1] = arr[var2];
            }
            arr[var2 + 1] = tmpData;
        }
    }
}
