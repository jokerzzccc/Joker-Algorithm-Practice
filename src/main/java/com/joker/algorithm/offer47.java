package com.joker.algorithm;

/**
 * <p>
 * 礼物的最大价值
 * </p>
 *
 * @author admin
 * @date 2023/8/4
 */
public class offer47 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxValue(grid));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maxValue(grid));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxValue(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
            int[][] dp = new int[m + 1][n + 1];

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 状态转移
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }

            return dp[m][n];
        }

    }

    /**
     * 解法二：动态规划(一位数组空间优化)
     */
    private static class Solution02 {

        public int maxValue(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
            // 考虑只与上一行和前一列相关，因此优化为一维数组
            int[] dp = new int[n + 1];

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 状态转移
                    dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
                }
            }

            return dp[n];
        }

    }

}
