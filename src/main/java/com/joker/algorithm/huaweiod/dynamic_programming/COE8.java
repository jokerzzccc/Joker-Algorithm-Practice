package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大报酬
 * 题目描述
 * 小明每周上班都会拿到自己的工作清单，工作清单内包含 n 项工作，每项工作都有对应的耗时时间（单位 h）和报酬，工作的总报酬为所有已完成工作的报酬之和，那么请你帮小明安排一下工作，保证小明在指定的工作时间内工作收入最大化。
 * 输入描述
 * T 代表工作时长（单位 h， 0 < T < 1000000），
 * n 代表工作数量（ 1 < n ≤ 3000）。
 * 接下来是 n 行，每行包含两个整数 t，w。
 * t 代表该工作消耗的时长（单位 h， t > 0），w 代表该项工作的报酬。
 * 输出描述
 * 输出小明指定工作时长内工作可获得的最大报酬。
 * <p>
 * 示例1
 * 输入
 * 40 3
 * 20 10
 * 20 20
 * 20 5
 * 输出
 * 30
 * ————————————————
 * 题型分析
 * 【中等】动态规划（0-1背包问题）
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE8 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        // 工作时长
        int T = Integer.parseInt(inputArr[0]);
        // 工作数量
        int n = Integer.parseInt(inputArr[1]);
        int[][] availableWorkArr = new int[n][2];
        for (int i = 0; i < n; i++) {
            availableWorkArr[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        // 记录工作清单中最小的耗时(为后面减少时间复杂度)
        int minTime = Integer.MAX_VALUE;
        for (int[] work : availableWorkArr) {
            minTime = Math.min(minTime, work[0]);
        }

        // dp 数组
        // dp[i][t] 表示，对于前 i 个工作项，在时间 T 内，能够获得的最大报酬
        // 默认值都为 0 dp[0][0] = 0;
        int[][] dp = new int[n + 1][T + 1];
        // 动态规划（自底向上）
        for (int i = 1; i <= n; i++) {
            for (int t = minTime; t <= T; t++) {
                // 选择当前 i 工作
                // availableWorkArr[i - 1][0] > t 则无法完成该项工作，此时最大报酬为 0。
                // availableWorkArr[i - 1][0] <= t 则可以完成该项工作，
                // 此时最大报酬为 【当前项报酬 + 前一项报酬在 t-availableWorkArr[i - 1][0] 时间内能获得的最大报酬】
                int current = availableWorkArr[i - 1][0] > t ? 0 : availableWorkArr[i - 1][1] + dp[i - 1][t - availableWorkArr[i - 1][0]];
                // 状态转移方程
                // dp[i - 1][j] 不选当前工作
                dp[i][t] = Math.max(dp[i - 1][t], current);
            }
        }

        System.out.println(dp[n][T]);
    }

}
