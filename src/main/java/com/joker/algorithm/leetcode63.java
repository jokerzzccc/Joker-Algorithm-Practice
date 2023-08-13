package com.joker.algorithm;

/**
 * <p>
 * 不同路径 II
 * </p>
 *
 * @author admin
 * @date 2023/8/13
 */
public class leetcode63 {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.uniquePathsWithObstacles(obstacleGrid));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.uniquePathsWithObstacles(obstacleGrid));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;

            // dp[i][j] 表示，(0,0) 到 (i,j) 的路径路数
            int[][] dp = new int[m][n];
            // 初始化第一列，只要碰到1，后面都无法到达（只能通过向下走）
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    break;
                }
                dp[i][0] = 1;
            }

            // 初始化第一行，只要碰到1，后面都无法到达（只能通过向右走）
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[0][j] == 1) {
                    break;
                }
                dp[0][j] = 1;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // 自动跳过路障(有路障的路径数都是0)
                    if (obstacleGrid[i][j] != 1) {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }

            return dp[m - 1][n - 1];
        }

    }

    /**
     * 解法二：动态规划 + 空间优化（滚动数组）
     */
    private static class Solution02 {

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[] dp = new int[n];

            dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[j] = 0;
                        continue;
                    }
                    if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                        dp[j] += dp[j - 1];
                    }
                }
            }

            return dp[n - 1];
        }

    }

}
