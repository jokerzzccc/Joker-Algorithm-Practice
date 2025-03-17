package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 字符串重新排序
 * 题目描述
 * 给定一个字符串s，s包括以空格分隔的若干个单词，请对s进行如下处理后输出：
 * 1、单词内部调整：对每个单词字母重新按字典序排序
 * 2、单词间顺序调整：
 * 统计每个单词出现的次数，并按次数降序排列
 * 次数相同，按单词长度升序排列
 * 次数和单词长度均相同，按字典升序排列
 * 请输出处理后的字符串，每个单词以一个空格分隔
 * 输入描述
 * 一行字符串，每个字符取值范围：[a-zA-z0-9]以及空格，字符串长度范围：[1，1000]
 * 输出描述
 * 输出处理后的字符串，每个单词以一个空格分隔。
 * 示例1
 * 输入
 * This is an apple
 * 输出
 * an is This aelpp
 * <p>
 * 示例2
 * 输入
 * My sister is in the house not in the yard
 * 输出
 * in in eht eht My is not adry ehosu eirsst
 * 说明
 * ————————————————
 * 题型分析：
 * 【简单】字符串 + 排序（用 Map, List）
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE96 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] inputStr = scanner.nextLine().trim().split(" ");
        scanner.close();

        // K-单词，V-次数
        Map<String, Integer> word2CountMap = new HashMap<>();
        for (String item : inputStr) {
            char[] currChars = item.toCharArray();
            Arrays.sort(currChars);
            word2CountMap.put(String.valueOf(currChars), word2CountMap.getOrDefault(String.valueOf(currChars), 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(word2CountMap.entrySet());
        entries.sort((o1, o2) -> {
            if (o1.getValue() != o2.getValue()) {
                // 排序规则一：统计每个单词出现的次数，并按次数降序排列
                return o2.getValue() - o1.getValue();
            } else if (o1.getKey().length() != o2.getKey().length()) {
                // 排序规则二：次数相同，按单词长度升序排列
                return o1.getKey().length() - o2.getKey().length();
            } else {
                // 排序规则三：次数和单词长度均相同，按字典升序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            for (Integer i = 0; i < entry.getValue(); i++) {
                res.append(entry.getKey()).append(" ");
            }
        }
        System.out.println(res.toString().trim());

    }

}
