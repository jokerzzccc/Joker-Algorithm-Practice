package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 不同路径
 * </p>
 *
 * @author admin
 * @date 2023/8/10
 */
public class leetcode62 {

    public static void main(String[] args) {
        int m = 3, n = 7;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.uniquePaths(m, n));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.uniquePaths(m, n));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int uniquePaths(int m, int n) {
            // dp[i][j] 是到达 (i, j) 最多路径
            int[][] dp = new int[m + 1][n + 1];

            // 对于第一行 dp[0][j] (一直向右)，或者第一列 dp[i][0] （一直向下），
            // 由于都是在边界，所以只能为 1（路径只有一条）
            for (int i = 0; i < n; i++) dp[0][i] = 1;
            for (int i = 0; i < m; i++) dp[i][0] = 1;

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            return dp[m - 1][n - 1];
        }

    }

    /**
     * 解法二：动态规划 + 空间优化
     * 因为我们每次只需要 dp[i-1][j],dp[i][j-1]
     */
    private static class Solution02 {

        public int uniquePaths(int m, int n) {
            int[] cur = new int[n];

            Arrays.fill(cur, 1);

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] += cur[j - 1];
                }
            }

            return cur[n - 1];
        }

    }

}
