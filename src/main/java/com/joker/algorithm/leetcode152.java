package com.joker.algorithm;

/**
 * <p>
 * 乘积最大子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/20
 */
public class leetcode152 {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxProduct(nums));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxProduct(int[] nums) {
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }

            // 维护两个变量：当前最大值，当前最小值（因为有负数的存在）
            int curMax = nums[0], curMin = nums[0];
            int ans = nums[0];

            for (int i = 1; i < length; i++) {
                int mx = curMax, mn = curMin;
                // 因为有负数，最大值可能变最小值，最小值可能变最大值。
                curMax = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i])); // 三个数取最大
                curMin = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i])); // 三个数取最小

                ans = Math.max(ans, curMax);
            }

            return ans;
        }

    }

}
