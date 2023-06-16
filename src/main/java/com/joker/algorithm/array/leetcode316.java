package com.joker.algorithm.array;

import java.util.Stack;

/**
 * <p>
 * 去除重复字母
 * </p>
 *
 * @author admin
 * @date 2023/6/17
 */
public class leetcode316 {

    public static void main(String[] args) {
        String s = "bcabc";

        Solution01 solution01 = new Solution01();
        String s1 = solution01.removeDuplicateLetters(s);
        System.out.println(s1);

    }

    /**
     * 解法一：贪心 + 单调栈
     */
    private static class Solution01 {

        public String removeDuplicateLetters(String s) {

            boolean[] vis = new boolean[256];
            // 维护一个计数器记录字符串中字符的数量
            // 因为输入为 ASCII 字符，大小 256 够用了
            int[] count = new int[256];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i)]++;
            }

            // 存放去重的结果
            Stack<Character> stk = new Stack<>();

            for (char c : s.toCharArray()) {
                // 每遍历过一个字符，都将对应的计数减一
                count[c]--;
                // 如果字符 c 存在栈中，直接跳过
                if (vis[c]) {
                    continue;
                }

                // 插入之前，和之前的元素比较一下大小
                // 如果字典序比前面的小，pop 前面的元素
                while (!stk.isEmpty() && stk.peek() > c) {
                    // 若之后不存在栈顶元素了，则停止 pop
                    if (count[stk.peek()] == 0) {
                        break;
                    }
                    // 若之后还有，则可以 pop
                    vis[stk.pop()] = false;
                }
                // 若不存在，则插入栈顶并标记为存在
                stk.push(c);
                vis[c] = true;
            }

            StringBuilder stringBuilder = new StringBuilder();
            while (!stk.isEmpty()) {
                stringBuilder.append(stk.pop());
            }
            // 栈中元素插入顺序是反的，需要 reverse 一下
            return stringBuilder.reverse().toString();
        }

    }

}
