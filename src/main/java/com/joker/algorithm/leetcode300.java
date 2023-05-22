package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 最长递增子序列
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/22
 */
public class leetcode300 {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        Solution01 solution01 = new Solution01();
        int maxLength01 = solution01.lengthOfLIS(nums);
        System.out.println(maxLength01);

        Solution02 solution02 = new Solution02();
        int maxLength02 = solution02.lengthOfLIS(nums);
        System.out.println(maxLength02);
    }

    /**
     * 解法二：贪心 + 二分查找
     */
    private static class Solution02 {

        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] tails = new int[nums.length];
            int maxRes = 0;
            for (int num : nums) {
                // 二分查找
                // 每个元素开始遍历 看能否插入到之前的 tails数组的位置 如果能 是插到哪里
                int left = 0, right = maxRes, pos = 0;
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (tails[middle] < num) {
                        left = middle + 1;
                        pos = left;
                    } else {
                        right = middle;
                    }
                }
                tails[pos] = num;
                // maxRes == right 说明目前tail数组的元素都比当前的num要小 因此最长子序列的长度可以增加了
                if (maxRes == right) {
                    maxRes++;
                }
                // debug
                Arrays.stream(tails).mapToObj(x -> x + ",").forEach(System.out::print);
                System.out.println("\n");
            }
            return maxRes;
        }

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            // 表示，最少都有自身一个子序列的长度
            Arrays.fill(dp, 1);
            int maxRes = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxRes = Math.max(maxRes, dp[i]);
            }

            return maxRes;
        }

    }

}
