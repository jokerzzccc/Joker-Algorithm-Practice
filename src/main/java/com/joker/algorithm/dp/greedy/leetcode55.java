package com.joker.algorithm.dp.greedy;

/**
 * <p>
 * 跳跃游戏
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode55 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.canJump(nums));
    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public boolean canJump(int[] nums) {
            int n = nums.length;
            int farthest = 0;
            for (int i = 0; i < n - 1; i++) {
                // 不断计算能跳到的最远距离
                farthest = Math.max(farthest, i + nums[i]);
                // 可能碰到了 0，卡住跳不动了
                if (farthest <= i) {
                    return false;
                }
            }

            return farthest >= n - 1;
        }

    }

}
