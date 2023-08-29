package com.joker.algorithm;

/**
 * <p>
 * 交错字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/29
 */
public class leetcode97 {

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isInterleave(s1, s2, s3));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.isInterleave(s1, s2, s3));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public boolean isInterleave(String s1, String s2, String s3) {
            int len1 = s1.length();
            int len2 = s2.length();
            int len3 = s3.length();
            if (len3 != len1 + len2) {
                return false;
            }

            // dp[i][j] 表示 s1 的前 i 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i + j 个元素
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];

            // base case
            dp[0][0] = true;

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    // 状态转移
                    int p = i + j - 1;
                    if (i > 0) {
                        dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                    }
                    if (j > 0) {
                        dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return dp[len1][len2];
        }

    }

    /**
     * 解法二：动态规划 + 滚动数组（空间优化）
     */
    private static class Solution02 {

        public boolean isInterleave(String s1, String s2, String s3) {
            int len1 = s1.length();
            int len2 = s2.length();
            int len3 = s3.length();
            if (len3 != len1 + len2) {
                return false;
            }

            boolean[] dp = new boolean[len2 + 1];

            dp[0] = true;

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    int p = i + j - 1;
                    if (i > 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                    }
                    if (j > 0) {
                        dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return dp[len2];
        }

    }

}
