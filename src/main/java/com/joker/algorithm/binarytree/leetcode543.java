package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树的直径
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode543 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution01 solution01 = new Solution01();
        int diameter01 = solution01.diameterOfBinaryTree(root);
        System.out.println(diameter01);

        Solution02 solution02 = new Solution02();
        int diameter02 = solution02.diameterOfBinaryTree(root);
        System.out.println(diameter02);

    }

    /**
     * 解法一：前序位置
     * 直截了当的思路就是遍历整棵树中的每个节点，
     * 然后通过每个节点的左右子树的最大深度算出每个节点的「直径」，最后把所有「直径」求个最大值即可
     */
    private static class Solution01 {

        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // 对每个节点计算直径，求最大直径
            traverse(root);
            return maxDiameter;
        }

        /**
         * 遍历二叉树
         */
        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 对每个节点计算直径
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            int curDiameter = leftMax + rightMax;
            // 更新全局最大直径
            maxDiameter = Math.max(maxDiameter, curDiameter);

            traverse(root.left);
            traverse(root.right);
        }

        /**
         * 计算二叉树的最大深度
         */
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            return 1 + Math.max(leftMax, rightMax);
        }

    }

    /**
     * 解法一：后序位置 (更优)
     * 我们应该把计算「直径」的逻辑放在后序位置，
     * 准确说应该是放在 `maxDepth` 的后序位置，因为 `maxDepth` 的后序位置是知道左右子树的最大深度的
     */
    private static class Solution02 {

        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // 对每个节点计算直径，求最大直径
            maxDepth(root);
            return maxDiameter;
        }

        /**
         * 计算二叉树的最大深度
         */
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            int curDiameter = leftMax + rightMax;
            maxDiameter = Math.max(maxDiameter, curDiameter);

            return 1 + Math.max(leftMax, rightMax);
        }

    }

}
