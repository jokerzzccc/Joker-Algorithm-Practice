package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 最大矩形
 * </p>
 *
 * @author admin
 * @date 2023/8/11
 */
public class leetcode85 {

    public static void main(String[] args) {
        char[][] martix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maximalRectangle(martix));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maximalRectangle(martix));

    }

    /**
     * 解法一：动态规划
     * <p>
     * 方法1:动态规划+枚举
     * 枚举每一个1作为矩形的左上角，求出该元素作为矩形左上角，向右下角延伸的最大面积矩形
     * 而后维护每个计算得到的最大矩形的最大值就是答案
     * 关键是如何快速求出能延伸的矩形面积最大值，很显然是由每一行中下一个0的位置约束
     * 因此可以通过DP的方法将每行中下一个0的位置先求出，把每次求面积最大值耗时加速至O(N)
     * 时间复杂度:O(M*N^2) 空间复杂度:O(M*N)
     */
    private static class Solution01 {

        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            // 存储每一行中的下一个0的索引
            int[][] nextZero = new int[m][n];
            for (int i = 0; i < m; i++) {
                int zeroPos = n;
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '0') zeroPos = j;
                    nextZero[i][j] = zeroPos;
                }
            }
            int res = 0;
            // 枚举每个左上角
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0') continue;
                    int ii = i, maxArea = 0, minWidth = n - j;
                    while (ii < m && matrix[ii][j] == '1') {
                        minWidth = Math.min(minWidth, nextZero[ii][j] - j);
                        int area = (ii - i + 1) * minWidth;
                        maxArea = Math.max(maxArea, area);
                        ii++;
                    }
                    res = Math.max(res, maxArea);
                }
            }
            return res;
        }

    }

    /**
     * 解法二：单调栈（更优）
     * 类比 leetcode84
     */
    private static class Solution02 {

        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }

            int[] heights = new int[matrix[0].length];
            int maxArea = 0;

            // 遍历每一行，统计柱形的高度，传递给 largestRectangleArea
            for (int row = 0; row < matrix.length; row++) {
                // 遍历每一列，更新高度
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] == '1') {
                        heights[col] += 1;
                    } else {
                        heights[col] = 0;
                    }
                }
                // 调用 leetcode84 的解法，更新函数
                maxArea = Math.max(maxArea, largestRectangleArea(heights));
            }

            return maxArea;
        }

        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) {
                return 0;
            }
            if (len == 1) {
                return heights[0];
            }

            int maxArea = 0;
            // 新数组两端加上两个高度为 0 的柱形(两哨兵)，便于计算
            int[] newHeights = new int[len + 2];
            System.arraycopy(heights, 0, newHeights, 1, len);
            len += 2;
            heights = newHeights;

            // 单调栈：存储下标
            // 下标对应的柱形的高度一定是递增(头 -> 尾)的
            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);

            for (int i = 1; i < len; i++) {
                // 确定一个柱形高度的最大面积（即找宽度）
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    maxArea = Math.max(maxArea, curHeight * curWidth);
                }

                stack.addLast(i);
            }

            return maxArea;
        }

    }

}


