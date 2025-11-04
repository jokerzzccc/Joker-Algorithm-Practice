package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 思路：
 * 滑动窗口，
 * 类似 leetcode-03-无重复字符的最长子串
 *
 * @author jokerzzccc
 * @date 2025/11/4
 */
public class JokerReal05 {

    public static void main(String[] args) {
        // 输入处理
        Scanner inputScanner = new Scanner(System.in);
        String str = inputScanner.nextLine();
        inputScanner.close();

        if (str.length() == 1) {
            System.out.println(1);
        }

        // 结果值：获取符合条件的子串长度
        int resMax = 0;

        // 左右指针
        int left = 0, right = 0;
        int n = str.length();
        // K-输入字符，V-数量
        // 窗口
        HashMap<Character, Integer> window = new HashMap<>();
        while (left < n && right < n) {
            char c = str.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = str.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }

            resMax = Math.max(resMax, right - left);
        }

        // 结果处理
        System.out.println(resMax);

    }

}
