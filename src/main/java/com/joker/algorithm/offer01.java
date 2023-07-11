package com.joker.algorithm;

/**
 * <p>
 * 判定字符是否唯一
 * </p>
 *
 * @author admin
 * @date 2023/7/11
 */
public class offer01 {

    public static void main(String[] args) {
        String s = "leetcode";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isUnique(s));

    }

    /**
     * 解法一：辅助数组标识
     */
    private static class Solution01 {

        public boolean isUnique(String astr) {
            if (astr == null) {
                return false;
            }
            int n = astr.length();
            if (n > 26) {
                return false;
            }

            char[] chars = astr.toCharArray();
            char[] arr = new char[26];

            for (int i = 0; i < n; i++) {
                if (arr[chars[i] - 'a'] == 0) {
                    arr[chars[i] - 'a'] = chars[i];
                    continue;
                }
                return false;
            }

            return true;
        }

    }

}
