package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>
 * 每日温度
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode739 {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        Solution01 solution01 = new Solution01();
        int[] result = solution01.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] dailyTemperatures(int[] temperatures) {
            int[] greater = nextGreaterElement(temperatures);
            int[] res = new int[greater.length];
            for (int i = 0; i < greater.length; i++) {
                res[i] = greater[i] == 0 ? 0 : greater[i] - i;
            }

            return res;
        }

        /**
         * 返回 nums 所有的下一个最大元素的 下标 数组 res
         */
        public int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                    stack.pop();
                }

                res[i] = stack.isEmpty() ? 0 : stack.peek();
                stack.push(i);
            }

            return res;
        }

    }

}
