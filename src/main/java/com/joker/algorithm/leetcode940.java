package com.joker.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 水果成篮
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode940 {

    public static void main(String[] args) {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.totalFruit(fruits));

    }

    /**
     * 解法一：滑动窗口（最大滑窗）
     * 题目可以理解为求只包含两种元素的最长连续子序列
     * (看英文题目）
     */
    private static class Solution01 {

        public int totalFruit(int[] fruits) {
            int n = fruits.length;
            // 滑动窗口
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

            int left = 0, res = 0;
            for (int right = 0; right < n; ++right) {
                cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
                // 超过窗口值，则，缩小窗口，
                // 如果篮子中的水果种类 超过两种（fruits[i] 的值超过两种），则需要移动左边界，对应从子序列中删去水果的value要减一
                while (cnt.size() > 2) {
                    cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                    // 若对应水果key的value变为0，说明篮子里(即区间 (left, right] )已经没有这种水果 fruits[left] 了，水果种类要对应变化
                    if (cnt.get(fruits[left]) == 0) {
                        cnt.remove(fruits[left]);
                    }
                    ++left;
                }
                res = Math.max(res, right - left + 1);
            }

            return res;
        }

    }

}
