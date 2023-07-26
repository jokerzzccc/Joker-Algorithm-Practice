package com.joker.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 两个数组的交集 II
 * </p>
 *
 * @author admin
 * @date 2023/7/26
 */
public class leetcode350 {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5}, nums2 = {9, 4, 9, 8, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.intersect(nums1, nums2)));

    }

    // 解法一：哈希表
    private static class Solution01 {

        public int[] intersect(int[] nums1, int[] nums2) {
            // 把长度小的放前面
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums1) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int[] intersection = new int[nums1.length];
            int index = 0;

            for (int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if (count > 0) {
                    intersection[index++] = num;
                    count--;
                    if (count > 0) {
                        map.put(num, count);
                    } else {
                        map.remove(num);
                    }
                }
            }

            return Arrays.copyOfRange(intersection, 0, index);

        }

    }

}
