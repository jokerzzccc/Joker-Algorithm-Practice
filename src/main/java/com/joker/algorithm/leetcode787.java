package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * K 站中转站内最便宜的航班
 * </p>
 *
 * @author admin
 * @date 2023/6/6
 */
public class leetcode787 {

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, k = 1;

        Solution01 solution01 = new Solution01();
        int cheapestPrice01 = solution01.findCheapestPrice(n, flights, src, dst, k);
        System.out.println(cheapestPrice01);

    }

    /**
     * 解法一：动态规划 + 递归 + 备忘录 + 自顶向下
     */
    private static class Solution01 {

        int src, dst;
        /**
         * K-to，V-[from,price]
         */
        Map<Integer, List<int[]>> inDegreeMap = new HashMap<>();
        /**
         * 备忘录
         */
        int[][] memo;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            this.src = src;
            this.dst = dst;
            // 将中转站个数转化成边的条数
            k++;
            memo = new int[n][k + 1];
            // memo 填充一个无意义的值
            for (int[] row : memo) {
                Arrays.fill(row, -2);
            }
            // 统计一个节点的入度，放入队列
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                inDegreeMap.putIfAbsent(to, new LinkedList<>());
                inDegreeMap.get(to).add(new int[]{from, price});
            }

            return dp(dst, k);
        }

        /**
         * 定义：从 src 出发，k 步之内到达 dst 的最短路径权重
         *
         * @param subDst 终点
         * @param k 步数（可以走过的边，一步就是一边）
         * @return 从 src 出发，k 步之内到达 dst 的最短路径权重
         */
        private int dp(int subDst, int k) {
            // base case
            if (subDst == src) {
                return 0;
            }
            if (k == 0) {
                return -1;
            }
            // 防止重叠子问题
            if (memo[subDst][k] != -2) {
                return memo[subDst][k];
            }

            // 初始化为最大值，方便等会取最小值
            int res = Integer.MAX_VALUE;
            // 当 s 有入度节点时，分解为子问题
            if (inDegreeMap.containsKey(subDst)) {
                for (int[] in : inDegreeMap.get(subDst)) {
                    int from = in[0];
                    int price = in[1];

                    // 从 src 到达相邻的入度节点所需的最短路径权重
                    int subProblem = dp(from, k - 1);
                    // 跳过无解的情况
                    if (subProblem != -1) {
                        res = Math.min(res, price + subProblem);
                    }
                }

            }
            memo[subDst][k] = res == Integer.MAX_VALUE ? -1 : res;
            return memo[subDst][k];
        }

    }

}
