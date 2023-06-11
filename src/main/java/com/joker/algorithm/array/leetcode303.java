package com.joker.algorithm.array;

/**
 * <p>
 * 区域和检索 - 数组不可变
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode303 {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        int left = 2;
        int right = 5;

        NumArray numArray = new NumArray(nums);
        int sumRange = numArray.sumRange(left, right);
        System.out.println(sumRange);

    }

    /**
     * 解法一：一维数组的前缀和
     */
    private static class NumArray {

        /**
         * 前缀和数组：preSum[i] 表示 数组前 i 个元素的数组和
         */
        private int[] preSum;

        public NumArray(int[] nums) {
            // preSum[0] = 0，便于计算累加和
            preSum = new int[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        /**
         * 查询闭区间 [left, right] 的累加和
         */
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }

    }

}
