# 目录

[toc]

# offer-13-机器人的运动范围

- 时间：2023-04-30
- 



# 1、题目

- https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/

- 难度：中等



# 2、解法

## 算法思路：

- 回溯法：DFS 或 BFS
- 



## 代码:

### 法一：DFS

```java
/**
 * <p>
 * 机器人的运动范围
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/4/30
 */
public class offer13 {

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int k = 1;
        Solution solution = new Solution();
        System.out.println(solution.movingCount(m, n, k));
    }

    static class Solution {

        int m, n, k;
        boolean[][] visited;

        public int movingCount(int m, int n, int k) {
            this.m = m;
            this.n = n;
            this.k = k;
            this.visited = new boolean[m][n];

            return dfs(0, 0, 0, 0);
        }

        public int dfs(int i, int j, int si, int sj) {
            if (i >= m || j >= n || k < si + sj || visited[i][j]) {
                return 0;
            }
            visited[i][j] = true;
            return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj)
                    + dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);

        }

    }

}
```





## 法二 BFS

```java
package com.joker.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 机器人的运动范围
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/4/30
 */
public class offer13 {

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int k = 1;
        Solution02 solution02 = new Solution02();
        System.out.println("solution02: " + solution02.movingCount(m, n, k));
    }



    static class Solution02 {

        public int movingCount(int m, int n, int k) {
            boolean[][] visited = new boolean[m][n];
            int res = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0, 0, 0});
            while (queue.size() > 0) {
                int[] x = queue.poll();
                int i = x[0];
                int j = x[1];
                int si = x[2];
                int sj = x[3];
                if (i >= m || j >= n || k < si + sj || visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                res++;
                queue.add(new int[]{i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj});
                queue.add(new int[]{i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
            }

            return res;
        }

    }

}

```









# THE END