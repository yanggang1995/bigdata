package com.yg.leecode._64;

/**
 * 64.最小路径和
 *
 * @author Y.G
 * @date 2020/6/27 15:23
 **/
public class Solution {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));
    }

    /**
     * 给定m * n 的网格a，从左上角到右下角的最小路径和
     * 走法 => 向下 或 向右，那么 a(i, j) 的最小路径 为 Min(a(i-1, j), a(i, j-1)) + a(i, j)
     * 动态规划：利用二维数组dp存储a(i, j) 的最小路径和
     * 状态转换方程：dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + a[i][j]
     * 边界值：
     *        dp[0][0] = a[i][j]
     *        i = 0 , j != 0 -> dp[i][j] = dp[i][j-1] + a[i][j]
     *        i != 0, j = 0 -> dp[i][j] = dp[i-1][j] + a[i][j]
     * 注：未使用grid 为dp数组，实际一般不会这样使用，会改变入参的值
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(i == j && i == 0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if(i == 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
