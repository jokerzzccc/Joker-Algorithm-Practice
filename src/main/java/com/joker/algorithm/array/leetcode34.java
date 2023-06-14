package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 在排序数组中查找元素的第一个和最后一个位置
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode34 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        Solution01 solution01 = new Solution01();
        int[] range01 = solution01.searchRange(nums, target);
        Arrays.stream(range01).forEach(System.out::println);

    }

    /**
     * 解法一：二分查找（左边界+右边界）
     */
    private static class Solution01 {

        public int[] searchRange(int[] nums, int target) {

            int leftBound = findLeft(nums, target);
            int rightBound = findRight(nums, target);

            return new int[]{leftBound, rightBound};

        }

        /**
         * 查询左边界
         */
        private int findLeft(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }

            // 如果索引越界，说明数组中无目标元素，返回 -1
            if (left < 0 || left >= nums.length) {
                return -1;
            }
            // 判断一下 nums[left] 是不是 target
            return nums[left] == target ? left : -1;
        }

        /**
         * 查询右边界
         */
        private int findRight(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }

            // right - 1 索引越界的话 target 肯定不存在
            if (right - 1 < 0 || right - 1 >= nums.length) {
                return -1;
            }
            // 判断一下 nums[right - 1] 是不是 target
            return nums[right - 1] == target ? (right - 1) : -1;
        }

    }

}
