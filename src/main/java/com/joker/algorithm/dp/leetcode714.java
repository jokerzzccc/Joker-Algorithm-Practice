package com.joker.algorithm.dp;

/**
 * <p>
 * 买卖股票的最佳时机含手续费
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode714 {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices, fee);
        System.out.println(maxProfit01);

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices, fee);
        System.out.println(maxProfit02);

    }

    /**
     * 解法一: 动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    //   dp[i][1]
                    // = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
                    // = max(dp[-1][1], dp[-1][0] - prices[i] - fee)
                    // = max(-inf, 0 - prices[i] - fee)
                    // = -prices[i] - fee
                    dp[i][1] = -prices[i] - fee;
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            }

            return dp[n - 1][0];

        }

    }

    /**
     * 解法二: 动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int tmp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, tmp - prices[i] - fee);
            }

            return dp_i_0;
        }

    }

}
