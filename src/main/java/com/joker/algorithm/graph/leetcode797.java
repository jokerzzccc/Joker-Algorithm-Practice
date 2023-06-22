package com.joker.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 所有可能的路径
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode797 {

    public static void main(String[] args) {

        int[][] graph = {{1, 2}, {3}, {3}, {}};

        Solution01 solution01 = new Solution01();
        List<List<Integer>> allPathsSourceTarget01 = solution01.allPathsSourceTarget(graph);
        System.out.println(allPathsSourceTarget01);

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        // 记录所有结果路径
        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 维护递归过程中经过的路径
            LinkedList<Integer> path = new LinkedList<>();
            traverse(graph, 0, path);
            return res;
        }

        /**
         * 图的遍历框架
         */
        void traverse(int[][] graph, int s, LinkedList<Integer> path) {

            // 加节点 s 到路径
            path.addLast(s);

            int n = graph.length;
            if (s == n - 1) {
                // 到达终点
                res.add(new LinkedList<>(path));
                // 可以在这直接 return，但要 removeLast 正确维护 path
                // path.removeLast();
                // return;
                // 不 return 也可以，因为图中不包含环，不会出现无限递归
            }

            // 递归每个相邻节点
            for (int v : graph[s]) {
                traverse(graph, v, path);
            }

            // 从路径移出节点 s
            path.removeLast();
        }

    }

}
