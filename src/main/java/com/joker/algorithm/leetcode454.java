package com.joker.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 四数相加 II
 * </p>
 *
 * @author admin
 * @date 2023/7/13
 */
public class leetcode454 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2}, nums2 = {-2, -1}, nums3 = {-1, 2}, nums4 = {0, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.fourSumCount(nums1, nums2, nums3, nums4));

    }

    /**
     * 解法一：分组 + 哈希表
     */
    private static class Solution01 {

        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> countAB = new HashMap<>();
            for (int a : nums1) {
                for (int b : nums2) {
                    int cur = a + b;
                    countAB.put(cur, countAB.getOrDefault(cur, 0) + 1);
                }
            }

            int target = 0;
            int ret = 0;
            for (int c : nums3) {
                for (int d : nums4) {
                    if (countAB.containsKey(target - (c + d))) {
                        ret += countAB.get(target - (c + d));
                    }
                }
            }

            return ret;
        }

    }

}
