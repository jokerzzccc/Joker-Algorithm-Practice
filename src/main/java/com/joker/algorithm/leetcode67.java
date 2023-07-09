package com.joker.algorithm;

/**
 * <p>
 * 二进制求和
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode67 {

    public static void main(String[] args) {
        String a = "11", b = "1";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.addBinary(a, b));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.addBinary(a, b));
    }

    /**
     * 解法一：模拟
     */
    private static class Solution01 {

        public String addBinary(String a, String b) {
            StringBuffer ans = new StringBuffer();

            int n = Math.max(a.length(), b.length());
            int carry = 0;
            for (int i = 0; i < n; ++i) {
                carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
                carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
                ans.append((char) (carry % 2 + '0'));
                carry /= 2;
            }

            if (carry > 0) {
                ans.append('1');
            }

            return ans.reverse().toString();
        }

    }

    /**
     * 解法一：Java 自带函数(不能处理很长的字符串)
     */
    private static class Solution02 {

        public String addBinary(String a, String b) {
            return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
        }

    }

}
