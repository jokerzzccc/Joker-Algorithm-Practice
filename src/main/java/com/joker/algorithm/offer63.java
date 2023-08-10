package com.joker.algorithm;

/**
 * <p>
 * 股票的最大利润
 * </p>
 *
 * @author admin
 * @date 2023/8/10
 */
public class offer63 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxProfit(prices));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maxProfit(prices));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 1) {
                return 0;
            }
            int n = prices.length;
            // dp[i] 代表以 prices[i - 1] 为结尾的子数组的最大利润
            int[] dp = new int[n + 1];
            // nums[0 ... i - 1] 的最小值
            int tmpMin = prices[0];

            for (int i = 1; i <= n; i++) {
                tmpMin = Math.min(tmpMin, prices[i - 1]);
                dp[i] = Math.max(dp[i - 1], prices[i - 1] - tmpMin);
            }

            return dp[n];
        }

    }

    /**
     * 解法二：动态规划(空间优化)
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 1) {
                return 0;
            }

            // nums[0 ... i - 1] 的最小值
            int tmpMin = Integer.MAX_VALUE;
            int maxProfit = 0;

            for (int price : prices) {
                tmpMin = Math.min(tmpMin, price);
                maxProfit = Math.max(maxProfit, price - tmpMin);
            }

            return maxProfit;
        }

    }

}
