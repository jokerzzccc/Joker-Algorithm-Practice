package com.joker.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>
 * 目标和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/31
 */
public class leetcode494 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        Solution01 solution01 = new Solution01();
        int targetSumWays01 = solution01.findTargetSumWays(nums, target);
        System.out.println(targetSumWays01);

        Solution011 solution011 = new Solution011();
        int targetSumWays011 = solution011.findTargetSumWays(nums, target);
        System.out.println(targetSumWays011);

        Solution02 solution02 = new Solution02();
        int targetSumWays02 = solution02.findTargetSumWays(nums, target);
        System.out.println(targetSumWays02);

        Solution022 solution022 = new Solution022();
        int targetSumWays022 = solution022.findTargetSumWays(nums, target);
        System.out.println(targetSumWays022);

    }

    /**
     * 解法一：动态规划 + 二维数组
     * 转化为背包问题(子集划分)
     * <p>
     * 原理：
     * 把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，那么他们和 target 存在如下关系
     * sum(A) - sum(B) = target;
     * sum(A) = target + sum(B);
     * sum(A) + sum(A) = target + sum(B) + sum(A);
     * 2 * sum(A) = target + sum(nums);
     * </p>
     */
    private static class Solution01 {

        public int findTargetSumWays(int[] nums, int target) {
            int sum = Arrays.stream(nums).sum();
            // 这两种情况，不可能存在合法的子集划分
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                return 0;
            }
            return subsets(nums, (sum + target) / 2);
        }

        /**
         * 计算 nums 中有几个子集的和为 sum
         */
        private int subsets(int[] nums, int sum) {
            int n = nums.length;
            int[][] dp = new int[n + 1][sum + 1];
            // base case
            dp[0][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    if (j >= nums[i - 1]) {
                        // 两种选择的结果之和
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    } else {
                        // 背包的空间不足，只能选择不装物品 i
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n][sum];
        }

    }

    /**
     * 解法一优化：动态规划 + 一维数组
     */
    private static class Solution011 {

        public int findTargetSumWays(int[] nums, int target) {
            int sum = Arrays.stream(nums).sum();
            // 这两种情况，不可能存在合法的子集划分
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                return 0;
            }
            return subsets(nums, (sum + target) / 2);
        }

        /**
         * 计算 nums 中有几个子集的和为 sum
         */
        private int subsets(int[] nums, int sum) {
            int n = nums.length;
            int[] dp = new int[sum + 1];
            // base case
            dp[0] = 1;

            for (int i = 1; i <= n; i++) {
                // j 要从后往前遍历
                for (int j = sum; j >= 0; j--) {
                    if (j >= nums[i - 1]) {
                        // 两种选择的结果之和
                        dp[j] = dp[j] + dp[j - nums[i - 1]];
                    } else {
                        // 背包的空间不足，只能选择不装物品 i
                        dp[j] = dp[j];
                    }
                }
            }
            return dp[sum];
        }

    }

    /**
     * 解法二：回溯
     * 把 target 减到 0
     */
    private static class Solution02 {

        /**
         * 满足结果的表达式数目
         */
        int result = 0;

        public int findTargetSumWays(int[] nums, int target) {
            if (nums.length == 0) {
                return 0;
            }
            backtrack(nums, 0, target);
            return result;
        }

        private void backtrack(int[] nums, int index, int remain) {
            if (index == nums.length) {
                if (remain == 0) {
                    // 说明恰好凑出 target
                    result++;
                }
                return;
            }

            // 给 nums[i] 选择 - 号
            remain += nums[index];
            backtrack(nums, index + 1, remain);
            // 撤销选择
            remain -= nums[index];

            // 给 nums[i] 选择 + 号
            remain -= nums[index];
            // 穷举 nums[i + 1]
            backtrack(nums, index + 1, remain);
            // 撤销选择
            remain += nums[index];
        }

    }

    /**
     * 解法二优化：回溯 + 备忘录
     * 把 target 减到 0
     */
    private static class Solution022 {

        /**
         * 备忘录
         */
        HashMap<String, Integer> memoMap = new HashMap<>();

        public int findTargetSumWays(int[] nums, int target) {
            if (nums.length == 0) {
                return 0;
            }

            return backtrack(nums, 0, target);
        }

        private int backtrack(int[] nums, int index, int remain) {
            // base case
            if (index == nums.length) {
                if (remain == 0) {
                    return 1;
                }
                return 0;
            }
            // 把它俩转成字符串才能作为哈希表的键
            String key = index + "," + remain;
            // 避免重复计算
            if (memoMap.containsKey(key)) {
                return memoMap.get(key);
            }
            // 还是穷举
            int result = backtrack(nums, index + 1, remain - nums[index])
                    + backtrack(nums, index + 1, remain + nums[index]);
            // 记入备忘录
            memoMap.put(key, result);
            return result;
        }

    }

}
