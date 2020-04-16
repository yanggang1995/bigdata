package com.yg;

/**
 * 数组工具类
 *
 * @author Y.G
 * @date 2020/4/2 22:17
 **/
public class ArrayUtil {

    public static String toString(int[] arr, int start, int end){
        StringBuilder result = new StringBuilder();
        for(int var1 = start; var1<= end; var1++){
            result.append(" ").append(arr[var1]);
        }
        return result.toString().replaceFirst(" ", "");
    }
}
