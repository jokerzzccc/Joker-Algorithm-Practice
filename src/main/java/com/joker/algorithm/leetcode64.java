package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 最小路径和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/4
 */
public class leetcode64 {
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        Solution01 solution01 = new Solution01();
        int minPathSum01 = solution01.minPathSum(grid);
        System.out.println(minPathSum01);

        Solution02 solution02 = new Solution02();
        int minPathSum02 = solution02.minPathSum(grid);
        System.out.println(minPathSum02);

    }

    /**
     * 动态规划+备忘录
     * 自顶向下
     */
    private static class Solution01 {

        /**
         * 备忘录
         */
        int[][] memo;

        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(grid, m - 1, n - 1);
        }


        /**
         * dp 函数的定义：从左上角位置（0,0）走到位置（i,j）的最小路径和为 dp(int[][] grid, int i, int j)
         */
        int dp(int[][] grid, int i, int j) {

            if (i == 0 && j == 0) {
                return grid[0][0];
            }

            if (i < 0 || j < 0) {
                return Integer.MAX_VALUE;
            }
            // 避免重复计算
            if (memo[i][j] != -1) {
                return memo[i][j];
            }
            memo[i][j] = Math.min(
                    dp(grid, i - 1, j),
                    dp(grid, i, j - 1)
            ) + grid[i][j];

            return memo[i][j];
        }
    }

    /**
     * 解法二：自底向上
     */
    private static class Solution02 {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];


            // base case
            dp[0][0] = grid[0][0];

            for (int i = 1; i < m; i++)
                dp[i][0] = dp[i - 1][0] + grid[i][0];

            for (int j = 1; j < n; j++)
                dp[0][j] = dp[0][j - 1] + grid[0][j];

            //
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            return dp[m - 1][n - 1];
        }
    }

}
