package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 寻找连续区间/数组连续和
 * 题目描述
 * 给定一个含有N个正整数的数组, 求出有多少个连续区间（包括单个正整数）, 它们的和大于等于x。
 * 输入描述
 * 第一行两个整数N x（0 < N <= 100000, 0 <= x <= 10000000)
 * 第二行有N个正整数（每个正整数小于等于100)。
 * 输出描述
 * 输出一个整数，表示所求的个数。
 * <p>
 * 用例1
 * 输入
 * 3 7
 * 3 4 7
 * 输出
 * 4
 * 第一行的3表示第二行数组输入3个数，第一行的7是比较数，用于判断连续数组是否大于该数；组合为 3 + 4; 3 + 4 + 7; 4 + 7; 7; 都大于等于指定的7；所以共四组.
 * <p>
 * 用例2
 * 输入
 * 10 10000
 * 1 2 3 4 5 6 7 8 9 10
 * 输出
 * 0
 * 所有元素的和小于10000，所以返回0。
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE102 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] firstRow = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = firstRow[0];
        int x = firstRow[1];
        int[] inputNums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 极端情况处理
        int allSum = Arrays.stream(inputNums)
                .sum();
        if (allSum < x) {
            System.out.println(0);
            return;
        } else if (allSum == x) {
            System.out.println(1);
            return;
        }

        int res = 0;

        int left = 0; // 滑动窗口的左端点
        int right = 0; // 滑动窗口的右端点
        int continuousIntervalCount = 0; // 记录连续区间个数
        // 当前窗口连续区间的和
        int sum = 0;
        // 滑动窗口 [left, right]，保持这个区间的和 >= x
        while (right < n) {
            sum += inputNums[right];
            // 收缩左区间，更新符合要求的连续区间个数
            while (sum >= x) {
                // 【核心】：如果当前区间和大于等于x，那么以 left 为起点的所有连续区间都符合要求
                // 公式解释：因为[left, right] 内的区间和都大于等于x，可以把这个区间当成一个整体，与后续数字的所有区间都是符合要求的。
                continuousIntervalCount += n - right;

                sum -= inputNums[left];
                left++;
            }

            right++;
        }

        System.out.println(continuousIntervalCount);

    }

}
