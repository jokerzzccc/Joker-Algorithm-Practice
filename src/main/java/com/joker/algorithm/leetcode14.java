package com.joker.algorithm;

/**
 * <p>
 * 最长公共前缀
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode14 {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestCommonPrefix(strs));

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            // 第一个和自己比，LCP 自然就是自己了。
            String curPrefix = strs[0];
            for (int i = 1; i < strs.length; i++) {
                curPrefix = subProblem(curPrefix, strs[i]);
                if (curPrefix.length() == 0) {
                    break;
                }
            }

            return curPrefix;
        }

        private String subProblem(String s1, String s2) {
            int p = 0;
            int minLen = Math.min(s1.length(), s2.length());
            while (p < minLen && s1.charAt(p) == s2.charAt(p)) {
                ++p;
            }

            return s1.substring(0, p);
        }

    }

}
