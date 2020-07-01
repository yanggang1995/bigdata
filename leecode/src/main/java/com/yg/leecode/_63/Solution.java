package com.yg.leecode._63;

/**
 * 63.不同路径和Ⅱ
 *
 * @author Y.G
 * @date 2020/6/27 15:04
 **/
public class Solution {
    public static void main(String[] args) {
        int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(arr));
    }

    /**
     * 基础思路参考62 不同路径
     * 增加：a(i, j)可能存在障碍，则 dp[i][j] = 0
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                if(i == j && i == 0){
                    dp[0][0] = 1;
                    continue;
                }
                if(i == 0){
                    dp[i][j] = dp[i][j-1];
                }else if(j == 0){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
