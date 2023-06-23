package com.joker.algorithm.dp;

import java.util.Arrays;

/**
 * <p>
 * 打家劫舍
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode198 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};

        Solution01 solution01 = new Solution01();
        int ro01 = solution01.rob(nums);
        System.out.println(ro01);

        Solution02 solution02 = new Solution02();
        int rob02 = solution02.rob(nums);
        System.out.println(rob02);

        Solution03 solution03 = new Solution03();
        int rob03 = solution03.rob(nums);
        System.out.println(rob03);

    }

    /**
     * 解法一：动态规划，自顶向下
     */
    private static class Solution01 {

        // 备忘录
        private int[] memo;

        public int rob(int[] nums) {

            // 初始化备忘录
            memo = new int[nums.length];
            Arrays.fill(memo, -1);

            // 强盗从第 0 间房子开始抢劫
            return dp(nums, 0);
        }

        /**
         * 返回 dp[start..] 能抢到的最大值
         */
        private int dp(int[] nums, int start) {
            // base case
            if (start >= nums.length) {
                return 0;
            }
            // 避免重复计算
            if (memo[start] != -1) {
                return memo[start];
            }

            int res = Math.max(
                    // 不抢，去下家
                    dp(nums, start + 1),
                    // 抢，去下下家
                    nums[start] + dp(nums, start + 2)
            );
            // 记入备忘录
            memo[start] = res;
            return res;
        }

    }

    /**
     * 解法二：动态规划，自底向上
     */
    private static class Solution02 {

        public int rob(int[] nums) {
            int n = nums.length;
            // dp[i] = x 表示：
            // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
            // base case: dp[n] = 0
            int[] dp = new int[n + 2];
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
            }
            return dp[0];
        }

    }

    /**
     * 解法三：动态规划，自底向上（一维数组优化）
     * 状态转移只和dp[i]最近的两个状态有关，所以可以进一步优化，将空间复杂度降低到 O(1)
     */
    private static class Solution03 {

        public int rob(int[] nums) {
            int n = nums.length;
            // 记录 dp[i+1] 和 dp[i+2]
            int dp_i_1 = 0, dp_i_2 = 0;
            // 记录 dp[i]
            int dp_i = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }

    }

}
