package com.joker.algorithm.dp;

/**
 * <p>
 * 爬楼梯
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode70 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.climbStairs(n));
    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int climbStairs(int n) {
            // dp[i] 表示，i 阶梯，有多少种爬法
            int[] dp = new int[n + 1];
            // base case
            dp[0] = 1;
            dp[1] = 1;

            // 状态转移，自底向上
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            return dp[n];
        }

    }

}
