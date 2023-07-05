package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 反转字符串中的元音字母
 * </p>
 *
 * @author admin
 * @date 2023/7/5
 */
public class leetcode345 {

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public String reverseVowels(String s) {
            int left = 0, right = s.length() - 1;
            Set<Character> set = new HashSet<>();
            set.add('a');
            set.add('A');
            set.add('e');
            set.add('E');
            set.add('i');
            set.add('I');
            set.add('o');
            set.add('O');
            set.add('u');
            set.add('U');

            char[] chars = s.toCharArray();

            while (left < right) {
                Character curLeft = chars[left];
                Character curRight = chars[right];
                if (set.contains(curLeft)) {
                    if (set.contains(curRight)) {
                        swap(chars, left, right);
                        left++;
                        right--;
                    } else {
                        right--;
                    }
                } else {
                    left++;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (char aChar : chars) {
                stringBuilder.append(aChar);
            }

            return stringBuilder.toString();
        }

        void swap(char[] chars, int left, int right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }

    }

}
