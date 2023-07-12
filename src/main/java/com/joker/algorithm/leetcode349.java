package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 两个数组的交集
 * </p>
 *
 * @author admin
 * @date 2023/7/12
 */
public class leetcode349 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1}, nums2 = {2, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.intersection(nums1, nums2)));
    }

    /**
     * 解法一：排序 + 双指针
     */
    private static class Solution01 {

        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int len1 = nums1.length;
            int len2 = nums2.length;
            int[] intersections = new int[len1 + len2];
            int index = 0, index1 = 0, index2 = 0;

            while (index1 < len1 && index2 < len2) {
                int tmp1 = nums1[index1];
                int tmp2 = nums2[index2];

                if (tmp1 == tmp2) {
                    // 保证加入元素的唯一性
                    if (index == 0 || tmp1 != intersections[index - 1]) {
                        intersections[index++] = tmp1;
                    }
                    index1++;
                    index2++;
                } else if (tmp1 < tmp2) {
                    index1++;
                } else if (tmp1 > tmp2) {
                    index2++;
                }

            }

            return Arrays.copyOfRange(intersections, 0, index);
        }

    }

}
