package com.joker.algorithm;

import java.util.LinkedList;

/**
 * 无重复字符的最长子串
 *
 * @author jokerzzccc
 * @date 2024/3/10
 */
public class LCRO16 {

    public static void main(String[] args) {
        String s = "abcabcbb";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.lengthOfLongestSubstring(s));

    }

    /**
     * 解法一：滑动窗口(遍历右边界)
     * 从每一个字符开始的，不包含重复字符的最长子串
     */
    private static class Solution01 {

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }

            int left = 0;
            int maxSubLen = 0;
            LinkedList<Character> queue = new LinkedList<>();
            queue.add(s.charAt(left));

            for (int right = 1; right < s.length(); right++) {
                char curr = s.charAt(right);
                while (queue.contains(curr)) {
                    queue.remove((Object) s.charAt(left));
                    left++;
                }

                queue.add(curr);
                maxSubLen = Math.max(maxSubLen, right - left + 1);
            }

            return maxSubLen;
        }

    }

}
