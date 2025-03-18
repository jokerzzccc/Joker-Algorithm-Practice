package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Scanner;

/**
 * 最长子字符串的长度
 * 题目描述
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出’l’、‘o’、‘x’ 字符都恰好出现了偶数次最长子字符串的长度。
 * 输入描述
 * 输入是一串小写的字母组成的字符串s。
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 * 输出描述
 * 输出是一个整数
 * <p>
 * 用例1
 * 输入
 * alolobo
 * 输出
 * 6
 * 说明：最长子字符串之一是 “alolob”，它包含 ‘l’，'o’各 2 个，以及 0 个 ‘x’ 。
 * <p>
 * 用例2
 * 输入
 * looxdolx
 * 输出
 * 7
 * 说明
 * 最长子字符串是 “oxdolxl”，由于是首尾连接在一起的，所以最后一个 ‘x’ 和开头的 'l’是连接在一起的，此字符串包含 2 个 ‘l’ ，2个 ‘o’ ，2个 ‘x’ 。
 * <p>
 * 用例3
 * 输入
 * bcbcbc
 * 输出
 * 6
 * 说明
 * 字符串 “bcbcbc” 本身就是最长的，因为 ‘l’、‘o’、‘x’ 都出现了 0 次。
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口
 * 思考：滑动窗口情况也可能是，left 固定，right 移动，但是有固定移动上限 n。本质还是穷举。
 * 思路：
 * 1. 外层循环
 * 从字符串的每个字符开始，遍历整个字符串。这个循环的目的是以每个字符作为子字符串的起始点。
 * 2. 内层循环
 * 从外层循环指定的起始点开始，遍历字符串的其余部分。这个循环的目的是检查从当前起始点开始的所有可能的子字符串。
 * 对于每个可能的子字符串，计算字符 ‘l’、‘o’ 和 ‘x’ 的出现次数。
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE71 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        scanner.close();

        int maxSubLength = 0;
        int n = inputStr.length();
        // 策略：
        // 外层循环，从字符串的每一个字符开始检查
        for (int left = 0; left < n; left++) {
            int l_cnt = 0, o_cnt = 0, x_cnt = 0;
            // 内层循环，从当前字符开始，遍历整个字符串
            // 因为是个环，以当前字符为起始，最长子串长度就都是 n
            for (int right = 0; right < n; right++) {
                // 计算当前字符的索引，处理环形字符串的情况
                int index = (left + right) % n;
                char ch = inputStr.charAt(index);

                if (ch == 'l') {
                    l_cnt++;
                } else if (ch == 'o') {
                    o_cnt++;
                } else if (ch == 'x') {
                    x_cnt++;
                }

                // 更新最大
                if (l_cnt % 2 == 0 && o_cnt % 2 == 0 && x_cnt % 2 == 0) {
                    maxSubLength = Math.max(maxSubLength, right + 1);
                }
            }

        }

        System.out.println(maxSubLength);
    }

}
