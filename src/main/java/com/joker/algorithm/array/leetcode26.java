package com.joker.algorithm.array;

/**
 * <p>
 * 删除有序数组中的重复项
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode26 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        Solution01 solution01 = new Solution01();
        int count = solution01.removeDuplicates(nums);
        // 5
        System.out.println(count);
    }

    /**
     * 解法一：快慢指针
     */
    private static class Solution01 {

        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    // 维护 nums[0..slow] 无重复
                    nums[slow] = nums[fast];
                }
                fast++;
            }
            // 数组长度为索引 + 1
            return slow + 1;

        }

    }

}
