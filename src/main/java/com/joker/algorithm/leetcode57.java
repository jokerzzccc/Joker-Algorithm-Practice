package com.joker.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <p>
 * 插入区间
 * </p>
 *
 * @author admin
 * @date 2023/8/28
 */
public class leetcode57 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.deepToString(solution01.insert(intervals, newInterval)));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals.length == 0){
                return new int[][]{newInterval};
            }

            int left = newInterval[0];
            int right = newInterval[1];
            boolean placed = false;

            LinkedList<int[]> resList = new LinkedList<>();
            for (int[] interval : intervals) {
                if (interval[0] > right){
                    // 在插入区间的右侧且无交集
                    if (!placed){
                        resList.add(new int[]{left, right});
                        placed = true;
                    }
                    resList.add(interval);
                } else if (interval[1]  < left) {
                    // 在插入区间的左侧且无交集
                    resList.add(interval);
                } else {
                    // 与插入区间有交集，计算它们的并集
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }

            if (!placed) {
                resList.add(new int[]{left, right});
            }

            return resList.toArray(new int[resList.size()][]);
        }

    }

}
