package com.joker.algorithm.binarytree;

/**
 * <p>
 * 完全二叉树的节点个数
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode222 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(new Solution01().countNodes(root));

    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public int countNodes(TreeNode root) {
            TreeNode l = root, r = root;
            // 沿最左侧和最右侧分别计算高度
            int hl = 0, hr = 0;
            while (l != null) {
                l = l.left;
                hl++;
            }
            while (r != null) {
                r = r.right;
                hr++;
            }
            // 如果左右侧计算的高度相同，则是一棵满二叉树
            if (hl == hr) {
                return (int) Math.pow(2, hl) - 1;
            }
            // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

    }

}
