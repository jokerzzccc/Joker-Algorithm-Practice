package com.joker.algorithm;

/**
 * <p>
 * 最长有效括号
 * </p>
 *
 * @author admin
 * @date 2023/7/15
 */
public class leetcode32 {

    public static void main(String[] args) {
        String s = ")()())";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestValidParentheses(s));
    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int longestValidParentheses(String s) {
            int n = s.length();
            int res = 0;

            // dp[i] 表示，以 s.charAt(i) 结尾的最长有效括号的长度
            int[] dp = new int[n];
            for (int i = 1; i < n; i++) {
                // 只有以 ')' 结尾，才可能是有效括号
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }

                    res = Math.max(res, dp[i]);
                }
            }

            return res;
        }

    }

}
