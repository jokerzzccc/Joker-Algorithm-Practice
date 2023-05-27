package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 编辑距离
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode72 {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        Solution01 solution01 = new Solution01();
        int minDistance01 = solution01.minDistance(word1, word2);
        System.out.println(minDistance01);

        Solution02 solution02 = new Solution02();
        int minDistance02 = solution02.minDistance(word1, word2);
        System.out.println(minDistance02);

    }

    /**
     * 解法二：自顶向下
     */
    private static class Solution02 {

        /**
         * 备忘录：减少计算重叠子问题；
         */
        int[][] memo;

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            memo = new int[m][n];

            // -1 表示未被计算
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            int dp = dp(word1, m - 1, word2, n - 1);
            // debug
            Arrays.stream(memo).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp;

        }

        /**
         * 返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
         */
        int dp(String word1, int i, String word2, int j) {
            // base case:  i 走完 s1 或 j 走完 s2，可以直接返回另一个字符串剩下的长度
            if (i == -1) {
                return j + 1;
            }
            if (j == -1) {
                return i + 1;
            }

            // 避免重复计算重叠子问题
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                // 相等的时候，啥都不做
                memo[i][j] = dp(word1, i - 1, word2, j - 1);
                // debug
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            } else {
                memo[i][j] = Math.min(dp(word1, i - 1, word2, j) + 1, // 删除
                        Math.min(dp(word1, i, word2, j - 1) + 1 // 插入
                                , dp(word1, i - 1, word2, j - 1) + 1 // 替换
                        ));
                // debug
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            }

            return memo[i][j];
        }

    }

    /**
     * 解法一：自底向上
     */
    private static class Solution01 {

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            // dp[i][j] 代表 word1 前 i 个字符串转换成 word2 前 j 个字符串需要最少步数
            int[][] dp = new int[m + 1][n + 1];
            // 边界条件，即，当 word2 为空串时，word1 需要全部删除；
            for (int i = 1; i <= m; i++) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
            // 边界条件，即，当 word1 为空串时，word1 需要全部插入；
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j - 1] + 1;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        // 相等的时候，可以不做操作
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // 不等的时候，三种操作选最小
                        // dp[i-1][j-1] 表示替换操作，
                        // dp[i-1][j] 表示删除操作，
                        // dp[i][j-1] 表示插入操作。
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }

            // debug
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp[m][n];
        }

    }

}
