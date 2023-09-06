package com.joker.algorithm;

import com.joker.algorithm.binarytree.TreeNode;

/**
 * <p>
 * 最深叶节点的最近公共祖先
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/6
 */
public class leetcode1123 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(2);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.lcaDeepestLeaves(root).val);

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        private TreeNode ans;
        /**
         * 全局最大深度
         */
        private int maxDepth = -1;

        public TreeNode lcaDeepestLeaves(TreeNode root) {
            dfs(root, 0);
            return ans;
        }

        private int dfs(TreeNode root, int depth) {
            if (root == null) {
                maxDepth = Math.max(maxDepth, depth);
                return depth;
            }

            int leftMaxDepth = dfs(root.left, depth + 1);
            int rightMaxDepth = dfs(root.right, depth + 1);

            // 后序遍历位置，更新答案
            if (leftMaxDepth == rightMaxDepth && leftMaxDepth == maxDepth) {
                ans = root;
            }
            // 当前子树最深叶节点的深度
            return Math.max(leftMaxDepth, rightMaxDepth);
        }

    }

}
