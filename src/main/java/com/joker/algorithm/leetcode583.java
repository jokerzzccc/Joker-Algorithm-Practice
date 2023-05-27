package com.joker.algorithm;

/**
 * <p>
 * 两个字符串的删除操作
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode583 {

    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";

        Solution01 solution01 = new Solution01();
        int minDistance = solution01.minDistance(word1, word2);
        System.out.println(minDistance);

    }

    private static class Solution01 {

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            int lcs = longestCommonSubsequence(word1, word2);

            return m - lcs + n - lcs;
        }

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
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            return dp[m][n];
        }

    }

}
