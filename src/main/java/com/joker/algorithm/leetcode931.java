package com.joker.algorithm;

import java.util.Arrays;

/**
 * 下降路径最小和
 *
 * @author jokerzzccc
 * @date 2023/11/7
 */
public class leetcode931 {

    public static void main(String[] args) {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minFallingPathSum(matrix));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.minFallingPathSum(matrix));
    }

    /**
     * 解法一：递归（从下往上）
     */
    private static class Solution01 {

        private int[][] matrix;
        /**
         * 备忘录
         */
        private int[][] memo;

        public int minFallingPathSum(int[][] matrix) {
            this.matrix = matrix;
            int n = matrix.length;
            int ans = Integer.MAX_VALUE;
            memo = new int[n][n];

            // 初始化备忘录
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], Integer.MIN_VALUE);
            }

            //
            for (int col = 0; col < n; col++) {
                ans = Math.min(ans, dfs(n - 1, col));
            }

            return ans;
        }

        /**
         * 递归
         */
        private int dfs(int row, int col) {
            // 不合法条件：出界
            if (col < 0 || col >= matrix.length) {
                return Integer.MAX_VALUE;
            }
            // 达到第一行：达到边界
            if (row == 0) {
                return matrix[row][col];
            }
            // 备忘录优化
            if (memo[row][col] != Integer.MIN_VALUE) {
                return memo[row][col];
            }

            // 三个方向取最小
            return memo[row][col] = Math.min(
                    Math.min(dfs(row - 1, col - 1), dfs(row - 1, col)),
                    dfs(row - 1, col + 1)
            ) + matrix[row][col];
        }

    }

    /**
     * 解法二：动态规划（自底向上）
     */
    private static class Solution02 {

        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            // dp[row][col + 1]: 表示从 matrix[row][col] 出发，向上走到第一排的路径最小和.
            // 此时 dp[row][0] 和 dp[row][n + 1] 就对应递归边界了
            int[][] dp = new int[n][n + 2];
            System.arraycopy(matrix[0], 0, dp[0], 1, n);
            for (int row = 1; row < n; row++) {
                // 初始化边界值
                dp[row - 1][0] = dp[row - 1][n + 1] = Integer.MAX_VALUE;
                for (int col = 0; col < n; col++) {
                    dp[row][col + 1] = Math.min(
                            Math.min(dp[row - 1][col], dp[row - 1][col + 1]),
                            dp[row - 1][col + 2]
                    ) + matrix[row][col];
                }
            }

            // 取最小答案：最后一行的和最小下降路径长度的最小值
            int ans = Integer.MAX_VALUE;
            for (int col = 1; col <= n; col++) {
                ans = Math.min(ans, dp[n - 1][col]);
            }

            return ans;
        }

    }

}
