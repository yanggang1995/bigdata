package com.yg.leecode._53;

/**
 * 53.最大子串和
 *
 * @author Y.G
 * @date 2020/6/24 11:15
 **/
public class Solution {
    public static void main(String[] args) {
        int[] arr = {1,2};
        System.out.println(maxSubArray(arr));
    }

    /**
     * 求出数组a中所有子串的和，并得到最大值
     * 抽象：
     *      以i为下标，如果以i结尾的所有子串的最大和为 f(i)，那么数组的最大和为max(f(i))
     *      要得到以i结尾的所有子串，只需要i-1的所有子串 + i 和 i本身，如果知道 i-1 的所有子串的最大和f(i-1)，
     *      那么只需要比较 f(i-1) + a[i]的值 和 a[i]的值就能得到i的所有子串最大和，所以 f(i) = max( f(i-1) + a[i], a[i] )
     * 动态规划：使用dp[i]存储i的最大和
     * 状态转换方程：dp[i] = max(dp[i-1] + a[i], a[i])
     * 边界条件：dp[0] = a[0]
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int maxSum = nums[0];
        dp[0] = maxSum;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > maxSum) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }
}
