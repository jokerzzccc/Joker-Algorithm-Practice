package com.joker.algorithm;

import java.util.HashMap;

/**
 * 环和杆
 *
 * @author jokerzzccc
 * @date 2023/11/2
 */
public class leetcode2103 {

    public static void main(String[] args) {
        String rings = "B0B6G0R6R0R6G9";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.countPoints(rings));

    }

    /**
     * 解法一：从杆的角度
     */
    private static class Solution01 {

        public int countPoints(String rings) {
            int n = rings.length();
            if (n < 6) {
                return 0;
            }

            HashMap[] rodCnt = new HashMap[10];
            for (int i = 0; i < 10; i++) {
                rodCnt[i] = new HashMap<>();
            }

            for (int i = 0; i < n; i += 2) {
                int tempRod = rings.charAt(i + 1) - '0';
                rodCnt[tempRod].put(rings.charAt(i), rodCnt[tempRod].getOrDefault(rings.charAt(i), 1));
            }

            int ans = 0;
            for (HashMap hashMap : rodCnt) {
                if (hashMap.size() >= 3) {
                    ans++;
                }
            }

            return ans;
        }

    }

}
