# 目录

[toc]

# leetcode-1905-统计子岛屿

- 时间：2023-06-26
- 参考链接：
  - [一文秒杀所有岛屿题目](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/yi-wen-mia-4f482/)
  - 







# 1、题目

- 题目：https://leetcode.cn/problems/count-sub-islands/
- 难度：中等



给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 **岛屿** 是由 **四个方向** （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。

如果 grid2 的一个岛屿，被 grid1 的一个岛屿 **完全** 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 **子岛屿** 。

请你返回 grid2 中 **子岛屿** 的 **数目** 。

示例 1：

![image-20230626222955549](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230626222955549.png)

```sh
输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
输出：3
解释：如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
```





提示：

- `m == grid1.length == grid2.length`
- `n == grid1[i].length == grid2[i].length`
- `1 <= m, n <= 500`
- `grid1[i][j] 和 grid2[i][j]` 都要么是 0 要么是 1 。





# 2、题解

## 题目分析

- 类比 leetcode-1254

**这道题的关键在于，如何快速判断子岛屿**？肯定可以借助 [Union Find 并查集算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-03a72/bing-cha-j-323f3/) 来判断，不过本文重点在 DFS 算法，就不展开并查集算法了。

什么情况下 `grid2` 中的一个岛屿 `B` 是 `grid1` 中的一个岛屿 `A` 的子岛？

当岛屿 `B` 中所有陆地在岛屿 `A` 中也是陆地的时候，岛屿 `B` 是岛屿 `A` 的子岛。

**反过来说，如果岛屿 `B` 中存在一片陆地，在岛屿 `A` 的对应位置是海水，那么岛屿 `B` 就不是岛屿 `A` 的子岛**。

那么，我们只要遍历 `grid2` 中的所有岛屿，把那些不可能是子岛的岛屿排除掉，剩下的就是子岛。



这道题的思路和计算「封闭岛屿」数量的思路有些类似，只不过后者排除那些靠边的岛屿，前者排除那些不可能是子岛的岛屿。

## 解法一: DFS

### 算法分析





### 代码

```java


/**
 * <p>
 * 统计子岛屿
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode1905 {

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}
        };
        int[][] grid2 = {
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0}
        };

        Solution01 solution01 = new Solution01();
        int islands01 = solution01.countSubIslands(grid1, grid2);
        System.out.println(islands01);

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int m = grid1.length, n = grid1[0].length;
            // 如果岛屿 B 中存在一片陆地，在岛屿 A 的对应位置是海水，那么岛屿 B 就不是岛屿 A 的子岛。
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                        // 这个岛屿肯定不是子岛，淹掉
                        dfs(grid2, i, j);
                    }
                }
            }

            // 现在 grid2 中剩下的岛屿都是子岛，计算岛屿数量
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid2[i][j] == 1) {
                        res++;
                        dfs(grid2, i, j);
                    }
                }
            }
            return res;
        }

        // 从 (i, j) 开始，将与之相邻的陆地都变成海水
        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == 0) {
                return;
            }

            grid[i][j] = 0;
            dfs(grid, i + 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i - 1, j);
            dfs(grid, i, j - 1);
        }

    }

}



```





### 复杂度分析











# THE END