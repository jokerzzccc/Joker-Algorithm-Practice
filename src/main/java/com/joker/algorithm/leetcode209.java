package com.joker.algorithm;

/**
 * <p>
 * 长度最小的子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/14
 */
public class leetcode209 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minSubArrayLen(target, nums));

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int start = 0, end = 0;
            int sum = 0;

            while (end < n) {
                sum += nums[end];
                while (sum >= target) {
                    ans = Math.min(ans, end - start + 1);
                    sum -= nums[start];
                    start++;
                }
                end++;
            }

            return ans == Integer.MAX_VALUE ? 0 : ans;
        }

    }

}
