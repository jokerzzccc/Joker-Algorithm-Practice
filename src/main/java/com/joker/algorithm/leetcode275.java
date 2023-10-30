package com.joker.algorithm;

/**
 * H 指数 II
 *
 * @author jokerzzccc
 * @date 2023/10/30
 */
public class leetcode275 {

    public static void main(String[] args) {
        int[] citations = {0, 1, 3, 5, 6};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.hIndex(citations));

    }

    /**
     * 二分查找
     */
    private static class Solution01 {

        public int hIndex(int[] citations) {
            int n = citations.length;
            int left = 1;
            int right = n;
            // 闭区间 [left, right]
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (citations[n - mid] >= mid) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

    }

}
