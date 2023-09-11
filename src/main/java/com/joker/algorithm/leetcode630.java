package com.joker.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * 课程表 III
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/11
 */
public class leetcode630 {

    public static void main(String[] args) {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.scheduleCourse(courses));

    }

    /**
     * 解法一：贪心 + 优先队列
     */
    private static class Solution01 {
        public int scheduleCourse(int[][] courses) {
            // 首先我们按课程结束时间升序排列, 我们总是首先考虑当前最先结束的课程
            Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
            // 设置一个优先级队列，这里存储了当前已学课程的长度信息，所需时间最多的在堆顶（大顶堆）
            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            // 当前学习课程的总花费
            int sum = 0;
            for (int[] course : courses) {
                int duration = course[0], lastDay = course[1];
                sum += duration;
                queue.add(duration);
                if (sum > lastDay) {
                    sum -= queue.poll();
                }
            }

            return queue.size();
        }
    }

}
