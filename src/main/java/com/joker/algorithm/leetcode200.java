package com.joker.algorithm;

/**
 * <p>
 * 岛屿数量
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode200 {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        Solution01 solution01 = new Solution01();
        int islands01 = solution01.numIslands(grid);
        System.out.println(islands01);

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int numIslands(char[][] grid) {
            int res = 0;
            int m = grid.length, n = grid[0].length;
            // 遍历 grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
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
        void dfs(char[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            // 超出索引边界
            if (i < 0 || j < 0 || i >= m || j >= n) return;
            if (grid[i][j] == '0') {
                // 已经是海水了
                return;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = '0';
            // 淹没上下左右的陆地
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }

    }

}
