package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 完美走位
 * 题目描述
 * 在第一人称射击游戏中，玩家通过键盘的A、S、D、W四个按键控制游戏人物分别向左、向后、向右、向前进行移动，从而完成走位。
 * 假设玩家每按动一次键盘，游戏任务会向某个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，此时游戏任务必定会回到原点，则称此次走位为完美走位。
 * 现给定玩家的走位（例如：ASDA），请通过更换其中一段连续走位的方式使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
 * 请返回待更换的连续走位的最小可能长度。
 * 如果原走位本身是一个完美走位，则返回0。
 * 输入描述
 * 输入为由键盘字母表示的走位s，例如：ASDA
 * 说明：
 * 1、走位长度 1 ≤ s.length ≤ 100000（也就是长度不一定是偶数）
 * 2、s.length 是 4 的倍数
 * 3、s中只含有’A’, ‘S’, ‘D’, ‘W’ 四种字符
 * 输出描述
 * 输出为待更换的连续走位的最小可能长度。
 * <p>
 * 示例1
 * 输入
 * WASDAASD
 * 输出
 * 1
 * 说明
 * 将第二个A替换为W，即可得到完美走位
 * <p>
 * 示例2
 * 输入
 * AAAA
 * 输出
 * 3
 * 说明
 * 将其中三个连续的A替换为WSD，即可得到完美走位
 * ————————————————
 * 题型分析：
 * 【中等】滑动窗口
 * 思路：本题其实就类似求最小覆盖子串（leetcode 76）
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE85 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        scanner.close();

        // 1. 定义状态
        // 初始化左右指针和结果变量
        int left = 0;
        int right = 0;
        // 待更换的连续子串的最小可能长度
        int minSubLength = inputStr.length();
        // 统计[left, right] 窗口外的字符出现次数
        HashMap<Character, Integer> externalChar2CountMap = new HashMap<>();
        // 2. 数据初始化:
        // 2.1 化统计输入字符串中每个方向键的出现次数
        for (char c : inputStr.toCharArray()) {
            externalChar2CountMap.put(c, externalChar2CountMap.getOrDefault(c, 0) + 1);
        }
        // 2.2 更新右指针对应的计数
        externalChar2CountMap.put(inputStr.charAt(0), externalChar2CountMap.get(inputStr.charAt(0)) - 1);

        while (true) {
            // 计算当前外部字符的最大计数
            int externalCharMaxCount = externalChar2CountMap.values().stream()
                    .max(Integer::compare)
                    .orElse(0);

            // 核心：
            // 计算当前窗口长度和可替换的字符数
            int windowLength = right - left + 1;
            // 窗口内的被替换后被留下的字符数
            // 理论：窗口内的字符数量 = 补充完窗口外缺的字符数 + 自身剩下的字符数量（也要能够被替换为一个完美走位，即 % 4 == 0）
            // 然后寻找最小窗口
            int windowLeaveCharCnt = windowLength;
            for (int externalItemCount : externalChar2CountMap.values()) {
                // 理论：窗口外的字符是不被替换的，哪个字符缺了，就从区间内替换
                windowLeaveCharCnt -= externalCharMaxCount - externalItemCount;
            }

            // 核心判断：如果可替换字符数大于等于0且能被4整除，则更新结果变量
            if (windowLeaveCharCnt >= 0 && windowLeaveCharCnt % 4 == 0) {
                // 更新结果变量
                minSubLength = Math.min(minSubLength, windowLength);

                // 更新左指针并检查是否越界
                if (left < inputStr.length()) {
                    externalChar2CountMap.put(inputStr.charAt(left), externalChar2CountMap.get(inputStr.charAt(left)) + 1);
                    left++;
                } else {
                    break;
                }
            } else {
                // 更新右指针并检查是否越界
                right++;
                if (right >= inputStr.length()) {
                    break;
                }
                externalChar2CountMap.put(inputStr.charAt(right), externalChar2CountMap.get(inputStr.charAt(right)) - 1);
            }
        }

        System.out.println(minSubLength);

    }

}
