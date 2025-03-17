package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 连续字母长度
 * 题目描述
 * 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
 * 输入描述
 * 第一行有一个子串(1<长度<=100)，只包含大写字母。
 * 输出描述
 * 输出连续出现次数第k多的字母的次数。
 * 示例1
 * 输入
 * AAAAHHHBBCDHHHH
 * 3
 * 输出
 * 2
 * 说明
 * 同一字母连续出现的最多的是A和H，四次；
 * 第二多的是H，3次，但是H已经存在4个连续的，故不考虑；
 * 下个最长子串是BB，所以最终答案应该输出2。
 * 示例2
 * 输入
 * AABAAA
 * 2
 * 输出
 * 1
 * 说明
 * 同一字母连续出现的最多的是A，三次；
 * 第二多的还是A，两次，但A已经存在最大连续次数三次，故不考虑；
 * 下个最长子串是B，所以输出1。
 * 示例3
 * 输入
 * ABC
 * 4
 * 输出
 * -1
 * 说明
 * 只含有3个包含同一字母的子串，小于k，输出-1
 * 示例4
 * 输入
 * ABC
 * 2
 * 输出
 * 1
 * 说明
 * 三个子串长度均为1，所以此时k = 1，k=2，k=3这三种情况均输出1。
 * ————————————————
 * 题型分析：
 * 【中等】 字符串 + 双指针
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE14 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine().trim();
        int k = scanner.nextInt();
        scanner.close();

        // 用于存储每个字符的最长连续子串长度
        HashMap<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        int right = 1;
        while (right <= inputStr.length()) {
            char curr = inputStr.charAt(left);
            while (right < inputStr.length() && inputStr.charAt(right) == curr) {
                right++;
            }
            int currLength = right - left;
            charMap.put(curr, Math.max(charMap.getOrDefault(curr, 0), currLength));
            left = right;
            right = left + 1;
        }

        // 将所有字符的最长子串长度存储到ArrayList中
        ArrayList<Integer> allMaxLengths = new ArrayList<>(charMap.values());
        // 降序排序(因为求的是第 K 长)
        Collections.sort(allMaxLengths, Collections.reverseOrder());
        // 如果k大于 allMaxLengths 中的元素数量，返回-1，否则返回第k大的长度值
        if (k > allMaxLengths.size()) {
            System.out.println(-1);
        } else {
            System.out.println(allMaxLengths.get(k - 1));
        }

    }

}
