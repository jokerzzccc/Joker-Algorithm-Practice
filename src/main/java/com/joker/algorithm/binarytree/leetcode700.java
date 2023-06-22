package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树中的搜索
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode700 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(new Solution01().searchBST(root, 2).val);

    }

    /**
     * 解法一：递归遍历，利用 BST 的左小右大的特性
     */
    private static class Solution01 {

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }

            if (root.val > val) {
                return searchBST(root.left, val);
            }

            if (root.val < val) {
                return searchBST(root.right, val);
            }

            return root;
        }

    }

}
