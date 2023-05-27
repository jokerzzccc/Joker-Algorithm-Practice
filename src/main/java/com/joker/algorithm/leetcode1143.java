package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 最长公共子序列
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/25
 */
public class leetcode1143 {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        Solution01 solution01 = new Solution01();
        int subsequenceLen01 = solution01.longestCommonSubsequence(text1, text2);
        System.out.println(subsequenceLen01);

        Solution02 solution02 = new Solution02();
        int subsequenceLen02 = solution02.longestCommonSubsequence(text1, text2);
        System.out.println(subsequenceLen02);

    }

    /**
     * 自底向上：从前到后 LCS
     */
    private static class Solution02 {

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();

            // 其中 dp[i][j] 表示 text1[0:i]和text[0:j] 的最长公共子序列的长度。
            // test[0:i] 表示 text1 长度为 i 的前缀。
            int[][] dp = new int[m + 1][n + 1];

            // 从 1 开始，是因为，text[0:0] 表示空串，空串和任何字符串的最长公共子序列都为空。
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    }
                }
            }

            // debug
            System.out.println("========");
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));

            return dp[m][n];
        }

    }

    /**
     * 自顶向下：从后到前 LCS
     */
    private static class Solution01 {

        // 备忘录，消除重叠子问题
        // memo[i][j]: text1[i...] 与 text2[j...] 的最长公共子序列
        // memo[0][0] 表示 text1.substring(0, text1.length()) 与 text2.substring(0, text2.length()) 的 LCS
        // i == 0 时，就表示，text1 为空串；j == 0 时，表示text2为空串
        private int[][] memo;

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();

            memo = new int[m][n];
            // 备忘录值为 -1 代表未曾计算
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            // 计算 s1[0..] 和 s2[0..] 的 lcs 长度
            int dp = dp(text1, 0, text2, 0);
            Arrays.stream(memo).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp;
        }

        int dp(String s1, int i, String s2, int j) {
            // base case
            if (i == s1.length() || j == s2.length()) {
                return 0;
            }

            // 如果之前计算过，则直接返回备忘录中的答案
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            if (s1.charAt(i) == s2.charAt(j)) {
                // s1[i] 和 s2[j] 必然在 lcs 中，
                // 加上 s1[i+1..] 和 s2[j+1..] 中的 lcs 长度，就是答案
                memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            } else {
                // s1[i] 和 s2[j] 至少有一个不在 lcs 中
                memo[i][j] = Math.max(
                        dp(s1, i + 1, s2, j),
                        dp(s1, i, s2, j + 1)
                );
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            }

            return memo[i][j];
        }

    }

}
