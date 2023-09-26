package com.joker.algorithm.dp;

/**
 * <p>
 * 打家劫舍 II
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode213 {

    public static void main(String[] args) {

        int[] nums = {2, 3, 2};

        Solution01 solution01 = new Solution01();
        int ro01 = solution01.rob(nums);
        System.out.println(ro01);
    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            return Math.max(robRange(nums, 0, n - 2),
                    robRange(nums, 1, n - 1));
        }

        // 仅计算闭区间 [start,end] 的最优结果
        int robRange(int[] nums, int start, int end) {
            int n = nums.length;
            int dp_i_1 = 0, dp_i_2 = 0;
            int dp_i = 0;
            for (int i = end; i >= start; i--) {
                dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }

    }

}
