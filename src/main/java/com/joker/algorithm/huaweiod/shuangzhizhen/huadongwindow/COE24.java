package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 补种未成活胡杨
 * 题目描述
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植N棵胡杨（编号1-N），排成一排。
 * 一个月后，有M棵胡杨未能成活。
 * 现可补种胡杨K棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 * 输入描述
 * N 总种植数量，1 <= N <= 100000
 * M 未成活胡杨数量，M 个空格分隔的数，按编号从小到大排列，1 <= M <= N
 * K 最多可以补种的数量，0 <= K <= M
 * 输出描述
 * 最多的连续胡杨棵树
 * <p>
 * 示例1
 * 输入
 * 5
 * 2
 * 2 4
 * 1
 * 输出
 * 3
 * 说明
 * 补种到2或4结果一样，最多的连续胡杨棵树都是3。
 * <p>
 * 示例2
 * 输入
 * 10
 * 3
 * 2 4 7
 * 1
 * 输出
 * 6
 * 说明
 * 种第7棵树，最多连续胡杨树棵数位6（5，6，7，8，9，10）
 * ————————————————
 * 题型分析：
 * 【简单】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE24 {

    public static void main(String[] args) {
        // 输出处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        int[] deadTrees = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();

        // 极端条件处理
        if (k == m) {
            System.out.println(n);
            return;
        }

        // 表示一排树，0-活着的，1-死亡的
        int[] window = new int[n];

        Arrays.fill(window, 0);
        for (int deadTree : deadTrees) {
            window[deadTree - 1] = 1;
        }

        int left = 0;
        int right = 0;
        // 滑动窗口：[left, right] 区间内的未成活的树个数
        int currSum = 0;
        int maxSubContinuousCnt = 0;
        while (right < n) {
            currSum += window[right];
            right++;

            // 收缩左边界
            while (currSum > k && left < right) {
                currSum -= window[left];
                left++;
            }

            // 更新最大成活区域的长度
            maxSubContinuousCnt = Math.max(maxSubContinuousCnt, right - left);
        }

        System.out.println(maxSubContinuousCnt);

    }

}
