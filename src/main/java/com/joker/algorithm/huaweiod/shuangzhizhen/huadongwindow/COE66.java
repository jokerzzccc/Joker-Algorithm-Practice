package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 恢复数字序列
 * 题目描述
 * 对于一个连续正整数组成的序列，可以将其拼接成一个字符串，再将字符串里的部分字符打乱顺序。
 * 如序列8 9 10 11 12，拼接成的字符串为89101112，打乱一部分字符后得到90811211，原来的正整数10就被拆成了0和1。
 * 现给定一个按如上规则得到的打乱字符的字符串，请将其还原成连续正整数序列，并输出序列中最小的数字。
 * 输入描述
 * 输入一行，为打乱字符的字符串和正整数序列的长度，两者间用空格分隔，字符串长度不超过200，正整数不超过1000，保证输入可以还原成唯一序列。
 * 输出描述
 * 输出一个数字，为序列中最小的数字。
 * <p>
 * 示例1
 * 输入
 * 19801211 5
 * 输出
 * 8
 * <p>
 * 示例2
 * 输入
 * 432111111111 4
 * 输出
 * 111
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口
 * 思考：滑动窗口不一定是需要两个指针，也可能只需要一个左指针和窗口长度。然后穷举
 * 思路：题目的核心是通过排列组合字符找到能组成连续正整数序列的方案，并返回其中最小的数字。
 * 统计打乱字符的字符串中各字符的数量。然后遍历所有可能的连续整数序列，对于每个序列，检查序列中的字符数量是否与打乱字符的字符串中各字符数量一致。
 * 如果找到一个匹配的序列，输出序列中最小的数字并结束循环。
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE66 {

    // 最大数字
    public static final int MAX_NUM = 1000;

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String mixedStr = input[0];
        // 原连续子序列的区间长度
        int targetLength = Integer.parseInt(input[1]);
        scanner.close();

        // 统计原字符串中单个字符的出现次数
        Map<Character, Integer> char2CntMap = new HashMap<>();
        for (char ch : mixedStr.toCharArray()) {
            char2CntMap.put(ch, char2CntMap.getOrDefault(ch, 0) + 1);
        }

        // 左指针（因为是正整数，所以从 1 开始）
        int left = 1;

        // 窗口里面的单个字符的出现次数
        Map<Character, Integer> windowChar2CntMap = new HashMap<>();
        // 初始化窗口字符次数
        for (int i = 1; i <= targetLength; i++) {
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                windowChar2CntMap.put(str.charAt(j), windowChar2CntMap.getOrDefault(str.charAt(j), 0) + 1);
            }
        }

        // 窗口长度固定为 targetLength
        while (left <= MAX_NUM - targetLength + 1) {
            // [left, right] 窗口
            int right = left + targetLength - 1;
            // 当前连续序列是否符合要求
            boolean isMatched = true;

            if (left != 1){
                // 更新窗口数据：根据窗口的跟新，更新字符出现次数
                String rightStr = String.valueOf(right);
                for (int i = 0; i < rightStr.length(); i++) {
                    windowChar2CntMap.put(rightStr.charAt(i), windowChar2CntMap.getOrDefault(rightStr.charAt(i), 0) + 1);
                }
            }


            // 对比：比较当前序列和目标序列的字符出现次数是否一致
            for (Character ch : char2CntMap.keySet()) {
                if (!windowChar2CntMap.containsKey(ch) || windowChar2CntMap.get(ch) != char2CntMap.get(ch)) {
                    isMatched = false;
                    break;
                }
            }

            // 判断：找到匹配的序列，退出循环
            if (isMatched) {
                break;
            }

            // 更新窗口数据，缩减左边界
            String leftStr = String.valueOf(left);
            for (int i = 0; i < leftStr.length(); i++) {
                windowChar2CntMap.put(leftStr.charAt(i), windowChar2CntMap.get(leftStr.charAt(i)) - 1);
            }
            // 移动左指针
            left++;
        }

        System.out.println(left);

    }

}
