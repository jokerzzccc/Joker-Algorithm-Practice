package com.joker.algorithm.array;

/**
 * <p>
 * 搜索插入位置
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode35 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.searchInsert(nums, target));

    }

    /**
     * 解法一：二分法，
     * 问题可转为，在一个有序数组中找第一个大于等于 target 的下标
     */
    private static class Solution01 {

        public int searchInsert(int[] nums, int target) {
            int n = nums.length;
            int left = 0, right = n - 1;
            int res = n;

            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (nums[middle] >= target) {
                    res = middle;
                    right = middle - 1;
                } else if (nums[middle] < target) {
                    left = middle + 1;
                }
            }

            return res;
        }

    }

}
