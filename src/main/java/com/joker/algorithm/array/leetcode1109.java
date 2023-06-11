package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 航班预订统计
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode1109 {

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        Solution01 solution01 = new Solution01();
        int[] flightBookings = solution01.corpFlightBookings(bookings, n);
        Arrays.stream(flightBookings).forEach(System.out::println);
    }

    private static class Solution01 {

        public int[] corpFlightBookings(int[][] bookings, int n) {
            // nums 初始化为全 0
            int[] nums = new int[n];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] booking : bookings) {
                // 注意转成数组索引要减一哦
                int i = booking[0] - 1;
                int j = booking[1] - 1;
                int val = booking[2];
                // 对区间 nums[i..j] 增加 val
                df.increment(i, j, val);
            }
            // 返回最终的结果数组
            return df.result();
        }

    }

    /**
     * 差分数组工具类
     */
    private static class Difference {

        /**
         * 差分数组:
         * diff[i] 就是 nums[i] 和 nums[i-1] 之差
         */
        private int[] diff;

        public Difference(int[] nums) {
            if (nums.length <= 0) {
                return;
            }
            diff = new int[nums.length];
            // 根据初始数组构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * 给闭区间 [i, j] 增加 val（可以是负数）
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;
            // 即，nums[j] 之后如果还有元素，就要减去加上的 val，因为，保证之操作区间 [i, j] 的数。
            // 当 j+1 >= diff.length 时，说明是对 nums[i] 及以后的整个数组都进行修改，那么就不需要再给 diff 数组减 val 了。
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * 返回结果数组:
         * diff 差分数组是可以反推出原始数组 nums
         */
        public int[] result() {
            int[] res = new int[diff.length];
            // 根据差分数组构造结果数组
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }

    }

}
