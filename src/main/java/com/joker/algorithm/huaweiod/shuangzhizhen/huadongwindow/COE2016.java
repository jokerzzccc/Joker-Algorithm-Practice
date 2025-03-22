package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.*;

/**
 * 寻找符合要求的最长子串
 * 题目要求我们从给定的字符串 s 中找出一个满足以下两个条件的最长子串：
 * 任意一个字符最多出现2次： 子串中的每个字符在子串中出现的次数不能超过2次。
 * 子串不包含指定字符： 子串不能包含输入的指定字符。
 * 输出的是满足以上条件的最长子串的长度。如果没有符合条件的子串，则返回0。
 * 示例分析
 * 示例 1
 * 输入：
 * D
 * ABC123
 * 2
 * 输出：
 * 6
 * 分析：
 * 排除字符为 D。
 * 字符串 s 为 ABC123。
 * 整个字符串 ABC123 不包含字符 D，并且每个字符都没有超过2次，因此整个字符串符合条件。
 * 满足条件的最长子串为 ABC123，长度为 6。
 * 示例 2
 * 输入：
 * D
 * ABACA123D
 * 输出：
 * 7
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口 + 数组最长子串
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE2016 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String specialStr = scanner.nextLine();
        String str = scanner.nextLine();
        scanner.close();

        // 需要排除的字符
        char specialChar = specialStr.charAt(0);
        // 存储窗口内每个字符出现的下标，同事 value 为 List ，也可计算每个字符的数量
        Map<Character, List<Integer>> char2IndexMap = new HashMap<>();
        // 定义左右指针
        int left = 0, right = 0;
        int maxSubLength = 0;
        while (right < str.length()) {
            char currChar = str.charAt(right);
            // 1. 遇到需要排除的字符时，更新所有变量
            if (currChar == specialChar) {
                // 计算当前有效窗口的长度
                if (right > left) {
                    maxSubLength = Math.max(maxSubLength, right - left);
                }
                // 移动指针并清空map，因为新的窗口开始
                right++;
                left = right;
                char2IndexMap.clear();
            } else {
                // 2. 正常字符推进
                char2IndexMap.computeIfAbsent(currChar, k -> new ArrayList<>());
                List<Integer> charIndexList = char2IndexMap.get(currChar);
                // 如果当前字符的出现次数已经 >= 2次，收缩左指针
                // 理论：当前字串内，任何单个字符的出现次数，都不可以大于 2 次
                if (charIndexList.size() >= 2) {
                    // 移动左指针，到 当前字符坐标列表里第一次出现的下一个位置
                    left = Math.max(left, charIndexList.get(0) + 1);
                    charIndexList.remove(0);
                }

                charIndexList.add(right);
                // 更新窗口长度
                maxSubLength = Math.max(maxSubLength, right - left + 1);

                right++;
            }
        }

        System.out.println(maxSubLength);

    }

}
