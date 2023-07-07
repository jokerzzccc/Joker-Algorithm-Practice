package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 最接近的三数之和
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode16 {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.threeSumClosest(nums, target));

    }

    /**
     * 解法一：排序 + 双指针
     * 类比 leetcode15
     */
    private static class Solution01 {

        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            int best = Integer.MAX_VALUE;

            // 枚举 a, 对应指针 first
            for (int first = 0; first < n; first++) {
                // 保证和上一次枚举的元素不相等,避免重复结果
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }

                // 枚举 b, 对应指针 second
                int second = first + 1;
                // c 对应的指针 third,初始指向数组的最右端
                int third = n - 1;

                while (second < third) {
                    int curSum = nums[first] + nums[second] + nums[third];

                    // 找到完全相等的，直接返回
                    if (curSum == target) {
                        return target;
                    }

                    // 因为是最接近的，这里就区别于 leetcode15
                    if (Math.abs(curSum - target) < Math.abs(best - target)) {
                        best = curSum;
                    }

                    // 如果和大于 target，移动 c 对应的指针
                    if (curSum > target) {
                        // 移动到下一个不相等的元素
                        while (second < --third && nums[third + 1] == nums[third]) {
                            --third;
                        }
                    } else {
                        // 如果和小于 target，移动 b 对应的指针
                        while (++second < third && nums[second - 1] == nums[second]) {
                            ++second;
                        }
                    }
                }

            }

            return best;
        }

    }

}
