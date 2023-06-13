package com.joker.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 字符串的排列
 * </p>
 *
 * @author admin
 * @date 2023/6/13
 */
public class leetcode567 {

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";

        Solution01 solution01 = new Solution01();
        boolean inclusion01 = solution01.checkInclusion(s1, s2);
        System.out.println(inclusion01);
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

        public boolean checkInclusion(String t, String s) {
            int sLen = s.length();
            int tLen = t.length();

            if (sLen < tLen) {
                return false;
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
                while (right - left >= tLen) {
                    // 在这里判断是否找到了合法的子串
                    // 当发现 valid == need.size() 时，就说明窗口中就是一个合法的排列，所以立即返回 true。
                    if (valid == need.size()) {
                        return true;
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
                        window.put(d, window.getOrDefault(d, 0) - 1);
                    }
                }
            }

            // 未找到符合条件的子串
            return false;
        }

    }

}


