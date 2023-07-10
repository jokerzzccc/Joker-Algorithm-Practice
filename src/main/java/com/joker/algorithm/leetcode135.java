package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 分发糖果
 * </p>
 *
 * @author admin
 * @date 2023/7/10
 */
public class leetcode135 {

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.candy(ratings));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.candy(ratings));
    }

    /**
     * 解法一：贪心 + 两次遍历
     */
    private static class Solution01 {

        public int candy(int[] ratings) {
            int n = ratings.length;

            // 从左到右遍历数组
            // 左规则：ratings[i-1]< ratings[i]时，i号学生的糖果数量将比 i-1 号孩子的糖果数量多。
            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 1;
                }
            }
            // 从右到左遍历数组
            // 右规则：当 ratings[i] > ratings[i + 1]时，i号学生的糖果数量将比 i+1 号孩子的糖果数量多。
            int right = 0;
            int ret = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    right++;
                } else {
                    right = 1;
                }

                // 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。
                // 每个人最终分得的糖果数量即为这两个数量的最大值。
                ret += Math.max(left[i], right);
            }

            // 返回答案
            return ret;
        }

    }

    /**
     * 解法一：贪心 + 两次遍历
     */
    private static class Solution02 {

        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] candies = new int[n];
            Arrays.fill(candies, 1); // 初始化每个孩子的糖果数为 1

            // 从左到右遍历，保证相邻两个孩子评分更高的孩子获得更多的糖果
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }

            // 从右到左遍历，保证相邻两个孩子评分更高的孩子获得更多的糖果
            for (int i = n - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                }
            }

            // 计算总糖果数
            int total = 0;
            for (int candy : candies) {
                total += candy;
            }

            return total;
        }

    }

}
