package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 代表团坐车
 * <p>
 * 题目描述
 * 某组织举行会议，来了多个代表团同时到达，接待处只有一辆汽车，可以同时接待多个代表团，为了提高车辆利用率，请帮接待员计算可以坐满车的接待方案，输出方案数量。
 * <p>
 * 约束:
 * 一个团只能上一辆车，并且代表团人数 (代表团数量小于30，每个代表团人数小于30)小于汽车容量(汽车容量小于100)
 * 需要将车辆坐满
 * <p>
 * 输入描述
 * 第一行 代表团人数，英文逗号隔开，代表团数量小于30，每个代表团人数小于30
 * 第二行 汽车载客量，汽车容量小于100
 * <p>
 * 输出描述
 * 坐满汽车的方案数量
 * 如果无解输出0
 * <p>
 * 示例1
 * 输入
 * 5,4,2,3,2,4,9
 * 10
 * 输出
 * 4
 * 说明
 * 解释以下几种方式都可以坐满车，所以，优先接待输出为4
 * [2,3,5]
 * [2.4.4]
 * [2.3.5]
 * [2,4,4]
 * ————————————————
 * 题型分析：
 * 【困难】动态规划-01背包（装满背包）+滚动数组优化
 * 思路：参考链接：https://www.nowcoder.com/discuss/526649664700018688
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2019 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input_arr = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int capacity = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        //
        // 代表团人数排序
        Arrays.sort(input_arr);
        int n = input_arr.length;
        // dp[i] 表示 载客量为i 时的方案数
        int[] dp = new int[capacity + 1];
        // 载客量为0时，方案数为1（不接待任何代表团）
        dp[0] = 1;

        // 遍历物品
        for (int value : input_arr) {
            // 遍历背包容量
            for (int j = capacity; j >= value; j--) {
                // 转移方程: 表示加上接待当前代表团后的方案数
                dp[j] += dp[j - value];
            }
        }

        System.out.println(dp[capacity]);
    }

}
