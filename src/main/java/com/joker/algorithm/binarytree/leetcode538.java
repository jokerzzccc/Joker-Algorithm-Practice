package com.joker.algorithm.binarytree;

/**
 * <p>
 * 把二叉搜索树转换为累加树
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode538 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        Solution01 solution = new Solution01();
        TreeNode result01 = solution.convertBST(root);
        System.out.println(result01);

    }

    /**
     * 解法一：反序中序遍历
     * BST 中序遍历特性（有序递增）
     */
    private static class Solution01 {

        /**
         * 记录累加和
         */
        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            traverse(root);
            return root;
        }

        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.right);
            // 维护累加和
            sum += root.val;
            // 将 BST 转化成累加树
            root.val = sum;
            traverse(root.left);
        }

    }

}
