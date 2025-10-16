package com.joker.algorithm.leetcodeplus.arrays2string;

import java.util.Arrays;

/**
 * 摆动排序
 *
 * @author jokerzzccc
 * @date 2024/11/5
 */
public class leetcode280 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};

        Solution01 solution01 = new Solution01();
        solution01.wiggleSort(nums);
        Arrays.stream(nums).forEach(System.out::println);

    }

    /**
     * 解法一：贪心
     */
    public static class Solution01 {

        public void wiggleSort(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                // 贪婪地检查每个索引 i。
                // 如果 i 是偶数，nums[i] 应该小于或等于 nums[i + 1]。如果它更大，即 nums[i] > nums[i + 1]，我们交换 nums[i] 和 nums[i + 1]。
                // 同样，如果 i 是奇数，nums[i] 应该大于或等于 nums[i + 1]。如果它更小，即 nums[i] < nums[i + 1]，我们交换 nums[i] 和 nums[i + 1]。
                if ((i % 2 == 0 && nums[i] > nums[i + 1])
                        || (i % 2 == 1 && nums[i] < nums[i + 1])) {
                    swap(nums, i, i + 1);
                }
            }

        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

}
