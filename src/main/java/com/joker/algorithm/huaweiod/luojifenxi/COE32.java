package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 跳房子I
 * 题目描述
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * <p>
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
 * <p>
 * 跳房子的过程中，可以向前跳，也可以向后跳。
 * <p>
 * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
 * <p>
 * 如果有，请输出索引和最小的步数组合。
 * <p>
 * 注意：
 * <p>
 * 数组中的步数可以重复，但数组中的元素不能重复使用。
 * 提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 * 输入描述
 * 第一行输入为每回合可能连续跳的步数，它是int整数数组类型。
 * <p>
 * 第二行输入为房子总格数count，它是int整数类型。
 * <p>
 * 备注
 * count ≤ 1000
 * 0 ≤ steps.length ≤ 5000
 * -100000000 ≤ steps ≤ 100000000
 * 输出描述
 * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）
 * <p>
 * 示例1
 * 输入
 * <p>
 * [1,4,5,2,2]
 * 7
 * 输出
 * <p>
 * [5, 2]
 * 说明
 * <p>
 * 示例2
 * 输入
 * <p>
 * [-1,2,4,9,6]
 * 8
 * <p>
 * 输出
 * <p>
 * [-1, 9]
 * 说明
 * <p>
 * 此样例有多种组合满足两回合跳到最后，譬如：[-1,9]，[2,6]，其中[-1,9]的索引和为0+3=3，[2,6]的索和为1+4=5，所以索引和最小的步数组合[-1,9]
 * ————————————————
 * 题型分析：
 * 有点类似【nSum 问题】,就是两数之和: 暴力循环
 *
 * @author jokerzzccc
 * @date 2025/3/12
 */
public class COE32 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] inputSteps = Arrays.stream(input.substring(1, input.length() - 1).split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int count = Integer.parseInt(scanner.nextLine());

        // 初始化最小索引和为最大整数值
        int minIndexSum = Integer.MAX_VALUE;
        // 初始化答案为空字符串
        String ans = "";
        for (int idx1 = 0; idx1 < inputSteps.length; idx1++) {
            for (int idx2 = idx1 + 1; idx2 < inputSteps.length; idx2++) {
                int step1 = inputSteps[idx1];
                int step2 = inputSteps[idx2];

                // 如果两个步数之和等于count
                if (step1 + step2 == count) {
                    int currSum = idx1 + idx2;
                    if (currSum < minIndexSum) {
                        minIndexSum = currSum;
                        ans = "[" + step1 + "," + step2 + "]";
                    }
                    // 找到满足条件的组合后，跳出内层循环
                    break;
                }
            }
        }

        System.out.println(ans);
        scanner.close();

    }

}
