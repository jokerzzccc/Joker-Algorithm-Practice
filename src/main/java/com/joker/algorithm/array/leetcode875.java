package com.joker.algorithm.array;

/**
 * <p>
 * 爱吃香蕉的珂珂
 * </p>
 *
 * @author admin
 * @date 2023/6/15
 */
public class leetcode875 {

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        Solution01 solution01 = new Solution01();
        int minEatingSpeed01 = solution01.minEatingSpeed(piles, h);
        System.out.println(minEatingSpeed01);

    }

    /**
     * 解法一：二分搜索（左侧边界）
     */
    private static class Solution01 {

        public int minEatingSpeed(int[] piles, int h) {
            // 最小速度，1
            int left = 1;
            // right 是开区间，所以 +1
            int right = (int) (Math.pow(10, 9) + 1);

            // 二分搜索，左侧边界，因为，x 要尽可能小
            // f(x) 是单调递减的，所以规律要适当调整
            while (left < right) {
                // mid 就是 x,
                int mid = left + (right - left) / 2;
                if (f(piles, mid) == h) {
                    // 搜索左侧边界，则需要收缩右侧边界
                    right = mid;
                } else if (f(piles, mid) < h) {
                    // 需要让 f(x) 的返回值大一些，即，x 值小一些
                    right = mid;
                } else if (f(piles, mid) > h) {
                    // 需要让 f(x) 的返回值小一些，即，x 值大一些
                    left = mid + 1;
                }
            }
            return left;
        }

        /**
         * f(x) 随着 x 的增加而减少：
         * 若吃香蕉的速度为 x 根/小时，则需要 f(x) 小时吃完所有香蕉
         */
        int f(int[] piles, int x) {
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += piles[i] / x;
                if (piles[i] % x > 0) {
                    hours++;
                }
            }
            return hours;
        }

    }

}
