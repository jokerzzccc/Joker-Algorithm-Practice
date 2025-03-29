package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 找终点
 * 题目描述
 * 给定一个正整数数组，设为nums，最大为100个成员，求从第一个成员开始，正好走到数组最后一个成员，所使用的最少步骤数。
 * <p>
 * 要求：
 * <p>
 * 第一步必须从第一元素开始，且1<=第一步的步长<len/2;（len为数组的长度，需要自行解析）。
 * 从第二步开始，只能以所在成员的数字走相应的步数，不能多也不能少, 如果目标不可达返回**-1**，只输出最少的步骤数量。
 * 只能向数组的尾部走，不能往回走。
 * 输入描述
 * 由正整数组成的数组，以空格分隔，数组长度小于100，请自行解析数据数量。
 * <p>
 * 输出描述
 * 正整数，表示最少的步数，如果不存在输出**-1**
 * <p>
 * 示例1
 * 输入
 * 7 5 9 4 2 6 8 3 5 4 3 9
 * 输出
 * 2
 * 说明
 * 第一步： 第一个可选步长选择2，从第一个成员7开始走2步，到达9；
 * 第二步： 从9开始，经过自身数字9对应的9个成员到最后。
 * 示例2
 * 输入
 * 1 2 3 7 1 5 9 3 2 1
 * 输出
 * -1
 * 说明
 * 无
 * ————————————————
 * 题型分析：
 * 模拟题，暴力枚举，有点双指针的意思
 *
 * @author jokerzzccc
 * @date 2025/3/12
 */
public class COE38 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        int n = inputArr.length;
        int middle = n / 2;
        // 遍历所有可能的步长，计算最少的步骤数
        int minSteps = Integer.MAX_VALUE;
        for (int i = 1; i < middle; i++) {
            // 初始化步数为1，因为第一步已经走出
            int step = 1;
            // 将索引设为当前步长
            int index = i;
            // 只要没有走到数组的最后一个元素
            while (index < n - 1) {
                index += inputArr[index];
                step++;
            }
            // 如果恰好到达数组的最后一个元素
            if (index == n - 1) {
                minSteps = Math.min(minSteps, step);
            }
        }

        if (minSteps != Integer.MAX_VALUE) {
            // 输出最小步骤数
            System.out.println(minSteps);
        } else {
            System.out.println(-1);
        }

    }

}
