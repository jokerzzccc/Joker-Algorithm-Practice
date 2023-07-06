package com.joker.algorithm;

/**
 * <p>
 * 整数反转
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode7 {

    public static void main(String[] args) {
        int x = 120;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.reverse(x));

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int reverse(int x) {
            int res = 0;

            while (x != 0) {
                if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int digit = x % 10;
                x /= 10;
                res = res * 10 + digit;
            }

            return res;
        }

    }

}
