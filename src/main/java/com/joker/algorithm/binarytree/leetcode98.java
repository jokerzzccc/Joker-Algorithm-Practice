package com.joker.algorithm.binarytree;

/**
 * <p>
 * 验证二叉搜索树
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode98 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isValidBST(root));
    }

    /**
     * 解法一：BST 递归
     */
    private static class Solution01 {

        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
        boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            // base case
            if (root == null) return true;
            // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
            if (min != null && root.val <= min.val) return false;
            if (max != null && root.val >= max.val) return false;
            // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left, min, root)
                    && isValidBST(root.right, root, max);
        }

    }

}
