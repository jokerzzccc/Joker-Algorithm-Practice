# 目录

[toc]

# leetcode-210-课程表 II

- 时间：2023-06-22
- 参考链接：
  - [环检测及拓扑排序算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/huan-jian--e36de/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/course-schedule-ii/
- 难度：中等



现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

- 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。



返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 **任意一种** 就可以了。如果不可能完成所有课程，返回 **一个空数组** 。





# 2、题解

## 题目分析

但是我们这道题和拓扑排序有什么关系呢？

**其实也不难看出来，如果把课程抽象成节点，课程之间的依赖关系抽象成有向边，那么这幅图的拓扑排序结果就是上课顺序**。



那么关键问题来了，如何进行拓扑排序？是不是又要秀什么高大上的技巧了？

**其实特别简单，将后序遍历的结果进行反转，就是拓扑排序的结果**。

## 解法一: DFS

### 算法分析



### 代码

```java


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
        System.out.println(order01);
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

}

```



### 复杂度分析

![image-20230623002949980](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623002949980.png)





## 解法二: BFS

### 算法分析





### 代码

```java


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

        Solution02 solution02 = new Solution02();
        int[] order02 = solution02.findOrder(numCourses, prerequisites);
        Arrays.stream(order02).forEach(System.out::print);
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
            // 开始执行 BFS 算法
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

```



### 复杂度分析

![image-20230623003015380](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623003015380.png)



# THE END