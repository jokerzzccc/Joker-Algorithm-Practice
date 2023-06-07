package com.joker.algorithm;

/**
 * <p>
 * 正则表达式匹配
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/6
 */
public class leetcode10 {

    public static void main(String[] args) {
        String s = "ab", p = ".*";

        Solution01 solution01 = new Solution01();
        boolean match01 = solution01.isMatch(s, p);
        System.out.println(match01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        /**
         * 备忘录
         */
        boolean[][] memo;

        public boolean isMatch(String s, String p) {
            memo = new boolean[s.length() + 1][p.length() + 1];
            char[] sCharArray = s.toCharArray();
            char[] pCharArray = p.toCharArray();

            return dp(sCharArray, 0, pCharArray, 0);
        }

        /**
         * dp函数的定义如下：
         * 若dp(s,i,p,j) = true，则表示s[i..]可以匹配p[j..]；
         * 若dp(s,i,p,j) = false，则表示s[i..]无法匹配p[j..]。
         */
        private boolean dp(char[] s, int i, char[] p, int j) {
            int m = s.length;
            int n = p.length;

            // base case
            if (j == n) {
                return i == m;
            }
            if (i == m) {
                // 如果能匹配空串，一定是字符和 * 成对儿出现
                if ((n - j) % 2 == 1) {
                    return false;
                }
                // 检查是否为 x*y*z* 这种形式
                for (; j + 1 < p.length; j += 2) {
                    if (p[j + 1] != '*') {
                        return false;
                    }
                }
                return true;
            }

            // 记录状态 (i, j)，消除重叠子问题
            if (memo[i][j]) {
                return memo[i][j];
            }

            // 核心逻辑
            boolean res = false;
            if (s[i] == p[j] || p[j] == '.') {
                // 匹配
                if (j < n - 1 && p[j + 1] == '*') {
                    // 1.1 通配符匹配 0 次或多次
                    res = dp(s, i, p, j + 2)
                            || dp(s, i + 1, p, j);
                } else {
                    // 1.2 常规匹配 1 次
                    res = dp(s, i + 1, p, j + 1);
                }
            } else {
                // 不匹配
                if (j < n - 1 && p[j + 1] == '*') {
                    // 2.1 通配符匹配 0 次
                    res = dp(s, i, p, j + 2);
                } else {
                    // 2.2 无法继续匹配
                    res = false;
                }
            }

            memo[i][j] = res;
            return res;
        }

    }

}
