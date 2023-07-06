package com.joker.algorithm;

/**
 * <p>
 * 一维数组的动态和
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode1480 {

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int[] runningSum(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = dp[i-1] + nums[i];
            }

            return dp;
        }

    }

}
