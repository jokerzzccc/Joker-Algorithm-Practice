package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 有序数组的平方
 * </p>
 *
 * @author admin
 * @date 2023/7/15
 */
public class leetcode977 {

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.sortedSquares(nums)));
    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];

            // 从两头选较大的那个数，逆序放入结果数组
            for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
                if (nums[i] * nums[i] > nums[j] * nums[j]) {
                    ans[pos] = nums[i] * nums[i];
                    ++i;
                } else {
                    ans[pos] = nums[j] * nums[j];
                    --j;
                }
                --pos;
            }

            return ans;
        }

    }

}
