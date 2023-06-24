package com.joker.algorithm.dp.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <p>
 * 区间列表的交集
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode986 {

    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        Solution01 solution01 = new Solution01();
        int[][] intervalIntersection01 = solution01.intervalIntersection(firstList, secondList);
        Arrays.deepToString(intervalIntersection01);

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            // 双指针，分别游走在 firstList, secondList
            int first_i = 0;
            int second_j = 0;

            LinkedList<int[]> res = new LinkedList<>();
            while (first_i < firstList.length && second_j < secondList.length) {
                int a_left = firstList[first_i][0];
                int a_right = firstList[first_i][1];
                int b_left = secondList[second_j][0];
                int b_right = secondList[second_j][1];
                // 两个区间存在交集
                if (b_right >= a_left && a_right >= b_left) {
                    // 计算出交集，加入 res
                    res.addLast(new int[]{Math.max(a_left, b_left), Math.min(a_right, b_right)});
                }
                // 指针前进
                if (b_right < a_right) {
                    second_j++;
                } else {
                    first_i++;
                }
            }

            return res.toArray(new int[res.size()][]);
        }

    }

}
