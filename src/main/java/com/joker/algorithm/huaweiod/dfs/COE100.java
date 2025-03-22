package com.joker.algorithm.huaweiod.dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 用户调度问题
 * 题目描述
 * 在通信系统中，一个常见的问题是对用户进行不同策略的调度，会得到不同的系统消耗和性能.
 * 假设当前有n个待串行调度用户，每个用户可以使用A/B/C三种不同的调度策略，不同的策略会消耗不同的系统资源。请你根据如下规则进行用户调度，并返回总的消耗资源数
 * 规则:
 * 1.相邻的用户不能使用相同的调度策略，例如，第1个用户使用了A策略，则第2个用户只能使用B或者C策好
 * 2.对单个用户而言，不同的调度策略对系统资源的消耗可以归一化后抽象为数值。
 * 例如，某用户分别使用A/B/C策略的系统消耗分别为15/8/17。
 * 3.每个用户依次选择当前所能选择的对系统资源消耗最少的策略(局部最优)，如果有多个满足要求的策略，选最后一个。
 * 输入描述
 * 第一行表示用户个数n
 * 接下来每一行表示一个用户分别使用三个策略的系统消耗resAresBresC
 * 输出描述
 * 最优策略组合下的总的系统资源消耗数
 * <p>
 * 用例1：
 * 输入
 * 3
 * 15 8 17
 * 12 20 9
 * 11 7 5
 * 输出
 * 24
 * 说明
 * 1号用户使用B策略，2号用户使用C策略，3号用户使用B策略。系统资源消耗：8+9+7=24。
 * ---------------------
 * 题型分析
 * 【中等】 dfs
 * 思路：注意，本题每一步的都是局部最优，而不是全局最优
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE100 {

    private static int res = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        dfs(arr, 0, -1);
        System.out.println(res);

    }

    private static void dfs(int[][] arr, int userIndex, int preStrategyIndex) {
        if (userIndex == arr.length) {
            return;
        }

        // 当前最小消耗资源
        int currMin = Integer.MAX_VALUE;
        // 当前选择的策略索引（0,1,2）
        int currStrategyIndex = -1;
        for (int i = 0; i < 3; i++) {
            if (i != preStrategyIndex) {
                if (currMin >= arr[userIndex][i]) {
                    currStrategyIndex = i;
                    currMin = arr[userIndex][i];
                }
            }
        }
        res += currMin;

        dfs(arr, userIndex + 1, currStrategyIndex);
    }

}
