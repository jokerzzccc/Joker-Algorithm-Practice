package com.joker.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 *
 * @author jokerzzccc
 * @date 2024/3/21
 */
public class leetcode144 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        List<Integer> res = solution01.preorderTraversal(root);

        res.stream().forEach(System.out::println);
    }

    /**
     * 解法一：迭代解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private static class Solution01 {

        public List<Integer> preorderTraversal(TreeNode root) {
            // 结果集
            ArrayList<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            // 辅助栈
            LinkedList<TreeNode> stack = new LinkedList<>();
            // 当前节点
            TreeNode currNode = root;
            while (!stack.isEmpty() || currNode != null) {
                while (currNode != null) {
                    res.add(currNode.val);
                    stack.push(currNode);
                    currNode = currNode.left;
                }
                currNode = stack.pop();
                currNode = currNode.right;
            }

            return res;
        }

    }

}
