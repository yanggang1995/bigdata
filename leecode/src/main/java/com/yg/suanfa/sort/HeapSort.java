package com.yg.suanfa.sort;

import com.yg.ArrayUtil;

import java.util.Arrays;

/**
 * 堆排序
 * - 数组可以被看作一棵完全二叉树
 * - 将二叉树，构建为一个大顶堆（堆：所有根节点要么比子节点都大[大顶堆]，要么都小[小顶堆]）
 * 1、从最大下标的叶子节点下标开始，判断其父节点和子节点的大小
 * 2、若子节点存在比父节点大的值，交换其中最大的值给父节点
 * 3、交换过后，判断被交换的子节点对应的子树是否满足大顶堆，不满足重复步骤2
 * 4、所有节点完成大顶堆后，将数组0下标和参与排序的最大下标值交换，最大下标值减一重复步骤1
 *
 * @author Y.G
 * @date 2020/4/2 20:58
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 5, 1, 3, 5, 2, 1, 6, 2, 6, 4};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println("-----------");
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        for(int var1=arr.length-1; var1 >0; var1--){
            for(int var2 = var1; var2 > 0; var2 -= 2){
                maxHeap(arr, var1, (var2 - 1) / 2);
            }
            System.out.println("大顶堆:" + ArrayUtil.toString(arr,0, var1));
            int tmp = arr[var1];
            arr[var1] = arr[0];
            arr[0] = tmp;
        }
    }

    /**
     * 递归构建大顶堆
     *
     * @param arr 数组
     * @param maxIndex 最大下标
     * @param parentIndex 父节点下标
     */
    private static void maxHeap(int[] arr, int maxIndex, int parentIndex){
        int leftIndex = 2 * parentIndex + 1;
        if(leftIndex > maxIndex){
         return;
        }
        int rightIndex = 2 * parentIndex + 2;
        int sonMaxIndex = leftIndex;
        if(rightIndex <= maxIndex && arr[rightIndex] > arr[leftIndex]){
            sonMaxIndex = rightIndex;
        }
        if(arr[sonMaxIndex] > arr[parentIndex]){
            int tmp = arr[parentIndex];
            arr[parentIndex] = arr[sonMaxIndex];
            arr[sonMaxIndex] = tmp;
            maxHeap(arr, maxIndex, sonMaxIndex);
        }
    }
}
