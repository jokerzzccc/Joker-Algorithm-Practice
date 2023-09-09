package com.joker.algorithm.graph;

import java.util.*;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode207 {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        Solution01 solution01 = new Solution01();
        boolean canFinish01 = solution01.canFinish(numCourses, prerequisites);
        System.out.println(canFinish01);

        Solution02 solution02 = new Solution02();
        boolean canFinish02 = solution02.canFinish(numCourses, prerequisites);
        System.out.println(canFinish02);

        Solution03 solution03 = new Solution03();
        System.out.println(solution03.canFinish(numCourses, prerequisites));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        // 记录一次递归堆栈中的节点，计量当前的路径
        boolean[] onPath;
        // 记录遍历过的节点，防止走回头路
        boolean[] visited;
        // 记录图中是否有环
        boolean hasCycle = false;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);

            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];

            for (int i = 0; i < numCourses; i++) {
                // 遍历图中的所有节点
                traverse(graph, i);
            }

            // 只要没有循环依赖可以完成所有课程
            return !hasCycle;
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
     * 总结下这段 BFS 算法的思路：
     * <p>
     * 1、构建邻接表，和之前一样，边的方向表示「被依赖」关系。
     * <p>
     * 2、构建一个 `indegree` 数组记录每个节点的入度，即 `indegree[i]` 记录节点 `i` 的入度。
     * <p>
     * 3、对 BFS 队列进行初始化，将入度为 0 的节点首先装入队列。
     * <p>
     * **4、开始执行 BFS 循环，不断弹出队列中的节点，减少相邻节点的入度，并将入度变为 0 的节点加入队列**。
     * <p>
     * **5、如果最终所有节点都被遍历过（`count` 等于节点数），则说明不存在环，反之则说明存在环**。
     */
    private static class Solution02 {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 建图，有向边代表「被依赖」关系
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 构建入度数组
            // 通过 indegree 数组实现的 visited 数组的作用，只有入度为 0 的节点才能入队，从而保证不会出现死循环。
            int[] indegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                int from = edge[1], to = edge[0];
                // 节点 to 的入度加一
                indegree[to]++;
            }

            // 根据入度初始化队列中的节点
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    // 节点 i 没有入度，即没有依赖的节点
                    // 可以作为拓扑排序的起点，加入队列
                    q.offer(i);

                }
            }

            // 记录遍历的节点个数
            int count = 0;
            // 开始执行 BFS 循环，不断弹出队列中的节点，减少相邻节点的入度，并将入度变为 0 的节点加入队列
            while (!q.isEmpty()) {
                // 弹出节点 cur，并将它指向的节点的入度减一
                int cur = q.poll();
                count++;
                for (int next : graph[cur]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        // 如果入度变为 0，说明 next 依赖的节点都已被遍历
                        q.offer(next);
                    }
                }
            }

            // 如果所有节点都被遍历过，说明不成环
            return count == numCourses;
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
     * 解法三：BFS
     * 寻找拓扑排序：BFS 结束时，如果仍有课的入度不为 0，无法被选，完成不了所有课。
     * 否则，能找到一种顺序把所有课上完。
     */
    private static class Solution03 {

        /**
         * 存储每个节点的入度
         * K-课程号，V-入度
         */
        private Map<Integer, Integer> inDegree = new HashMap<>();

        /**
         * 邻接表：
         * K-当前课程，V-依赖当前课程的后续课程（K 的后继节点链表）
         */
        private Map<Integer, List<Integer>> adj = new HashMap<>();

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            for (int i = 0; i < numCourses; i++) {
                inDegree.put(i, 0);
            }

            // 1. 初始化入度，和依赖关系(邻接表)
            for (int[] relation : prerequisites) {
                // EG: (3,0), 0->3, 想学3号课程要先完成0号课程, 更新3号课程的入度和0号课程的依赖(邻接表)
                int cur = relation[1];
                int next = relation[0];

                // 1.1 更新入度
                inDegree.put(next, inDegree.getOrDefault(next, 0) + 1);

                // 1.2 更新当前节点的邻接表
                if (!adj.containsKey(cur)) {
                    adj.put(cur, new ArrayList<>());
                }
                adj.get(cur).add(next);

            }

            // 2. BFS,
            // 将入度为0的课程放入队列, 队列中的课程就是没有先修, 可以学的课程
            Queue<Integer> queue = new LinkedList<>();
            for (int key : inDegree.keySet()) {
                if (inDegree.get(key) == 0) {
                    queue.offer(key);
                }
            }

            // 取出一个节点, 对应学习这门课程.
            // 遍历当前邻接表, 更新其入度; 更新之后查看入度, 如果为0, 加入到队列
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                // 遍历当前课程的邻接表, 更新后继节点的入度
                if (!adj.containsKey(cur)) {
                    continue;
                }
                List<Integer> successorList = adj.get(cur);
                for (int s : successorList) {
                    inDegree.put(s, inDegree.get(s) - 1);
                    // 入度为 0 时，加入队列
                    if (inDegree.get(s) == 0) {
                        queue.offer(s);
                    }
                }
            }

            // 3. 判断结果：是否可以找到一个拓扑排序；
            // BFS 结束时，如果仍有课的入度不为 0，无法被选，完成不了所有课
            for (Integer node : inDegree.keySet()) {
                if (inDegree.get(node) != 0) {
                    return false;
                }
            }

            return true;
        }

    }

}
