package com.joker.algorithm.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二叉树中所有距离为 K 的结点
 * </p>
 *
 * @author admin
 * @date 2023/6/27
 */
public class leetcode863 {

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

        int k = 2;
        TreeNode target = root.left;

        Solution01 solution01 = new Solution01();
        List<Integer> distanceK = solution01.distanceK(root, target, k);
        distanceK.stream().forEach(System.out::println);

    }

    /**
     * 解法一： DFS + 树变图 +  哈希缓存
     */
    private static class Solution01 {

        /**
         * 记录每个结点对应的父结点
         */
        Map<Integer, TreeNode> parents = new HashMap<>();

        List<Integer> ans = new ArrayList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 从 root 出发 DFS，记录每个结点的父结点
            findParents(root);

            // 从 target 出发 DFS，寻找所有深度为 k 的结点（左右儿子 + 父结点）
            // from 是为了避免重复搜索，因为有父结点搜索这个方向
            findAns(target, null, 0, k);

            return ans;
        }

        private void findAns(TreeNode node, TreeNode from, int depth, int k) {
            if (node == null) {
                return;
            }
            if (depth == k) {
                ans.add(node.val);
                return;
            }
            // 左儿子递归
            if (node.left != from) {
                findAns(node.left, node, depth + 1, k);
            }
            // 右儿子递归
            if (node.right != from) {
                findAns(node.right, node, depth + 1, k);
            }
            // 父节点递归
            if (parents.get(node.val) != from) {
                findAns(parents.get(node.val), node, depth + 1, k);
            }
        }

        /**
         * 记录每个结点的父结点, 到缓存 map
         */
        private void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }

    }

}
