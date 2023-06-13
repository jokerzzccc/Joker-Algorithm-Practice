package com.joker.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 最小覆盖子串
 * </p>
 *
 * @author admin
 * @date 2023/6/13
 */
public class leetcode76 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        Solution01 solution01 = new Solution01();
        String minWindow01 = solution01.minWindow(s, t);
        System.out.println(minWindow01);
    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        /**
         * 用于记录需要的字符(t 的字符)及其出现的次数
         */
        Map<Character, Integer> need = new HashMap<>();
        /**
         * 用于窗口中的字符及其出现的次数
         */
        Map<Character, Integer> window = new HashMap<>();

        public String minWindow(String s, String t) {
            int sLen = s.length();
            int tLen = t.length();

            if (sLen < tLen) {
                return "";
            }
            // 统计 t 中各字符出现次数
            for (int i = 0; i < tLen; i++) {
                need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
            }
            // 索引左闭右开区间 [left, right) 称为一个「窗口」
            int left = 0;
            int right = 0;
            // 窗口中满足需要的字符个数
            int valid = 0;
            // 记录最小覆盖子串的起始索引及长度
            int start = 0, len = Integer.MAX_VALUE;

            while (right < sLen) {
                // c 是将移入窗口的字符
                char c = s.charAt(right);
                // 扩大窗口
                right++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        // 只有当 window[c] 和 need[c] 对应的出现次数一致时，才能满足条件，valid 才能 +1
                        valid++;
                    }
                }

                // 判断左侧窗口是否要收缩
                while (valid == need.size()) {
                    // 更新最小覆盖子串
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    // d 是将移出窗口的字符
                    char d = s.charAt(left);
                    // 缩小窗口
                    left++;
                    // 进行窗口内数据的一系列更新
                    if (need.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            // 只有当 window[d] 内的出现次数和 need[d] 相等时，才能 -1
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }

    }

}
