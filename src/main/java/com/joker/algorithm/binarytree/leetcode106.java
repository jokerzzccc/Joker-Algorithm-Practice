package com.joker.algorithm.binarytree;

import java.util.HashMap;

/**
 * <p>
 * 从中序与后序遍历序列构造二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode106 {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.buildTree(inorder, postorder);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     * 画图理解
     */
    private static class Solution01 {

        // 存储 inorder 中值到索引的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                valToIndex.put(inorder[i], i);
            }
            return build(inorder, 0, inorder.length - 1,
                    postorder, 0, postorder.length - 1);

        }

        /**
         * build 函数的定义：
         * 后序遍历数组为 postorder[postStart..postEnd]，
         * 中序遍历数组为 inorder[inStart..inEnd]，
         * 构造二叉树，返回该二叉树的根节点
         */
        TreeNode build(int[] inorder, int inStart, int inEnd,
                int[] postorder, int postStart, int postEnd) {

            if (inStart > inEnd) {
                return null;
            }
            // root 节点对应的值就是后序遍历数组的最后一个元素
            int rootVal = postorder[postEnd];

            // rootVal 在中序遍历数组中的索引
            int index = valToIndex.get(rootVal);
            // 左子树的节点个数
            int leftSize = index - inStart;
            TreeNode root = new TreeNode(rootVal);

            // 递归构造左右子树
            root.left = build(inorder, inStart, index - 1,
                    postorder, postStart, postStart + leftSize - 1);

            root.right = build(inorder, index + 1, inEnd,
                    postorder, postStart + leftSize, postEnd - 1);
            return root;
        }

    }

}
