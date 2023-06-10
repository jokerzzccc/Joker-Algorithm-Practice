package com.joker.algorithm.array;

/**
 * <p>
 * 移动零
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode283 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};

        Solution01 solution01 = new Solution01();
        solution01.moveZeroes(nums);
    }

    /**
     * 解法一：快慢指针
     */
    private static class Solution01 {

        public void moveZeroes(int[] nums) {
            // 去除 nums 中的所有 0，返回不含 0 的数组长度
            int p = removeElement(nums, 0);
            // 将 nums[p..] 的元素赋值为 0
            for (; p < nums.length; p++) {
                nums[p] = 0;
            }
        }

        private int removeElement(int[] nums, int val) {
            int fast = 0, slow = 0;
            while (fast < nums.length) {
                if (nums[fast] != val) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }

            return slow;
        }

    }

}
