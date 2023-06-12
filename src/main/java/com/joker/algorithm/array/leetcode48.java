package com.joker.algorithm.array;

/**
 * <p>
 * 旋转图像
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode48 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution01 solution01 = new Solution01();
        solution01.rotate(matrix);
    }

    /**
     * 解法一：
     * 我们可以先将 `n x n` 矩阵 `matrix` 按照左上到右下的对角线进行镜像对称；
     * 然后再对矩阵的每一行进行反转**：
     * 发现结果就是 `matrix` 顺时针旋转 90 度的结果：
     */
    private static class Solution01 {

        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 1、先沿对角线镜像对称二维矩阵
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    // swap(matrix[i][j], matrix[j][i]);
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // 2、然后反转二维矩阵的每一行
            for (int[] row : matrix) {
                reverse(row);
            }

            // debug
            for (int[] row : matrix) {
                for (int element : row) {
                    System.out.print(element + " ");
                }
                System.out.println("\n");
            }

        }

        /**
         * 反转一维数组
         */
        private void reverse(int[] arr) {
            int i = 0, j = arr.length - 1;
            while (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

    }

}
