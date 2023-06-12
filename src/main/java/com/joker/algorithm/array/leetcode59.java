package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 螺旋矩阵 II
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode59 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        int[][] matrix01 = solution01.generateMatrix(n);
        for (int[] row : matrix01) {
            Arrays.stream(row).forEach(System.out::print);
            System.out.println("\n");
        }

    }

    /**
     * 解法一：类比，leetcode54
     */
    private static class Solution01 {

        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int upper_bound = 0, lower_bound = n - 1;
            int left_bound = 0, right_bound = n - 1;
            // 需要填入矩阵的数字
            int num = 1;
            while (num <= n * n) {
                if (upper_bound <= lower_bound) {
                    // 在顶部从左向右遍历
                    for (int j = left_bound; j <= right_bound; j++) {
                        matrix[upper_bound][j] = num++;
                    }
                    // 上边界下移
                    upper_bound++;
                }

                if (left_bound <= right_bound) {
                    // 在右侧从上向下遍历
                    for (int i = upper_bound; i <= lower_bound; i++) {
                        matrix[i][right_bound] = num++;
                    }
                    // 右边界左移
                    right_bound--;
                }

                if (upper_bound <= lower_bound) {
                    // 在底部从右向左遍历
                    for (int j = right_bound; j >= left_bound; j--) {
                        matrix[lower_bound][j] = num++;
                    }
                    // 下边界上移
                    lower_bound--;
                }

                if (left_bound <= right_bound) {
                    // 在左侧从下向上遍历
                    for (int i = lower_bound; i >= upper_bound; i--) {
                        matrix[i][left_bound] = num++;
                    }
                    // 左边界右移
                    left_bound++;
                }

            }

            return matrix;
        }

    }

}
