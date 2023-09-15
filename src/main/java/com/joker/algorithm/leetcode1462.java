package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 课程表 IV
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/12
 */
public class leetcode1462 {

    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = {{1, 2}, {1, 0}, {2, 0}};
        int[][] queries = {{1, 0}, {1, 2}};

        Solution01 solution01 = new Solution01();
        solution01.checkIfPrerequisite(numCourses, prerequisites, queries)
                .forEach(System.out::println);
        System.out.println("========================");
        Solution02 solution02 = new Solution02();
        solution02.checkIfPrerequisite(numCourses, prerequisites, queries)
                .forEach(System.out::println);

    }


    /**
     * 解法一：BFS + 拓扑排序
     */
    private static class Solution01 {
        /**
         * 邻接表，表示图
         */
        List<Integer>[] graph;
        /**
         * inDegree[i] 表示 i 的入度
         */
        int[] inDegree;

        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            // 图，初始化
            graph = new List[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new ArrayList<>();
            }

            inDegree = new int[numCourses];
            // isPre[a][b] 表示，a 是否为 b 的直接或间接先驱
            boolean[][] isPre = new boolean[numCourses][numCourses];
            for (int[] prerequisite : prerequisites) {
                ++inDegree[prerequisite[1]];
                graph[prerequisite[0]].add(prerequisite[1]);
            }

            // BFS
            // queue 里存储的总是入度为0的节点
            Queue<Integer> queue = new ArrayDeque<>();
            // 入度为0的节点初始化队列
            for (int i = 0; i < numCourses; ++i) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                // 当前节点
                int curr = queue.poll();
                // 将这个节点及其所有前置条件节点设置为所有后续节点的前置条件节点
                for (int adj : graph[curr]) {
                    isPre[curr][adj] = true;
                    for (int i = 0; i < numCourses; i++) {
                        // isPre[i][adj] ,isPre[i][curr] 只要一个为 true,则 isPre[i][adj] 为true
                        isPre[i][adj] = isPre[i][adj] | isPre[i][curr];
                    }

                    // 对每一个后续节点入度进行 −1 操作
                    --inDegree[adj];
                    if (inDegree[adj] == 0) {
                        queue.offer(adj);
                    }
                }
            }

            // 结果判定
            List<Boolean> res = new ArrayList<>();
            for (int[] query : queries) {
                res.add(isPre[query[0]][query[1]]);
            }
            return res;
        }
    }

    /**
     * 解法二：DFS + 拓扑排序
     */
    private static class Solution02 {
        /**
         * 邻接表，表示图
         */
        List<Integer>[] graph;
        /**
         * visited[i] 表示节点 i 是否已经被访问
         */
        boolean[] visited;

        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            // 图，初始化
            graph = new List[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new ArrayList<>();
            }

            visited = new boolean[numCourses];
            // isPre[a][b] 表示，a 是否为 b 的直接或间接先驱
            boolean[][] isPre = new boolean[numCourses][numCourses];
            for (int[] prerequisite : prerequisites) {
                graph[prerequisite[0]].add(prerequisite[1]);
            }

            // 对所有课程节点 DFS，构造 isPre
            for (int i = 0; i < numCourses; ++i) {
                dfs(isPre, i);
            }

            // 结果判定
            List<Boolean> res = new ArrayList<Boolean>();
            for (int[] query : queries) {
                res.add(isPre[query[0]][query[1]]);
            }
            return res;
        }

        private void dfs(boolean[][] isPre, int curr) {
            // 剪枝
            if (visited[curr]) {
                return;
            }

            visited[curr] = true;

            // 对其全部后继节点递归进行「深度优先搜索」流程
            for (int adj : graph[curr]) {
                dfs(isPre, adj);
                // 后序位置
                // 将节点 curr 置为其每一个后继节点 adj 的先决条件
                isPre[curr][adj] = true;
                // 以及对于每一个以 adj 为先决条件的节点 i，节点 curr 同样为 i 的先决条件
                for (int i = 0; i < isPre.length; ++i) {
                    isPre[curr][i] = isPre[curr][i] | isPre[adj][i];
                }
            }

        }
    }
}
