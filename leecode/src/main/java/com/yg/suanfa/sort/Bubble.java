package com.yg.suanfa.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Y.G
 * @date 2020/3/29 17:26
 **/
public class Bubble {
    public static void main(String[] args) {
        int [] arr = new int[] {2,4,5, 1,3, 5 , 2, 6, 2, 6, 4};
        Arrays.stream(arr).mapToObj(v ->" " + v).forEach(System.out::print);
        bubble(arr);
        System.out.println();
        System.out.println("-------------");
        Arrays.stream(arr).mapToObj(v ->" " + v).forEach(System.out::print);
    }

    private static void bubble(int [] arr){
        int total = arr.length;
        while (total>1){
            for(int var1 =1 ;var1< total; var1++){
                if(arr[var1 -1] > arr[var1]){
                    int tmp  = arr[var1];
                    arr[var1] = arr[var1 -1];
                    arr[var1 - 1] = tmp;
                }
            }
            total--;
        }
    }
}
