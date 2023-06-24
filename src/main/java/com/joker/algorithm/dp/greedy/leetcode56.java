package com.joker.algorithm.dp.greedy;

import java.util.*;

/**
 * <p>
 * 合并区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode56 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        Solution01 solution01 = new Solution01();
        int[][] result = solution01.merge(intervals);
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            // 按区间的 start 升序排列
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            LinkedList<int[]> merged = new LinkedList<>();
            merged.add(intervals[0]);
            for (int i = 1; i < intervals.length; i++) {
                int[] curInterval = intervals[i];
                int[] last = merged.getLast();
                if (curInterval[0] <= last[1]) {
                    // 是相交区间，可以合并为更大的区间
                    // 找到最大的 end
                    last[1] = Math.max(last[1], curInterval[1]);
                } else {
                    // 是不相交的
                    // 处理下一个待合并区间，添加到结果集
                    merged.add(curInterval);
                }

            }

            return merged.toArray(new int[merged.size()][]);
        }

    }

}
