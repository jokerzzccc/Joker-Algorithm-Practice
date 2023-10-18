package com.joker.algorithm;

import java.util.PriorityQueue;

/**
 * 执行 K 次操作后的最大分数
 *
 * @author jokerzzccc
 * @date 2023/10/19 0:04
 */
public class leetcode2530 {

    public static void main(String[] args) {
        int[] nums = {1, 10, 3, 3, 3};
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxKelements(nums, k));

    }

    /**
     * 解法一：贪心 + 优先队列（大顶堆）
     */
    private static class Solution01 {

        public long maxKelements(int[] nums, int k) {
            PriorityQueue<Long> queue = new PriorityQueue<>((a, b) -> (int) (b - a));
            for (int num : nums) {
                queue.offer((long) num);
            }

            long ans = 0;
            while (k > 0 && !queue.isEmpty()) {
                long tmpMax = queue.poll();
                ans += tmpMax;
                k--;
                // 向上取整
                queue.offer((tmpMax + 2) / 3);
            }

            return ans;
        }

    }

}
