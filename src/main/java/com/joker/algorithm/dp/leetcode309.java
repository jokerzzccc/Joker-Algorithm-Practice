package com.joker.algorithm.dp;

/**
 * <p>
 * 最佳买卖股票时机含冷冻期
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode309 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices);
        System.out.println(maxProfit01);

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices);
        System.out.println(maxProfit02);

    }

    /**
     * 解法一: 动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                // base case 1
                if (i - 1 == -1) {

                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                // base case 2
                if (i - 2 == -1) {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                    // i - 2 小于 0 时根据状态转移方程推出对应 base case
                    dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                    //   dp[i][1]
                    // = max(dp[i-1][1], dp[-1][0] - prices[i])
                    // = max(dp[i-1][1], 0 - prices[i])
                    // = max(dp[i-1][1], -prices[i])
                    continue;
                }

                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }

            return dp[n - 1][0];
        }

    }

    /**
     * 解法二: 动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            // 代表 dp[i-2][0]
            int dp_pre_0 = 0;
            for (int i = 0; i < n; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
                dp_pre_0 = temp;
            }

            return dp_i_0;
        }

    }

}
