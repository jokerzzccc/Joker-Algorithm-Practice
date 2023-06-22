package com.joker.algorithm.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 不同的二叉搜索树 II
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode95 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        List<TreeNode> treeNodes01 = solution01.generateTrees(n);
        System.out.println(treeNodes01);

    }

    /**
     * 解法一：回溯
     */
    private static class Solution01 {

        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new LinkedList<>();
            // 构造闭区间 [1, n] 组成的 BST
            return build(1, n);

        }

        /**
         * 构造闭区间 [low, high] 组成的 BST
         */
        private List<TreeNode> build(int low, int high) {
            List<TreeNode> res = new LinkedList<>();
            // base case
            // 即 【low, high】 为空
            if (low > high) {
                res.add(null);
                return res;
            }

            // 1、穷举 root 节点的所有可能。
            for (int i = low; i <= high; i++) {
                // 2、递归构造出左右子树的所有合法 BST。
                List<TreeNode> leftTree = build(low, i - 1);
                List<TreeNode> rightTree = build(i + 1, high);
                // 3、给 root 节点穷举所有左右子树的组合。
                for (TreeNode leftNode : leftTree) {
                    for (TreeNode rightNode : rightTree) {
                        // i 作为根节点 root 的值
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        root.right = rightNode;
                        res.add(root);
                    }
                }
            }
            return res;
        }

    }

}
