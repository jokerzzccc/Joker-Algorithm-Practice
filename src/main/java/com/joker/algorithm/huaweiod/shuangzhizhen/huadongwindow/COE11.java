package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.*;

/**
 * 最左侧冗余覆盖子串
 * 题目描述
 * 给定两个字符串s1和s2和正整数K，其中s1长度为n1，s2长度为n2，在s2中选一个子串，满足:
 * 该子串长度为n1+k
 * 该子串中包含s1中全部字母，
 * 该子串每个字母出现次数不小于s1中对应的字母，
 * 我们称s2以长度k冗余覆盖s1，给定s1，s2，k，求最左侧的s2以长度k冗余覆盖s1的子串的首个元素的下标，如果没有返回**-1**。
 * 输入描述
 * 输入三行，第一行为s1，第二行为s2，第三行为k，s1和s2只包含小写字母
 * 备注
 * 0 ≤ len(s1) ≤ 1000000
 * 0 ≤ len(s2) ≤ 20000000
 * 0 ≤ k ≤ 1000
 * 输出描述
 * 最左侧的s2以长度k冗余覆盖s1的子串首个元素下标，如果没有返回**-1。**
 * <p>
 * 示例1
 * 输入
 * ab
 * aabcd
 * 1
 * 输出
 * 0
 * 说明
 * 子串aab和abc符合要求，由于aab在abc的左侧，因此输出aab的下标：0
 * <p>
 * 示例2
 * 输入
 * abc
 * dfs
 * 10
 * 输出
 * -1
 * 说明
 * s2无法覆盖s1，输出 -1
 * ————————————————
 * 题型分析：
 * 【中等】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE11 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine().trim();
        String s2 = scanner.nextLine().trim();
        int k = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();
        int len1 = s1.length();
        int len2 = s2.length();
        int targetLength = len1 + k;

        // 处理s1中的字符
        Set<Character> s1Set = new TreeSet<>();
        // K-字符，V-出现次数
        Map<Character, Integer> char2CntMap = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            s1Set.add(s1.charAt(i));
            char2CntMap.put(s1.charAt(i), char2CntMap.getOrDefault(s1.charAt(i), 0) + 1);
        }

        //
        int left = 0;
        // 窗口大小
        int right = targetLength;
        boolean isFound = false;
        // 遍历判断 [left, right) 这个窗口的字串是否符合条件。
        while (right < len2 && !isFound) {
            String currStr = s2.substring(left, right);
            // 当前子串是否符合条件
            boolean isValid = true;
            // 数据预处理
            Set<Character> currSet = new TreeSet<>();
            Map<Character, Integer> currChar2CntMap = new HashMap<>();
            for (int i = 0; i < targetLength; i++) {
                currSet.add(currStr.charAt(i));
                currChar2CntMap.put(currStr.charAt(i), currChar2CntMap.getOrDefault(currStr.charAt(i), 0) + 1);
            }
            // 条件二：S1 字母是否全部包含
            for (Character ch : s1Set) {
                if (currStr.indexOf(ch) == -1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                left++;
                right++;
                continue;
            }
            // 条件三：该子串每个字母出现次数不小于s1中对应的字母，
            for (Character ch : currSet) {
                if (currChar2CntMap.get(ch) < char2CntMap.get(ch)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                left++;
                right++;
                continue;
            }

            isFound = true;
        }

        // 输出结果
        if (!isFound) {
            System.out.println(-1);
        } else {
            System.out.println(left);
        }

    }

}
