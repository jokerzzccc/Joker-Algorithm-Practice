package com.joker.algorithm;

/**
 * <p>
 * 最长回文子串
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/24
 */
public class leetcode005 {

    public static void main(String[] args) {
        String s = "babad";

        Solution01 solution01 = new Solution01();
        String longestPalindrome01 = solution01.longestPalindrome(s);
        System.out.println(longestPalindrome01);

        Solution02 solution02 = new Solution02();
        String longestPalindrome02 = solution02.longestPalindrome(s);
        System.out.println(longestPalindrome02);
    }

    /**
     * 中心扩展法
     */
    private static class Solution02 {

        // 中心扩展法
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int start = 0, end = 0;
            // 遍历每一个字符串，每一个字符都是中心
            for (int i = 0; i < s.length(); i++) {
                // 两种边界条件，len1: 中心子串长度为1；len2: 中心子串长度为2；
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int subLen = Math.max(len1, len2);
                if (subLen > end - start + 1) {
                    start = i - (subLen - 1) / 2;
                    end = i + subLen / 2;
                }
            }

            return s.substring(start, end + 1);
        }

        /**
         * 由中心向两边扩展
         *
         * @param s 输入字符串
         * @param left 左下标
         * @param right 右下标
         * @return 以当前字符为中心的最长回文字符串的长度。
         */
        public int expandAroundCenter(String s, int left, int right) {
            // 不越界，且头尾相等，才是回文。
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return right - left - 1;
        }

    }

    /**
     * 解法一：动态规划版
     */
    private static class Solution01 {

        public String longestPalindrome(String s) {
            int length = s.length();
            if (length < 2) {
                return s;
            }
            // dp[i][j]: 表示 S[i...j] 是回文子串。
            boolean[][] dp = new boolean[length][length];

            // 单个字符都是回文子串
            for (int i = 0; i < length; i++) {
                dp[i][i] = true;
            }

            char[] chars = s.toCharArray();
            int maxLen = 1;
            int begin = 0;
            // 枚举子串长度
            for (int subLen = 2; subLen <= length; subLen++) {
                // 枚举左边界 i
                for (int i = 0; i < length; i++) {
                    // 右边界 j
                    int j = subLen + i - 1;
                    // 右边界越界
                    if (j >= length) {
                        break;
                    }

                    if (chars[i] != chars[j]) {
                        dp[i][j] = false;
                    } else {
                        // 小于等于三个，且头尾相等，则一定是回文字符串，比如 "aba"
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            // 当头尾两个相等，则由子串决定
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    // 比对，是否更新最长回文子串
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }

            return s.substring(begin, begin + maxLen);

        }

    }

}
