package com.joker.algorithm.dp.greedy;

import java.util.Arrays;

/**
 * <p>
 * 会议室 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode253 {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        Solution01 solution01 = new Solution01();
        int meetingRooms01 = solution01.minMeetingRooms(intervals);
        System.out.println(meetingRooms01);

    }

    /**
     * 解法一：贪心 + 差分数组
     */
    private static class Solution01 {

        int minMeetingRooms(int[][] meetings) {
            int n = meetings.length;
            int[] begin = new int[n];
            int[] end = new int[n];
            for (int i = 0; i < n; i++) {
                begin[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }
            Arrays.sort(begin);
            Arrays.sort(end);

            // 扫描过程中的计数器
            int count = 0;
            // 双指针技巧
            int res = 0, i = 0, j = 0;
            while (i < n && j < n) {
                if (begin[i] < end[j]) {
                    // 扫描到一个红点
                    count++;
                    i++;
                } else {
                    // 扫描到一个绿点
                    count--;
                    j++;
                }
                // 记录扫描过程中的最大值
                res = Math.max(res, count);
            }

            return res;
        }

    }

}
