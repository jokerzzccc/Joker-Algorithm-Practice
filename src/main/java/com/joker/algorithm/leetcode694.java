package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 不同岛屿的数量
 * </p>
 *
 * @author admin
 * @date 2023/6/26
 */
public class leetcode694 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}

        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numDistinctIslands(grid));

    }

    /**
     * 解法一：DFS
     */
    private static class Solution01 {

        public int numDistinctIslands(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // 记录所有岛屿的序列化结果
            Set<String> islands = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        // 淹掉这个岛屿，同时存储岛屿的序列化结果
                        StringBuilder sb = new StringBuilder();
                        // 初始的方向可以随便写，不影响正确性
                        dfs(grid, i, j, sb, 666);
                        islands.add(sb.toString());
                    }
                }
            }

            // 不相同的岛屿数量
            return islands.size();
        }

        /**
         * 每次使用 dfs 遍历岛屿的时候,序列化这个岛屿
         *
         * @param sb 岛屿序列化后的字符串
         * @param dir dir 记录方向（分别用 1, 2, 3, 4 代表上下左右，用 -1, -2, -3, -4 代表上下左右的撤销）
         */
        void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
                return;
            }
            // 前序遍历位置：进入 (i, j)
            grid[i][j] = 0;
            // 做选择
            sb.append(dir).append(',');

            dfs(grid, i - 1, j, sb, 1); // 上
            dfs(grid, i + 1, j, sb, 2); // 下
            dfs(grid, i, j - 1, sb, 3); // 左
            dfs(grid, i, j + 1, sb, 4); // 右

            // 后序遍历位置：离开 (i, j)
            // 撤销选择
            sb.append(-dir).append(',');
        }

    }

}
