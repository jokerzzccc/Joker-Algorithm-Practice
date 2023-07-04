package com.joker.algorithm;

/**
 * <p>
 * 比较版本号
 * </p>
 *
 * @author admin
 * @date 2023/7/4
 */
public class leetcode165 {

    public static void main(String[] args) {
        String version1 = "1.0.1";
        String version2 = "1";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.compareVersion(version1, version2));
    }

    /**
     * 解法一：字符串分割
     */
    private static class Solution01 {

        public int compareVersion(String version1, String version2) {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");
            int maxLen = Math.max(v1.length, v2.length);
            int[] v1_int = new int[maxLen];
            int[] v2_int = new int[maxLen];
            for (int i = 0; i < maxLen; i++) {
                if (i < v1.length) {
                    v1_int[i] = Integer.parseInt(v1[i]);
                } else {
                    v1_int[i] = 0;
                }

                if (i < v2.length) {
                    v2_int[i] = Integer.parseInt(v2[i]);
                } else {
                    v2_int[i] = 0;
                }
            }

            int p = 0;
            int res = 0;
            while (p < maxLen) {
                if (v1_int[p] > v2_int[p]) {
                    res = 1;
                    break;
                } else if (v1_int[p] < v2_int[p]) {
                    res = -1;
                    break;
                }
                p++;
            }

            return res;

        }

    }

}
