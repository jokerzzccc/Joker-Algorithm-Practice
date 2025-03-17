package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.Scanner;

/**
 * 环中最长子串/字符成环找偶数O
 * 题目描述
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出 ‘o’ 字符出现了偶数次最长子字符串的长度。
 * 输入描述
 * 输入是一串小写字母组成的字符串
 * 备注
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母
 * 输出描述
 * 输出是一个整数
 * 示例1
 * 输入
 * alolobo
 * 输出
 * 6
 * 说明
 * 最长子字符串之一是 “alolob”，它包含’o’ 2个。
 * 示例2
 * 输入
 * looxdolx
 * 输出
 * 7
 * 说明
 * 最长子字符串是 “oxdolxl”，由于是首尾连接在一起的，所以最后一个 ‘x’ 和开头的 ‘l’是连接在一起的，此字符串包含 2 个’o’ 。
 * 示例3
 * 输入
 * bcbcbc
 * 输出
 * 6
 * 说明
 * 这个示例中，字符串 “bcbcbc” 本身就是最长的，因为 ‘o’ 都出现了 0 次。
 * ————————————————
 * 题型分析：
 * 【简单】字符串
 * 核心解题思路：
 * 如果 ‘o’ 出现的次数为偶数，那么整个字符串就符合题目的要求：即包含偶数次的 ‘o’。
 * 如果 ‘o’ 出现的次数为奇数，则只能通过去掉一个字符（任意字符）来使得剩下的子字符串中的 ‘o’ 出现次数变为偶数。去掉一个字符后，最长的可能子字符串的长度就是 len - 1。
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE41 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        //
        char[] charArray = input.toCharArray();
        int n = charArray.length;
        // o 的个数
        int targetOCnt = 0;
        for (char c : charArray) {
            if (c == 'o') {
                targetOCnt++;
            }
        }

        // 如果'o'字符出现的次数是偶数，则输出字符串的长度
        if (targetOCnt % 2 == 0) {
            System.out.println(n);
        } else {
            // 如果'o'字符出现的次数是奇数，则输出字符串长度减1
            System.out.println(n - 1);
        }

    }

}
