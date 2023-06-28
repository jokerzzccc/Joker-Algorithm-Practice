package com.joker.algorithm;

import java.util.Stack;

/**
 * <p>
 * 有效的括号
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode20 {

    public static void main(String[] args) {
        String s = "()[]{}";

        Solution01 solution01 = new Solution01();
        boolean valid01 = solution01.isValid(s);
        System.out.println(valid01);

    }

    /**
     * 解法一：栈
     */
    private static class Solution01 {

        public boolean isValid(String s) {
            Stack<Character> left = new Stack<>();
            for (char c : s.toCharArray()) {
                // 遇到左括号就入栈
                if (c == '(' || c == '[' || c == '{') {
                    left.push(c);
                } else {
                    // 遇到右括号就去栈中寻找最近的左括号，看是否匹配
                    if (!left.isEmpty() && leftOf(c) == left.peek()) {
                        // 匹配到，就移除栈顶的左括号
                        left.pop();
                    } else {
                        // 匹配不到就就返回 false
                        return false;
                    }
                }
            }

            // 是否所有的左括号都被匹配了
            return left.isEmpty();

        }

        char leftOf(char c) {
            if (c == '}') return '{';
            if (c == ')') return '(';
            return '[';
        }

    }

}
