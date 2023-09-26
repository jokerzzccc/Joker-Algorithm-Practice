# 目录

[toc]

# leetcode-787-K 站中转站内最便宜的航班

- 时间：2023-06-06
- 参考链接：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/lv-you-she-f3b76/




# 1、题目

- 题目：https://leetcode.cn/problems/cheapest-flights-within-k-stops/
- 难度：中等



有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。

现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。

 



# 2、题解

## 题目分析

**很明显，这题就是个加权有向图中求最短路径的问题**。

说白了，就是给你一幅加权有向图，让你求 `src` 到 `dst` 权重最小的一条路径，同时要满足，**这条路径最多不能超过 `K + 1` 条边**（经过 `K` 个节点相当于经过 `K + 1` 条边）。



## 解法一

### 算法分析

**dp 函数定义：**

```java
int dp(int s, int k);
```

函数的定义如下：

**从起点 `src` 出发，`k` 步之内（一步就是一条边）到达节点 `s` 的最小路径权重为 `dp(s, k)`**。

那么，`dp` 函数的 base case 就显而易见了：

```java
// 定义：从 src 出发，k 步之内到达 s 的最小成本
int dp(int s, int k) {
    // 从 src 到 src，一步都不用走
    if (s == src) {
        return 0;
    }
    // 如果步数用尽，就无解了
    if (k == 0) {
        return -1;
    }

    // ...
}
```

题目想求的最小机票开销就可以用 `dp(dst, K+1)` 来表示：

```java
int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    // 将中转站个数转化成边的条数
    K++;
    //...
    return dp(dst, K);
```

添加了一个 `K` 条边的限制，状态转移方程怎么写呢？



**状态转移方程**

`s1, s2` 是指向 `dst` 的相邻节点，我只要知道 `K - 1` 步之内从 `src` 到达 `s1, s2`，那我就可以在 `K` 步之内从 `src` 到达 `dst`。

也就是如下关系式：

```java
dp(dst, k) = min(
    dp(s1, k - 1) + w1, 
    dp(s2, k - 1) + w2
)
```

这就是新的状态转移方程

### 代码

```java

/**
 * <p>
 * K 站中转站内最便宜的航班
 * </p>
 *
 * @author admin
 * @date 2023/6/6
 */
public class leetcode787 {

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, k = 1;

        Solution01 solution01 = new Solution01();
        int cheapestPrice01 = solution01.findCheapestPrice(n, flights, src, dst, k);
        System.out.println(cheapestPrice01);

    }

    /**
     * 解法一：动态规划 + 递归 + 备忘录 + 自顶向下
     */
    private static class Solution01 {

        int src, dst;
        /**
         * K-to，V-[from,price]
         */
        Map<Integer, List<int[]>> inDegreeMap = new HashMap<>();
        /**
         * 备忘录
         */
        int[][] memo;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            this.src = src;
            this.dst = dst;
            // 将中转站个数转化成边的条数
            k++;
            memo = new int[n][k + 1];
            // memo 填充一个无意义的值
            for (int[] row : memo) {
                Arrays.fill(row, -2);
            }
            // 统计一个节点的入度，放入队列
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                inDegreeMap.putIfAbsent(to, new LinkedList<>());
                inDegreeMap.get(to).add(new int[]{from, price});
            }

            return dp(dst, k);
        }

        /**
         * 定义：从 src 出发，k 步之内到达 dst 的最短路径权重
         *
         * @param subDst 终点
         * @param k 步数（可以走过的边，一步就是一边）
         * @return 从 src 出发，k 步之内到达 dst 的最短路径权重
         */
        private int dp(int subDst, int k) {
            // base case
            if (subDst == src) {
                return 0;
            }
            if (k == 0) {
                return -1;
            }
            // 防止重叠子问题
            if (memo[subDst][k] != -2) {
                return memo[subDst][k];
            }

            // 初始化为最大值，方便等会取最小值
            int res = Integer.MAX_VALUE;
            // 当 s 有入度节点时，分解为子问题
            if (inDegreeMap.containsKey(subDst)) {
                for (int[] in : inDegreeMap.get(subDst)) {
                    int from = in[0];
                    int price = in[1];

                    // 从 src 到达相邻的入度节点所需的最短路径权重
                    int subProblem = dp(from, k - 1);
                    // 跳过无解的情况
                    if (subProblem != -1) {
                        res = Math.min(res, price + subProblem);
                    }
                }

            }
            memo[subDst][k] = res == Integer.MAX_VALUE ? -1 : res;
            return memo[subDst][k];
        }

    }

}
```





### 复杂度分析











# THE END