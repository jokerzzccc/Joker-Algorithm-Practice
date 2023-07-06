package com.joker.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 电话号码的字母组合
 * </p>
 *
 * @author admin
 * @date 2023/7/6
 */
public class leetcode17 {

    public static void main(String[] args) {
        String digits = "23";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.letterCombinations(digits));

    }

    /**
     * 解法一：回溯
     */
    private static class Solution01 {

        Map<Character, String> cachePhone = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        List<String> resList = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return resList;
            }

            StringBuilder sb = new StringBuilder();
            backTracking(digits, 0, sb);

            return resList;
        }

        private void backTracking(String digits, int index, StringBuilder curStr) {
            // base case
            // digits 遍历深度完的时候
            if (index == digits.length()) {
                resList.add(curStr.toString());
                return;
            }

            char digit = digits.charAt(index);
            String letters = cachePhone.get(digit);
            int curCnt = letters.length();

            for (int i = 0; i < curCnt; i++) {
                // 做选择
                curStr.append(letters.charAt(i));

                backTracking(digits, index + 1, curStr);

                // 撤销选择
                curStr.deleteCharAt(index);
            }
        }

    }

}
