package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 零钱兑换
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/21
 */
public class leetcode322 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        // 法一：动态规划
//        Solution solution = new Solution();
//        int res = solution.coinChange(coins, amount);

        // 法二：记忆递归
        Solution02 solution02 = new Solution02();
        int res = solution02.coinChange(coins, amount);

        System.out.println(res);
    }

    private static class Solution02 {

        // 记忆递归版
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            if ((coins == null && coins.length == 0) || amount < 0) {
                return -1;
            }
            int[] memo = new int[amount];
            return coinChange(coins, amount, memo);
        }

        int coinChange(int[] coins, int curAmount, int[] memo) {
            if (curAmount < 0) {
                return -1;
            }
            // base case
            if (curAmount == 0) {
                return 0;
            }
            // 查备忘录，防止重复计算
            if (memo[curAmount - 1] != 0) {
                return memo[curAmount - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 计算子问题的结果
                int subRes = coinChange(coins, curAmount - coin, memo);
                // 子问题无解则跳过
                if (subRes == -1) {
                    continue;
                }
                // 在子问题中选择最优解，然后加一
                min = Math.min(min, subRes + 1);
            }
            // 把计算结果存入备忘录
            memo[curAmount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return memo[curAmount - 1];
        }

    }

    private static class Solution {

        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            int max = amount + 1;
            // 数组大小为 amount + 1，初始值也为 amount + 1
            Arrays.fill(dp, amount + 1);
            // base case
            dp[0] = 0;

            // 外层 for 循环在遍历所有状态的所有取值
            for (int i = 0; i < dp.length; i++) {
                // 内层 for 循环再求所有选择的最小值
                for (int coin : coins) {
                    // 子问题无解，跳过
                    if (i - coin < 0) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }

            return (dp[amount] == amount + 1) ? -1 : dp[amount];

        }

    }

}
