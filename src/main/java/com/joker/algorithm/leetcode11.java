package com.joker.algorithm;

/**
 * <p>
 * 盛最多水的容器
 * </p>
 *
 * @author admin
 * @date 2023/6/29
 */
public class leetcode11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        Solution01 solution01 = new Solution01();
        int maxArea01 = solution01.maxArea(height);
        System.out.println(maxArea01);

    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public int maxArea(int[] height) {
            // 两个指针
            int left = 0, right = height.length - 1;
            int res = 0;

            while (left < right) {
                // [left, right] 之间的矩形面积
                int curArea = Math.min(height[left], height[right]) * (right - left);
                res = Math.max(res, curArea);

                // 双指针技巧，移动较低的一边,保持高的一方指针不动
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return res;
        }

    }

}
