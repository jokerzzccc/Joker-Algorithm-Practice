package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 四数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode18 {

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;

        Solution01 solution01 = new Solution01();
        List<List<Integer>> fourSum01 = solution01.fourSum(nums, target);
        for (List<Integer> list : fourSum01) {
            System.out.println(list);
        }

    }

    /**
     * 解法一：排序 + 双指针
     */
    private static class Solution01 {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) {
                return quadruplets;
            }
            Arrays.sort(nums);
            int n = nums.length;
            for (int first = 0; first < n - 3; first++) {
                // 避免重复
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // 一些剪枝条件，最小的都比 target 大
                if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                    break;
                }
                // 最大的都比 target 小
                if ((long) nums[first] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }

                for (int second = first + 1; second < n - 2; second++) {
                    // 避免重复
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 一些剪枝条件，最小的都比 target 大
                    if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                        break;
                    }
                    // 最大的都比 target 小
                    if ((long) nums[first] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                        continue;
                    }

                    // 双指针法
                    int third = second + 1, fourth = n - 1;
                    while (third < fourth) {
                        int curSum = nums[first] + nums[second] + nums[third] + nums[fourth];
                        if (curSum < target) {
                            third++;
                        } else if (curSum > target) {
                            fourth--;
                        } else if (curSum == target) {
                            // 找到一个答案
                            // 如果和等于 target, 则将枚举到的四个数加到答案中，
                            // 然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数；
                            quadruplets.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                            while (third < fourth && nums[third] == nums[third + 1]) {
                                third++;
                            }
                            third++;
                            while (third < fourth && nums[fourth] == nums[fourth - 1]) {
                                fourth--;
                            }
                            fourth--;
                        }
                    }
                }
            }

            return quadruplets;
        }

    }

}
