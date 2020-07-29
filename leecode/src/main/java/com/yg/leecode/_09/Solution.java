package com.yg.leecode._09;

/**
 * 9.回文数
 *
 * @author Y.G
 * @date 2020/7/5 18:51
 **/
public class Solution {
    public static void main(String[] args) {
        int num = -121;
        System.out.println(isPalindrome(num));
    }

    /**
     * 双指针，回文数首尾必相等，同步长向中心缩进，直到不相等或首位相碰
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int start = 0;
        int end = str.length() - 1;
        while(start < end){
            if(str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
