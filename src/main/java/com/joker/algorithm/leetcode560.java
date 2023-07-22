package com.joker.algorithm;

import java.util.HashMap;

/**
 * <p>
 * 和为 K 的子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode560 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.subarraySum(nums, k));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.subarraySum(nums, k));

    }

    /**
     * 解法一：暴力枚举
     */
    private static class Solution01 {

        public int subarraySum(int[] nums, int k) {
            int n = nums.length;
            int resCount = 0;

            for (int i = 0; i < n; i++) {
                int tmpSum = 0;
                // 统计 [0,i] 里有多少个符合答案
                // 因为 nums[i] 可能为负数，所以，不能用双指针
                for (int j = i; j >= 0; j--) {
                    tmpSum += nums[j];
                    if (tmpSum == k) {
                        resCount++;
                    }
                }
            }

            return resCount;
        }

    }

    /**
     * 解法二：前缀和 + 哈希表优化
     */
    private static class Solution02 {

        public int subarraySum(int[] nums, int k) {
            int n = nums.length;

            int resCount = 0;
            // pre 变量用来记录当前数以前的所有数的和
            // 因为 pre[i] 只与 pre[i - 1] 有关，所以用变量代替数组
            int pre = 0;
            // K-以下标 i 结尾的连续子数组的和；V-出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            // base case, 第一个数的前缀和，
            map.put(0, 1);

            for (int i = 0; i < n; i++) {
                // 前缀和
                pre += nums[i];
                // pre[j - 1] = pre[i] - k;
                // 统计有多少个j 使 [j, i] 的和为k 的问题转换为 统计有多少个前缀和为 pre[i] - k
                if (map.containsKey(pre - k)) {
                    resCount += map.get(pre - k);
                }
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }

            return resCount;
        }

    }

}
