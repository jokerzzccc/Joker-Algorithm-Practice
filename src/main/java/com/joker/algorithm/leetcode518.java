package com.joker.algorithm;

/**
 * <p>
 * 零钱兑换 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/30
 */
public class leetcode518 {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        Solution01 solution01 = new Solution01();
        int change01 = solution01.change(amount, coins);
        System.out.println(change01);

        Solution02 solution02 = new Solution02();
        int change02 = solution02.change(amount, coins);
        System.out.println(change02);

    }

    /**
     * 解法一：动态规划 + 二维数组
     */
    private static class Solution01 {

        public int change(int amount, int[] coins) {
            int n = coins.length;
            // dp 定义：若只使用 `coins` 中的前 `i` 个（`i` 从 1 开始计数）硬币的面值，若想凑出金额 `j`，有 `dp[i][j]` 种凑法
            int[][] dp = new int[n + 1][amount + 1];
            // base case
            // 总金额为 0 时，不放进去就是一种方法
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= amount; j++) {
                    if (j - coins[i - 1] >= 0) {
                        // 两种选择，将第 i 个 coin 不放进背包，或放进背包
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                    } else {
                        // 不放进背包，因为放不进去
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[n][amount];
        }

    }

    /**
     * 解法一：动态规划 + 一维数组（空间优化）
     */
    private static class Solution02 {

        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1; // base case
            for (int coin : coins) {
                for (int j = 1; j <= amount; j++) {
                    if (j - coin >= 0) {
                        dp[j] = dp[j] + dp[j - coin];
                    }
                }
            }

            return dp[amount];
        }

    }

}
