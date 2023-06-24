package com.joker.algorithm.dp;

/**
 * <p>
 * 买卖股票的最佳时机 III
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode123 {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

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
            int maxK = 2;
            int[][][] dp = new int[n][maxK + 1][2];
            for (int i = 0; i < n; i++) {
                // 穷举状态 k
                for (int k = maxK; k > 0; k--) {
                    // base case
                    if (i - 1 == -1) {
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

    }

    /**
     * 解法二: 动态规划 + 空间优化
     * 这里 k 取值范围比较小，所以也可以不用 for 循环，直接把 k = 1 和 2 的情况全部列举出来也可以。
     * 状态转移方程：
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case
            int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
            int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
            for (int price : prices) {
                dp_i20 = Math.max(dp_i20, dp_i21 + price);
                dp_i21 = Math.max(dp_i21, dp_i10 - price);
                dp_i10 = Math.max(dp_i10, dp_i11 + price);
                dp_i11 = Math.max(dp_i11, -price);
            }

            return dp_i20;
        }

    }

}