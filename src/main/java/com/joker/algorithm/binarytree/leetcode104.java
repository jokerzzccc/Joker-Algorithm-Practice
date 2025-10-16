package com.joker.algorithm.binarytree;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p>
 * 二叉树的最大深度
 * </p>
 *
 * @author admin
 * @date 2023/6/18
 */
public class leetcode104 {

    public static void main(String[] args) {

        PriorityBlockingQueue<Object> objects = new PriorityBlockingQueue<>();
        objects.add(1);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        int maxDepth01 = solution01.maxDepth(root);
        System.out.println(maxDepth01);

        Solution02 solution02 = new Solution02();
        int maxDepth02 = solution02.maxDepth(root);
        System.out.println(maxDepth02);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        // 记录最大深度
        int maxDepth = 0;
        // 记录遍历到的节点的深度
        int depth = 0;

        public int maxDepth(TreeNode root) {
            traverse(root);
            return maxDepth;

        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            depth++;
            if (root.left == null && root.right == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
            traverse(root.left);
            traverse(root.right);
            // 后序位置
            depth--;
        }

    }

    /**
     * 解法二：DFS
     */
    private static class Solution02 {

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;

        }

    }

}
