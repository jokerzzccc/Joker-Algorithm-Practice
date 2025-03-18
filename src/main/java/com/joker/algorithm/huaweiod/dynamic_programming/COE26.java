package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 查找充电设备组合
 * 题目描述
 * 某个充电站，可提供 n 个充电设备，每个充电设备均有对应的输出功率。
 * 任意个充电设备组合的输出功率总和，均构成功率集合 P 的 1 个元素。
 * 功率集合 P 的最优元素，表示最接近充电站最大输出功率 p_max 的元素。
 * 输入描述
 * 输入为 3 行：
 * 第 1 行为充电设备个数 n
 * 第 2 行为每个充电设备的输出功率
 * 第 3 行为充电站最大输出功率 p_max
 * 备注
 * 充电设备个数 n > 0
 * 最优元素必须小于或等于充电站最大输出功率 p_max
 * 输出描述
 * 功率集合 P 的最优元素
 * 示例1
 * 输入
 * 4
 * 50 20 20 60
 * 90
 * 输出
 * 90
 * 说明
 * 当充电设备输出功率50、20、20组合时，其输出功率总和为90，最接近充电站最大充电输出功率，因此最优元素为90。
 * <p>
 * 示例2
 * 输入
 * 2
 * 50 40
 * 30
 * 输出
 * 0
 * 说明
 * 所有充电设备的输出功率组合，均大于充电站最大充电输出功率30，此时最优元素值为0。
 * <p>
 * 示例3
 * 输入
 * 3
 * 2 3 10
 * 9
 * 输出
 * 5
 * 说明
 * 选择功率为2，3的设备构成功率集合，总功率为5，最接近最大功率9。不能选择设备10，因为已经超过了最大功率9。
 * <p>
 * 示例3
 * 输入
 * 3
 * 1 2 3
 * 5
 * 输出
 * 5
 * 说明
 * 无
 * ————————————————
 * 题型分析
 * 【中等】动态规划（0-1背包问题）
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE26 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入充电设备个数
        int n = Integer.parseInt(scanner.nextLine().trim());
        // 每个设备的功率
        int[] inputPowerArray = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 最大功率
        int pMax = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        // 设备的最小功率（减少后面 DP 遍历时的时间复杂度）
        int minPower = Arrays.stream(inputPowerArray).min().getAsInt();
        // dp[i][p] 表示前 i 个充电设备组合，且功率不超过 p 的最大组合功率数
        int[][] dp = new int[n + 1][pMax + 1];
        for (int i = 1; i <= n; i++) {
            for (int p = minPower; p <= pMax; p++) {
                // 状态转移方程
                if (inputPowerArray[i - 1] > p) {
                    // 如果当前设备功率大于当前最大功率，则不选择该设备
                    dp[i][p] = dp[i - 1][p];
                } else {
                    // 选择当前设备：【当前设备功率 + （前 i - 1 个设备组合，最大功率不超过 p - inputPowerArray[i - 1] 的最大功率）】
                    dp[i][p] = Math.max(dp[i - 1][p], dp[i - 1][p - inputPowerArray[i - 1]] + inputPowerArray[i - 1]);
                }

            }

        }

        System.out.println(dp[n][pMax]);
    }

}
