package com.joker.algorithm;

/**
 * <p>
 * 字符串相加
 * </p>
 *
 * @author admin
 * @date 2023/7/21
 */
public class leetcode415 {

    public static void main(String[] args) {
        String num1 = "11", num2 = "123";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.addStrings(num1, num2));

    }

    /**
     * 解法一：模拟
     * 类比 leetcode67
     */
    private static class Solution01 {

        public String addStrings(String num1, String num2) {
            StringBuffer ans = new StringBuffer();

            int n = Math.max(num1.length(), num2.length());
            int carry = 0;

            for (int i = 0; i < n; i++) {
                carry += i < num1.length() ? (num1.charAt(num1.length() - 1 - i) - '0') : 0;
                carry += i < num2.length() ? (num2.charAt(num2.length() - 1 - i) - '0') : 0;
                ans.append((char) (carry % 10 + '0'));
                carry /= 10;
            }

            while (carry % 10 != 0) {
                ans.append((char) (carry % 10 + '0'));
                carry /= 10;
            }

            return ans.reverse().toString();
        }

    }

}
