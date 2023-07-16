package com.joker.algorithm.dp;

import java.util.Arrays;

/**
 * <p>
 * 最后一块石头的重量 II
 * </p>
 *
 * @author admin
 * @date 2023/7/16
 */
public class leetcode1049 {

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.lastStoneWeightII(stones));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.lastStoneWeightII(stones));

        Solution03 solution03 = new Solution03();
        System.out.println(solution03.lastStoneWeightII(stones));

    }

    /**
     * 解法一：动态规划-0/1 背包，
     */
    private static class Solution01 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            int target = sum / 2;
            int n = stones.length;
            int res = Integer.MAX_VALUE;

            // dp[i + 1][j] 表示前 i 个石头能否凑出重量 j
            boolean[][] dp = new boolean[n + 1][target + 1];
            // base case
            // dp[0][...] 表示不选任何石头的状态，所以 dp[0][0] 为 true, 其余 dp[0][j] 均为 false
            dp[0][0] = true;

            // 状态一：可选择的物品 stones[i]
            for (int i = 0; i < n; i++) {
                // 状态一：背包的容量 target
                for (int weight = 0; weight <= target; weight++) {
                    // 做选择
                    if (weight < stones[i]) {
                        // 不能选第 i 个石头
                        dp[i + 1][weight] = dp[i][weight];

                    } else {
                        // 不选：  dp[i][weight]
                        // 选：则需要考虑能否凑出重量 weight - stones[i]
                        dp[i + 1][weight] = dp[i][weight] || dp[i][weight - stones[i]];
                    }

                    // 更新答案，
                    // 求出所有的 dp[n][...] 后，最大的 weight 即为，符合要求能取到的小于等于 target 的最大重量
                    if (dp[n][weight]) {
                        res = Math.min(res, sum - 2 * weight);
                    }
                }
            }

            return res;
        }

    }

    /**
     * 解法二：动态规划-0/1 背包，
     */
    private static class Solution02 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            // 背包容量
            int target = sum / 2;
            int n = stones.length;

            // dp[i][j] 表示，表示前 i 个石头可以凑出的满足要求的最大重量
            int[][] dp = new int[n + 1][target + 1];
            for (int i = 1; i <= n; i++) {
                int curStone = stones[i - 1];
                for (int j = 0; j <= target; j++) {
                    // 不选 当前石头
                    dp[i][j] = dp[i - 1][j];
                    if (j >= curStone) {
                        // 不选或者选当前石头
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - curStone] + curStone);
                    }
                }
            }

            return Math.abs(sum - 2 * dp[n][target]);
        }

    }

    /**
     * 解法三：动态规划-0/1 背包，(一维数组优化)
     */
    private static class Solution03 {

        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            // 背包容量
            int target = sum / 2;

            // dp[i] 表示，当前 stone 之前（包含当前）的几个石头，当重量为 i 时，能放进去的最多石头的重量和 maxWeight
            int[] dp = new int[target + 1];

            for (int stone : stones) {
                for (int i = target; i >= stone; i--) {
                    // 做选择，不放进去，和放进去
                    dp[i] = Math.max(dp[i], dp[i - stone] + stone);
                }
            }

            return sum - 2 * dp[target];
        }

    }

}
