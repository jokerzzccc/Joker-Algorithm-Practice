package com.joker.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 罗马数字转整数
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode13 {

    public static void main(String[] args) {
        String s = "MCMXCIV";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.romanToInt(s));
    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        Map<Character, Integer> romanMap = new LinkedHashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
            put('a', 4);
            put('b', 9);
            put('c', 40);
            put('d', 90);
            put('e', 400);
            put('f', 900);
        }};

        public int romanToInt(String s) {
            s = s.replace("IV", "a");
            s = s.replace("IX", "b");
            s = s.replace("XL", "c");
            s = s.replace("XC", "d");
            s = s.replace("CD", "e");
            s = s.replace("CM", "f");

            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                res += romanMap.get(s.charAt(i));
            }

            return res;
        }

    }

}
