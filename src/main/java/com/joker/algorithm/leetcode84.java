package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 柱状图中最大的矩形
 * </p>
 *
 * @author admin
 * @date 2023/8/11
 */
public class leetcode84 {

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.largestRectangleArea(heights));

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

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
