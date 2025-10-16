package com.joker.algorithm;

import java.util.*;

/**
 * 每颗子树内缺失的最小基因值
 *
 * @author jokerzzccc
 * @date 2023/10/31
 */
public class leetcode2003 {

    public static void main(String[] args) {
        int[] parents = {-1, 0, 0, 2};
        int[] nums = {1, 2, 3, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.smallestMissingValueSubtree(parents, nums)));
    }

    /**
     * 解法一：DFS（leetcode 超出内存限制）
     */
    private static class Solution01 {

        public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
            int n = parents.length;
            int[] ans = new int[n];
            Arrays.fill(ans, 1);

            // 找基因值为 1 的节点，为出发点；
            // 因为基因值互不相同；
            int node = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 1) {
                    node = i;
                    break;
                }
            }
            // 不存在基因值为 1 的节点
            if (node < 0) {
                return ans;
            }

            // 建树
            List<Integer>[] graph = buildGraph(parents, n);

            Set<Integer> visited = new HashSet<>();
            int mex = 2; // 缺失的最小基因值
            // 从出发点，遍历每个节点（直到遍历完根节点）
            while (node >= 0) {
                dfs(node, graph, visited, nums);
                while (visited.contains(mex)) { // node 子树包含这个基因值
                    mex++;
                }

                ans[node] = mex; // 缺失的最小基因值
                node = parents[node]; // 往上走
            }

            return ans;
        }

        /**
         * dfs 遍历 x 子树
         */
        private void dfs(int x, List<Integer>[] graph, Set<Integer> visited, int[] nums) {
            visited.add(nums[x]); // 标记基因值
            for (int son : graph[x]) {
                if (!visited.contains(nums[son])) {
                    dfs(son, graph, visited, nums);
                }
            }
        }

        /**
         * 构建树：邻接表法
         */
        private List<Integer>[] buildGraph(int[] parents, int n) {
            List<Integer>[] graph = new ArrayList[n];
            Arrays.setAll(graph, ArrayList::new);
            for (int i = 1; i < n; i++) {
                graph[parents[i]].add(i);
            }

            return graph;
        }

    }

}
