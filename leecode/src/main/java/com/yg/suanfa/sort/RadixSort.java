package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 1、根据位数排序
 * 2、例：先根据个位将数据分别放在对应的数组中
 * 3、按各位从小到大取出数据，根据先进先出放入原数组
 * 4、再按10排序，直到数组中数据的最高位
 *
 * @author Y.G
 * @date 2020/4/1 14:25
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        // 基数缓存数组，二维数组
        int[][] tmpArr = new int[10][arr.length];
        // 基数缓存数组下标最大值
        int[] tmpIndex = new int[10];
        int radix = 1;
        while (tmpIndex[0] < arr.length - 1) {
            for (int var1 = 0; var1 < tmpIndex.length; var1++) {
                tmpIndex[var1] = -1;
            }
            for (int var1 = 0; var1 < arr.length; var1++) {
                int part = (arr[var1] / radix) % 10;
                int index = tmpIndex[part];
                tmpArr[part][++index] = arr[var1];
                tmpIndex[part] = index;
            }
            // 将缓存数组中的值回写至数组
            for (int var1 = 0, index = 0; var1 < tmpIndex.length; var1++) {
                for (int var2 = 0; var2 <= tmpIndex[var1]; var2++) {
                    arr[index++] = tmpArr[var1][var2];
                }
            }
            radix *= 10;
            System.out.println("--- vvv ---");
            for(int[] var2:tmpArr){
                System.out.println(Arrays.toString(var2));
            }
            System.out.println("--- ^^^ ---");
            tmpArr = new int[10][arr.length];
        }
    }
}
