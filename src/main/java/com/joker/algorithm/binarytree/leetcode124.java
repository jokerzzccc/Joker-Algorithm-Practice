package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树中的最大路径和
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode124 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxPathSum(root));

    }

    /**
     * 解法一：递归(dfs) + 后序位置（才能获取到子树的信息）
     */
    private static class Solution01 {

        private int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return maxSum;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // 递归计算左右子节点的最大贡献值
            // 只有在最大贡献值大于 0 时，才会选取对应子节点,
            // 如果子树路径和为负则应当置0表示最大路径不包含子树，即子节点的路径为负的时候，不如不选
            int leftMax = Math.max(dfs(node.left), 0);
            int rightMax = Math.max(dfs(node.right), 0);

            // ====后序遍历位置====
            // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
            // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
            // 更新答案
            maxSum = Math.max(maxSum, node.val + leftMax + rightMax);

            // 返回节点的最大贡献值
            // 返回经过root的单边最大分支给当前root的父节点计算使用,
            // 因为经过 root 节点，路径的节点不能重复，肯定只能选一边，
            return node.val + Math.max(leftMax, rightMax);
        }

    }

}
