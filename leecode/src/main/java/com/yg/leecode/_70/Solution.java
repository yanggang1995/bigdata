package com.yg.leecode._70;

/**
 * 70.爬楼梯
 *
 * @author Y.G
 * @date 2020/6/27 15:53
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(climbStairs_better(3));
    }

    /**
     * 每次走 1 或 2 个台阶，到n阶有多少种走法
     * 假设到n阶有 f(n) 种走法，且到n 只可能是 n-1 走一阶 或 n-2 走两阶，所以f(n) = f(n-1) + f(n-2)
     * 动态规划：使用一维数组dp[i]表示到i阶有多少种走法
     * 状态转换方程：dp[i] = dp[i-1] + dp[i-2]
     * 边界条件：
     * dp[0] = 1
     * dp[1] = 2
     */
    public static int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        if (n > 0) {
            dp[0] = 1;
        }
        if (n > 1) {
            dp[1] = 2;
        }
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 优化：每次只会使用到两个值，即 f(n-1) 和 f(n-2) ，所以可以忽略数组
     *
     * @param n
     * @return
     */
    public static int climbStairs_better(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            int result = 0;
            int n_1 = 1, n_2 = 2;
            for (int i = 2; i < n; i++) {
                result = n_1 + n_2;
                n_2 = n_1;
                n_1 = result;
            }
            return result;
        }
    }
}
