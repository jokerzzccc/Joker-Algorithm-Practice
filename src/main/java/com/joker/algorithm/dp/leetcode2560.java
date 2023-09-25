package com.joker.algorithm.dp;

/**
 * <p>
 * 打家劫舍 IV
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/19
 */
public class leetcode2560 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 9};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minCapability(nums, k));
    }

    /**
     * 解法一：二分 + DP
     * 最小化最大值
     */
    private static class Solution01 {
        public int minCapability(int[] nums, int k) {
            int left = 0, right = 0;
            for (int num : nums) {
                right = Math.max(right, num);
            }
            // 二分查找，
            // 开区间：(left, right)
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;
                if (check(nums, k, mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }

            return right;

        }

        /**
         * dp (滚动变量空间优化)。
         * 当小偷的窃取能力为 midNum 时，是否可以窃取至少 k 个房子 :
         * f1 代表不考虑当前房屋时，小偷窃取的最多的房屋数;
         * f2 代表考虑当前房屋时 ，小偷窃取的最多的房屋数
         */
        private boolean check(int[] nums, int k, int midNum) {
            int f1 = 0, f2 = 0;
            for (int num : nums) {
                // 当前的金额超过了小偷的能力，则不能窃取
                if (num > midNum) {
                    f1 = f2;
                } else { // 如果当前的金额小于等于小偷的能力 ，小偷可以选择窃取
                    int tmp = f2;
                    f2 = Math.max(f2, f1 + 1);
                    f1 = tmp;
                }
            }

            return f2 >= k;
        }
    }
}
