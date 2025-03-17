package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 单词接龙
 * 题目描述
 * 单词接龙的规则是：
 * 可用于接龙的单词首字母必须要前一个单词的尾字母相同；
 * 当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
 * 现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，
 * 请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
 * 输入描述
 * 输入的第一行为一个非负整数，表示起始单词在数组中的索引K，0 <= K < N ；
 * 输入的第二行为一个非负整数，表示单词的个数N；
 * 接下来的N行，分别表示单词数组中的单词。
 * 备注：
 * 单词个数N的取值范围为[1, 20]；
 * 单个单词的长度的取值范围为[1, 30]；
 * 输出描述
 * 输出一个字符串，表示最终拼接的单词串。
 * 示例1
 * 输入
 * 0
 * 6
 * word
 * dd
 * da
 * dc
 * dword
 * d
 * 输出
 * <p>
 * worddwordda
 * 说明
 * 先确定起始单词word，再接以d开头的且长度最长的单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出worddwordda。
 * 示例2
 * 输入
 * 4
 * 6
 * word
 * dd
 * da
 * dc
 * dword
 * d
 * 输出
 * dwordda
 * 说明
 * 先确定起始单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出dwordda。
 * ————————————————
 * 【简单】字符串 + 优先级队列
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE31 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine().trim());
        int length = Integer.parseInt(scanner.nextLine().trim());
        // 输入字符数组
        String[] inputArray = new String[length];
        // K-起始字符，V-相同起始字符的优先级队列
        Map<Character, PriorityQueue<String>> map = new HashMap<>();

        // 遍历输入字符串数组
        for (int i = 0; i < length; i++) {
            String currWord = scanner.nextLine().trim();
            inputArray[i] = currWord;
            // 为 index 不需要加入 MAP，因为直接算进结果了
            if (i == index) {
                continue;
            }
            char currBeginChar = currWord.charAt(0);
            map.putIfAbsent(currBeginChar, new PriorityQueue<>((a, b) -> {
                // 符合规则的优先级队列
                if (a.length() != b.length()) {
                    // 优先选择长度最长的单词；
                    return b.length() - a.length();
                } else {
                    // 如果长度相等，选择字典序最小的单词。
                    return a.compareTo(b);
                }
            }));
            map.get(currBeginChar).offer(currWord);
        }

        // 构造输出结果
        StringBuilder sb = new StringBuilder();
        sb.append(inputArray[index]);
        while (true) {
            char currBeginChar = sb.charAt(sb.length() - 1);
            if (map.containsKey(currBeginChar)) {
                String currWord = map.get(currBeginChar).poll();
                sb.append(currWord);
            } else {
                break;
            }
        }

        System.out.println(sb);
        scanner.close();
    }

}
