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

        Solution02 solution02 = new Solution02();
        int minimumHP02 = solution02.calculateMinimumHP(dungeon);
        System.out.println(minimumHP02);
    }

    /**
     * 动态规划+备忘录
     * （递归写法）
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

    /**
     * 解法二：动态规划
     * （循环写法）
     */
    private static class Solution02 {

        public int calculateMinimumHP(int[][] dungeon) {
            int n = dungeon.length, m = dungeon[0].length;
            // dp[i][j] 表示：从坐标(i, j) 到终点所需要的最小初始值
            int[][] dp = new int[n + 1][m + 1];
            // 边界条件：
            // i = n - 1 || j = m - 1 时，dp[i][j] 转移需要用到 dp[i + 1][j], dp[i][j + 1] 无效值。
            // 因此代码实现中给无效值赋值为极大值
            for (int i = 0; i <= n; ++i) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            // 特别的，dp[n - 1][m - 1] 转移需要用到的 dp[n - 1][m], dp[n][m - 1] 均为无效值，
            // 因此我们给这两个值赋值为 1
            dp[n][m - 1] = dp[n - 1][m] = 1;

            for (int i = n - 1; i >= 0; --i) {
                for (int j = m - 1; j >= 0; --j) {
                    // 状态转移方程：max(min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1)
                    int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(minn - dungeon[i][j], 1);
                }
            }

            // 最终答案
            return dp[0][0];
        }

    }

}
