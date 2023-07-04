package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 最长不含重复字符的子字符串
 * </p>
 *
 * @author admin
 * @date 2023/7/4
 */
public class offer48 {

    public static void main(String[] args) {
        String s = "pwwkew";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.lengthOfLongestSubstring(s));

    }

    /**
     * 解法一：双指针，维护一个没有重复元素的滑动窗口 set
     */
    private static class Solution01 {

        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            int n = s.length();

            Set<Character> set = new HashSet<>();
            int first = 0, second = 0;
            int max = 0;
            while (second < n) {
                char c = s.charAt(second);
                // 用while 的原因是，有连续重复的字符
                while (set.contains(c)) {
                    set.remove(s.charAt(first++));
                }
                set.add(c);
                max = Math.max(max, second - first + 1);

                second++;
            }

            return max;
        }

    }

}
