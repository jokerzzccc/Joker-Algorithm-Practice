package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最少交换次数
 * 题目描述
 * 给出数字K,请输出所有结果小于K的整数组合到一起的最少交换次数。
 * 组合一起是指满足条件的数字相邻，不要求相邻后在数组中的位置。
 * 数据范围：
 * 100 <= K <= 100
 * 100 <= 数组中数值 <= 100
 * 输入描述
 * 第一行输入数组：1 3 1 4 0
 * 第二行输入K数值：2
 * 输出描述
 * 第一行输出最少交换次数：1
 * 示例1
 * 输入
 * 1 3 1 4 0
 * 2
 * 输出
 * 1
 * 说明
 * 小于2的表达式是1 1 0, 共三种可能将所有符合要求数字组合一起，最少交换1次。
 * ————————————————
 * 题型分析:
 * 类似 leetcode 1151 最少交换次数来组合所有的1
 * 【中等】一维数组 + 滑动窗口 + 双指针
 *
 * @author jokerzzccc
 * @date 2025/3/14
 */
public class COE57 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(scanner.nextLine());

        // 小于 K 的数量
        int lowerCnt = 0;
        for (int j : inputArr) {
            if (j < k) {
                lowerCnt++;
            }
        }
        if (lowerCnt <= 1) {
            System.out.println(0);
            return;
        }

        // 最小交换次数
        int minSwapCnt = 0;
        // 初始值 = 前 lowerCnt 窗口的大于 K 的数量
        for (int i = 0; i < lowerCnt; i++) {
            if (inputArr[i] >= k) {
                minSwapCnt++;
            }
        }
        // 滑动窗口：维持 lowerCnt 大小的窗口，计算当前窗口内的交换次数（大于 K 的数量），并更新最小交换次数
        int tempSwapCnt = minSwapCnt;
        for (int right = lowerCnt; right < inputArr.length; right++) {
            int left = right - lowerCnt;
            // 每次左右指针都会向前移动一步，因为只用判断边界的大小()，就可以更新窗口内的交换次数
            if (inputArr[left] >= k && inputArr[right] < k) {
                // 左边移除的数字 >= K && 右边移入的数字 < K
                // 则交换次数减1
                tempSwapCnt--;
            } else if (inputArr[left] < k && inputArr[right] >= k) {
                // 左边移除的数字 < K && 右边移入的数字 >= K
                tempSwapCnt++;
            }

            minSwapCnt = Math.min(minSwapCnt, tempSwapCnt);
        }

        System.out.println(minSwapCnt);

        scanner.close();
    }

}
