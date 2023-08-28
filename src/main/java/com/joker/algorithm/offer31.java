package com.joker.algorithm;

import java.util.Stack;

/**
 * <p>
 * 栈的压入、弹出序列
 * </p>
 *
 * @author admin
 * @date 2023/8/28
 */
public class offer31 {

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.validateStackSequences(pushed, popped));

    }

    /**
     * 解法一：模拟
     */
    private static class Solution01 {

        public boolean validateStackSequences(int[] pushed, int[] popped) {
            // 辅助栈
            Stack<Integer> stack = new Stack<>();
            // 弹出序列的索引 i
            int i = 0;
            for (int num : pushed) {
                // num 入栈
                stack.push(num);
                // 若 stack 的栈顶元素 = 弹出序列元素 popped[i] ，则执行出栈与 i++ ；
                while (!stack.isEmpty() && stack.peek() == popped[i]) {
                    stack.pop();
                    i++;
                }
            }

            return stack.isEmpty();
        }

    }

}
