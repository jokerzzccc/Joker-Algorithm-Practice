package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>
 * 下一个更大元素 II
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode503 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3};

        Solution01 solution01 = new Solution01();
        int[] greaterElements01 = solution01.nextGreaterElements(nums);
        System.out.println(Arrays.toString(greaterElements01));

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();
            // 数组长度加倍模拟环形数组
            for (int i = 2 * n - 1; i >= 0; i--) {
                // 索引 i 要求模，其他的和模板一样
                while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                    stack.pop();
                }
                res[i % n] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(nums[i % n]);
            }

            return res;
        }

    }

}
