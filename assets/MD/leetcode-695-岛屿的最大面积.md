# 目录

[toc]

# leetcode-695-岛屿的最大面积

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 







# 1、题目

- 题目：https://leetcode.cn/problems/max-area-of-island/
- 难度：中等



给你一个大小为 m x n 的二进制矩阵 grid 。

**岛屿** 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 **水平或者竖直的四个方向上** 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

岛屿的面积是岛上值为 1 的单元格的数目。

计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。

示例 1：

![image-20230626222505214](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230626222505214.png)

```sh
输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
输出：6
解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
```





**提示：**

+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 50`
+ `grid[i][j]` 为 `0` 或 `1`



# 2、题解

## 题目分析

- 类比 leetcode-1254

**这题的大体思路和之前完全一样，只不过 `dfs` 函数淹没岛屿的同时，还应该想办法记录这个岛屿的面积**。

我们可以给 `dfs` 函数设置返回值，记录每次淹没的陆地的个数，直接看解法吧：

## 解法一: DFS

### 算法分析





### 代码

```java

/**
 * <p>
 * 岛屿的最大面积
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode695 {

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxAreaOfIsland(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int maxAreaOfIsland(int[][] grid) {
            // 记录岛屿的最大面积
            int res = 0;
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        // 淹没岛屿，并更新最大岛屿面积
                        res = Math.max(res, dfs(grid, i, j));
                    }
                }
            }
            return res;
        }

        // 淹没与 (i, j) 相邻的陆地，并返回淹没的陆地面积
        int dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                // 超出索引边界
                return 0;
            }
            if (grid[i][j] == 0) {
                // 已经是海水了
                return 0;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = 0;

            return dfs(grid, i + 1, j)
                    + dfs(grid, i, j + 1)
                    + dfs(grid, i - 1, j)
                    + dfs(grid, i, j - 1) + 1;
        }

    }

}


```





### 复杂度分析











# THE END