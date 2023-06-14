package com.joker.algorithm.array;

/**
 * <p>
 * 二分查找
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode704 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        Solution01 solution01 = new Solution01();
        int search01 = solution01.search(nums, target);
        System.out.println(search01);
    }

    /**
     * 解法一：二分查找
     */
    private static class Solution01 {

        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                // left + (right - left)/2 等价于 (left + right)/2 ，但是可以防止整型溢出。
                int mid = left + (right - left) / 2;
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] < target)
                    left = mid + 1;
                else if (nums[mid] > target)
                    right = mid - 1;
            }

            return -1;
        }

    }

}
