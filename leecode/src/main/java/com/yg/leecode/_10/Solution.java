package com.yg.leecode._10;

/**
 * 10.正则表达式匹配
 *
 * @author Y.G
 * @date 2020/6/27 16:44
 **/
public class Solution {


    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b*";
/*        s = "mississippi";
        p = "mis*is*p*.";
        s = "ab";
        p = ".*";*/
        System.out.println(isMatch(s, p));
    }

    /**
     * 字符串匹配，s: 正常字符串，p: 正则字符串
     * 动态规划匹配：若要满足S(i, j)匹配，当s(i) == p(j) 时，则S(i-1, j-1)配置即可
     * p(j) 可能为 . 或 *
     * 1、. 时，s(i) == p(j) 为 true
     * 2、* 时，只与前一个字符组合生效，所以需要判断s(i) 和 p(j-1)的相等关系：
     *          1）s(i) = p(j-1) 说明s(i)满足p(j)和*的组合，那么就分为两种情况：已经匹配多个 S(i-1,j) 满足、匹配0个 S(i, j-2) 满足
     *          2) s(i) != p(j-1) 说明s(i)对应的字符不满足p(j-1)和*的组合，那么S(i, j) 的结果由 S(i, j-2)决定
     * 状态转换方程：
     *              p(j) = * : s(i) = p(j-1) dp[i][j] = dp[i-1][j] || dp[i][j-2]
     *              p(j) = * : s(i) != p(j-1) dp[i][j] = dp[i][j-2]
     *              p(j) != * : dp[i][j] = dp[i-1][j-1]
     * 边界条件：
     *         1）s 和 p 同为空，dp[0][0] = true
     *         2）s为空字符串时，p不为空，带*的组合都满足，p(j) = * dp[0][j] = dp[0][j-2]
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(p.charAt(j-1) == '*'){
                    if(i > 0 && matches(s.charAt(i-1), p.charAt(j-2))){
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }else{
                    if(i > 0){
                        dp[i][j] = dp[i-1][j-1] && matches(s.charAt(i-1), p.charAt(j-1));
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static boolean matches(char a, char b){
        if(b == '.'){
            return true;
        }
        return a == b;
    }
}
