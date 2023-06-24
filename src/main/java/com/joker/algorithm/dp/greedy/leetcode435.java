package com.joker.algorithm.dp.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 无重叠区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode435 {

    public static void main(String[] args) {

        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};

        Solution01 solution01 = new Solution01();
        int erased01 = solution01.eraseOverlapIntervals(intervals);
        System.out.println(erased01);

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int eraseOverlapIntervals(int[][] intervals) {
            int n = intervals.length;
            return n - intervalSchedule(intervals);
        }

        /**
         * 算出这些区间中最多有几个互不相交的区间
         */
        private int intervalSchedule(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            // 按 end 升序排序
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

            // 至少有一个区间不相交
            int count = 1;
            // 排序后，第一个区间就是 x
            int x_end = intervals[0][1];
            for (int[] interval : intervals) {
                int start = interval[0];
                if (start >= x_end) {
                    count++;
                    x_end = interval[1];
                }
            }

            return count;
        }

    }

}
