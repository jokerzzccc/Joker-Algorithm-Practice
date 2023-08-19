package com.joker.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 使数组严格递增
 * </p>
 *
 * @author admin
 * @date 2023/8/18
 */
public class leetcode1187 {

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {4, 3, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.makeArrayIncreasing(arr1, arr2));

    }

    /**
     * 解法一：动态规划 + DFS + 贪心 + 二分查找
     * 思路：选与不选
     * 可以使用 leetcode300 最长递增子序列的思路
     */
    private static class Solution01 {

        /**
         * 入参数组
         */
        private int[] nums1, nums2;

        /**
         * 备忘录优化:
         * map 数组下标为 nums1 下标（0...n-1）
         * key：pre, value: 操作数
         */
        private Map<Integer, Integer>[] memo;

        public int makeArrayIncreasing(int[] arr1, int[] arr2) {
            this.nums1 = arr1;
            this.nums2 = arr2;
            Arrays.sort(nums2);
            int n = nums1.length;
            memo = new HashMap[n];
            Arrays.setAll(memo, HashMap::new);

            // 假设 nums1[n-1] 右侧有个无穷大的数
            int ans = dfs(n - 1, Integer.MAX_VALUE);

            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }

        /**
         * 把从 nums1[0] 到 nums1[i] 的这段前缀替换成严格递增数组，且数组的最后一个数小于 pre，
         * 所需要的最小操作数。记为 dfs(i,pre)
         *
         * @param index nums1 下标
         * @param pre 从后向前算的
         */
        private int dfs(int index, int pre) {
            // base case: i<0 数组没有元素了，操作数自然就是0
            if (index < 0) {
                return 0;
            }
            // 备忘录优化
            if (memo[index].containsKey(pre)) {
                return memo[index].get(pre);
            }

            // 不替换 nums1[i]
            int res = nums1[index] < pre ? dfs(index - 1, nums1[index]) : Integer.MAX_VALUE / 2;

            // 二分查找 nums2 中小于 pre 的最大数的下标
            int k = lowerBound(nums2, pre) - 1;
            // 状态转移
            // nums1[i] 替换成小于 pre 的最大数
            if (k >= 0) {
                res = Math.min(res, dfs(index - 1, nums2[k]) + 1);
            }

            // 存入备忘录
            memo[index].put(pre, res);

            return res;
        }

        /**
         * 二分查找 nums
         */
        private int lowerBound(int[] nums, int target) {
            // 区间 (left,right)
            int left = -1, right = nums.length;
            while (left + 1 < right) {
                int mid = (right + left) >>> 1;
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            return right;
        }

    }

    /**
     * 解法二：动态规划 + DFS + 贪心 + 二分查找
     * 思路：枚举选哪个
     */
    private static class Solution02 {

        /**
         * 入参数组
         */
        private int[] nums1, nums2;

        /**
         * 备忘录优化:
         * map 数组下标为 nums1 下标（0...n-1）
         * key：pre, value: 操作数
         */
        private Map<Integer, Integer>[] memo;

        public int makeArrayIncreasing(int[] arr1, int[] arr2) {
            this.nums1 = arr1;
            this.nums2 = arr2;
            Arrays.sort(nums2);
            int n = nums1.length;
            memo = new HashMap[n];
            Arrays.setAll(memo, HashMap::new);

            // 假设 nums1[n-1] 右侧有个无穷大的数
            int ans = dfs(n - 1, Integer.MAX_VALUE);

            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }

        /**
         * 把从 nums1[0] 到 nums1[i] 的这段前缀替换成严格递增数组，且数组的最后一个数小于 pre，
         * 所需要的最小操作数。记为 dfs(i,pre)
         *
         * @param index nums1 下标
         * @param pre 从后向前算的
         */
        private int dfs(int index, int pre) {
            // base case: i<0 数组没有元素了，操作数自然就是0
            if (index < 0) {
                return 0;
            }
            // 备忘录优化
            if (memo[index].containsKey(pre)) {
                return memo[index].get(pre);
            }

            // 不替换 nums1[i]
            int res = nums1[index] < pre ? dfs(index - 1, nums1[index]) : Integer.MAX_VALUE / 2;

            // 二分查找 nums2 中小于 pre 的最大数的下标
            int k = lowerBound(nums2, pre) - 1;
            // 状态转移
            // nums1[i] 替换成小于 pre 的最大数
            if (k >= 0) {
                res = Math.min(res, dfs(index - 1, nums2[k]) + 1);
            }

            // 存入备忘录
            memo[index].put(pre, res);

            return res;
        }

        /**
         * 二分查找 nums
         */
        private int lowerBound(int[] nums, int target) {
            // 区间 (left,right)
            int left = -1, right = nums.length;
            while (left + 1 < right) {
                int mid = (right + left) >>> 1;
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            return right;
        }

    }

}
