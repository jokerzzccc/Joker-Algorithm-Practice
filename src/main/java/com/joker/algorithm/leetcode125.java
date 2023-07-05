package com.joker.algorithm;

/**
 * <p>
 * 验证回文串
 * </p>
 *
 * @author admin
 * @date 2023/7/5
 */
public class leetcode125 {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isPalindrome(s));
    }

    /**
     * 解法一：原字符串上直接判断
     * 在移动任意一个指针时，需要不断地向另一指针的方向移动，直到遇到一个字母或数字字符， 或者两指针重合为止。
     * 也就是说，我们每次将指针移到下一个字母字符或数字字符，再判断这两个指针指向的字符是否相同。
     */
    private static class Solution01 {

        public boolean isPalindrome(String s) {
            int n = s.length();
            int left = 0, right = n - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    ++left;
                }
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    --right;
                }
                if (left < right) {
                    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                        return false;
                    }
                    ++left;
                    --right;
                }

            }

            return true;
        }

    }

}
