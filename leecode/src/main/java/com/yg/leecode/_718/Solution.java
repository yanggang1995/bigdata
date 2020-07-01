package com.yg.leecode._718;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/6/23 10:31
 **/
class Solution {
    public static void main(String[] args) {
        int[] a = {1,2,3,2,1};
        int[] b = {3,2,1,4,7};
        System.out.println(findLength(a, b ));
    }

    /**
     * 通过矩阵的方式进行公共比较
     *   1 2 3 2 1
     * 3 0 0 1 0 0
     * 2 0 1 0 1 0
     * 1 1 0 0 0 1
     * 4 0 0 0 0 0
     * 7 0 0 0 0 0
     * 找到最大的单位矩阵即对角线为1最长的子矩阵，对应的字数组即为最大公共数组，其对角线长度为公共字数组最大长度
     * 1、按照对角线进行倒序处理，最优子问题是 对角线为1
     * 2、状态变换方程：下一个对角线值为上一对角线值 + 当前坐标值状态  matrix[i][j] = matrix[i+1][j+1] + 1
     * 3、最大字数组长度 = max(matrix[i][j])
     * @param A 数组
     * @param B 数组
     * @return 最大长度
     */
    public static int findLength(int[] A, int[] B){
        int[][] matrix = new int[A.length + 1][B.length + 1];
        int maxLen =  0;
        for(int i=A.length-1; i>=0; i--){
            for(int j=B.length - 1; j>=0; j--){
                if(A[i] == B[j]){
                    matrix[i][j] = matrix[i+1][j+1] + 1;
                    if(maxLen < matrix[i][j]){
                        maxLen = matrix[i][j];
                    }
                }
            }
        }
        return maxLen;
    }


    /**
     * 从最短的数组开始，穷举完所有字数组，然后比较
     * eg: 最笨拙的方式，效率低下
     * @param A 数组A
     * @param B 数组B
     * @return 最长子数组长度
     */
    public static int findLength1(int[] A, int[] B) {
        // 获取长度较短的数组
        int[] min = B,max=A;
        if(A.length <= B.length){
            min = A;
            max = B;
        }
        int maxLen = 0;
        // 遍历长度为步长
        for(int i=1; i<= min.length; i++) {
            // 根据步长取出字串，遍历另外数组，是否满足字串
            for(int j=0; j<= min.length-i; j++){
//                for(int k=j; k< j+i; k++) {
//                    System.out.print(min[k]);
//                    System.out.print("-");
//                }
//                System.out.println();
                for(int k=0;k<= max.length-i; k++){
                    boolean equ = true;
                    for(int index=0; index<i; index++){
                        if(min[j+index] != max[k+index]){
                            equ = false;
                            break;
                        }
                    }
                    if(equ) {
//                        System.out.println(k + " - " + i);
                        maxLen = i;
                        break;
                    }
                }
            }
        }
        return maxLen;
    }
}