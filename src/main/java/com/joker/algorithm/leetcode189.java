package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 轮转数组
 * </p>
 *
 * @author admin
 * @date 2023/7/23
 */
public class leetcode189 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        Solution01 solution01 = new Solution01();
        solution01.rotate(nums, k);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 解法一：三次数组翻转（最优）
     */
    private static class Solution01 {

        public void rotate(int[] nums, int k) {
            int len = nums.length;
            // k 有可能大于 len，所以取余
            k %= len;

            // 首先对整个数组实行翻转，这样子原数组中需要翻转的子数组，就会跑到数组最前面。
            // 这时候，从 k 处分隔数组，左右两数组，各自进行翻转即可。
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        /**
         * 原地翻转数组:
         * 时间复杂度:O(N) 空间复杂度:O(1)
         */
        public void reverse(int[] nums, int left, int right) {
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

    }

    /**
     * 解法二：环状替换
     */
    private static class Solution02 {

        public void rotate(int[] nums, int k) {
            int len = nums.length;
            // k 有可能大于 len，所以取余
            k %= len;

            // 需要遍历的次数
            int count = gcd(k, len);

            for (int start = 0; start < count; start++) {
                int currentIdx = start;
                int prevNum = nums[start];

                do {
                    int nextIdx = (currentIdx + k) % len;
                    int tempNum = nums[nextIdx];
                    nums[nextIdx] = prevNum;
                    prevNum = tempNum;
                    currentIdx = nextIdx;
                } while (start != currentIdx);

            }

        }

        /**
         * 求最大公约数
         */
        public int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }

    }

}
