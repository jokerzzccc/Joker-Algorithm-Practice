package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 有效的正方形
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode593 {

    public static void main(String[] args) {
        int[] p1 = {0, 0}, p2 = {1, 1}, p3 = {1, 0}, p4 = {0, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.validSquare(p1, p2, p3, p4));

    }

    /**
     * 解法一：数学，几何
     */
    private static class Solution01 {

        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            // 两条边为 (p1, p2) (p3, p4)
            if (Arrays.equals(p1, p2)) {
                return false;
            }
            if (help(p1, p2, p3, p4)) {
                return true;
            }

            // 两条边为 (p1, p3) (p2, p4)
            if (Arrays.equals(p1, p3)) {
                return false;
            }
            if (help(p1, p3, p2, p4)) {
                return true;
            }

            // 两条边为 (p1, p4) (p2, p3)
            if (Arrays.equals(p1, p4)) {
                return false;
            }
            if (help(p1, p4, p2, p3)) {
                return true;
            }

            return false;

        }

        public boolean help(int[] p1, int[] p2, int[] p3, int[] p4) {
            int[] v1 = {p1[0] - p2[0], p1[1] - p2[1]};
            int[] v2 = {p3[0] - p4[0], p3[1] - p4[1]};
            if (checkMidPoint(p1, p2, p3, p4) && checkLength(v1, v2) && calCos(v1, v2)) {
                return true;
            }

            return false;
        }

        /**
         * 检查两条斜边的长度是否相同 -> 矩形
         */
        public boolean checkLength(int[] v1, int[] v2) {
            return (v1[0] * v1[0] + v1[1] * v1[1]) == (v2[0] * v2[0] + v2[1] * v2[1]);
        }

        /**
         * 检查两条斜边的中点是否相同 -> 平行四边形
         */
        public boolean checkMidPoint(int[] p1, int[] p2, int[] p3, int[] p4) {
            return (p1[0] + p2[0]) == (p3[0] + p4[0]) && (p1[1] + p2[1]) == (p3[1] + p4[1]);
        }

        /**
         * 检查两条斜边是否垂直 -> 两条斜边是否相互垂直
         */
        public boolean calCos(int[] v1, int[] v2) {
            return (v1[0] * v2[0] + v1[1] * v2[1]) == 0;
        }

    }

}
