package com.joker.algorithm;

/**
 * <p>
 * 二维数组中的查找
 * </p>
 *
 * @author admin
 * @date 2023/8/14
 */
public class offer04 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findNumberIn2DArray(matrix, target));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.findNumberIn2DArray(matrix, target));

    }

    /**
     * 解法一：标志数：（ Z 字查找）（奇特的思路）
     * 我们将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。
     */
    private static class Solution01 {

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 初始左下角坐标 matrix[i][j] 为标志数 flag
            int i = matrix.length - 1, j = 0;
            while (i >= 0 && j < matrix[0].length) {
                // 若 flag > target ，则 target 一定在 flag 所在 行的上方 ，即 flag 所在行可被消去。
                // 即消去第 i 行元素
                if (matrix[i][j] > target) {
                    i--;
                } else if (matrix[i][j] < target) {
                    // 若 flag < target ，则 target 一定在 flag 所在 列的右方 ，即 flag 所在列可被消去。
                    // 即消去第 j 列元素
                    j++;
                } else if (matrix[i][j] == target) {
                    return true;
                }
            }

            return false;
        }

    }

    /**
     * 解法二：二分查找
     */
    private static class Solution02 {

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 逐行二分查找
            for (int[] row : matrix) {
                int index = binarySearch(row, target);
                if (index >= 0) {
                    return true;
                }
            }

            return false;
        }

        /**
         * 二分查找
         */
        private int binarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int tmp = nums[mid];
                if (tmp == target) {
                    return mid;
                } else if (tmp > target) {
                    high = mid - 1;
                } else if (tmp < target) {
                    low = mid + 1;
                }
            }

            return -1;
        }

    }

}
