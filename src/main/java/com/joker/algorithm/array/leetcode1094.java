package com.joker.algorithm.array;

/**
 * <p>
 * 拼车
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode1094 {

    public static void main(String[] args) {
        int[][] bookings = {{2, 1, 5}, {3, 3, 7}};
        int n = 4;

        Solution01 solution01 = new Solution01();
        boolean carPooling01 = solution01.carPooling(bookings, n);
        System.out.println(carPooling01);

        Solution02 solution02 = new Solution02();
        boolean carPooling02 = solution02.carPooling(bookings, n);
        System.out.println(carPooling02);
    }

    /**
     * 解法二：迭代
     */
    private static class Solution02 {

        public boolean carPooling(int[][] trips, int capacity) {
            int sites[] = new int[1001];
            for (int[] trip : trips) {
                // 上车加
                sites[trip[1]] += trip[0];
                // 下车减
                sites[trip[2]] -= trip[0];
            }
            // 从始发站计数，超过capacity则超载
            int total = 0;
            for (int passengerChanges : sites) {
                total += passengerChanges;
                if (total > capacity) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * 解法一：差分数组
     */
    private static class Solution01 {

        public boolean carPooling(int[][] trips, int capacity) {
            // 最多有 1001 个车站
            int[] nums = new int[1001];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] trip : trips) {
                // 乘客数量
                int val = trip[0];
                // 第 trip[1] 站乘客上车
                int i = trip[1];
                // 第 trip[2] 站乘客已经下车，
                // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
                int j = trip[2] - 1;
                // 进行区间操作
                df.increment(i, j, val);
            }

            int[] res = df.result();

            // 客车自始至终都不应该超载
            for (int i = 0; i < res.length; i++) {
                if (capacity < res[i]) {
                    return false;
                }
            }

            return true;
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
