package com.joker.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 俄罗斯套娃信封问题
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/23
 */
public class leetcode354 {

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Solution02 solution02 = new Solution02();
        int max = solution02.maxEnvelopes(envelopes);
        System.out.println(max);
    }

    // 动态规划版 + 二分查找
    private static class Solution02 {

        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes.length == 0) {
                return 0;
            }

            int n = envelopes.length;
            Arrays.sort(envelopes, (e1, e2) -> {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            });

            // 对高度数组寻找 LIS（最长递增子序列）
            int[] heights = new int[n];
            for (int i = 0; i < n; i++) {
                heights[i] = envelopes[i][1];
            }

            return lengthOfLIS(heights);
        }

        private int lengthOfLIS(int[] nums) {
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
            }
            return maxRes;
        }

    }

    // 动态规划版(某些极端测试用例会超出时间限制)
    private static class Solution01 {

        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes.length == 0) {
                return 0;
            }

            int n = envelopes.length;
            Arrays.sort(envelopes, new Comparator<int[]>() {
                public int compare(int[] e1, int[] e2) {
                    if (e1[0] != e2[0]) {
                        return e1[0] - e2[0];
                    } else {
                        return e2[1] - e1[1];
                    }
                }
            });

            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int ans = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (envelopes[j][1] < envelopes[i][1]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }

            return ans;
        }

    }

}
