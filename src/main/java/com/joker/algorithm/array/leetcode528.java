package com.joker.algorithm.array;

import java.util.Random;

/**
 * <p>
 * 按权重随机选择
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode528 {

    public static void main(String[] args) {
        int[] w = {1, 3};

        Solution01 solution01 = new Solution01(w);
        int count01 = 5;
        for (int i = 0; i < count01; i++) {
            int index = solution01.pickIndex();
            System.out.println(index);
        }

    }

    /**
     * 解法一：前缀和 + 二分查找（左侧边界）
     */
    private static class Solution01 {

        /**
         * 前缀和数组
         */
        private int[] preSum;
        private Random random = new Random();

        public Solution01(int[] w) {
            int n = w.length;
            // 构建前缀和数组，偏移一位留给 preSum[0]
            preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }

        }

        public int pickIndex() {
            int n = preSum.length;
            // 方法在 [0, n) 中生成一个随机整数
            // 再加一就是在闭区间 [1, preSum[n - 1]] 中随机选择一个数字
            int target = random.nextInt(preSum[n - 1]) + 1;
            // 获取 target 在前缀和数组 preSum 中的索引（返回的这个值是 preSum 中大于等于 target 的最小元素索引）
            // 别忘了前缀和数组 preSum 和原始数组 w 有一位索引偏移
            return left_bound(preSum, target) - 1;
        }

        /**
         * 二分查找，左侧边界
         */
        private int left_bound(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 别返回，锁定左侧边界
                    right = mid - 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }

            return left;
        }

    }

}
