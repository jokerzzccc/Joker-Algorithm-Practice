package com.joker.algorithm.binarytree;

/**
 * <p>
 * 最大二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode654 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.constructMaximumBinaryTree(nums);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     */
    private static class Solution01 {

        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        /**
         * 定义：将 nums[low..high] 构造成符合条件的树，返回根节点
         */
        private TreeNode build(int[] nums, int low, int high) {
            // base case
            if (low > high) {
                return null;
            }

            // 找到数组中的最大值和对应的索引
            int index = -1, maxVal = Integer.MIN_VALUE;
            for (int i = low; i <= high; i++) {
                if (maxVal < nums[i]) {
                    index = i;
                    maxVal = nums[i];
                }
            }

            // 先构造出根节点
            TreeNode root = new TreeNode(maxVal);
            // 递归调用构造左右子树
            root.left = build(nums, low, index - 1);
            root.right = build(nums, index + 1, high);

            return root;
        }

    }

}
