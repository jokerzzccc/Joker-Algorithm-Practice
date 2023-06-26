package com.joker.algorithm;

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
