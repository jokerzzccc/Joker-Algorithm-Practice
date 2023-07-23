package com.joker.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 分割回文串
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode131 {

    public static void main(String[] args) {
        String s = "aab";

        Solution01 solution01 = new Solution01();
        List<List<String>> partition01 = solution01.partition(s);
        for (List<String> strings : partition01) {
            System.out.println(strings);
        }

        System.out.println("================================");

        Solution02 solution02 = new Solution02();
        List<List<String>> partition02 = solution02.partition(s);
        for (List<String> strings : partition02) {
            System.out.println(strings);
        }

        System.out.println("================================");

        Solution03 solution03 = new Solution03();
        List<List<String>> partition03 = solution03.partition(s);
        partition03.stream().forEach(System.out::println);

    }

    /**
     * 解法一：回溯算法
     */
    private static class Solution01 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }

            char[] chars = s.toCharArray();
            backTracking(chars, 0, len);
            return res;
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                // 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
                // 判断是否为回文
                if (!checkPalindrome(chars, index, i)) {
                    continue;
                }
                // 做选择
                track.addLast(new String(chars, index, i + 1 - index));

                // 递归，纵向遍历
                backTracking(chars, i + 1, len);

                // 撤销选择
                track.removeLast();
            }

        }

        /**
         * 判断子串区间 [left, right] 是否为一个回文字符串
         */
        private boolean checkPalindrome(char[] chars, int left, int right) {
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

    }

    /**
     * 解法二：回溯算法 + 动态规划优化（预处理所有子串是否为回文串）
     */
    private static class Solution02 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }
            char[] chars = s.toCharArray();

            // 预处理，动态规划,类比 leetcode5
            // 状态：dp[i][j] 表示 s[i...j] 是否是回文
            boolean[][] dp = new boolean[len][len];
            // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
            for (int right = 0; right < len; right++) {
                // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
                for (int left = 0; left <= right; left++) {
                    if (chars[left] == chars[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            // 回溯
            backTracking(chars, 0, len, dp);

            return res;
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len, boolean[][] dp) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    track.addLast(new String(chars, index, i + 1 - index));
                    backTracking(chars, i + 1, len, dp);
                    track.removeLast();
                }
            }

        }

    }

    /**
     * 解法三：回溯算法 + 中心扩展法（预处理所有子串是否为回文串）
     */
    private static class Solution03 {

        // 结果集
        private final List<List<String>> res = new LinkedList<>();

        // 记录回溯算法的递归路径
        private final LinkedList<String> track = new LinkedList<>();

        // 状态：dp[i][j] 表示 s[i...j] 是否是回文
        boolean[][] dp;

        public List<List<String>> partition(String s) {
            int len = s.length();
            if (len == 0) {
                return res;
            }
            char[] chars = s.toCharArray();

            // 预处理，动态规划,类比 leetcode5
            dp = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                // 两种边界条件，len1: 中心子串长度为1；len2: 中心子串长度为2；
                expandAroundCenter(s, i, i);
                expandAroundCenter(s, i, i + 1);
            }

            // 回溯
            backTracking(chars, 0, len, dp);

            return res;
        }

        /**
         * 由中心向两边扩展
         *
         * @param s 输入字符串
         * @param left 左下标
         * @param right 右下标
         */
        public void expandAroundCenter(String s, int left, int right) {
            // 不越界，且头尾相等，才是回文。
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                dp[left][right] = true;
                --left;
                ++right;
            }
        }

        /**
         * 回溯算法
         */
        private void backTracking(char[] chars, int index, int len, boolean[][] dp) {
            // 终止条件：到叶结点的时候
            if (index == len) {
                res.add(new LinkedList<>(track));
                return;
            }

            // 回溯算法
            // for 是同一层的横向遍历
            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    track.addLast(new String(chars, index, i + 1 - index));
                    backTracking(chars, i + 1, len, dp);
                    track.removeLast();
                }
            }

        }

    }

}


