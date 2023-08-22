package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 给定行和列的和求可行矩阵
 * </p>
 *
 * @author admin
 * @date 2023/8/22
 */
public class leetcode1065 {

    public static void main(String[] args) {
        int[] rowSum = {3, 8}, colSum = {4, 7};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.deepToString(solution01.restoreMatrix(rowSum, colSum)));

        Solution02 solution02 = new Solution02();
        System.out.println(Arrays.deepToString(solution02.restoreMatrix(rowSum, colSum)));

    }

    /**
     * 解法一：贪心
     * 因为题目保证有一个解，就可以贪心，每次填入矩阵能填的 Math.min(rowSum[i], colSum[j])
     */
    private static class Solution01 {

        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int m = rowSum.length;
            int n = colSum.length;
            int[][] matrix = new int[m][n];

            // 遍历行
            for (int i = 0; i < m; i++) {
                // 遍历列
                for (int j = 0; j < n; j++) {
                    // 每次取较小的值填入矩阵
                    matrix[i][j] = Math.min(rowSum[i], colSum[j]);
                    rowSum[i] -= matrix[i][j];
                    colSum[j] -= matrix[i][j];
                }
            }

            return matrix;

        }

    }

    /**
     * 解法二：贪心 + 时间优化
     * 从左上角出发，每次要么去掉一行，要么去掉一列。
     */
    private static class Solution02 {

        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int m = rowSum.length;
            int n = colSum.length;
            int[][] matrix = new int[m][n];

            // 从左上角开始遍历
            for (int i = 0, j = 0; i < m && j < n; ) {
                int rs = rowSum[i], cs = colSum[j];
                // 取 min 值的时候，如果 rowSum 更小，那么该行右边的就全是0了，就可以跳过该行设值了
                if (rs < cs) { // 去掉第 i 行，往下走
                    colSum[j] -= rs;
                    matrix[i++][j] = rs;
                } else { // 去掉第 j 列，往右走
                    // 取 min 值的时候，如果 colSum 更小，那么该列下边的就全是0了，就可以跳过该列设值了
                    rowSum[i] -= cs;
                    matrix[i][j++] = cs;
                }
            }

            return matrix;

        }

    }

}
