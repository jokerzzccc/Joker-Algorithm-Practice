package com.joker.algorithm.binarytree;

/**
 * <p>
 * 翻转二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode226 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Solution01 solution01 = new Solution01();
        TreeNode invertTree01 = solution01.invertTree(root);
        System.out.println(invertTree01);

        Solution02 solution02 = new Solution02();
        TreeNode invertTree02 = solution02.invertTree(root);
        System.out.println(invertTree02);

    }

    /**
     * 解法一：遍历思维
     */
    private static class Solution01 {

        public TreeNode invertTree(TreeNode root) {
            // 遍历二叉树，交换每个节点的子节点
            traverse(root);
            return root;
        }

        // 二叉树遍历函数
        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }

            /**** 前序位置 ****/
            // 每一个节点需要做的事就是交换它的左右子节点
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
            // 遍历框架，去遍历左右子树的节点
            traverse(root.left);
            traverse(root.right);
        }

    }

    /**
     * 解法二：分解问题思维
     */
    private static class Solution02 {

        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            // 利用函数定义，先翻转左右子树
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);

            // 然后交换左右子节点
            root.left = right;
            root.right = left;

            // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
            return root;
        }

    }

}
