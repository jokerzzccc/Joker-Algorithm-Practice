package com.joker.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 滑动窗口最大值
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode239 {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        Solution01 solution01 = new Solution01();
        int[] maxSlidingWindow01 = solution01.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(maxSlidingWindow01));

    }

    /**
     * 解法一：单调队列
     */
    private static class Solution01 {

        public int[] maxSlidingWindow(int[] nums, int k) {
            MonotonicQueue window = new MonotonicQueue();
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                if (i < k - 1) {
                    //先填满窗口的前 k - 1
                    window.push(nums[i]);
                } else {
                    // 窗口向前滑动，加入新数字
                    window.push(nums[i]);
                    // 记录当前窗口的最大值
                    res.add(window.max());
                    // 移出旧数字
                    window.pop(nums[i - k + 1]);
                }
            }

            int[] ans = res.stream().mapToInt(e -> e).toArray();
            return ans;
        }

        /**
         * 单调队列
         */
        class MonotonicQueue {

            /**
             * 双链表，支持头部和尾部增删元素
             * 维护其中的元素自尾部到头部单调递增
             */
            private LinkedList<Integer> maxQueue = new LinkedList<>();

            /**
             * 在队尾添加元素 n,
             * 维护 maxQueue 的单调性质
             */
            void push(int n) {
                while (!maxQueue.isEmpty() && maxQueue.getLast() < n) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(n);
            }

            /**
             * 返回当前队列中的最大值
             */
            int max() {
                // 队头的元素肯定是最大的
                return maxQueue.getFirst();
            }

            /**
             * 队头元素如果是 n，删除它
             */
            void pop(int n) {
                // 因为我们想删除的队头元素 n 可能已经被「压扁」了，可能已经不存在了，所以这时候就不用删除了
                if (n == maxQueue.getFirst()) {
                    maxQueue.pollFirst();
                }

            }

        }

    }

}
