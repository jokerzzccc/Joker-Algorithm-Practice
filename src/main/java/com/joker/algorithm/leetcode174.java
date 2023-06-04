package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 地下城游戏
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/4
 */
public class leetcode174 {

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        Solution01 solution01 = new Solution01();
        int minimumHP01 = solution01.calculateMinimumHP(dungeon);
        System.out.println(minimumHP01);
    }

    /**
     * 动态规划+备忘录
     */
    private static class Solution01 {

        /**
         * 从 (i, j) 到达右下角，需要的初始血量至少是多少
         */
        int[][] memo;

        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            int dp = dp(dungeon, 0, 0);
            Arrays.stream(memo).forEach(row -> System.out.println(Arrays.toString(row)));
            return dp;
        }

        /**
         * 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少
         */
        private int dp(int[][] dungeon, int i, int j) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            // base case
            if (i == m - 1 && j == n - 1) {
                return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
            }
            if (i == m || j == n) {
                return Integer.MAX_VALUE;
            }
            // 避免重复计算
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            // 状态转移逻辑
            int res = Math.min(
                    dp(dungeon, i, j + 1),
                    dp(dungeon, i + 1, j)
            ) - dungeon[i][j];
            // 骑士的生命值至少为 1
            memo[i][j] = res <= 0 ? 1 : res;

            return memo[i][j];
        }

    }

}
