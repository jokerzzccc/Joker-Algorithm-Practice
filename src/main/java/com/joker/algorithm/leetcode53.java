package com.joker.algorithm;

/**
 * <p>
 * 最大子数组和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/27
 */
public class leetcode53 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Solution01 solution01 = new Solution01();
        int maxSubArraySum = solution01.maxSubArray(nums);
        System.out.println(maxSubArraySum);

        Solution02 solution02 = new Solution02();
        int maxSubArraySum02 = solution02.maxSubArray(nums);
        System.out.println(maxSubArraySum02);

        Solution03 solution03 = new Solution03();
        int maxSubArraySum03 = solution03.maxSubArray(nums);
        System.out.println(maxSubArraySum03);

    }

    /**
     * 解法三：使用【滚动变量】优化动态规划
     */
    private static class Solution03 {

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int pre = 0;
            int res = nums[0];

            for (int num : nums) {
                pre = Math.max(num, pre + num);
                res = Math.max(pre, res);
            }

            return res;
        }

    }

    /**
     * 方法二：分治法(线段树)
     */
    private static class Solution02 {

        public static class Status {

            // 对于一个区间 $[l,r]$ ，我们可以维护四个量：
            //
            //- lSum表示$[l,r]$内以 $l$ 为左端点的最大子段和
            //- rSum表示 $[l,r]$ 内以r为右端点的最大子段和
            //- mSum表示 $[l,r]$ 内的最大子段和
            //- iSum表示  $[l,r]$ 的区间和
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }

        }

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        /**
         * 一个操作，表示，查询 nums 序列 [l,r] 内的最大字段和。
         */
        private Status getInfo(int[] nums, int left, int right) {
            if (left == right) {
                return new Status(nums[left], nums[left], nums[left], nums[left]);
            }
            int middle = (left + right) / 2;
            Status leftSub = getInfo(nums, left, middle);
            Status rightSub = getInfo(nums, middle + 1, right);
            return pushUp(leftSub, rightSub);

        }

        private Status pushUp(Status leftSub, Status rightSub) {
            int iSum = leftSub.iSum + rightSub.iSum;
            int lSum = Math.max(leftSub.lSum, leftSub.iSum + rightSub.lSum);
            int rSum = Math.max(rightSub.rSum, rightSub.iSum + leftSub.rSum);
            int mSum = Math.max(Math.max(leftSub.mSum, rightSub.mSum), leftSub.rSum + rightSub.lSum);
            return new Status(lSum, rSum, mSum, iSum);

        }

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int n = nums.length;

            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[n];
            dp[0] = nums[0];
            int maxSum = dp[0];
            for (int i = 1; i < n; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }

                maxSum = Math.max(maxSum, dp[i]);
            }

            return maxSum;
        }

    }

}


