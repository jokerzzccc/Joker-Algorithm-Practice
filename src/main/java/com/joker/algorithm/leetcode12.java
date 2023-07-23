package com.joker.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 整数转罗马数字
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode12 {

    public static void main(String[] args) {
        int num = 58;

        Solution01 solution01 = new Solution01();
        // 输出 LVIII
        System.out.println(solution01.intToRoman(num));

    }

    /**
     * 解法一：模拟，贪心
     * 每次尽可能使用大的字符
     */
    private static class Solution01 {

        /**
         * 由大到小
         */
        Map<String, Integer> romanMap = new LinkedHashMap<String, Integer>() {{
            put("M", 1000);
            put("CM", 900);
            put("D", 500);
            put("CD", 400);
            put("C", 100);
            put("XC", 90);
            put("L", 50);
            put("XL", 40);
            put("X", 10);
            put("IX", 9);
            put("V", 5);
            put("IV", 4);
            put("I", 1);
        }};

        public String intToRoman(int num) {
            StringBuilder res = new StringBuilder();

            for (Map.Entry<String, Integer> entry : romanMap.entrySet()) {
                Integer value = entry.getValue();
                String roman = entry.getKey();
                while (num >= value) {
                    num -= value;
                    res.append(roman);
                }
                if (num == 0) {
                    break;
                }
            }

            return res.toString();
        }

    }

}
