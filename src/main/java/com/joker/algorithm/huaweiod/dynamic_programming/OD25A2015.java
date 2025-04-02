package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.*;

/**
 * 跳格子3
 * <p>
 * 题目描述
 * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，
 * 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。
 * <p>
 * 输入描述
 * 第一行输入总的格子数量 n
 * 第二行输入每个格子的分数 score[i]
 * 第三行输入最大跳的步长 k
 * <p>
 * 备注
 * 格子的总长度 n 和步长 k 的区间在 [1, 100000]
 * 每个格子的分数 score[i] 在 [-10000, 10000] 区间中
 * 输出描述
 * 输出最大得分
 * <p>
 * 示例1
 * 输入
 * 6
 * 1 -1 -6 7 -17 7
 * 2
 * <p>
 * 输出
 * 14
 * ————————————————
 * <p>
 * 题型分析
 * 【困难】动态规划 + 队列时间优化
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2015 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[] input_arr = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        // 特殊情况处理：如果格子数量为1，直接输出该格子的分数
        if (n == 1) {
            System.out.println(input_arr[0]);
            return;
        }

        // dp[i]表示到达 索引 i 个格子时，能得到的最大分数
        int[] dp = new int[n];
        dp[0] = input_arr[0];
        // 使用 大顶堆 来维护窗口内[i-k,i)的最大dp值的索引
        // bigHeap 主要是为了优化时间复杂度。
        // int[]: {到索引 i 格子的最大得分，索引 i}
        PriorityQueue<int[]> bigHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        bigHeap.add(new int[]{dp[0], 0});

        for (int i = 1; i < n; i++) {
            // 如果队列不为空且队列头部的索引已经超出了跳跃范围，从队列中移除头部
            while (!bigHeap.isEmpty() && i - bigHeap.peek()[1] > k) {
                bigHeap.poll();
            }
            // 换句话说，dp[i] 是当前格子的分数加上能跳到这个格子的最大分数。
            dp[i] = input_arr[i] + bigHeap.peek()[0];

            bigHeap.add(new int[]{dp[i], i});
        }

        System.out.println(dp[n - 1]);
    }

}
