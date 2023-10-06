package com.joker.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>
 * 最接近原点的 K 个点
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/6
 **/
public class leetcode973 {

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}};
        int k = 1;

        Solution01 solution01 = new Solution01();
        int[][] res1 = solution01.kClosest(points, k);
        System.out.println(Arrays.deepToString(res1));

    }

    /**
     * 解法一：大根堆
     */
    private static class Solution01 {
        public int[][] kClosest(int[][] points, int k) {
            // 大根堆：坐标到原点的距离为排序内容
            PriorityQueue<int[]> queue = new PriorityQueue<>((array1, array2) -> array2[0] - array1[0]);

            // 队列里最多只有 K 个元素
            for (int i = 0; i < k; i++) {
                queue.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
            }

            int len = points.length;
            for (int i = k; i < len; i++) {
                int tempDist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                if (tempDist < queue.peek()[0]) {
                    queue.poll();
                    queue.offer(new int[]{tempDist, i});
                }
            }

            // 返回答案
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; i++) {
                ans[i] = points[queue.poll()[1]];
            }

            return ans;


        }
    }

}
