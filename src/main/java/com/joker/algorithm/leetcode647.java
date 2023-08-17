package com.joker.algorithm;

/**
 * <p>
 * 回文子串
 * </p>
 *
 * @author admin
 * @date 2023/8/17
 */
public class leetcode647 {

    public static void main(String[] args) {
        String s = "aaa";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.countSubstrings(s));

    }

    /**
     * 解法一：中心扩展法,
     * 分别以一个字符为中心、两个字符为中心。 共2n-1种可能
     */
    private static class Solution01 {

        public int countSubstrings(String s) {
            int n = s.length();
            // 初始值，默认单个字符为回文子串
            int res = n;

            // 以一个字符为中心
            for (int i = 0; i < n; i++) {
                int left = i - 1;
                int right = i + 1;
                while (left >= 0 && right < n) {
                    if (s.charAt(left--) == s.charAt(right++)) {
                        res++;
                    } else {
                        break;
                    }
                }
            }

            // 以两个字符为中心
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    res++;
                    int left = i - 1;
                    int right = i + 2;
                    while (left >= 0 && right < n) {
                        if (s.charAt(left--) == s.charAt(right++)) {
                            res++;
                        } else {
                            break;
                        }
                    }
                }
            }

            return res;
        }

    }

}
