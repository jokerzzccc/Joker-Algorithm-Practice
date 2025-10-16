package com.joker.algorithm;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 *
 * @author jokerzzccc
 * @date 2024/3/20
 */
public class leetcode238 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = new Solution01().productExceptSelf(nums);
        Arrays.stream(result).forEach(System.out::println);
    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int[] productExceptSelf(int[] nums) {
            int[] result = new int[nums.length];
            // 从前往后计算 i 前面的乘积
            int product = 1;
            for (int i = 0; i < nums.length; i++) {
                result[i] = product;
                product *= nums[i];
            }
            // 从后往前计算 i 后面的乘积
            product = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                result[i] *= product;
                product *= nums[i];
            }

            return result;
        }

    }

}
