package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * 前 K 个高频元素
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode347 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        Solution01 solution01 = new Solution01();
        int[] topKFrequent01 = solution01.topKFrequent(nums, k);
        System.out.println(Arrays.toString(topKFrequent01));
    }

    /**
     * 解法一：优先队列（堆排序）
     */
    private static class Solution01 {

        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                frequencies.put(nums[i], frequencies.getOrDefault(nums[i], 0) + 1);
            }

            // 创建一个按出现频率降序排序的优先队列
            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));

            for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();

                if (queue.size() == k) {
                    // 如果，当前出现频率 > 堆顶，则用当前元素替换掉堆顶
                    if (queue.peek()[1] < count) {
                        queue.poll();
                        queue.offer(new int[]{num, count});
                    }
                } else {
                    queue.offer(new int[]{num, count});
                }
            }

            // 得到结果
            int[] ret = new int[k];
            for (int i = 0; i < k; ++i) {
                ret[i] = queue.poll()[0];
            }

            return ret;
        }

    }

}
