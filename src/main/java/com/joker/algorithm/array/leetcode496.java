package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * <p>
 * 下一个更大元素 I
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode496 {

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};

        Solution01 solution01 = new Solution01();
        int[] greaterElement01 = solution01.nextGreaterElement(nums1, nums2);
        Arrays.stream(greaterElement01).forEach(System.out::println);

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 记录 nums2 中每个元素的下一个更大元素
            int[] greater = nextGreaterElement(nums2);
            // 转化成映射：元素 x -> x 的下一个最大元素
            HashMap<Integer, Integer> greaterMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums2.length; i++) {
                greaterMap.put(nums2[i], greater[i]);
            }
            // nums1 是 nums2 的子集，所以根据 greaterMap 可以得到结果
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i] = greaterMap.getOrDefault(nums1[i], -1);
            }
            return res;
        }

        /**
         * 返回 nums 所有的下一个最大元素的数组 res
         */
        public int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                    stack.pop();
                }

                res[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(nums[i]);
            }

            return res;
        }

    }

}
