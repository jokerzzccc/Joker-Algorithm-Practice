package com.joker.algorithm.binarytree;

/**
 * <p>
 * 填充每个节点的下一个右侧节点指针
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode116 {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Solution01 solution01 = new Solution01();
        Node connected = solution01.connect(root);
        System.out.println(connected);

    }

    /**
     * 解法一：遍历思维
     */
    private static class Solution01 {

        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            // 遍历「三叉树」，连接相邻节点
            traverse(root.left, root.right);
            return root;
        }

        // 三叉树遍历框架
        private void traverse(Node left, Node right) {
            if (left == null && right == null) {
                return;
            }

            /**** 前序位置 ****/
            // 将传入的两个节点穿起来
            left.next = right;
            // 连接相同父节点的两个子节点
            traverse(left.left, left.right);
            traverse(right.left, right.right);
            // 连接跨越父节点的两个子节点
            traverse(left.right, right.left);
        }

    }

}
