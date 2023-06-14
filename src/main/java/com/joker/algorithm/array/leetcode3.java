package com.joker.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 无重复字符的最长子串
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode3 {

    public static void main(String[] args) {
        String s = "abcabcbb";

        Solution01 solution01 = new Solution01();
        int length01 = solution01.lengthOfLongestSubstring(s);
        System.out.println(length01);

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        Map<Character, Integer> window = new HashMap<>();

        public int lengthOfLongestSubstring(String s) {
            int left = 0;
            int right = 0;

            int res = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                window.put(c, window.getOrDefault(c, 0) + 1);
                while (window.get(c) > 1) {
                    char d = s.charAt(left);
                    left++;
                    window.put(d, window.get(d) - 1);
                }
                res = Math.max(res, right - left);
            }

            return res;
        }

    }

}
