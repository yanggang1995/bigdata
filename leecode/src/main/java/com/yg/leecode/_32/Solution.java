package com.yg.leecode._32;

/**
 * 32.括号匹配
 *
 * @author Y.G
 * @date 2020/6/30 16:51
 **/
public class Solution {
    public static void main(String[] args) {
        String bar = "";
//        bar = ")";
//        bar = "(";
//        bar = ")()())";
//        bar = "((((()";
//        bar = "())))))";
//        bar = "))))))";
//        bar = "(((((";
//        bar = "()(())";
//        System.out.println(longestValidParentheses_cursor(bar));
        System.out.println(longestValidParentheses(bar));
    }

    /**
     * 动态规划：dp[i] 表示以i下标结尾的最大有效子括号字符串长度，那么S的最长有效括号Max(dp[i])
     * 1、当S(i) = '('时，以i结尾将不能构成任何有效的括号子串
     * 2、当S(i) = ')'时，如果S(i-1)='('，则以i结尾可以构成有效括号，最长有效子串长度为 dp[i] = dp[i-2] + 2
     * 3、当S(i) = ')'时，如果S(i-1) != '(' 但是能在小于i的下标中找到一个未匹配的 '('，那么这个'('的下标一定为 i-dp[i-1]-1，那么 dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
     * 因为 '(' 肯定不能构成有效括号，所以不包含在dp[i-1]，其构成有效括号，那么dp[i-dp[i-1]-2] + '(' + dp[i-1] + ')' 也能构成新的有效括号子串
     * 状态转换方程：
     *              dp[i] = { S(i) = ')' & S(i-1) = '(' } -> dp[i] = dp[i-2] + 2
     *              dp[i] = { S(i) = ')' & S(i-dp[i-1]-1) = '(' -> dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
     * 边界条件：单个字符为dp[0] = 0 , 满足下标不越界
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLen = Math.max(dp[i], maxLen);
            }
        }
        return maxLen;
    }

    /**
     * 括号匹配，从左括号出现开始，左括号与右括号出现次数要一致
     * 1）通过游标数记录左括号数，同时记录可能满足的字符串起始下标，逢右减一，直到为0，计算长度，更新起始下标
     * 2）游标为负数，则前面的字符串已不满足括号匹配
     * 3）从左往右匹配完成后，若游标大于0 ，则需要从右往左至上一起始下标，重复上述操作
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses_cursor(String s) {
        int start = 0;
        int end = 0;
        int cursor = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (cursor == 0) {
                    maxLen = rMax(maxLen, start, end);
                    start = i + 1;
                    end = start;
                } else {
                    cursor--;
                }
            }
            if (s.charAt(i) == '(') {
                cursor++;
            }
            if (cursor == 0) {
                end = i;
            }
        }
        if (cursor == 0) {
            maxLen = rMax(maxLen, start, end);
        }
        int lastStart = start;
        start = s.length() - 1;
        end = s.length() - 1;
        cursor = 0;
        for (int i = s.length() - 1; i >= lastStart; i--) {
            if (s.charAt(i) == '(') {
                if (cursor == 0) {
                    maxLen = sMax(maxLen, start, end);
                    start = i - 1;
                    end = start;
                } else {
                    cursor--;
                }
            }
            if (s.charAt(i) == ')') {
                cursor++;
            }
            if (cursor == 0) {
                end = i;
            }
        }
        return sMax(maxLen, start, end);
    }

    public static int rMax(int maxLen, int start, int end) {
        return Math.max(maxLen, start == end ? 0 : end - start + 1);
    }

    public static int sMax(int maxLen, int start, int end) {
        return Math.max(maxLen, start == end ? 0 : start - end + 1);
    }
}
