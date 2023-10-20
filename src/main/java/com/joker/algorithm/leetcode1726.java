package com.joker.algorithm;

import java.util.HashMap;

/**
 * 同积元组
 *
 * @author jokerzzccc
 * @date 2023/10/19
 */
public class leetcode1726 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 10};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.tupleSameProduct(nums));
    }

    /**
     * 解法一：组合 + 哈希表
     */
    private static class Solution01 {

        public int tupleSameProduct(int[] nums) {
            int length = nums.length;
            if (length <= 3) {
                return 0;
            }
            HashMap<Integer, Integer> cnt = new HashMap<>();

            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    int key = nums[i] * nums[j];
                    cnt.merge(key, 1, Integer::sum);
                }
            }

            int ans = 0;
            // 组合数
            for (Integer count : cnt.values()) {
                ans += count * (count - 1) / 2;
            }

            return ans * 8;
        }

    }

}
