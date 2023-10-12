package com.joker.algorithm;

/**
 * 摆动序列
 *
 * @author jokerzzccc
 * @date 2023/10/12 12:37
 */
public class leetcode376 {

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.wiggleMaxLength(nums));

    }

    /**
     * 解法一：贪心
     * 不断交错的选择峰与谷
     */
    private static class Solution01 {

        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            if (n <= 1) {
                return n;
            }

            int prevDiff = nums[1] - nums[0];
            int ans = prevDiff == 0 ? 1 : 2;

            // 不断交错的选择峰与谷
            for (int i = 2; i < n; i++) {
                int diff = nums[i] - nums[i - 1];
                if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) {
                    ans++;
                    prevDiff = diff;
                }
            }

            return ans;
        }

    }

}
