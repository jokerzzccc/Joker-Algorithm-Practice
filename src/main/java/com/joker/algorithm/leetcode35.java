package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 三数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode35 {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};

        Solution01 solution01 = new Solution01();
        List<List<Integer>> threeSum01 = solution01.threeSum(nums);
        threeSum01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：排序 + 双指针
     */
    private static class Solution01 {

        public List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            // 枚举 a, 对应指针 first
            for (int first = 0; first < n; first++) {
                // 需要和上一次枚举的数不相同，避免重复结果
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // c 对应的指针 third,初始指向数组的最右端
                int third = n - 1;
                int target = -nums[first];

                // 枚举 b, 对应指针 second
                for (int second = first + 1; second < n; second++) {
                    // 需要和上一次枚举的数不相同
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 需要保证 b 的指针在 c 的指针的左侧
                    // 向左移动指针 c，直到 a+b+c 不大于 0
                    while (second < third && nums[second] + nums[third] > target) {
                        --third;
                    }
                    // 如果指针重合，随着 b 后续的增加
                    // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                    if (second == third) {
                        break;
                    }

                    // 找到一个结果,即 a+b+c==0
                    if (nums[second] + nums[third] == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        res.add(list);
                    }
                }
            }

            return res;
        }

    }

}
