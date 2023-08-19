package com.joker.algorithm;

/**
 * <p>
 * 反转字符串 II
 * </p>
 *
 * @author admin
 * @date 2023/8/18
 */
public class leetcode541 {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.reverseStr(s, k));

    }

    /**
     * 解法一：模拟 + 双指针
     */
    private static class Solution01 {

        public String reverseStr(String s, int k) {
            int n = s.length();
            char[] chars = s.toCharArray();
            for (int i = 0; i < n; i += 2 * k) {
                reverse(chars, i, Math.min(i + k, n) - 1);
            }

            return new String(chars);
        }

        public void reverse(char[] chars, int left, int right) {
            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }

    }

}
