package com.joker.algorithm;

/**
 * <p>
 * 修车的最少时间
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/8
 */
public class leetcode2594 {

    public static void main(String[] args) {
        int[] ranks = {4, 2, 3, 1};
        int cars = 10;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.repairCars(ranks, cars));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.repairCars(ranks, cars));

    }

    /**
     * 解法一：二分查找
     */
    private static class Solution01 {

        public long repairCars(int[] ranks, int cars) {
            // 能力值最小的那个（修车最快的）
            int minR = ranks[0];
            for (int rank : ranks) {
                minR = Math.min(minR, rank);
            }

            // 修好车的时间范围：bottomTime-下限，topTime-上限
            long bottomTime = 0;
            long topTime = (long) minR * cars * cars;

            // 二分查找：最小的最大值
            // 开区间 (bottomTime, topTime)
            while (bottomTime + 1 < topTime) {
                long mid = (bottomTime + topTime) >> 1;
                // 当前时间，可以修好的车数
                long tmpCars = 0;
                for (int rank : ranks) {
                    tmpCars += Math.sqrt(mid / rank);
                }
                if (tmpCars >= cars) {
                    // 可以修好所有的车，那时间范围就往小的那边走
                    topTime = mid;
                } else {
                    // 修不好，时间范围就往大的那边走
                    bottomTime = mid;
                }
            }

            return topTime;
        }

    }

    /**
     * 解法二：二分查找 + 时间优化
     * 能力值相同的人，在 t 分钟内修好的车的个数是一样的。
     */
    private static class Solution02 {

        public long repairCars(int[] ranks, int cars) {
            // 因为最多有100个值不同;
            // cnt 统计 各个能力值的数量
            int[] cnt = new int[101]; // 数组比哈希表更快
            // 能力值最小的那个（修车最快的）
            int minR = ranks[0];
            for (int rank : ranks) {
                cnt[rank]++;
                minR = Math.min(minR, rank);
            }

            // 修好车的时间范围：bottomTime-下限，topTime-上限
            long bottomTime = 0;
            long topTime = (long) minR * cars * cars;

            // 二分查找：最小的最大值
            // 开区间 (bottomTime, topTime)
            while (bottomTime + 1 < topTime) {
                long mid = (bottomTime + topTime) >> 1;
                // 当前时间，可以修好的车数
                long tmpCars = 0;

                // 依据题目限制，1 <= ranks[i] <= 100
                for (int rank = minR; rank <= 100 && tmpCars < cars; rank++) {
                    // 能力值相同的人，在 t 分钟内修好的车的个数是一样的。
                    tmpCars += (long) Math.sqrt(mid / rank) * cnt[rank];
                }
                if (tmpCars >= cars) {
                    // 可以修好所有的车，那时间范围就往小的那边走
                    topTime = mid;
                } else {
                    // 修不好，时间范围就往大的那边走
                    bottomTime = mid;
                }
            }

            return topTime;
        }

    }

}
