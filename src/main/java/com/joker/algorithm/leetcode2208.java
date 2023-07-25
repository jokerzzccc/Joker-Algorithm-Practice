package com.joker.algorithm;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * <p>
 * 将数组和减半的最少操作次数
 * </p>
 *
 * @author admin
 * @date 2023/7/25
 */
public class leetcode2208 {

    public static void main(String[] args) {
        int[] nums = {5, 19, 8, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.halveArray(nums));

    }

    /**
     * 解法一：贪心 + 优先队列（大顶堆）
     */
    private static class Solution01 {

        public int halveArray(int[] nums) {
            double sum = 0;

            // 大顶堆的优先队列
            PriorityQueue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
            for (int num : nums) {
                queue.offer(num * 1.0);
                sum += num;
            }

            sum /= 2.0;
            int ans = 0;
            while (sum > 0) {
                double tmp = queue.poll();
                sum -= tmp / 2.0;
                queue.offer(tmp / 2.0);
                ans++;
            }

            return ans;
        }

    }

}
