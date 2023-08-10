package com.joker.algorithm;

/**
 * <p>
 * 重复的子字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/7
 */
public class leetcode459 {

    public static void main(String[] args) {
        String s = "abcabcabcabc";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.repeatedSubstringPattern(s));

    }

    /**
     * 解法一：枚举
     */
    private static class Solution01 {

        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();

            for (int i = 1; i * 2 <= n; i++) {
                if (n % i == 0) {
                    boolean match = true;
                    for (int j = i; j < n; j++) {
                        if (s.charAt(j) != s.charAt(j - i)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return true;
                    }
                }
            }

            return false;
        }

    }

}
