package com.joker.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 餐厅过滤器
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/9/27
 **/
public class leetcode1333 {

    public static void main(String[] args) {
        int[][] restaurants = {{1, 4, 1, 40, 10}, {2, 8, 0, 50, 5}, {3, 8, 1, 30, 4}, {4, 10, 0, 10, 3}, {5, 1, 1, 15, 1}};
        int veganFriendly = 1;
        int maxPrice = 50;
        int maxDistance = 10;

        Solution01 solution01 = new Solution01();
        solution01.filterRestaurants(restaurants, veganFriendly, maxPrice, maxDistance)
                .forEach(System.out::println);

    }

    /**
     * 解法一：排序
     */
    private static class Solution01 {
        private static int compare(int[] o1, int[] o2) {
            if (o1[1] != o2[1]) {
                return o2[1] - o1[1];
            } else {
                return o2[0] - o1[0];
            }
        }

        public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
            List<int[]> filtered = new ArrayList<>();

            for (int[] restaurant : restaurants) {
                if (restaurant[3] <= maxPrice && restaurant[4] <= maxDistance && !(veganFriendly == 1 && restaurant[2] == 0)) {
                    filtered.add(restaurant);
                }
            }

            return filtered.stream()
                    .sorted(Solution01::compare)
                    .map(ints -> ints[0])
                    .collect(Collectors.toList());
        }
    }

}
