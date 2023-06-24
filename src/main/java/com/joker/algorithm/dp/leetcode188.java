package com.joker.algorithm.dp;

/**
 * <p>
 * 买卖股票的最佳时机 IV
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode188 {

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(k, prices);
        System.out.println(maxProfit01);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int maxProfit(int maxK, int[] prices) {
            int n = prices.length;
            if (n <= 0) {
                return 0;
            }
            // 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 `k` 应该不超过 `n/2`，
            // 如果超过，就没有约束作用了，相当于 `k` 没有限制的情况
            if (maxK > n / 2) {
                // 复用之前交易次数 k 没有限制(leetcode122)的情况
                return maxProfit_k_inf(prices);
            }

            // base case：
            // dp[-1][...][0] = dp[...][0][0] = 0
            // dp[-1][...][1] = dp[...][0][1] = -infinity
            int[][][] dp = new int[n][maxK + 1][2];
            // k = 0 时的 base case
            for (int i = 0; i < n; i++) {
                dp[i][0][0] = 0;
                dp[i][0][1] = Integer.MIN_VALUE;
            }

            for (int i = 0; i < n; i++) {
                for (int k = maxK; k > 0; k--) {
                    if (i - 1 == -1) {
                        // 处理 i = -1 时的 base case
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }
                    // 今天没有持有股票，max(昨天就没有-今天保持不动，昨天就持有-今天卖出)
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    // 今天持有股票，max(昨天就持有-今天保持不动，昨天没有-今天买进)
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }

            return dp[n - 1][maxK][0];
        }

        /**
         * leetcode122 (k 无限制)
         */
        public int maxProfit_k_inf(int[] prices) {
            int n = prices.length;
            // base case
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
            }

            return dp_i_0;
        }

    }

}
