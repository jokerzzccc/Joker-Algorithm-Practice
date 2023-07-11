package com.joker.algorithm;

/**
 * <p>
 * 搜索旋转排序数组
 * </p>
 *
 * @author admin
 * @date 2023/7/11
 */
public class leetcode33 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.search(nums, target));

    }

    /**
     * 解法一：二分查找
     * 这道题和平常二分法查找的不同就在于,把一个有序递增的数组分成了,两个递增的数组,我们需要做的就是判断这个数在哪一个递增的数组中,然后再去用常规的二分法去解决
     */
    private static class Solution01 {

        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            if (n == 1) {
                return nums[0] == target ? 0 : -1;
            }

            int left = 0;
            int right = n - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                // 如果[mid，right] 是有序数组，且 target 的大小满足 (nums[mid + 1], mums[right]] ,
                // 否则我们应该将搜索范围缩小至[mid + 1，right], 否则在 [left, mid - 1] 中寻找。
                if (nums[mid] < nums[right]) { // 右边 [mid + 1, right]是递增有序数组，且 target 在右边
                    // 目标值在右边
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    }
                    // 目标值在左边
                    else {
                        right = mid - 1;
                    }
                }
                // 如果[left，mid-1] 是有序数组，且 target 的大小满足[nums[left], mums[mid]),
                // 则我们应该将搜索范围缩小至[left，mid-1], 否则在 [mid + 1, right] 中寻找。
                else { // 左边 [left, mid - 1] 是递增数组，且 target 在左边
                    // 目标值在左边
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    }
                    // 目标值在右边
                    else {
                        left = mid + 1;
                    }
                }
            }

            return -1;
        }

    }

}
