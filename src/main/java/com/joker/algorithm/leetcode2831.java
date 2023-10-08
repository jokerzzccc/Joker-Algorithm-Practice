package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 找出最长等值子数组
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/8
 **/
public class leetcode2831 {

    public static void main(String[] args) {
        Integer[] num = {1, 3, 2, 3, 1, 3};
        List<Integer> nums = Arrays.asList(num);
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestEqualSubarray(nums, k));

    }

    /**
     * 解法一：同向双指针(分组滑动窗口)
     */
    private static class Solution01 {
        public int longestEqualSubarray(List<Integer> nums, int k) {
            int n = nums.size();
            int ans = 0;

            // 相同元素的下标记录记录到同一个 List:
            List<Integer>[] pos = new ArrayList[n + 1];
            Arrays.setAll(pos, e -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                int x = nums.get(i);
                pos[x].add(i);
            }

            // 遍历每个数字符合要求的等值子数组长度
            for (List<Integer> ps : pos) {
                // 剪枝：当前数字的数量小于等于已有最大答案时
                if (ps.size() <= ans) {
                    continue;
                }

                // 滑动窗口，
                int left = 0;
                for (int right = 0; right < ps.size(); right++) {
                    /*
                     要删除的元素（无效数）个数超过了 k 个，就左指针右移；
                     窗口大小 - 有效数个数 =  无效个数；
                    */
                    while ((ps.get(right) - ps.get(left) + 1) - (right - left + 1) > k) {
                        left++;
                    }

                    // 更新答案：取子数组有效数连续最大数量
                    ans = Math.max(ans, right - left + 1);
                }
            }

            return ans;
        }
    }

}
