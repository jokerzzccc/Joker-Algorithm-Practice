package com.joker.algorithm;

/**
 * 最大单词长度乘积
 *
 * @author jokerzzccc
 * @date 2023/11/6
 */
public class leetcode318 {

    public static void main(String[] args) {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxProduct(words));
    }

    /**
     * 解法一：位运算模拟
     */
    private static class Solution01 {

        public int maxProduct(String[] words) {
            int n = words.length;
            int idx = 0;
            int[] masks = new int[n];

            for (String word : words) {
                // t 指代某个 word: 低 26 来代指 word 中字母 a-z 是否出现过
                int t = 0;
                for (int i = 0; i < word.length(); i++) {
                    int u = word.charAt(i) - 'a';
                    t |= (1 << u);
                }
                masks[idx++] = t;
            }

            int ans = 0;
            // 对每个「字符对」所对应的两个 int 值执行 & 操作
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    // 若两字符无重复字符，则结果为 0
                    if ((masks[i] & masks[j]) == 0) {
                        ans = Math.max(ans, words[i].length() * words[j].length());
                    }
                }
            }

            return ans;
        }

    }

}
