package com.joker.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * N 字形变换
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode6 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;

        Solution01 solution01 = new Solution01();
        String convert01 = solution01.convert(s, numRows);
        System.out.println(convert01);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public String convert(String s, int numRows) {
            int n = s.length();
            if (numRows == 1 || numRows >= n) {
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            // flag 用于改变遍历的方向，下上下...
            int flag = -1;
            // 当前添加字符的行数
            int row = 0;
            for (int i = 0; i < n; i++) {
                rows.get(row).append(s.charAt(i));
                if (row == 0 || row == numRows - 1) {
                    flag = -flag;
                }
                row += flag;
            }
            StringBuilder res = new StringBuilder();
            for (StringBuilder sb : rows) {
                res.append(sb);
            }

            return res.toString();
        }

    }

}
