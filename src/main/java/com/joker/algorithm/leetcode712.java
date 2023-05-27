package com.joker.algorithm;

/**
 * <p>
 * 两个字符串的最小ASCII删除和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode712 {

    public static void main(String[] args) {
        String s1 = "sea", s2 = "eat";

        Solution01 solution01 = new Solution01();
        int minimumDeleteSum = solution01.minimumDeleteSum(s1, s2);
        System.out.println(minimumDeleteSum);

    }

    private static class Solution01 {

        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            // 其中 dp[i][j] 表示使 s1[0:i]和 s2[0:j] 相同的最小 ASCII 删除和
            // 其中 s1[0:i] 表示 s1 的长度为 i 的前缀。
            int[][] dp = new int[m + 1][n + 1];
            // 边界条件：j = 0
            for (int i = 1; i <= m; i++) {
                dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
            }
            // 边界条件：i = 0
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j - 1] + s2.codePointAt(j - 1);
            }

            for (int i = 1; i <= m; i++) {
                int code1 = s1.codePointAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    int code2 = s2.codePointAt(j - 1);
                    if (code1 == code2) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] + code1, dp[i][j - 1] + code2);
                    }
                }
            }

            return dp[m][n];
        }

    }

}
