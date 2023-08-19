package com.joker.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 最长字符串链
 * </p>
 *
 * @author admin
 * @date 2023/8/19
 */
public class leetcode1048 {

    public static void main(String[] args) {
        String[] words = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestStrChain(words));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.longestStrChain(words));

    }

    /**
     * 解法一：动态规划 + 递归
     */
    private static class Solution01 {

        /**
         * 备忘录优化
         * key: words 的字符串，value: dfs(key)
         */
        private Map<String, Integer> memo = new HashMap<>();

        public int longestStrChain(String[] words) {
            // 初始化备忘录
            for (String word : words) {
                // 0 表示还没计算
                memo.put(word, 0);
            }

            int res = 0;
            for (String word : words) {
                res = Math.max(res, dfs(word));
            }

            return res;

        }

        /**
         * 定义 dfs(s) 表示以 s 结尾的词链的最长长度
         */
        private int dfs(String str) {
            // 备忘录优化
            int res = memo.get(str);
            if (res > 0) {
                return res;
            }

            // 计算 str 减一长度的子字符串的词链最长长度
            for (int i = 0; i < str.length(); i++) {
                // 去掉str[i]
                String subStr = str.substring(0, i) + str.substring(i + 1);
                // subStr 在 words 中
                if (memo.containsKey(subStr)) {
                    res = Math.max(res, dfs(subStr));
                }
            }

            memo.put(str, res + 1);
            return res + 1;
        }

    }

    /**
     * 解法二：动态规划 + 迭代 ：
     * 自底向上：总是从短的字符串转移到长的字符串
     */
    private static class Solution02 {

        /**
         * key: words 的字符串，value: 以 key 结尾的词链的最长长度
         */
        private Map<String, Integer> wordToChainLongest = new HashMap<>();

        public int longestStrChain(String[] words) {
            // 按长度排序
            Arrays.sort(words, Comparator.comparingInt(String::length));

            int res = 0;
            for (String word : words) {
                // 计算 word 子字符串的词链最长长度
                int subRes = 0;
                for (int i = 0; i < word.length(); i++) {
                    // 去掉str[i]
                    String subStr = word.substring(0, i) + word.substring(i + 1);
                    subRes = Math.max(subRes, wordToChainLongest.getOrDefault(subStr, 0));
                }

                // +1 是因为加当前 word
                wordToChainLongest.put(word, subRes + 1);
                res = Math.max(res, subRes + 1);
            }

            return res;

        }

    }

}
