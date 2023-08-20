package com.joker.algorithm.binarytree;

/**
 * <p>
 * 平衡二叉树
 * </p>
 *
 * @author admin
 * @date 2023/8/20
 */
public class leetcode110 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isBalanced(root));

    }

    /**
     * 解法一：递归（自底向上）
     */
    private static class Solution01 {

        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;

        }

        /**
         * 如果一棵子树是平衡的，则返回其高度(以当前节点为根节点的高度)（高度一定是非负整数），否则返回 −1
         */
        private int height(TreeNode root) {
            // base case
            if (root == null) {
                return 0;
            }

            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            // 后序位置
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }

    }

}
