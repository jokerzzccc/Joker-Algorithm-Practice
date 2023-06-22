package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树的最近公共祖先 III
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1650 {

    public static void main(String[] args) {

    }

    /**
     * 解法一：
     * 这道题其实不是公共祖先的问题，而是单链表相交的问题
     */
    private static class Solution01 {

        public Node lowestCommonAncestor(Node p, Node q) {
            // 链表双指针技巧
            Node a = p, b = q;
            while (a != b) {
                // a 走一步，如果走到根节点，转到 q 节点
                if (a == null) {
                    a = q;
                } else {
                    a = a.parent;
                }

                // b 走一步，如果走到根节点，转到 p 节点
                if (b == null) {
                    b = p;
                } else {
                    b = b.parent;
                }
            }
            return a;
        }

    }

    class Node {

        public int val;
        public Node left;
        public Node right;
        public Node parent;

    }

    ;

}
