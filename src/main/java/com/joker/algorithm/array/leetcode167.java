package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 两数之和 II - 输入有序数组
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode167 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Solution01 solution01 = new Solution01();
        int[] ints = solution01.twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);

    }

    /**
     * 解法一：双指针
     * 只要数组有序，就应该想到双指针技巧
     */
    private static class Solution01 {

        public int[] twoSum(int[] numbers, int target) {
            // 一左一右两个指针相向而行
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    // 题目要求的索引是从 1 开始的
                    return new int[]{left + 1, right + 1};
                } else if (sum < target) {
                    // 因为数组非递减
                    left++;
                } else if (sum > target) {
                    right--;
                }
            }

            return new int[]{-1, -1};
        }

    }

}