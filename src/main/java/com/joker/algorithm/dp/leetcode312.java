package com.joker.algorithm.dp;

/**
 * <p>
 * 戳气球
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode312 {

    public static void main(String[] args) {

        int[] nums = {3, 1, 5, 8};

        Solution01 solution01 = new Solution01();
        int maxCoins01 = solution01.maxCoins(nums);
        System.out.println(maxCoins01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxCoins(int[] nums) {
            int n = nums.length;
            // 添加两侧的虚拟气球，即添加 nums[-1] = nums[n] = 1两个边界，形成一个新的数组 points
            // 现在气球的索引变成了从1到n，points[0]和points[n+1]可以认为是两个「虚拟气球」。
            // 同时，问题改变为：
            // 在一排气球points中，请你戳破气球0和气球n+1之间的所有气球（不包括0和n+1），使得最终只剩下气球0和气球n+1两个气球，最多能够得到多少分？
            int[] points = new int[n + 2];
            points[0] = points[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                points[i] = nums[i - 1];
            }

            // dp[i][j] = x表示，戳破气球i和气球j之间（开区间，不包括i和j）的所有气球，可以获得的最高分数为x。
            // base case 已经都被初始化为 0
            int[][] dp = new int[n + 2][n + 2];
            // 开始状态转移
            // i 应该从下往上
            for (int i = n; i >= 0; i--) {
                // j 应该从左往右
                for (int j = i + 1; j < n + 2; j++) {
                    // k （i, j）中最后戳破的气球是哪个？
                    for (int k = i + 1; k < j; k++) {
                        // 择优做选择，选最大
                        dp[i][j] = Math.max(
                                dp[i][j],
                                dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]
                        );
                    }
                }
            }

            return dp[0][n + 1];
        }

    }

}
