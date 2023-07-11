package com.joker.algorithm;

/**
 * <p>
 * 最大的以 1 为边界的正方形
 * </p>
 *
 * @author admin
 * @date 2023/7/11
 */
public class leetcode1139 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.largest1BorderedSquare(grid));

    }

    /**
     * 解法一：动态规划
     * 核心点，就是以每一个单元格为正方形的右下顶点，这样才方便 dp 自底向上的状态转移
     */
    private static class Solution01 {

        public int largest1BorderedSquare(int[][] grid) {
            int m = grid.length, n = grid[0].length;

            // (i,j)左方连续1的个数
            int[][] left = new int[m + 1][n + 1];
            // (i,j)上方连续1的个数
            int[][] up = new int[m + 1][n + 1];

            // 记录正方形的最大边长
            int maxBorder = 0;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (grid[i - 1][j - 1] == 1) {
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = up[i - 1][j] + 1;
                        // 取左方和上方连续1的较小值作为临时边界
                        // curBorder 可以认为是以（i, j）为右下顶点的正方形 的 下边和右边的长度，
                        // 我们还需要根据正方形上边和左边的长度，来确认是否满足正方形的条件
                        int curBorder = Math.min(left[i][j], up[i][j]);
                        // 判断正方形的左边和上边的长度是否大于curSide，如果不大于，我们就缩小正方形
                        // 的长度 curBorder，然后继续判断
                        while (left[i - curBorder + 1][j] < curBorder || up[i][j - curBorder + 1] < curBorder) {
                            curBorder--;
                        }
                        maxBorder = Math.max(maxBorder, curBorder);
                    }

                }
            }

            return maxBorder * maxBorder;
        }

    }

}
