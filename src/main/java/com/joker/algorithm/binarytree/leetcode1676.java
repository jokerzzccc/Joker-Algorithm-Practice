package com.joker.algorithm.binarytree;

import java.util.HashSet;

/**
 * <p>
 * 二叉树的最近公共祖先 IV
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1676 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        System.out.println(new Solution01()
                .lowestCommonAncestor(root,
                        new TreeNode[]{new TreeNode(7)
                                , new TreeNode(6)
                                , new TreeNode(2)
                                , new TreeNode(4)}).val);

    }

    /**
     * 解法一：递归
     * 类比 leetcode236
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            // 将列表转化成哈希集合，便于判断元素是否存在
            HashSet<Integer> values = new HashSet<>();
            for (TreeNode node : nodes) {
                values.add(node.val);
            }

            return find(root, values);
        }

        /**
         * 在二叉树中寻找 values 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, HashSet<Integer> values) {
            if (root == null) {
                return null;
            }

            // 前序位置
            if (values.contains(root.val)) {
                return root;
            }

            TreeNode left = find(root.left, values);
            TreeNode right = find(root.right, values);

            // 后序位置，已经知道左右子树是否存在目标值
            if (left != null && right != null) {
                // 当前节点是 LCA 节点
                return root;
            }

            return left != null ? left : right;
        }

    }

}
