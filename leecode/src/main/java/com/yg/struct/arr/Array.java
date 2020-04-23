package com.yg.struct.arr;

/**
 * 数组
 *
 * @author Y.G
 * @date 2020/3/31 15:34
 **/
public class Array {
    public static void main(String[] args) {
        // 1、定长数组并初始化赋值
        int [] arr1 = new int[10];
        for(int var1 = 0; var1 < arr1.length; var1++){
            arr1[var1] = var1;
        }
        // 2、静态数组，通过数据决定长度
        int [] arr2 = {1,2,3,4,5};

        // 3、声明对象时赋值，可直接作为方法的返回值
        int [] arr3 = new int[]{1,2,3,4,5};
    }
}
