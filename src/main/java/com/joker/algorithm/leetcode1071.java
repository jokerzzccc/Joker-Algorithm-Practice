package com.joker.algorithm;

/**
 * <p>
 * 字符串的最大公因子
 * </p>
 *
 * @author admin
 * @date 2023/8/22
 */
public class leetcode1071 {

    public static void main(String[] args) {
        String str1 = "ABCABC";
        String str2 = "ABC";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.gcdOfStrings(str1, str2));

    }

    /**
     * 解法一：枚举前缀
     */
    private static class Solution01 {

        public String gcdOfStrings(String str1, String str2) {
            int len1 = str1.length();
            int len2 = str2.length();

            // 从长度大的开始枚举字符串前缀
            for (int i = Math.min(len1, len2); i >= 1; i--) {
                // 公因数才可以
                if (len1 % i == 0 && len2 % i == 0) {
                    String prefix = str1.substring(0, i);
                    if (check(prefix, str1) && check(prefix, str2)) {
                        return prefix;
                    }
                }
            }

            return "";
        }

        /**
         * 校验：str 是否可以由  prefix 前缀，完全组成；
         */
        public boolean check(String prefix, String str) {
            int count = str.length() / prefix.length();
            StringBuilder ans = new StringBuilder();
            for (int i = 1; i <= count; i++) {
                ans.append(prefix);
            }
            return ans.toString().equals(str);
        }

    }

}
