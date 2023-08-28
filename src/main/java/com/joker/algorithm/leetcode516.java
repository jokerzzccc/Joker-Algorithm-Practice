package com.joker.algorithm;

/**
 * <p>
 * 最长回文子序列
 * </p>
 *
 * @author admin
 * @date 2023/8/28
 */
public class leetcode516 {

    public static void main(String[] args) {
        String s = "bbbab";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestPalindromeSubseq(s));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            // dp[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少
            int[][] dp = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                // 初始化，单个字符的回文长度 1
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    // 状态转移
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[0][n - 1];
        }

    }

}
