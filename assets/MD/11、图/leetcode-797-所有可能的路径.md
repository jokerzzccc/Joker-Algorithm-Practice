

# 目录

[toc]

# leetcode-797-所有可能的路径

- 时间：2023-06-22
- 参考链接：
  - [图论基础及遍历算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/tu-lun-ji--d55b2/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/all-paths-from-source-to-target/
- 难度：中等

给你一个有 n 个节点的 **有向无环图（DAG）**，请你找出所有从节点 0 到节点 n-1 的路径并输出（**不要求按特定顺序**）

 graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。



提示：

- n == graph.length
- 2 <= n <= 15
- 0 <= graph[i][j] < n
- graph[i][j] != i（即不存在自环）
- graph[i] 中的所有元素 **互不相同**
- 保证输入为 **有向无环图（DAG）**





# 2、题解

## 题目分析



## 解法一

### 算法分析





### 代码

```java

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

```

- 注意 Java 的语言特性，因为 Java 函数参数传的是对象引用，所以向 `res` 中添加 `path` 时需要拷贝一个新的列表，否则最终 `res` 中的列表都是空的。

### 复杂度分析

![image-20230622212914750](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622212914750.png)









# THE END