package com.joker.algorithm;

/**
 * <p>
 * 最长重复子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/27
 */
public class leetcode718 {

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 0, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findLength(nums1, nums2));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int findLength(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;

            // 令 dp[i][j] 表示 A[i:] 和 B[j:] 的最长公共前缀
            int[][] dp = new int[len1 + 1][len2 + 1];
            int ans = 0;

            for (int i = len1 - 1; i >= 0; i--) {
                for (int j = len2 - 1; j >= 0; j--) {
                    // 状态转移方程
                    if (nums1[i] == nums2[j]) {
                        dp[i][j] = 1 + dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = 0;
                    }

                    // 更新答案
                    ans = Math.max(ans, dp[i][j]);
                }
            }

            return ans;
        }

    }

}
