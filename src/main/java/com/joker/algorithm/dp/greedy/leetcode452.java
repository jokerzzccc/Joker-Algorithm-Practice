package com.joker.algorithm.dp.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 用最少数量的箭引爆气球
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode452 {

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findMinArrowShots(points));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int findMinArrowShots(int[][] points) {
            if (points.length == 0) {
                return 0;
            }
            // 按 end 升序排序
            Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

            // 至少有一个区间不相交
            int count = 1;
            // 排序后，第一个区间就是 x
            int x_end = points[0][1];
            for (int[] point : points) {
                int start = point[0];
                // 类比，leetcode435 : 把 >= 改成 > 就行了
                if (start > x_end) {
                    count++;
                    x_end = point[1];
                }
            }

            return count;
        }

    }

}
