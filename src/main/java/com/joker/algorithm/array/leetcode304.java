package com.joker.algorithm.array;

/**
 * <p>
 * 二维区域和检索 - 矩阵不可变
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode304 {

    public static void main(String[] args) {

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        int sumRegion1 = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println(sumRegion1);

    }

    /**
     * 解法一：二维数组的前缀和
     */
    private static class NumMatrix {

        // 定义：preSum[i][j] 记录 matrix 中子矩阵 [0, 0, i-1, j-1] 的元素和
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            // 构造前缀和矩阵
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
                }
            }

        }

        /**
         * 计算子矩阵 [row1, col1, row2, col2] 的元素和
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 目标矩阵之和由四个相邻矩阵运算获得
            // 因为 row1, 是索引下标，而 preSum 的下标是表示前几个元素，
            // 所以，row/col + 1 才是对应的本来的 preSum, 而不 +1 就相当于减一了
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];

        }

    }

}
