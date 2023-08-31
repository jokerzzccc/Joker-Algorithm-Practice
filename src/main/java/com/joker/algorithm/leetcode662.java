package com.joker.algorithm;

import com.joker.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * <p>
 * 二叉树最大宽度
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/8/31
 */
public class leetcode662 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.widthOfBinaryTree(root)); // 4

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.widthOfBinaryTree(root));

    }

    /**
     * 解法一：DFS（更优）
     */
    private static class Solution01 {

        /**
         * 当前 depth 最左节点的编号:
         * k-depth,v-当前层的最左结点的编号
         */
        Map<Integer, Integer> map = new HashMap<>();

        private int ans;

        public int widthOfBinaryTree(TreeNode root) {
            dfs(root, 1, 0);
            return ans;
        }

        /**
         * 可以利用先 DFS 左节点，再 DFS 右节点的性质可知，
         * 每层的最左节点必然是最先被遍历到，
         * 因此我们只需要记录当前层最先被遍历到点编号（即当前层最小节点编号），
         * 并在 DFS 过程中计算宽度，更新答案即可。
         *
         * @param root
         * @param no 当前节点编号
         * @param depth 二叉树深度，根节点为 0
         */
        private void dfs(TreeNode root, int no, int depth) {
            if (root == null) {
                return;
            }
            if (!map.containsKey(depth)) {
                map.put(depth, no);
            }

            // 更新最长宽度
            ans = Math.max(ans, no - map.get(depth) + 1);
            // 对同层内的节点进行重新编号（使得同层最靠左的非空节点编号为 1）
            // 通过重编号操作 我们可以消除由于深度加深带来的编号溢出问题
            no = no - map.get(depth) + 1;
            // 先 DFS 左子节点，再 DFS 右子节点
            // 左子节点编号为 u << 1
            dfs(root.left, no << 1, depth + 1);
            // 右节点编号为 u << 1 | 1
            dfs(root.right, no << 1 | 1, depth + 1);
        }

    }

    /**
     * 解法一：BFS
     */
    private static class Solution02 {

        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 存储节点对应的下标(编号)
            Map<TreeNode, Integer> nodeToIndexMap = new HashMap<>();
            // 通过队列实现 bfs
            Queue<TreeNode> queue = new LinkedList<>();

            //初始化
            int maxWidth = 1;
            queue.offer(root);
            nodeToIndexMap.put(root, 1);

            while (!queue.isEmpty()) {
                // 获取每层起始节点（即最左端点）的下标
                int start = nodeToIndexMap.get(queue.peek());
                int size = queue.size();
                while (size > 0) {
                    TreeNode node = queue.poll();
                    size--;
                    // 获取此节点下标，用与给其的左右孩子确定下标
                    // 左右子节点的编号规则，为满二叉树的规则
                    int curIndex = nodeToIndexMap.get(node);
                    if (node.left != null) {
                        nodeToIndexMap.put(node.left, curIndex * 2);
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        nodeToIndexMap.put(node.right, curIndex * 2 + 1);//确定右孩子下标
                        queue.offer(node.right);//节点入队
                    }

                    // 本层遍历结束，更新层宽最大值
                    if (size == 0) {
                        maxWidth = Math.max(maxWidth, curIndex - start + 1);
                    }
                }
            }

            return maxWidth;
        }

    }

}
