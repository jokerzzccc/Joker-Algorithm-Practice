package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 排序数组
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode912 {

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};

        Solution01 solution01 = new Solution01();
        int[] sortArray = solution01.sortArray(nums);
        Arrays.stream(sortArray).forEach(System.out::print);
        System.out.println("\n");

    }

    /**
     * 解法一：归并排序
     */
    private static class Solution01 {

        /**
         * 临时辅助数组
         */
        int[] tmp;

        public int[] sortArray(int[] nums) {
            tmp = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 定义mergeSort(nums,l,r) 函数表示对 nums 数组里[l, r]的部分进行排序，整个函数流程如下：
         */
        private void mergeSort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            int i = left, j = mid + 1;
            int cnt = 0;
            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
                    tmp[cnt++] = nums[i++];
                } else {
                    tmp[cnt++] = nums[j++];
                }
            }
            while (i <= mid) {
                tmp[cnt++] = nums[i++];
            }
            while (j <= right) {
                tmp[cnt++] = nums[j++];
            }
            for (int k = 0; k < right - left + 1; ++k) {
                nums[k + left] = tmp[k];
            }
        }

    }

}
