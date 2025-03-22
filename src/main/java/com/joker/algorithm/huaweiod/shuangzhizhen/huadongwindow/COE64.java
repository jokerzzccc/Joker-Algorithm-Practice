package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 关联子串
 * 题目描述
 * 给定两个字符串 str1 和 str2
 * str1 进行排列组合只要有一个为 str2 的子串则认为 str1 是 str2 的关联子串请返回子串在str2 的起始位置，若不是关联子串则返回-1.
 * 输入描述
 * 无
 * 输出描述
 * 无
 * 用例
 * 测试用例1：
 * 1、输入
 * abc abcabc
 * 2、输出
 * 0
 * 3、说明
 * str1的排列组合abc出现在str2的索引0处。
 * <p>
 * 测试用例2：
 * 1、输入
 * xyz abcdef
 * 2、输出
 * -1
 * 3、说明
 * str1的任何排列组合都不在str2中。
 * -------------------
 * 题型分析：
 * 【中等】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE64 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStr = scanner.nextLine().split(" ");
        String str1 = inputStr[0];
        String str2 = inputStr[1];
        scanner.close();

        char[] charArray1 = str1.toCharArray();
        int n1 = charArray1.length;
        char[] charArray2 = str2.toCharArray();
        int n2 = charArray2.length;

        if (n1 > n2) {
            System.out.println(-1);
            return;
        }

        // 统计字符的出现次数
        Map<Character, Integer> targetChar2CntMap = new HashMap<>();
        for (char c : charArray1) {
            targetChar2CntMap.put(c, targetChar2CntMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = n1 - 1;
        // 最后结果
        int minIndex = -1;

        // 初始化第一个子串的字符数量
        Map<Character, Integer> currChar2CntMap = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            currChar2CntMap.put(charArray2[i], currChar2CntMap.getOrDefault(charArray2[i], 0) + 1);
        }
        // 滑动窗口：对比 [left, right] 的子串的字符数是否等于目标串
        while (right < n2) {
            boolean isFound = true;
            // 判断当前子串的字符次数是否满足条件
            for (Map.Entry<Character, Integer> entry : targetChar2CntMap.entrySet()) {
                if (currChar2CntMap.getOrDefault(entry.getKey(), 0) != entry.getValue()) {
                    isFound = false;
                    break;
                }
            }

            if (!isFound) {
                // 移动指针，更新变量
                right++;
                if (right >= n2) {
                    break;
                }
                currChar2CntMap.put(charArray2[right], currChar2CntMap.getOrDefault(charArray2[right], 0) + 1);
                currChar2CntMap.put(charArray2[left], currChar2CntMap.get(charArray2[left]) - 1);
                left++;
            } else {
                minIndex = left;
                break;
            }

        }

        System.out.println(minIndex);

    }

}
