package com.joker.algorithm.dp.greedy;

import java.util.Arrays;

/**
 * <p>
 * 视频拼接
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode1024 {

    public static void main(String[] args) {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int time = 10;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.videoStitching(clips, time));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int videoStitching(int[][] clips, int time) {
            if (time == 0) return 0;
            // 按起点升序排列，起点相同的降序排列
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });
            // 记录选择的短视频个数
            int res = 0;

            int curEnd = 0, nextEnd = 0;
            int i = 0, n = clips.length;
            while (i < n && clips[i][0] <= curEnd) {
                // 在第 res 个视频的区间内贪心选择下一个视频
                while (i < n && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    i++;
                }
                // 找到下一个视频，更新 curEnd
                res++;
                curEnd = nextEnd;
                if (curEnd >= time) {
                    // 已经可以拼出区间 [0, T]
                    return res;
                }
            }

            // 无法连续拼出区间 [0, T]
            return -1;
        }

    }

}
