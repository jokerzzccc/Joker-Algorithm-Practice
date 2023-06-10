package com.joker.algorithm.array;

/**
 * <p>
 * 移除元素
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode27 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        Solution01 solution01 = new Solution01();
        int count = solution01.removeElement(nums, val);
        //
        System.out.println(count);
    }

    /**
     * 解法一：快慢指针
     * 如果 fast 遇到值为 val 的元素，则直接跳过，否则就赋值给 slow 指针，并让 slow 前进一步。
     */
    private static class Solution01 {

        public int removeElement(int[] nums, int val) {
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
