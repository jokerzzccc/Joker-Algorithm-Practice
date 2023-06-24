package com.joker.algorithm.dp.greedy;

import java.util.Arrays;

/**
 * <p>
 * 跳跃游戏 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode45 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.jump(nums));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.jump(nums));
    }

    /**
     * 解法一：动态规划
     * 这个时间复杂度为 O(n^2) ，过高
     */
    private static class Solution01 {

        int[] memo;

        public int jump(int[] nums) {
            int n = nums.length;
            // 备忘录都初始化为 n，相当于 INT_MAX
            // 因为从 0 跳到 n - 1 最多 n - 1 步
            memo = new int[n];
            Arrays.fill(memo, n);

            return dp(nums, 0);

        }

        /**
         * 定义：从索引 p 跳到最后一格，至少需要 dp(nums, p) 步
         */
        private int dp(int[] nums, int p) {
            int n = nums.length;
            // base case
            if (p >= n - 1) {
                return 0;
            }
            // 子问题已经计算过
            if (memo[p] != n) {
                return memo[p];
            }

            int curSteps = nums[p];
            // 你可以选择跳 1 步，2 步...
            for (int i = 1; i <= curSteps; i++) {
                // 穷举每一个选择
                // 计算每一个子问题的结果
                int subProblem = dp(nums, i + p);
                // 取其中最小的作为最终结果
                memo[p] = Math.min(memo[p], subProblem + 1);
            }

            return memo[p];
        }

    }

    /**
     * 解法二：贪心
     */
    private static class Solution02 {

        public int jump(int[] nums) {
            int n = nums.length;
            // i 和 end 标记了可以选择的跳跃步数
            // farthest 标记了所有选择 [i..end] 中能够跳到的最远距离
            int end = 0, farthest = 0;
            // jumps 记录了跳跃次数
            int jumps = 0;
            for (int i = 0; i < n - 1; i++) {
                farthest = Math.max(nums[i] + i, farthest);
                if (end == i) {
                    jumps++;
                    end = farthest;
                }
            }

            return jumps;
        }

    }

}
