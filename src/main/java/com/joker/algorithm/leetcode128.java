package com.joker.algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>
 * 最长连续序列
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode128 {

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestConsecutive(nums));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.longestConsecutive(nums));

    }

    /**
     * 解法一：哈希表 (HashSet)
     */
    private static class Solution01 {

        public int longestConsecutive(int[] nums) {
            // 存储每个端点值，并去重，两个相等的，表示不连续
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            HashMap<Integer, Integer> pointToLongest = new HashMap<>();

            // 最长连续子序列的长度
            int longest = 0;
            for (Integer num : set) {
                // 因为，如果 num 存在前驱 num - 1, 则肯定会在 num - 1 开始计算，而不会在 num 计算，
                // 所以可以直接跳过
                if (!set.contains(num - 1)) {
                    int curNum = num;
                    int curLongest = 1;

                    while (set.contains(curNum + 1)) {
                        curNum += 1;
                        curLongest += 1;
                    }

                    longest = Math.max(longest, curLongest);
                }
            }

            return longest;
        }

        public int longestConsecutive2(int[] nums) {
            HashMap<Integer, Integer> pointToLongest = new HashMap<>();

            // 最长连续子序列的长度
            int longest = 0;
            for (Integer num : nums) {
                // 因为，如果 num 存在前驱 num - 1, 则肯定会在 num - 1 开始计算，而不会在 num 计算，
                // 所以可以直接跳过
                if (!pointToLongest.containsKey(num)) {
                    int left = pointToLongest.getOrDefault(num - 1, 0);
                    int right = pointToLongest.getOrDefault(num + 1, 0);
                    int curLen = 1 + left + right;

                    longest = Math.max(longest, curLen);

                    pointToLongest.put(num, curLen);
                    pointToLongest.put(num - left, curLen);
                    pointToLongest.put(num + right, curLen);
                }
            }

            return longest;
        }

    }

    /**
     * 解法二：哈希表（HashMap ,没有自动排序）
     */
    private static class Solution02 {

        public int longestConsecutive(int[] nums) {
            // 端点值（nums[i]） -> 对应的连续区间的长度
            HashMap<Integer, Integer> pointToLongest = new HashMap<>();

            // 最长连续子序列的长度
            int longest = 0;

            for (Integer num : nums) {
                if (!pointToLongest.containsKey(num)) {
                    int left = pointToLongest.getOrDefault(num - 1, 0);
                    int right = pointToLongest.getOrDefault(num + 1, 0);
                    int curLen = 1 + left + right;

                    // 更新答案
                    longest = Math.max(longest, curLen);

                    // 更新当前以及区间端点值
                    pointToLongest.put(num, curLen);
                    pointToLongest.put(num - left, curLen);
                    pointToLongest.put(num + right, curLen);
                }
            }

            return longest;
        }

    }

}
