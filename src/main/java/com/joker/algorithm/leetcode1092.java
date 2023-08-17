package com.joker.algorithm;

import java.util.Objects;

/**
 * <p>
 * 最短公共超序列
 * </p>
 *
 * @author admin
 * @date 2023/8/16
 */
public class leetcode1092 {

    public static void main(String[] args) {
        String str1 = "abac", str2 = "cab";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.shortestCommonSupersequence(str1, str2));

    }

    /**
     * 解法一：动态规划 + 递归 + 备忘录
     */
    private static class Solution01 {

        /**
         * 入参
         */
        private String s1, s2;

        /**
         * 备忘录
         */
        private int[][] memo;

        public String shortestCommonSupersequence(String str1, String str2) {
            if (Objects.equals(str1, str2)) {
                return str1;
            }
            s1 = str1;
            s2 = str2;

            int len1 = s1.length();
            int len2 = s2.length();
            memo = new int[len1][len2];

            return makeAns(len1 - 1, len2 - 1);
        }

        /**
         * makeAns(i,j) 返回 s1 的前 index1(下标) 个字母和 s2 的前 index2(下标) 个字母的最短公共超序列.
         * 看上去和 dfs 没啥区别，但是末尾的递归是 if-else;
         * makeAns(i-1,j) 和 makeAns(i,j-1) 不会都调用;
         * 所以 makeAns 的递归树仅仅是一条链.
         */
        private String makeAns(int index1, int index2) {
            // s1 是空串，返回剩余的 s2
            if (index1 < 0) {
                return s2.substring(0, index2 + 1);
            }
            // s2 是空串，返回剩余的 s1
            if (index2 < 0) {
                return s1.substring(0, index1 + 1);
            }
            // s1,s2 尾字母相同，则最短公共超序列一定包含该尾字母
            if (s1.charAt(index1) == s2.charAt(index2)) {
                return makeAns(index1 - 1, index2 - 1) + s1.charAt(index1);
            }

            // 如果下面 if 成立，说明上面 dfs 中的 min 取的是 dfs(i - 1, j)
            // 说明 dfs(i - 1, j) 对应的公共超序列更短
            // 那么就在 makeAns(i - 1, j) 的结果后面加上 s[i]
            // 否则说明 dfs(i, j - 1) 对应的公共超序列更短
            // 那么就在 makeAns(i, j - 1) 的结果后面加上 t[j]
            if (dfs(index1, index2) == dfs(index1 - 1, index2) + 1) {
                return makeAns(index1 - 1, index2) + s1.charAt(index1);
            } else {
                return makeAns(index1, index2 - 1) + s2.charAt(index2);
            }

        }

        /**
         * dfs(i,j) 返回 s1 的前 i 个字母和 s2 的前 j 个字母的最短公共超序列的长度
         */
        private int dfs(int i, int j) {
            // s1 是空串，返回剩余的 s2 的长度
            if (i < 0) {
                return j + 1;
            }
            // s2 是空串，返回剩余的 s1 的长度
            if (j < 0) {
                return i + 1;
            }
            // 备忘录优化： 避免重复计算 dfs 的结果
            if (memo[i][j] > 0) {
                return memo[i][j];
            }
            // s1,s2 尾字母相同，则最短公共超序列一定包含该尾字母
            if (s1.charAt(i) == s2.charAt(j)) {
                return memo[i][j] = dfs(i - 1, j - 1) + 1;
            }

            return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - 1)) + 1;
        }

    }

}
