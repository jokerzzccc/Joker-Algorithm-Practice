package com.joker.algorithm;

/**
 * 乘积小于 K 的子数组
 *
 * @author jokerzzccc
 * @date 2023/10/28
 */
public class leetcode713 {

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numSubarrayProductLessThanK(nums, k));

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        public int numSubarrayProductLessThanK(int[] nums, int k) {
            // 由题意，及参数范围
            if (k <= 1) {
                return 0;
            }

            int n = nums.length;
            // 记录连续数组的组合个数
            int res = 0;
            // 乘积
            int multi = 1;
            for (int left = 0, right = 0; right < n; right++) {
                multi *= nums[right];
                while (left <= right && multi >= k) {
                    multi /= nums[left];
                    left++;
                }
                res += right - left + 1;
            }

            return res;
        }

    }

}
