# 目录

[toc]

# leetcode-1020-飞地的数量

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 







# 1、题目

- 题目：https://leetcode.cn/problems/number-of-enclaves/
- 难度：中等



给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。

一次 **移动** 是指从一个陆地单元格走到另一个相邻（**上、下、左、右**）的陆地单元格或跨过 grid 的边界。

返回网格中 **无法** 在任意次数的移动中离开网格边界的陆地单元格的数量。

示例 1：

![image-20230626211057900](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230626211057900.png)

```sh
输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
输出：3
解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
```

**提示：**

+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 500`
+ `grid[i][j]` 的值为 `0` 或 `1`





# 2、题解

## 题目分析

- 类比 leetcode-1254



## 解法一: DFS

### 算法分析





### 代码

```java


/**
 * <p>
 * 飞地的数量
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode1020 {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numEnclaves(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int numEnclaves(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // 上下边界变为海水
            for (int i = 0; i < n; i++) {
                dfs(grid, 0, i);
                dfs(grid, m - 1, i);
            }
            // 左右边界变为海水
            for (int i = 0; i < m; i++) {
                dfs(grid, i, 0);
                dfs(grid, i, n - 1);
            }

            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        res++;
                    }
                }
            }

            return res;
        }

        // 从 (i, j) 开始，将与之相邻的陆地都变成海水
        void dfs(int[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
                return;
            }
            if (grid[i][j] == 0) {
                return;
            }
            grid[i][j] = 0;
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }

    }

}


```





### 复杂度分析











# THE END