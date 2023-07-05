package com.joker.algorithm.binarytree;

/**
 * <p>
 * 对称二叉树
 * </p>
 *
 * @author admin
 * @date 2023/7/5
 */
public class leetcode101 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isSymmetric(root));
    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public boolean isSymmetric(TreeNode root) {
            // 两个指针同时指向根节点
            return recur(root, root);
        }

        private boolean recur(TreeNode p1, TreeNode p2) {
            if (p1 == null && p2 == null) {
                return true;
            }
            if (p1 == null || p2 == null) {
                return false;
            }

            // 判断是否镜像对称
            return p1.val == p2.val && recur(p1.left, p2.right) && recur(p1.right, p2.left);
        }

    }

}
