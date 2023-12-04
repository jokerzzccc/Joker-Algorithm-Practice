package com.joker.algorithm.binarytree;

/**
 * 从二叉搜索树到更大和树
 *
 * @author jokerzzccc
 * @date 2023/12/4
 */
public class leetcode1038 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        inOrderTraverse(root);
        System.out.println("\r\n===");
        Solution01 solution01 = new Solution01();
        TreeNode res1 = solution01.bstToGst(root);
        inOrderTraverse(res1);
    }

    /**
     * 解法一：递归
     * 递归的顺序：右子树-根-左子树
     */
    private static class Solution01 {

        /**
         * 当前遍历到的所有节点的和
         */
        private int tmpSum = 0;

        public TreeNode bstToGst(TreeNode root) {
            dfs(root);
            return root;
        }

        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }

            // 递归右子树
            dfs(node.right);
            tmpSum += node.val;
            node.val = tmpSum;
            // 递归左子树
            dfs(node.left);
        }

    }

    /**
     * 前序遍历输出二叉树
     */
    private static void inOrderTraverse(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + "->");
            inOrderTraverse(node.left);
            inOrderTraverse(node.right);
        }
    }

}
