package com.joker.algorithm.graph;

/**
 * 阈值距离内邻居最少的城市
 *
 * @author jokerzzccc
 * @date 2023/11/14
 */
public class leetcode1334 {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findTheCity(n, edges, distanceThreshold));
    }

    /**
     * 解法一：Floyd 算法
     * 我们可以求出每一个节点 ppp 到其它节点的最短路，然后查看与 ppp 距离在 distanceThreshold 以内的邻居数量最小的节点。
     * <p>
     * 我们可以使用 Floyd 算法得到任意两点之间的最短路，然后统计满足条件的邻居数量。
     */
    private static class Solution01 {

        /**
         * graph[i][j] : 最终表示 i -> j 的最短路径值
         */
        private int[][] graph;
        int vertexNum;

        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            graph = new int[n][n];
            this.vertexNum = n;

            // 初始化无向图
            buildGraph(edges);
            // floyd 算法：求最短路路径
            doFloyd();
            // 统计答案
            return getAns(n, distanceThreshold);
        }

        /**
         * 求答案
         */
        private int getAns(int n, int distanceThreshold) {
            int ans = -1;
            int cnt = n + 10;
            for (int i = 0; i < n; i++) {
                // cur: 当前符合要求的可达顶点数
                int cur = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && graph[i][j] <= distanceThreshold) {
                        cur++;
                    }
                }
                // 更新答案：取可达顶点最少的顶点 i ，如果相等，取顶点编号更大的顶点。
                if (cur <= cnt) {
                    cnt = cur;
                    ans = i;
                }
            }

            return ans;
        }

        /**
         * floyd 算法：求两点之间的最短路径
         */
        private void doFloyd() {
            // floyd 基本流程为三层循环: [枚举中转点 - 枚举起点 - 枚举终点] => 松弛操作
            for (int p = 0; p < vertexNum; p++) {
                for (int i = 0; i < vertexNum; i++) {
                    for (int j = 0; j < vertexNum; j++) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][p] + graph[p][j]);
                    }
                }

            }

        }

        /**
         * 初始化图：用邻接矩阵表示
         */
        private void buildGraph(int[][] edges) {
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {
                    graph[i][j] = i == j ? 0 : 0x3f3f3f3f;
                }
            }
            // 存图：无向图
            for (int[] edge : edges) {
                int from = edge[0], to = edge[1], weight = edge[2];
                graph[from][to] = graph[to][from] = Math.min(graph[from][to], weight);
            }
        }

    }

}
