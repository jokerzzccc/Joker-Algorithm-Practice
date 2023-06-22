package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树中的插入操作
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode701 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        TreeNode result01 = solution01.insertIntoBST(root, 5);
        System.out.println(result01);

    }

    /**
     * 解法一：遍历递归
     */
    private static class Solution01 {

        public TreeNode insertIntoBST(TreeNode root, int val) {
            // 找到空位置插入新节点
            if (root == null) {
                return new TreeNode(val);
            }
            if (val < root.val) {
                root.left = insertIntoBST(root.left, val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }

            return root;
        }

    }

}
