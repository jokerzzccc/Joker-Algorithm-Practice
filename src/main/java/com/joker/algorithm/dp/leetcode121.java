package com.joker.algorithm.dp;

/**
 * <p>
 * 买卖股票的最佳时机
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode121 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices);
        System.out.println(maxProfit01);

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices);
        System.out.println(maxProfit02);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                // base case
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                // 今天没有持有股票，max(昨天就没有-今天保持不动，昨天就持有-今天卖出)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                // 今天持有股票，max(昨天就持有-今天保持不动，昨天没有-今天买进)
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }

            return dp[n - 1][0];
        }

    }

    /**
     * 解法二：动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                // dp[i][1] = max(dp[i-1][1], -prices[i])
                dp_i_1 = Math.max(dp_i_1, -prices[i]);
            }

            return dp_i_0;
        }

    }

}
