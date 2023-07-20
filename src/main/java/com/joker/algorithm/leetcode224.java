package com.joker.algorithm;

import java.util.LinkedList;

/**
 * <p>
 * 基本计算器
 * </p>
 *
 * @author admin
 * @date 2023/7/20
 */
public class leetcode224 {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.calculate(s));

    }

    /**
     * 解法一：双栈
     */
    private static class Solution01 {

        /**
         * 字符
         */
        private LinkedList<Character> ops = new LinkedList<>();
        /**
         * 数字
         */
        private LinkedList<Integer> nums = new LinkedList<>();

        public int calculate(String s) {
            // 为了防止第一个数为负数，先往 nums 加个 0
            nums.addLast(0);
            // 将所有的空格去掉
            s = s.replaceAll(" ", "");
            int n = s.length();
            char[] strChars = s.toCharArray();

            for (int i = 0; i < n; i++) {
                char curChar = strChars[i];
                if (curChar == '(') {
                    ops.addLast(curChar);
                } else if (curChar == ')') {
                    // 计算到最近一个左括号为止
                    while (!ops.isEmpty()) {
                        char op = ops.peekLast();
                        if (op != '(') {
                            calculateCurr();
                        } else {
                            // 遇到最近一个左括号，出栈，并结束计算
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (Character.isDigit(curChar)) { // 是数字时
                        // curSequenceNum 连续数字代表的数值
                        int curSequenceNum = 0;
                        int j = i;
                        // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                        while (j < n && Character.isDigit(strChars[j])) {
                            curSequenceNum = curSequenceNum * 10 + (strChars[j++] - '0');
                        }
                        // 加入值栈
                        nums.addLast(curSequenceNum);
                        // 因为j++ 加到 strChas[j] 不为数字了。
                        i = j - 1;
                    } else { // 是 +, - 时
                        // 为防止 () 内出现的首个字符为运算符，将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
                        if (i > 0 && (strChars[i - 1] == '(' || strChars[i - 1] == '+' || strChars[i - 1] == '-')) {
                            nums.addLast(0);
                        }
                        // 有一个新操作要入栈时，先把栈内可以算的都算了
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            calculateCurr();
                        }
                        // 加入当前操作符
                        ops.addLast(curChar);
                    }
                }
            }

            while (!ops.isEmpty()) {
                calculateCurr();
            }

            return nums.peekLast();
        }

        /**
         * 计算当前值，放入值栈
         */
        void calculateCurr() {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;
            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            nums.addLast(op == '+' ? a + b : a - b);
        }

    }

}
