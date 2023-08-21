package com.joker.algorithm;

/**
 * <p>
 * 加油站
 * </p>
 *
 * @author admin
 * @date 2023/8/21
 */
public class leetcode134 {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.canCompleteCircuit(gas, cost));

    }

    /**
     * 解法一：贪心
     * 离透支最多位置最远的位置就是最安全的起点(亏空最严重的一个点必须放在最后一步走，等着前面剩余的救助)
     */
    private static class Solution01 {

        public int canCompleteCircuit(int[] gas, int[] cost) {
            int len = gas.length;
            // 邮箱总剩余量
            int totalSpare = 0;
            // 邮箱的最小剩余量
            int minSpare = Integer.MAX_VALUE;
            int minIndex = 0;

            for (int i = 0; i < len; i++) {
                totalSpare += gas[i] - cost[i];
                if (totalSpare < minSpare) {
                    minSpare = totalSpare;
                    minIndex = i;
                }
            }

            // minSpare >= 0 表示 所有情况都是满足的（即不管从哪里出发）
            // totalSpare < 0 表示总油量是负的，肯定绕不了一圈
            return totalSpare < 0 ? -1 : (minSpare >= 0 ? 0 : (minIndex + 1) % len);
        }

    }

}
