package com.joker.algorithm;

import com.joker.algorithm.binarytree.TreeNode;

/**
 * <p>
 * 修剪二叉搜索树
 * </p>
 *
 * @author admin
 * @date 2023/8/30
 */
public class leetcode669 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);

        Solution01 solution01 = new Solution01();
        TreeNode ansNode = solution01.trimBST(root, 1, 2);
        System.out.println(ansNode.val);

    }

    /**
     * 解法一：dfs
     */
    private static class Solution01 {

        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) {
                return root;
            }
            if (root.val < low) {
                return trimBST(root.right, low, high);
            } else if (root.val > high) {
                return trimBST(root.left, low, high);
            } else {
                root.left = trimBST(root.left, low, high);
                root.right = trimBST(root.right, low, high);

                return root;
            }
        }

    }

}
