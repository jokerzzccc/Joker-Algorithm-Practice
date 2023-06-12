package com.joker.algorithm.array;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 螺旋矩阵
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode54 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        Solution01 solution01 = new Solution01();
        List<Integer> integers01 = solution01.spiralOrder(matrix);
        integers01.forEach(System.out::print);
    }

    /**
     * 解法一：按层模拟，定义四个顶点变量
     */
    private static class Solution01 {

        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int upper_bound = 0, lower_bound = m - 1;
            int left_bound = 0, right_bound = n - 1;
            List<Integer> res = new LinkedList<>();
            // res.size() == m * n 则遍历完整个数组
            // 即，四条边按上右下左的顺时针方向遍历
            while (res.size() < m * n) {
                // 上边界，在顶部从左向右遍历
                if (upper_bound <= lower_bound) {
                    for (int i = left_bound; i <= right_bound; i++) {
                        res.add(matrix[upper_bound][i]);
                    }
                    // 上边界下移
                    upper_bound++;
                }
                // 右边界，在右侧从上向下遍历
                if (left_bound <= right_bound) {
                    for (int i = upper_bound; i <= lower_bound; i++) {
                        res.add(matrix[i][right_bound]);
                    }
                    // 右边界左移
                    right_bound--;
                }

                // 下边界，在下侧从右向左遍历
                if (upper_bound <= lower_bound) {
                    for (int i = right_bound; i >= left_bound; i--) {
                        res.add(matrix[lower_bound][i]);
                    }
                    // 下边界上移
                    lower_bound--;
                }

                // 左边界，在左侧从下向上遍历
                if (left_bound <= right_bound) {
                    for (int i = lower_bound; i >= upper_bound; i--) {
                        res.add(matrix[i][left_bound]);
                    }
                    // 左边界右移
                    left_bound++;
                }
            }

            return res;
        }

    }

}
