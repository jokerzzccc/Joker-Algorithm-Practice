package com.joker.algorithm.dp;

/**
 * <p>
 * 通配符匹配
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode44 {

    public static void main(String[] args) {
        String s = "aa", p = "a";

        System.out.println(new Solution01().isMatch(s, p));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            // `dp[i][j]` 表示：字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配
            boolean[][] dp = new boolean[m + 1][n + 1];

            // base case
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                if (p.charAt(i - 1) == '*') {
                    dp[0][i] = true;
                } else {
                    break;
                }
            }

            // 状态转移计算
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }

            return dp[m][n];
        }

    }

}
