package com.joker.algorithm;

/**
 * <p>
 * 递减元素使数组呈锯齿状
 * </p>
 *
 * @author admin
 * @date 2023/8/17
 */
public class leetcode1144 {

    public static void main(String[] args) {
        int[] nums = {9, 6, 1, 6, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.movesToMakeZigzag(nums));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int movesToMakeZigzag(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            // 总共有两种，偶数位大或奇数位大，取最小
            return Math.min(help(nums, 0), help(nums, 1));
        }

        private int help(int[] nums, int index) {
            int res = 0;

            // 因为具有单调性，挨着贪心取最大即可
            for (int i = index; i < nums.length; i += 2) {
                int tmpCnt = 0;

                // 取相邻元素，满足条件时的较大操作次数
                if (i - 1 >= 0) {
                    tmpCnt = Math.max(tmpCnt, nums[i] - nums[i - 1] + 1);
                }
                if (i + 1 < nums.length) {
                    tmpCnt = Math.max(tmpCnt, nums[i] - nums[i + 1] + 1);
                }

                res += tmpCnt;
            }

            return res;
        }

    }

}
