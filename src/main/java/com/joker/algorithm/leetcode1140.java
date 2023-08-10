package com.joker.algorithm;

/**
 * <p>
 * 石子游戏 II
 * </p>
 *
 * @author admin
 * @date 2023/8/6
 */
public class leetcode1140 {

    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.stoneGameII(piles));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int stoneGameII(int[] piles) {
            int len = piles.length;
            int sum = 0;

            // dp[i][j] 表示剩余 [i : len - 1] 堆时，M = j 的情况下，先取的人能获得的最多石子数
            int[][] dp = new int[len][len + 1];

            // 从后往前
            for (int i = len - 1; i >= 0; i--) {
                sum += piles[i];
                // 状态转移
                for (int M = 1; M <= len; M++) {
                    if (i + 2 * M >= len) {
                        dp[i][M] = sum;
                    } else {
                        for (int x = 1; x <= 2 * M; x++) {
                            dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                        }
                    }
                }

            }

            return dp[0][1];
        }

    }

}
