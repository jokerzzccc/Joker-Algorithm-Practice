package com.joker.algorithm.array;

import java.util.Arrays;

/**
 * <p>
 * 反转字符串
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode344 {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};

        Solution01 solution01 = new Solution01();
        solution01.reverseString(s);

    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public void reverseString(char[] s) {
            int left = 0, right = s.length - 1;
            while (left < right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
                left++;
                right--;
            }
            // debug
            System.out.println(s);

        }

    }

}