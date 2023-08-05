package com.joker.algorithm;

/**
 * <p>
 * 最大连续1的个数 III
 * </p>
 *
 * @author admin
 * @date 2023/8/5
 */
public class leetcode1004 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestOnes(nums, k));
    }

    /**
     * 解法一：滑动窗口
     * 题意转换。把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」
     * 转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」
     */
    private static class Solution01 {

        public int longestOnes(int[] nums, int k) {
            int n = nums.length;
            // 维护滑动窗口[left, right]的左右指针
            int left = 0, right = 0;
            // 窗口内 0 的个数
            int zeroCounts = 0;
            // 窗口长度
            int res = 0;

            while (right < n) {
                if (nums[right] == 0) {
                    zeroCounts++;
                }
                // 左指针右移，知道窗口内的0的个数 <= k
                while (zeroCounts > k) {
                    if (nums[left] == 0) {
                        zeroCounts--;
                    }
                    left++;
                }

                res = Math.max(res, right - left + 1);
                right++;
            }

            return res;
        }

    }

}
