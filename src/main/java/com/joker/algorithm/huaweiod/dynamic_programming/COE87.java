package com.joker.algorithm.huaweiod.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 玩牌高手
 * 题目描述
 * 给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，
 * 请计算所有轮结束后其可以获得的最高总分数。
 * <p>
 * 选择规则如下：
 * 在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
 * 选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数，若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
 * 选手的初始总分数为0，且必须依次参加每一轮
 * <p>
 * 输入描述
 * 第一行为一个小写逗号分割的字符串，表示n轮的牌面分数，1<= n <=20。
 * 分数值为整数，-100 <= 分数值 <= 100。
 * 不考虑格式问题。
 * 输出描述
 * 所有轮结束后选手获得的最高总分数。
 * <p>
 * 示例1
 * 输入
 * 1,-5,-6,4,3,6,-2
 * 输出
 * 11
 * 说明
 * ————————————————
 * 题型分析：
 * 【简单】动态规划【0-1背包】
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE87 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputArray = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        int n = inputArray.length;
        // dp[i] 表示前 i 个牌面组合，其最大总分数
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 不选当前的牌面的最大总分数
            int last = 0;
            if (i <= 3) {
                last = 0;
            } else {
                last = dp[i - 3];
            }

            // 状态转移方程
            dp[i] = Math.max(last, dp[i - 1] + inputArray[i - 1]);
        }

        System.out.println(dp[n]);

    }

}
