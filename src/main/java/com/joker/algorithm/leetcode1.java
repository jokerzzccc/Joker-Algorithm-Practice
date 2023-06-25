package com.joker.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 两数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;

        Solution01 solution01 = new Solution01();
        int[] twoSum = solution01.twoSum(nums, target);
        System.out.println(Arrays.toString(twoSum));

    }

    /**
     * 解法一：缓存
     */
    private static class Solution01 {

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }

            return new int[]{};
        }

    }

}
