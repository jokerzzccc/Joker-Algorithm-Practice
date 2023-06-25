package com.joker.algorithm;

import com.joker.algorithm.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 二叉树的最小深度
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode111 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        int minDepth01 = solution01.minDepth(root);
        System.out.println(minDepth01);

    }

    /**
     * 解法一： BFS
     */
    private static class Solution01 {

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // root 本身就是一层，depth 初始化为 1
            int depth = 1;
            // while 循环控制一层一层往下走，for 循环利用 sz 变量控制从左到右遍历每一层二叉树节点
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curNode = queue.poll();
                    /* 判断是否到达终点 */
                    if (curNode.left == null && curNode.right == null)
                        return depth;

                    /* 将 curNode 的相邻节点加入队列 */
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
                /* 这里增加步数 */
                depth++;
            }

            return depth;
        }

    }

}
