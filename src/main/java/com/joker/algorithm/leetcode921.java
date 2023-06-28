package com.joker.algorithm;

/**
 * <p>
 * 使括号有效的最少添加
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode921 {

    public static void main(String[] args) {
        String s = "(((";

        Solution01 solution01 = new Solution01();
        int minAddToMakeValid01 = solution01.minAddToMakeValid(s);
        System.out.println(minAddToMakeValid01);

    }

    /**
     * 解法一：贪心
     * 核心思路是以左括号为基准，通过维护对右括号的需求数 need，来计算最小的插入次数。
     */
    private static class Solution01 {

        public int minAddToMakeValid(String s) {
            // 记录插入次数
            int res = 0;
            // 变量记录右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    // 对右括号的需求 + 1
                    need++;
                }
                if (s.charAt(i) == ')') {
                    // 对右括号的需求 - 1
                    need--;
                    if (need == -1) {
                        // 需插入一个左括号
                        need = 0;
                        res++;
                    }
                }
            }

            // 插入剩余所需的右括号
            return res + need;
        }

    }

}
