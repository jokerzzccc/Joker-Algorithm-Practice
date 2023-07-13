package com.joker.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 找到字符串中所有字母异位词
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode438 {

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";

        Solution01 solution01 = new Solution01();
        List<Integer> anagrams01 = solution01.findAnagrams(s, p);
        anagrams01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        /**
         * 用于记录需要的字符及其出现的次数
         */
        Map<Character, Integer> need = new HashMap<>();
        /**
         * 用于窗口中的字符及其出现的次数
         */
        Map<Character, Integer> window = new HashMap<>();

        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            for (int i = 0; i < pLen; i++) {
                char c = p.charAt(i);
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            int left = 0;
            int right = 0;
            int valid = 0;
            // 记录结果
            List<Integer> res = new ArrayList<>();

            while (right < sLen) {
                char c = s.charAt(right);
                right++;
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (need.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }
                // 判断左侧窗口是否要收缩
                while (right - left >= pLen) {
                    // 当窗口符合条件时，把起始索引加入 res
                    if (valid == need.size()) {
                        res.add(left);
                    }

                    char d = s.charAt(left);
                    left++;
                    if (need.containsKey(d)) {
                        if (need.get(d).equals(window.get(d))) {
                            valid--;
                        }
                        window.put(d, window.getOrDefault(d, 0) - 1);
                    }

                }
            }

            return res;

        }

    }

}
