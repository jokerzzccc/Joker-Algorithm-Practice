package com.joker.algorithm;

/**
 * <p>
 * 统计封闭岛屿的数目
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode1254 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.closedIsland(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int j = 0; j < n; j++) {
                // 把靠上边的岛屿淹掉
                dfs(grid, 0, j);
                // 把靠下边的岛屿淹掉
                dfs(grid, m - 1, j);
            }
            for (int i = 0; i < m; i++) {
                // 把靠左边的岛屿淹掉
                dfs(grid, i, 0);
                // 把靠右边的岛屿淹掉
                dfs(grid, i, n - 1);
            }
            // 遍历 grid，剩下的岛屿都是封闭岛屿
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        // 每发现一个岛屿，岛屿数量加一
                        res++;
                        // 然后使用 DFS 将岛屿淹了,即，1相邻的全部变为0
                        dfs(grid, i, j);
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
            if (grid[i][j] == 1) {
                // 已经是海水了
                return;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = 1;
            // 淹没上下左右的陆地
            dfs(grid, i + 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i - 1, j);
            dfs(grid, i, j - 1);
        }

    }

}
