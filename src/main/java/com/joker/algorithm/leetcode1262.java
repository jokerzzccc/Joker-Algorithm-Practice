package com.joker.algorithm;

/**
 * <p>
 * 可被三整除的最大和
 * </p>
 *
 * @author admin
 * @date 2023/7/20
 */
public class leetcode1262 {

    public static void main(String[] args) {
        int[] nums = {3, 6, 5, 1, 8};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxSumDivThree(nums));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maxSumDivThree(nums));

    }

    /**
     * 解法一：动态规划（一维数组空间优化）
     */
    private static class Solution01 {

        public int maxSumDivThree(int[] nums) {
            // dp[j] 前 i 个数选取了若干数，并且它们的和 mod 3  = j(0=<j<3) 时，这些数的和的最大值。
            // dp[0] 表示，选取了的数能整除3的最大和；dp[1] 表示 选取了的数中能除3余1的最大和
            // 以下是空间优化
            int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
            for (int num : nums) {
                int[] cur = new int[3];
                System.arraycopy(dp, 0, cur, 0, 3);
                for (int i = 0; i < 3; i++) {
                    // 状态转移：不选或选当前数
                    cur[(i + num % 3) % 3] = Math.max(cur[(i + num % 3) % 3], dp[i] + num);
                }
                dp = cur;
            }

            return dp[0];
        }

    }

    /**
     * 解法二：动态规划（二维数组空间）
     */
    private static class Solution02 {

        public int maxSumDivThree(int[] nums) {
            int n = nums.length;
            // dp[i][j] 表示 前 i 个数选取了若干数，并且它们的和 mod 3  = j( 0 =< j <3) 时，这些数的和的最大值。
            // 维护 余数分别为 0， 1， 2 的三个最大和
            // base case, i=0 时，表示，一个数都没选，取模也是0，
            int[][] dp = new int[n + 1][3];

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < 3; j++) {
                    // 不选当前数 nums[i - 1]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);

                    int v = (dp[i - 1][j] + nums[i - 1]) % 3;
                    // 选当前数 nums[i - 1]
                    dp[i][v] = Math.max(dp[i][v], dp[i - 1][j] + nums[i - 1]);
                }
            }

            return dp[n][0];
        }

    }

}
