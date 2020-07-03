package com.yg.leecode._378;

/**
 * 378.有序矩阵中的第k小的元素
 *
 * @author Y.G
 * @date 2020/7/2 18:23
 **/
public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{1,  5,  9},{10, 11, 13},{12, 13, 15}};
        System.out.println(kthSmallest(matrix, 8));
    }
    public static int kthSmallest(int[][] matrix, int k) {
        int mLen = matrix.length;
        int[] sort = matrix[0];
        for(int i =1; i < mLen; i++){
            int[] newSort = new int[sort.length + mLen];
            int leftIndex=0;
            int rightIndex=0;
            int mIndex=0;
            while (leftIndex < sort.length || rightIndex < mLen){
                while (leftIndex < sort.length && (rightIndex >= mLen || sort[leftIndex] <= matrix[i][rightIndex] )){
                    newSort[mIndex++] = sort[leftIndex++];
                }
                while (rightIndex < mLen && ( leftIndex >= sort.length || matrix[i][rightIndex] <= sort[leftIndex])){
                    newSort[mIndex++] = matrix[i][rightIndex++];
                }
            }
            sort = newSort;
        }
        return sort[k-1];
    }
}
