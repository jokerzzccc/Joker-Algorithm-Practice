package com.joker.algorithm.dp;

/**
 * <p>
 * 整数拆分
 * </p>
 *
 * @author admin
 * @date 2023/7/26
 */
public class leetcode343 {

    public static void main(String[] args) {
        int n = 10;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.integerBreak(n));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.integerBreak(n));

    }

    /**
     * 解法一：数学公式推导
     */
    private static class Solution01 {

        public int integerBreak(int n) {
            if (n <= 3) {
                return n - 1;
            }
            int a = n / 3, b = n % 3;
            if (b == 0) {
                return (int) Math.pow(3, a);
            }
            if (b == 1) {
                return (int) Math.pow(3, a - 1) * 4;
            }

            return (int) Math.pow(3, a) * 2;

        }

    }

    /**
     * 解法二：动态规划
     */
    private static class Solution02 {

        public int integerBreak(int n) {
            if (n <= 3) {
                return n - 1;
            }

            // dp[i] 表示，表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
            // dp[0] = dp[1] = 0
            int[] dp = new int[n + 1];
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
            }

            return dp[n];
        }

    }

}
