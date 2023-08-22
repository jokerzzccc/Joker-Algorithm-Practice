package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * 字符串的排列
 * </p>
 *
 * @author admin
 * @date 2023/8/22
 */
public class offer38 {

    public static void main(String[] args) {
        String s = "abc";

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.toString(solution01.permutation(s)));

    }

    /**
     * 解法一：回溯 + 剪枝
     */
    private static class Solution01 {

        /**
         * 结果集
         */
        List<String> res = new ArrayList<>();

        /**
         * 入参字符串
         */
        char[] chars;

        public String[] permutation(String s) {
            chars = s.toCharArray();
            backtrack(0);
            return res.toArray(new String[0]);
        }

        private void backtrack(int index) {
            // base case ：终止条件，字符串递归完的时候
            if (index == chars.length - 1) {
                res.add(String.valueOf(chars));
                return;
            }

            // 用于排除重复的字符
            Set<Character> set = new HashSet<>();
            // 往后递归
            for (int j = index; j < chars.length; j++) {
                // 剪枝
                // 固定相同的元素在排列中的相对位置
                if (set.contains(chars[j])) {
                    continue;
                }
                set.add(chars[j]);

                // 做选择, 即固定 chars[j] 为当前位字符
                swap(j, index);
                // 回溯, 即开始固定第 index + 1 个字符
                backtrack(index + 1);
                // 撤销选择
                swap(j, index);
            }
        }

        private void swap(int a, int b) {
            char tmp = chars[a];
            chars[a] = chars[b];
            chars[b] = tmp;
        }

    }

}
