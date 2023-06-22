package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树的最近公共祖先
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode236 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        System.out.println(new Solution01().lowestCommonAncestor(root, root.left, root.right).val);
    }

    /**
     * 解法一: 递归
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return find(root, p.val, q.val);
        }

        /**
         * 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) return null;

            // 前序位置
            if (root.val == val1 || root.val == val2) {
                // 如果遇到目标值，直接返回
                return root;
            }
            TreeNode left = find(root.left, val1, val2);
            TreeNode right = find(root.right, val1, val2);
            // 后序位置，已经知道左右子树是否存在目标值
            if (left != null && right != null) {
                // 当前节点是 LCA 节点
                return root;
            }

            return left != null ? left : right;
        }

    }

}
