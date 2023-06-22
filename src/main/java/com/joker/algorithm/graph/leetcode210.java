package com.joker.algorithm.graph;

import java.util.*;

/**
 * <p>
 * 课程表 II
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode210 {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        Solution01 solution01 = new Solution01();
        int[] order01 = solution01.findOrder(numCourses, prerequisites);
        Arrays.stream(order01).forEach(System.out::print);

        System.out.println("\n");

        Solution02 solution02 = new Solution02();
        int[] order02 = solution02.findOrder(numCourses, prerequisites);
        Arrays.stream(order02).forEach(System.out::print);
    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        // 记录后序遍历结果
        List<Integer> postorder = new ArrayList<>();
        // 记录是否存在环
        boolean hasCycle = false;
        // 记录一次递归堆栈中的节点，计量当前的路径
        boolean[] onPath;
        // 记录遍历过的节点，防止走回头路
        boolean[] visited;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            // 遍历图
            for (int i = 0; i < numCourses; i++) {
                traverse(graph, i);
            }
            // 有环图无法进行拓扑排序
            if (hasCycle) {
                return new int[]{};
            }

            // 逆后序遍历结果即为拓扑排序结果
            Collections.reverse(postorder);
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = postorder.get(i);
            }
            return res;
        }

        /**
         * 遍历图
         */
        void traverse(List<Integer>[] graph, int s) {
            if ((onPath[s])) {
                // 出现环
                hasCycle = true;
            }

            if (visited[s] || hasCycle) {
                // 如果已经找到了环，也不用再遍历了
                return;
            }

            // 前序代码位置
            visited[s] = true;
            onPath[s] = true;
            for (Integer t : graph[s]) {
                traverse(graph, t);
            }

            // 后序代码位置
            postorder.add(s);
            onPath[s] = false;
        }

        /**
         * 邻接表的方式建图
         */
        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            // 图中共有 numCourses 个节点
            List<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : prerequisites) {
                int from = edge[1], to = edge[0];
                // 添加一条从 from 指向 to 的有向边
                // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
                graph[from].add(to);
            }
            return graph;
        }

    }

    /**
     * 解法二：BFS
     */
    private static class Solution02 {

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 建图，和环检测算法相同
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 计算入度，和环检测算法相同
            int[] indegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                int from = edge[1], to = edge[0];
                indegree[to]++;
            }

            // 根据入度初始化队列中的节点，和环检测算法相同
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);

                }
            }

            // 记录拓扑排序结果
            int[] res = new int[numCourses];
            // 记录遍历节点的顺序（索引）
            int count = 0;
            // 开始执行 BFS 循环，不断弹出队列中的节点，减少相邻节点的入度，并将入度变为 0 的节点加入队列
            while (!q.isEmpty()) {
                int cur = q.poll();
                // 弹出节点的顺序即为拓扑排序结果
                res[count] = cur;
                count++;
                for (int next : graph[cur]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            if (count != numCourses) {
                // 存在环，拓扑排序不存在
                return new int[]{};
            }

            return res;
        }

        /**
         * 邻接表的方式建图
         */
        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            // 图中共有 numCourses 个节点
            List<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : prerequisites) {
                int from = edge[1], to = edge[0];
                // 添加一条从 from 指向 to 的有向边
                // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
                graph[from].add(to);
            }
            return graph;
        }

    }

}
