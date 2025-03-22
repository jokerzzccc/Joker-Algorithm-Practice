package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 最长的指定瑕疵度的元音子串
 * <p>
 * 题目描述
 * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。比如:
 * “a” 、 “aa”是元音字符串，其瑕疵度都为0
 * “aiur”不是元音字符串（结尾不是元音字符）
 * “abira”是元音字符串，其瑕疵度为2
 * 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
 * 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。
 * 输入描述
 * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
 * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
 * 输出描述
 * 输出为一个整数，代表满足条件的元音字符子串的长度。
 * <p>
 * 示例1
 * 输入：
 * 0
 * asdbuiodevauufgh
 * 输出：
 * 3
 * <p>
 * ————————————————
 * 题型分析：
 * 【中等】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE88 {

    public static final String META_STR = "aeiouAEIOU";

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int expectedFlawNum = Integer.parseInt(scanner.nextLine().trim());
        String inputStr = scanner.nextLine().trim();
        scanner.close();

        //
        // 记录字符串中所有元音字母的下标
        List<Integer> vowelIdxs = new ArrayList<>();
        for (int i = 0; i < inputStr.length(); i++) {
            if (META_STR.contains(String.valueOf(inputStr.charAt(i)))) {
                vowelIdxs.add(i);
            }
        }

        // left, right 定位的都是元音字母的下标
        // 窗口：[left, right] 其中 inputStr[left], inputStr[right] 都必定是元音字符
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while (right < vowelIdxs.size()) {
            // 窗口内的瑕疵数 = 窗口字符的总数量 - 窗口内的元音字符数量
            int windowFlawNum = vowelIdxs.get(right) - vowelIdxs.get(left) + 1 - (right - left + 1);
            if (windowFlawNum > expectedFlawNum) {
                // 如果瑕疵度超过了预期，左指针右移
                left++;
            } else {
                // 如果瑕疵度不超过预期，记录子串长度
                if (windowFlawNum == expectedFlawNum) {
                    maxLen = Math.max(maxLen, vowelIdxs.get(right) - vowelIdxs.get(left) + 1);
                }
                // 右指针右移
                right++;
            }
        }

        System.out.println(maxLen);

    }

}
