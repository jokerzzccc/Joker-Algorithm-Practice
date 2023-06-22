package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树的最近公共祖先 II
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1644 {

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

        System.out.println(new Solution01().lowestCommonAncestor(root, root.left, new TreeNode(10)));

    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        // 用于记录 p 和 q 是否存在于二叉树中
        boolean foundP = false;
        boolean foundQ = false;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res = find(root, p.val, q.val);
            if (!foundP || !foundQ) {
                return null;
            }
            // p 和 q 都存在二叉树中，才有公共祖先
            return res;
        }

        /**
         * 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) {
                return null;
            }
            TreeNode left = find(root.left, val1, val2);
            TreeNode right = find(root.right, val1, val2);

            // 后序位置，判断当前节点是不是 LCA 节点
            if (left != null && right != null) {
                return root;
            }

            // 后序位置，判断当前节点是不是目标值
            if (root.val == val1 || root.val == val2) {
                // 找到了，记录一下
                if (root.val == val1) foundP = true;
                if (root.val == val2) foundQ = true;
                return root;
            }

            return left != null ? left : right;
        }

    }

}
