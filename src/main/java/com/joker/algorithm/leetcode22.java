package com.joker.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 括号生成
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/14
 */
public class leetcode22 {

    public static void main(String[] args) {
        List<String> parenthesis;
        int n = 2;

        // 法一
//        Solution01 solution01 = new Solution01();
//        parenthesis = solution01.generateParenthesis(n);

        // 法二
        Solution02 solution02 = new Solution02();
        parenthesis = solution02.generateParenthesis(n);

        System.out.println(parenthesis);

    }

    static class Solution01 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            if (n <= 0) {
                return res;
            }
            backTrack("", 0, 0, n);
            return res;
        }

        /**
         * @param curStr 当前递归得到的结果
         * @param left 左括号已经用了几个
         * @param right 右括号已经用了几个
         * @param n 左括号、右括号一共得用几个
         */
        void backTrack(String curStr, int left, int right, int n) {
            if (right == n && left == n) {
                res.add(curStr);
                return;
            }

            // 剪枝
            if (left < right) {
                return;
            }

            if (left < n) {
                backTrack(curStr + "(", left + 1, right, n);
            }

            if (right < n) {
                backTrack(curStr + ")", left, right + 1, n);
            }
        }

    }

    static class Solution02 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            List<List<String>> total = new ArrayList<>();
            if (n <= 0) {
                return res;
            }
            List<String> list0 = new ArrayList<>();
            list0.add("");
            total.add(list0);
            List<String> list1 = new ArrayList<>();
            list1.add("()");
            total.add(list1);

            for (int i = 2; i <= n; i++) {
                List<String> temp = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    List<String> str1 = total.get(j);
                    List<String> str2 = total.get(i - j - 1);
                    for (String s1 : str1) {
                        for (String s2 : str2) {
                            String el = "(" + s1 + ")" + s2;
                            temp.add(el);
                        }
                    }
                }
                total.add(temp);
            }
            return total.get(n);
        }

    }

}
