package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 根据身高重建队列
 * </p>
 *
 * @author admin
 * @date 2023/7/25
 */
public class leetcode406 {

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.deepToString(solution01.reconstructQueue(people)));

    }

    /**
     * 解法一：排序
     * 从高到低
     */
    private static class Solution01 {

        public int[][] reconstructQueue(int[][] people) {
            // 排序，根据 h 降序，k 升序
            Arrays.sort(people, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[1] - o2[1];
                }
            });

            ArrayList<int[]> res = new ArrayList<>();
            for (int[] person : people) {
                res.add(person[1], person);
            }

            return res.toArray(new int[res.size()][]);
        }

    }

}
