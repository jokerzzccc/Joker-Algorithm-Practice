package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树的最近公共祖先
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode235 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        System.out.println(new Solution01().lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val);
    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 保证 val1 较小，val2 较大
            int val1 = Math.min(p.val, q.val);
            int val2 = Math.max(p.val, q.val);
            return find(root, val1, val2);
        }

        /**
         * 在 BST 中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) {
                return null;
            }
            if (root.val > val2) {
                // 当前节点太大，去左子树找
                return find(root.left, val1, val2);
            }
            if (root.val < val1) {
                // 当前节点太小，去右子树找
                return find(root.right, val1, val2);
            }
            // val1 <= root.val <= val2
            // 则当前节点就是最近公共祖先
            return root;
        }

    }

}
