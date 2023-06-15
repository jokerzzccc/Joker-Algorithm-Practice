package com.joker.algorithm.array;

/**
 * <p>
 * 在 D 天内送达包裹的能力
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode1011 {

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        Solution01 solution01 = new Solution01();
        int days1 = solution01.shipWithinDays(weights, days);
        System.out.println(days1);

    }

    /**
     * 解法一：二分搜索，左侧边界
     */
    private static class Solution01 {

        public int shipWithinDays(int[] weights, int days) {
            // 船的最小载重
            int left= 0;
            // 最大载重
            // right 是开区间，所以 +1
            int right =  1;
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right){
                int mid = left + (right - left) /2;
                if (f(weights, mid) <= days) {
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
            for (int i = 0; i < weights.length;) {
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
