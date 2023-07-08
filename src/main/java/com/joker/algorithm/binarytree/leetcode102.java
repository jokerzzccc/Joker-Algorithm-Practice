package com.joker.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 二叉树的层序遍历
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode102 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        List<List<Integer>> levelOrder01 = solution01.levelOrder(root);
        levelOrder01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return res;
            }

            // 队列，存储每一层的节点，跟着 while 变化
            LinkedList<TreeNode> queue = new LinkedList<>();
            // 初始化，加入 root，第一层
            queue.offer(root);

            // while 是横向，for 是纵向
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();

                int curLevel = queue.size();
                for (int i = 0; i < curLevel; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                res.add(level);

            }

            return res;
        }

    }

}
