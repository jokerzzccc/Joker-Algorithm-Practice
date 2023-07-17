package com.joker.algorithm;

import java.util.LinkedList;

/**
 * <p>
 * 字符串解码
 * </p>
 *
 * @author admin
 * @date 2023/7/17
 */
public class leetcode394 {

    public static void main(String[] args) {
        String s1 = "2[abc]3[cd]ef";
        String s2 = "3[a2[c]]";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.decodeString(s1));

    }

    /**
     * 解法一：辅助栈
     */
    private static class Solution01 {

        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            // 存放数字的倍数栈
            LinkedList<Integer> stackMulti = new LinkedList<>();
            // 存放字符串的栈
            LinkedList<String> stackRes = new LinkedList<>();

            for (char c : s.toCharArray()) {
                if (c == '[') {
                    // 入栈
                    stackMulti.addLast(multi);
                    stackRes.addLast(res.toString());

                    multi = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    // 出栈
                    StringBuilder tmp = new StringBuilder();
                    int curMulti = stackMulti.removeLast();
                    for (int i = 0; i < curMulti; i++) {
                        tmp.append(res);
                    }
                    res = new StringBuilder(stackRes.removeLast() + tmp);
                } else if (Character.isDigit(c)) {
                    multi = multi * 10 + Integer.parseInt(c + "");
                } else if (Character.isLetter(c)) {
                    res.append(c);
                }
            }

            return res.toString();
        }

    }

}
