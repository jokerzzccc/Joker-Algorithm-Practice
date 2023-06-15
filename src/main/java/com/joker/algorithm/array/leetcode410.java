package com.joker.algorithm.array;

/**
 * <p>
 * 分割数组的最大值
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode410 {

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;

        Solution01 solution01 = new Solution01();
        int split01 = solution01.splitArray(nums, m);
        System.out.println(split01);
    }

    /**
     * 解法一：二分搜索，左侧边界
     * 和 leetcode1011 代码一模一样
     */
    private static class Solution01 {

        public int splitArray(int[] weights, int k) {
            // 船的最小载重
            int left = 0;
            // 最大载重
            // right 是开区间，所以 +1
            int right = 1;
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (f(weights, mid) <= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        /**
         * 当运载能力为 x 时，需要 f(x) 天运送完货物。
         * f(x) 随着 x 的增加而递减
         */
        int f(int[] weights, int x) {
            int days = 0;
            for (int i = 0; i < weights.length; ) {
                int cap = x;
                while (i < weights.length) {
                    if (cap < weights[i]) {
                        break;
                    } else {
                        cap -= weights[i];
                    }
                    i++;
                }
                days++;
            }
            return days;
        }

    }

}



