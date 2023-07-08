package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 最大数
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode179 {

    public static void main(String[] args) {
        int[] nums = {10, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.largestNumber(nums));
    }

    /**
     * 解法一：排序
     *自定义一种排序方式 比较 s1 + s2 和 s2 + s1
     */
    private static class Solution01 {

        public String largestNumber(int[] nums) {
            String[] sortStrings = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                sortStrings[i] = String.valueOf(nums[i]);
            }

            // 大的放前面，降序
            // 自定义一种排序方式 比较 s1 + s2 和 s2 + s1 , 降序，
            Arrays.sort(sortStrings, (x, y) -> {
                String sx = x + y;
                String sy = y + x;
                return sy.compareTo(sx);
            });

            StringBuilder sb = new StringBuilder();
            for (String s : sortStrings) {
                sb.append(s);
            }

            // 处理前导0
            int len = sb.length();
            int k = 0;
            while (k < len - 1 && sb.charAt(k) == '0') {
                k++;
            }

            return sb.substring(k);
        }

    }

}
