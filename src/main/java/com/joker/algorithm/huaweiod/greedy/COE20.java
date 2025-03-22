package com.joker.algorithm.huaweiod.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串变换最小字符串
 * <p>
 * 题目描述
 * 给定一个字符串 s，最多只能进行一次变换，返回变换后能得到的最小字符串(按照字典序进行比较)变换规则: 交换字符串中任意两个不同位置的字符
 * 输入描述
 * 一串小写字母组成的字符串 s
 * 输出描述
 * 按照要求进行变换得到的最小字符串。
 * <p>
 * 用例1
 * 输入
 * abcdef
 * 输出
 * abcdef
 * 说明
 * abcdef已经是最小字符串，不需要交换。
 * <p>
 * 用例2
 * 输入
 * bcdefa
 * 输出
 * acdefb
 * 说明
 * a和b进行位置交换，可以得到最小字符串
 * --------------------
 * 题型分析
 * 【简单】贪心
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        // 排序
        char[] sortedArr = s.toCharArray();
        Arrays.sort(sortedArr);

        StringBuilder sb = new StringBuilder(s);
        // 寻找原字符串与排序后的第一个不相匹配的字符
        for (int i = 0; i < s.length(); i++) {
            if (sortedArr[i] != sb.charAt(i)) {
                // 待交换的字符（大）
                char temp = sb.charAt(i);
                // 寻找该字符在原字符串中(最后面那个)的下标索引
                int swapIndex = -1;
                for (int j = i + 1; j < s.length(); j++) {
                    if (sb.charAt(j) == sortedArr[i]) {
                        swapIndex = j;
                    }
                }

                sb.setCharAt(i, sortedArr[i]);
                sb.setCharAt(swapIndex, temp);
                break;
            }
        }

        System.out.println(sb.toString());
    }

}
