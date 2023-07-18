package com.joker.algorithm;

/**
 * <p>
 * 完全平方数
 * </p>
 *
 * @author admin
 * @date 2023/7/18
 */
public class leetcode279 {

    public static void main(String[] args) {
        int n = 12;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numSquares(n));

    }

    /**
     * 解法一：动态规划
     * 完全背包问题
     */
    private static class Solution01 {

        public int numSquares(int n) {
            // dp[i] 表示，最少需要多少个平方数来表示整数 i
            // 边界条件：dp[0] = 0
            int[] dp = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                // 每次都先取最大，即最坏情况 +1
                dp[i] = i;
                for (int j = 1; j * j <= i; j++) {
                    // 状态转移方程, j*j 本身就是一种组合，所以需要 + 1
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }

            return dp[n];
        }

    }

}
