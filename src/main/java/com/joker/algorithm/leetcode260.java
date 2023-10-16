package com.joker.algorithm;

import java.util.Arrays;

/**
 * 只出现一次的数字 III
 *
 * @author jokerzzccc
 * @date 2023/10/16 1:21
 */
public class leetcode260 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.singleNumber(nums)));

    }

    /**
     * 解法一：位运算
     */
    private static class Solution01 {

        public int[] singleNumber(int[] nums) {
            int xorsum = 0;
            for (int num : nums) {
                xorsum ^= num;
            }

            // 防止溢出
            // xorsum & (-xorsum) : 返回 最低位的 1 在哪一位。
            int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
            int type1 = 0, type2 = 0;
            for (int num : nums) {
                if ((num & lsb) != 0) {
                    type1 ^= num;
                } else {
                    type2 ^= num;
                }
            }
            return new int[]{type1, type2};
        }

    }

}
