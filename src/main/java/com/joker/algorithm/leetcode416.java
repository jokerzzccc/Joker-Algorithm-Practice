package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 分割等和子集
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/29
 */
public class leetcode416 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        Solution01 solution01 = new Solution01();
        boolean canPartition01 = solution01.canPartition(nums);
        System.out.println(canPartition01);

        Solution02 solution02 = new Solution02();
        boolean canPartition02 = solution02.canPartition(nums);
        System.out.println(canPartition02);

    }

    /**
     * 解法二：动态规划 + 一维数组（空间优化）
     */
    private static class Solution02 {

        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            // 特判：如果是奇数，就不符合要求
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];

            // base case
            dp[0] = true;

            for (int i = 0; i < n; i++) {
                for (int j = target; j >= 0; j--) {
                    if (j - nums[i] >= 0) {
                        // 背包容量充足的情况下
                        dp[j] = dp[j] || dp[j - nums[i]];
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[j]);
                    }
                }
            }
            System.out.println(Arrays.toString(dp));

            return dp[target];
        }

    }

    /**
     * 解法一：动态规划 + 二维数组
     */
    private static class Solution01 {

        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            // 特判：如果是奇数，就不符合要求
            if (sum % 2 != 0) {
                return false;
            }

            int target = sum / 2;
            // 创建二维状态数组，行：第几个物品（对应数组下标为 i - 1），列：容量（包括 0）
            boolean[][] dp = new boolean[n + 1][target + 1];

            // base case
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= target; j++) {
                    if (j - nums[i - 1] < 0) {
                        // 背包容量不足，不能装入第 i 个物品
                        dp[i][j] = dp[i - 1][j];

                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    } else {
                        // 第 i 个物品（对应的种来为 nums[i - 1]） 装入或不装入背包
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    }
                }
            }

            // debug
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));

            return dp[n][target];
        }

    }

}
