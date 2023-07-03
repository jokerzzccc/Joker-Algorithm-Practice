package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 环形子数组的最大和
 * </p>
 *
 * @author admin
 * @date 2023/7/3
 */
public class leetcode918 {

    public static void main(String[] args) {
        int[] nums = {1, -2, 3, -2};

        Solution01 solution01 = new Solution01();
        int maxed01 = solution01.maxSubarraySumCircular(nums);
        System.out.println(maxed01);

    }

    /**
     * 解法一：前缀和数组 + 单调队列
     */
    private static class Solution01 {

        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;

            // 前缀和数组，且将 nums 拼成两个
            int[] preSum = new int[n * 2 + 1];
            for (int i = 1; i <= n * 2; i++) {
                preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
            }

            int ans = nums[0];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offer(0);

            // 单调队列的维护
            for (int j = 1; j <= 2 * n; j++) {
                if (deque.peekFirst() < j - n)
                    deque.pollFirst();

                // The optimal i is deque[0], for cand. answer P[j] - P[i].
                ans = Math.max(ans, preSum[j] - preSum[deque.peekFirst()]);

                // Remove any i1's with P[i2] <= P[i1].
                while (!deque.isEmpty() && preSum[j] <= preSum[deque.peekLast()])
                    deque.pollLast();

                deque.offerLast(j);

            }

            return ans;
        }

    }

}
