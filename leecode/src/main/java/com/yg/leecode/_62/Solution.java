package com.yg.leecode._62;

import java.util.Arrays;

/**
 * 62.不同路径
 *
 * @author Y.G
 * @date 2020/6/27 14:03
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(uniquePaths_better(7, 3));
    }

    /**
     * m * n  的网格中从(1, 1) 到达(m, n) 的路径有多少条
     * 走法是只能向下 或 向右，那么到达(m, n)的条数可以抽象为：到达(m-1, n) + 到达(m, n-1)
     * 动态规划：使用二维数组抽象网格，即 a[i][j] 表示到达坐标(i+1, j+1)的路径条数
     * 边界值：i==j && i==0 -> a[0][0] = 1
     * i!=j && (i = 0 或 j = 0) -> 则 a[i][j] = a[i][j-1] 或 a[i][j] = a[i-1][j]
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && i == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 优化思路：到达 i = 0 或 j= 0 的点，其路径只有一种
     * 那么将这些路径都初始化，如 3 * 2
     * 1 1 1
     * 1
     * 1
     * 则 i > 0 && j > 0 ->  dp[i][j] = dp[0][j] + dp[0][j-1]
     * 因为每一列的每个位置，都可以看作是同一列的相同位置 状态在不停的改变
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths_better(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
