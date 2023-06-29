package com.joker.algorithm;

/**
 * <p>
 * 接雨水
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        Solution01 solution01 = new Solution01();
        int trap01 = solution01.trap(height);
        System.out.println(trap01);

        Solution02 solution02 = new Solution02();
        int trap02 = solution02.trap(height);
        System.out.println(trap02);

        Solution03 solution03 = new Solution03();
        int trap03 = solution03.trap(height);
        System.out.println(trap03);

    }

    /**
     * 解法一：暴力算法
     */
    private static class Solution01 {

        public int trap(int[] height) {
            int n = height.length;
            int res = 0;
            for (int i = 1; i < n - 1; i++) {
                int l_max = 0, r_max = 0;
                // 找右边最高的柱子 [i,n]
                for (int j = i; j < n; j++) {
                    r_max = Math.max(r_max, height[j]);
                }
                // 找左边最高的柱子 [0,i]
                for (int j = i; j >= 0; j--) {
                    l_max = Math.max(l_max, height[j]);
                }
                // 如果自己就是最高的话，
                // l_max == r_max == height[i]
                res += Math.min(r_max, l_max) - height[i];
            }

            return res;
        }

    }

    /**
     * 解法二：备忘录优化
     */
    private static class Solution02 {

        public int trap(int[] height) {
            if (height.length == 0) {
                return 0;
            }

            int n = height.length;
            int res = 0;

            // 数组充当备忘录
            int[] l_max = new int[n];
            int[] r_max = new int[n];
            // 初始化 base case
            l_max[0] = height[0];
            r_max[n - 1] = height[n - 1];

            // 从左向右计算 l_max
            for (int i = 1; i < n; i++) {
                l_max[i] = Math.max(height[i], l_max[i - 1]);
            }
            // 从右向左计算 r_max
            for (int j = n - 2; j >= 0; j--) {
                r_max[j] = Math.max(height[j], r_max[j + 1]);
            }

            // 计算答案
            for (int i = 1; i < n - 1; i++) {
                res += Math.min(r_max[i], l_max[i]) - height[i];
            }

            return res;
        }

    }

    /**
     * 解法三：双指针（最优）
     */
    private static class Solution03 {

        public int trap(int[] height) {
            // 两个指针
            int left = 0, right = height.length - 1;
            // 最大值变量
            // leftMax 是 `height[0..left]` 中最高柱子的高度，rightMax 是 height[right..end]
            int leftMax = 0, rightMax = 0;
            int res = 0;

            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);

                // res += min(l_max, r_max) - height[i]
                if (leftMax < rightMax) {
                    res += leftMax - height[left];
                    left++;
                } else {
                    res += rightMax - height[right];
                    right--;
                }
            }

            return res;
        }

    }

}
