package com.yg.leecode._05;

/**
 * 05 最长回文子串
 *
 * @author Y.G
 * @date 2020/6/23 17:58
 **/
public class Solution {
    public static void main(String[] args) {
        String str = "bb";
        System.out.println(longestPalindrome(str));
    }

    /**
     * 回文串：正序和倒序一致
     * 假设S(i, j) 是回文串，那么S(i+1, j-1)一定是回文串
     * 即 S(i+1, j-1) 为回文串且 S(i) = S(j) 则 S(i, j) 一定是回文串
     *
     * 动态规划：使用二维数组，通过[i][j]表示S(i,j)，其值表示是否为回文串
     * 状态转换方程：dp[i][j] = dp[i+1][j-1] && S(i) == S(j)
     * 最长回文串条件：dp[i][j] = true && maxLen > j-i+ 1  maxLen = j-i+1
     * 边界条件：
     *      单字符串
     *      S(i) = S(j) [长度为0,1,2 都默认满足回文串 ]
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public static String longestPalindrome(String s) {
        // 问题抽象：s(i,j)是回文串，那么s(i+1,j-1) 也是回文子串
        // 所以 s(i,j)为回文串的条件是：s(i+1, j-1) and s(i) = s(j)
        if(s.length() < 2){
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 1;
        int start = 0;
        for (int var1 = 0; var1 < dp.length; var1++) {
            dp[var1][var1] = true;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && maxLen < j - i + 1) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
