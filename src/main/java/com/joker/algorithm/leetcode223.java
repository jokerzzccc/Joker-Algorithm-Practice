package com.joker.algorithm;

/**
 * <p>
 * 矩形面积
 * </p>
 *
 * @author admin
 * @date 2023/8/15
 */
public class leetcode223 {

    public static void main(String[] args) {
        int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2));

    }

    /**
     * 解法一：投影计算重叠面积
     */
    private static class Solution01 {

        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);

            int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
            int overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
            // 重叠区域面积
            int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);

            return area1 + area2 - overlapArea;
        }

    }

}
