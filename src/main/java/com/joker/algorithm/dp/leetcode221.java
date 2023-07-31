package com.joker.algorithm.dp;

/**
 * <p>
 * 最大正方形
 * </p>
 *
 * @author admin
 * @date 2023/7/31
 */
public class leetcode221 {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maximalSquare(matrix));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maximalSquare(matrix));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maximalSquare(char[][] matrix) {
            // base condition
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            // dp[i + 1][j + 1] 表示 「以第 i 行、第 j 列为右下角的正方形的最大边长」
            // 相当于已经预处理新增第一行、第一列均为0
            int[][] dp = new int[m + 1][n + 1];
            int maxSide = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 状态转移：
                    // 若某格子值为 1，则以此为右下角的正方形的、最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。
                    if (matrix[i][j] == '1') {
                        dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                        maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
                    }
                }
            }

            return maxSide * maxSide;
        }

    }

    /**
     * 解法二：动态规划 + 空间优化（一维数组）
     */
    private static class Solution02 {

        public int maximalSquare(char[][] matrix) {
            // base condition
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            // dp[i + 1][j + 1] 表示 「以第 i 行、第 j 列为右下角的正方形的最大边长」
            // 相当于已经预处理新增第一行、第一列均为0
            int[] dp = new int[n + 1];
            int maxSide = 0;
            // 西北角、左上角
            // 增加 northwest 西北角, 解决"左上角"的问题
            int northwest = 0;

            for (int i = 0; i < m; i++) {
                // 遍历每行时，还原回辅助的原值0
                northwest = 0;
                for (int j = 0; j < n; j++) {
                    int nextNorthwest = dp[j + 1];
                    // 状态转移：
                    // 若某格子值为 1，则以此为右下角的正方形的、最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。
                    if (matrix[i][j] == '1') {
                        dp[j + 1] = Math.min(Math.min(dp[j], dp[j + 1]), northwest) + 1;
                        maxSide = Math.max(maxSide, dp[j + 1]);
                    } else {
                        dp[j + 1] = 0;
                    }
                    northwest = nextNorthwest;
                }
            }

            return maxSide * maxSide;
        }

    }

}
