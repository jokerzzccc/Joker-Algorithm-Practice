package com.joker.algorithm;

/**
 * <p>
 * 缺失的第一个正数
 * </p>
 *
 * @author admin
 * @date 2023/8/5
 */
public class leetcode41 {

    public static void main(String[] args) {
        int[] nums = {7, 8, 9, 11, 12};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.firstMissingPositive(nums));

    }

    /**
     * 解法一：原地置换
     * 类比 offer03
     */
    private static class Solution01 {

        public int firstMissingPositive(int[] nums) {
            int len = nums.length;

            // 对于 nums[i] 中的数，其属于[1...len] 的数，放到对应下标 nums[i] - 1 上
            for (int i = 0; i < len; i++) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                // 大于 len 的不用管，因为要找的是未出现的最小正整数
                while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                }
            }

            // 要找的数一定在 [1, len + 1] 左闭右闭这个区间里.
            // 第 1 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数
            for (int i = 0; i < len; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            // 都正确则返回数组长度 + 1
            return len + 1;
        }

        /**
         * 原地交换 nums 两个下标的元素
         */
        private void swap(int[] nums, int index1, int index2) {
            int tmp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = tmp;
        }

    }

}
