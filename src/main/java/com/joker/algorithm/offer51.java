package com.joker.algorithm;

/**
 * <p>
 * 数组中的逆序对
 * </p>
 *
 * @author admin
 * @date 2023/7/19
 */
public class offer51 {

    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.reversePairs(nums));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.reversePairs(nums));

    }

    /**
     * 解法一：动态规划（会超时）
     */
    private static class Solution01 {

        public int reversePairs(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }
            int n = nums.length;
            // dp[i] 表示 到第i个数的逆序对数目
            int[] dp = new int[n + 1];

            for (int i = 2; i <= n; i++) {
                int tmp = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[j] > nums[i - 1]) {
                        tmp++;
                    }
                }
                dp[i] = dp[i - 1] + tmp;
            }

            return dp[n];
        }

    }

    /**
     * 解法二：归并排序(最优)
     */
    private static class Solution02 {

        /**
         * 入参数组
         */
        int[] nums;
        /**
         * 用于合并阶段暂存元素的辅助数组
         */
        int[] tmp;

        public int reversePairs(int[] nums) {
            this.nums = nums;
            tmp = new int[nums.length];
            return mergeSort(0, nums.length - 1);
        }

        /**
         * 归并排序
         */
        private int mergeSort(int left, int right) {
            // 终止条件
            if (left >= right) {
                return 0;
            }

            // 递归划分
            int middle = left + (right - left) / 2;
            int res = mergeSort(left, middle) + mergeSort(middle + 1, right);

            // 合并阶段
            // 合并阶段 本质上是 合并两个排序数组 的过程，而每当遇到 左子数组当前元素 > 右子数组当前元素 时，
            // 意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」

            // i,j 分别指向左右子数组首元素
            int i = left, j = middle + 1;
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }
            for (int k = left; k <= right; k++) {
                // 左子数组已经遍历完，只更新排序结果
                if (i == middle + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1 || tmp[i] <= tmp[j]) {
                    // 右子数组已经遍历完，或，右子数组当前元素大于左子数组当前元素时，
                    // 只更新排序结果
                    nums[k] = tmp[i++];
                } else {
                    // 当左当前元素大于右数组当前元素的时候，就需要在更新排序后的数组同时，更新逆序对数了
                    nums[k] = tmp[j++];
                    // 统计逆序对
                    res += middle - i + 1;
                }
            }

            return res;
        }

    }

}
