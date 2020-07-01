package com.yg.leecode._96;

/**
 * 96. 不同的二叉搜索树
 * eg: 动态规划
 * @author Y.G
 * @date 2020/6/23 16:19
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    /**
     * 二叉搜索树：左子树的元素小于根节点，右子树的元素大于根节点
     * 1、节点i的构成二叉搜索树的个数 = 左子树个数 * 右子树个数  -> 最优子问题
     * 2、所以n阶个数为1~n每个数作为根节点的个数：G(n) = [i=1~n] ∑ G(i-1) * G(n-i)
     * 3、动态规划：将每一阶的计算结果保存起来避免重复计算
     * 4、状态转换方程：nums[n]+=nums[i-1]*nums[n-i]
     *
     * @param n n阶
     * @return 不同数
     */
    public static int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                nums[i] += nums[j - 1] * nums[i - j];
            }
        }
        return nums[n];
    }
}
