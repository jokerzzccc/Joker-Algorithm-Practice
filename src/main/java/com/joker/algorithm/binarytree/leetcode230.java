package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树中第K小的元素
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode230 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(new Solution01().kthSmallest(root, 2));

    }

    /**
     * 解法一：BST 的中序遍历特性
     */
    private static class Solution01 {

        // 记录结果
        int res = 0;
        // 记录当前元素的排名
        int rank = 0;

        public int kthSmallest(TreeNode root, int k) {
            // 利用 BST 的中序遍历特性
            traverse(root, k);
            return res;
        }

        void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }

            traverse(root.left, k);
            /* 中序遍历代码位置 */
            rank++;
            if (k == rank) {
                // 找到第 k 小的元素
                res = root.val;
                return;
            }
            /*****************/
            traverse(root.right, k);
        }

    }

}
