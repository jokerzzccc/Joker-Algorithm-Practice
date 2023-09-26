# 目录

[toc]

# leetcode-1462-课程表 IV

- 时间：2023-09-12
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/course-schedule-iv/description/
- 难度：中等

你总共需要上 `numCourses` 门课，课程编号依次为 `0` 到 `numCourses-1` 。你会得到一个数组 `prerequisite` ，其中 `prerequisites[i] = [ai, bi]` 表示如果你想选 `bi` 课程，你 **必须** 先选 `ai` 课程。

+ 有的课会有直接的先修课程，比如如果想上课程 `1` ，你必须先上课程 `0` ，那么会以 `[0,1]` 数对的形式给出先修课程数对。

先决条件也可以是 **间接** 的。如果课程 `a` 是课程 `b` 的先决条件，课程 `b` 是课程 `c` 的先决条件，那么课程 `a` 就是课程 `c` 的先决条件。

你也得到一个数组 `queries` ，其中 `queries[j] = [uj, vj]`。对于第 `j` 个查询，您应该回答课程 `uj` 是否是课程 `vj` 的先决条件。

返回一个布尔数组 `answer` ，其中 `answer[j]` 是第 `j` 个查询的答案。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/05/01/courses4-1-graph.jpg)

```
输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
输出：[false,true]
解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
```

**示例 2：**

```
输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
输出：[false,false]
解释：没有先修课程对，所以每门课程之间是独立的。
```

**示例 3：**

![img](https://assets.leetcode.com/uploads/2021/05/01/courses4-3-graph.jpg)

```
输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
输出：[true,true]
```

 

**提示：**

+ `2 <= numCourses <= 100`
+ `0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)`
+ `prerequisites[i].length == 2`
+ `0 <= ai, bi <= n - 1`
+ `ai != bi`
+ 每一对 `[ai, bi]` 都 **不同**
+ 先修课程图中没有环。
+ `1 <= queries.length <= 104`
+ `0 <= ui, vi <= n - 1`
+ `ui != vi`



# 2、题解

## 题目分析

- 参考链接：https://leetcode.cn/problems/course-schedule-iv/solutions/2417905/ke-cheng-biao-iv-by-leetcode-solution-mpc3/



## 解法一：BFS + 拓扑排序

### 算法分析





### 代码

```java


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

   
}

```





### 复杂度分析

![image-20230912212029651](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230912212029651.png)

## 解法二：DFS + 拓扑排序

### 算法分析





### 代码

```java


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

```





### 复杂度分析

![image-20230912212040685](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230912212040685.png)







# THE END