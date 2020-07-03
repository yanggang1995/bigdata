package com.yg.leecode._108;

/**
 * 108.将有序数组转换成二叉搜索树
 *
 * @author Y.G
 * @date 2020/7/3 9:31
 **/
public class Solution {

    public static void main(String[] args) {
        int[] arr = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(arr));
    }

    /**
     * 二分 + 递归
     * 首先数组为升序数组，需要构成二叉搜索树(左子节点小于父节点，右子节点大于父节点)，且左右子树高度差绝对值不超过 1
     * 可以得到，取数组中间位置的元素作为父节点，其左边的子数组 和 右边的子数组，长度绝对值不超过 1
     * 通过相同的方法对子数组进行迭代，并形成二叉搜索树即可得到 一棵左右子树高度差绝对值不超过 1 的二叉搜索树
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return rec(nums, 0, nums.length - 1);
    }

    public static TreeNode rec(int[] nums, int start, int end){
        if(end < start){
            return null;
        }else if ( start == end){
            return new TreeNode(nums[start]);
        }
        int mid = (end - start) / 2 + start;
        TreeNode midNode = new TreeNode(nums[mid]);
        midNode.left = rec(nums, start, mid -1);
        midNode.right = rec(nums, mid+1, end);
        return midNode;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}

