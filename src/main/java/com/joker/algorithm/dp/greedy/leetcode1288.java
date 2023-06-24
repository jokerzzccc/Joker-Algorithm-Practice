package com.joker.algorithm.dp.greedy;

import java.util.Arrays;

/**
 * <p>
 * 删除被覆盖区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode1288 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};

        Solution01 solution01 = new Solution01();
        int removeCoveredIntervals01 = solution01.removeCoveredIntervals(intervals);
        System.out.println(removeCoveredIntervals01);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int removeCoveredIntervals(int[][] intervals) {
            // 按照起点升序排列，起点相同时降序排列
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });

            // 记录合并区间的起点和终点，初始为排序后的第一个区间
            int left = intervals[0][0];
            int right = intervals[0][1];

            // 被覆盖的区间数
            int res = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] curInterval = intervals[i];
                // 情况一，找到覆盖区间
                if (left <= curInterval[0] && right >= curInterval[1]) {
                    res++;
                }

                // 情况二，找到相交区间，合并
                if (right >= curInterval[0] && right <= curInterval[1]) {
                    right = curInterval[1];
                }

                // 情况三，完全不相交，更新起点和终点
                if (right < curInterval[0]) {
                    left = curInterval[0];
                    right = curInterval[1];
                }
            }

            return intervals.length - res;
        }

    }

}
