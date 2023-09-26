# 目录

[toc]

# leetcode-207-课程表

- 时间：2023-06-22
- 参考链接：
  - [环检测及拓扑排序算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/huan-jian--e36de/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/course-schedule/
- 难度：中等



你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

- 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。



请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。



提示：

- 1 <= numCourses <= 10^5
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- 0 <= ai, bi < numCourses
- prerequisites[i] 中的所有课程对 **互不相同**





# 2、题解

## 题目分析

题目应该不难理解，什么时候无法修完所有课程？当存在循环依赖的时候。

**看到依赖问题，首先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖**。

具体来说，我们首先可以把课程看成「有向图」中的节点，节点编号分别是 `0, 1, ..., numCourses-1`，把课程之间的依赖关系看做节点之间的有向边。

比如说必须修完课程 `1` 才能去修课程 `3`，那么就有一条有向边从节点 `1` 指向 `3`。

**如果发现这幅有向图中存在环，那就说明课程之间存在循环依赖，肯定没办法全部上完；反之，如果没有环，那么肯定能上完全部课程**。

以我刷题的经验，常见的存储方式是使用邻接表，比如下面这种结构：

```java
List<Integer>[] graph;
```

**`graph[s]` 是一个列表，存储着节点 `s` 所指向的节点**。

## 解法一: DFS

### 算法分析

1、建图

常见的存储方式是使用邻接表，比如下面这种结构：

```java
List<Integer>[] graph;
```

**`graph[s]` 是一个列表，存储着节点 `s` 所指向的节点**。

所以我们首先可以写一个建图函数：



2、图建出来了，怎么判断图中有没有环呢？**先不要急，我们先来思考如何遍历这幅图，只要会遍历，就可以判断图中是否存在环了**。

前文 [图论基础](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/tu-lun-ji--d55b2/) 写了 DFS 算法遍历图的框架，无非就是从多叉树遍历框架扩展出来的，加了个 `visited` 数组罢了：

注意图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。

这样，就能遍历这幅图中的所有节点了，你打印一下 `visited` 数组，应该全是 true。



3、现在可以思考如何判断这幅图中是否存在环。

我们前文 [回溯算法核心套路详解](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/hui-su-sua-c26da/) 说过，你可以把递归函数看成一个在递归树上游走的指针，这里也是类似的：

你也可以把 `traverse` 看做在图中节点上游走的指针，只需要再添加一个布尔数组 `onPath` 记录当前 `traverse` 经过的路径：

这里就有点回溯算法的味道了，在进入节点 `s` 的时候将 `onPath[s]` 标记为 true，离开时标记回 false，如果发现 `onPath[s]` 已经被标记，说明出现了环。

### 代码

```java


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

}

```



### 复杂度分析

![image-20230623003200084](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623003200084.png)





## 解法二: BFS

### 算法分析

其实 BFS 算法借助 `indegree` 数组记录每个节点的「入度」，也可以实现这两个算法。不熟悉 BFS 算法的读者可阅读前文 [BFS 算法核心框架](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/bfs-suan-f-463fd/)。

所谓「出度」和「入度」是「有向图」中的概念，很直观：如果一个节点 `x` 有 `a` 条边指向别的节点，同时被 `b` 条边所指，则称节点 `x` 的出度为 `a`，入度为 `b`。



我先总结下这段 BFS 算法的思路：

1、构建邻接表，和之前一样，边的方向表示「被依赖」关系。

2、构建一个 `indegree` 数组记录每个节点的入度，即 `indegree[i]` 记录节点 `i` 的入度。

3、对 BFS 队列进行初始化，将入度为 0 的节点首先装入队列。

**4、开始执行 BFS 循环，不断弹出队列中的节点，减少相邻节点的入度，并将入度变为 0 的节点加入队列**。

**5、如果最终所有节点都被遍历过（`count` 等于节点数），则说明不存在环，反之则说明存在环**。

### 代码

```java


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

        Solution02 solution02 = new Solution02();
        boolean canFinish02 = solution02.canFinish(numCourses, prerequisites);
        System.out.println(canFinish02);

    }

    /**
     * 解法二：BFS
     */
    private static class Solution02 {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 建图，有向边代表「被依赖」关系
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 构建入度数组
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
            // 开始执行 BFS 循环
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

}

```



### 复杂度分析

![image-20230623003211854](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623003211854.png)



# THE END