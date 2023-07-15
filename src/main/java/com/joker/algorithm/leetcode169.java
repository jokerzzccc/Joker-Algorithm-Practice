package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 多数元素
 * </p>
 *
 * @author admin
 * @date 2023/7/15
 */
public class leetcode169 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.majorityElement(nums));

    }

    // 解法一：排序 + 双指针
    private static class Solution01 {

        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            int countTarget = n / 2;
            int tmp = 1;
            int first = 0, second = 1;
            while (second < n) {
                if (nums[first] == nums[second]) {
                    second++;
                    tmp++;
                    if (tmp > countTarget) {
                        return nums[first];
                    }
                } else {
                    first = second;
                    second++;
                }
            }

            return -1;

        }

    }

    // 解法二：排序 + 众数理论
    private static class Solution02 {

        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

    }

    // 解法三：摩尔投票理论(最优)
    private static class Solution03 {

        public int majorityElement(int[] nums) {
            int cand_num = nums[0], count = 1;
            for (int i = 1; i < nums.length; ++i) {
                if (cand_num == nums[i])
                    ++count;
                else if (--count == 0) {
                    cand_num = nums[i];
                    count = 1;
                }
            }
            return cand_num;
        }

    }

}
