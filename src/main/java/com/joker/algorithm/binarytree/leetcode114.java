package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树展开为链表
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode114 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        Solution01 solution01 = new Solution01();
        solution01.flatten(root);
        System.out.println(root);

    }

    /**
     * 解法一：分解问题思维
     */
    private static class Solution01 {

        // 定义：将以 root 为根的树拉平为链表
        public void flatten(TreeNode root) {
            // base case
            if (root == null) {
                return;
            }
            // 利用定义，把左右子树拉平
            flatten(root.left);
            flatten(root.right);

            /**** 后序遍历位置 ****/
            // 1、左右子树已经被拉平成一条链表
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 2、将左子树作为右子树
            root.left = null;
            root.right = left;

            // 3、将原先的右子树接到当前右子树的末端
            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            p.right = right;
        }

    }

}
