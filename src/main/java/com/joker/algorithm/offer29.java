package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 顺时针打印矩阵
 * </p>
 *
 * @author admin
 * @date 2023/7/19
 */
public class offer29 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.spiralOrder(matrix)));

    }

    /**
     * 解法一：按层模拟，定义四个顶点变量
     * 类比 leetcode54
     */
    private static class Solution01 {

        public int[] spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0){
                return new int[0];
            }
            int m = matrix.length;
            int n = matrix[0].length;
            // 定义上下左右四个边界
            int upperBound = 0, lowerBound = m - 1;
            int leftBound = 0, rightBound = n - 1;

            // 结果数组
            int[] res = new int[m * n];
            int index = 0;

            // 顺时针循环填充，填满
            while (index < m * n) {
                // 上边界，在顶部从左向右遍历
                if (upperBound <= lowerBound) {
                    for (int i = leftBound; i <= rightBound; i++) {
                        res[index] = matrix[upperBound][i];
                        index++;
                    }
                    // 上边界下移
                    upperBound++;
                }

                // 右边界，在右侧从上向下遍历
                if (leftBound <= rightBound) {
                    for (int i = upperBound; i <= lowerBound; i++) {
                        res[index] = matrix[i][rightBound];
                        index++;
                    }
                    // 右边界左移
                    rightBound--;
                }

                // 下边界，在下侧从右向左遍历
                if (upperBound <= lowerBound) {
                    for (int i = rightBound; i >= leftBound; i--) {
                        res[index] = matrix[lowerBound][i];
                        index++;
                    }
                    // 下边界上移
                    lowerBound--;
                }

                // 左边界，在左侧从下向上遍历
                if (leftBound <= rightBound) {
                    for (int i = lowerBound; i >= upperBound; i--) {
                        res[index] = matrix[i][leftBound];
                        index++;
                    }
                    // 左边界右移
                    leftBound++;
                }
            }

            return res;
        }

    }

}
