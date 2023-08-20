package com.joker.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 数据流的中位数
 * </p>
 *
 * @author admin
 * @date 2023/8/20
 */
public class leetcode295 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }

    /**
     * 解法一： 双优先队列
     */
    private static class MedianFinder {

        /**
         * 有序整数列表的：左半队列，大顶堆
         */
        private PriorityQueue<Integer> leftQueue;
        /**
         * 有序整数列表的：右半队列，小顶堆
         */
        private PriorityQueue<Integer> rightQueue;

        /**
         * 总数量是否为奇数
         */
        private boolean isOddSize;

        /**
         * 总数量
         */
        private int size;

        public MedianFinder() {
            leftQueue = new PriorityQueue<>((a, b) -> b - a);
            rightQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        }

        public void addNum(int num) {
            if (!isOddSize) {
                if (rightQueue.isEmpty() || num < rightQueue.peek()) {
                    leftQueue.add(num);
                } else {
                    leftQueue.add(rightQueue.poll());
                    rightQueue.add(num);
                }
            } else {
                if (leftQueue.peek() <= num) {
                    rightQueue.add(num);
                } else {
                    rightQueue.add(leftQueue.poll());
                    leftQueue.add(num);
                }
            }

            size++;
            isOddSize = size % 2 != 0;
        }

        /**
         * O(1) 时间复杂度 取中位数
         */
        public double findMedian() {
            if (size > 0) {
                if (isOddSize) {
                    return leftQueue.peek();
                } else {
                    return (leftQueue.peek() + rightQueue.peek()) / 2.0;
                }
            }

            return 0;

        }

    }

}
