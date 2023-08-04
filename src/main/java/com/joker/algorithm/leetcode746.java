package com.joker.algorithm;

/**
 * <p>
 * 使用最小花费爬楼梯
 * </p>
 *
 * @author admin
 * @date 2023/8/2
 */
public class leetcode746 {

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minCostClimbingStairs(cost));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            // dp[i] 表示达到下标 i 的最小花费
            // 由于可以选择下标 0 或 1 作为初始阶梯，因此有 dp[0]=dp[1]=0
            int[] dp = new int[n + 1];

            for (int i = 2; i <= n; i++) {
                // 状态转移
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }

            return dp[n];
        }

    }

}
