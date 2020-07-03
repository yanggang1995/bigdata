package com.yg.leecode._44;

/**
 * 44.通配符匹配
 *
 * @author Y.G
 * @date 2020/7/1 18:42
 **/
public class Solution {
    public static void main(String[] args) {
        String s = "adceb";
        String p = "*a*b";
/*        s = "aa";
        p = "?";
        s = "aab";
        p = "c*a*b";*/
        System.out.println(isMatch(s, p));
    }

    /**
     * 可参考：10.正则表达式匹配
     * 动态规划：字符串匹配，s(0, i) 要和 p(0, j) 匹配，则需要 s(0, i-1) 和 p(0, j-1)已经匹配且 s(i) = p(j)
     * 所以用dp[i][j] 表示s(0, i) 和 p(0, j)是否匹配，又因为p中存在*，*可以匹配0个或多个字符，所以当p(j) = * 时，分为：匹配0个 或 匹配多个的情况
     * 状态转换方程：
     *              p(j) != * -> dp[i][j] = dp[i-1][j-1] && s(i) == p(j)
     *              p(j) = *  -> dp[i][j] = dp[i][j-1] (匹配0个) || dp[i-1][j](匹配多个)
     *  边界条件：同为空字符串 dp[0][0] = true
     *           s为空字符,和p的匹配关系 取决与p
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i=0; i <= s.length(); i++){
            for(int j=1; j<= p.length(); j++){
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = i==0 ? dp[i][j-1] : dp[i-1][j] || dp[i][j-1];
                }else{
                    dp[i][j] = i != 0 &&dp[i - 1][j - 1] && (p.charAt(j-1) == '?' || s.charAt(i - 1) == p.charAt(j - 1));
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
