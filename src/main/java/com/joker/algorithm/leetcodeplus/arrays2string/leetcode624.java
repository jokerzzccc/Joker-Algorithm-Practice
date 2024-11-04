package com.joker.algorithm.leetcodeplus.arrays2string;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数组列表中的最大距离
 *
 * @author jokerzzccc
 * @date 2024/11/5
 */
public class leetcode624 {

    public static void main(String[] args) {
        int[][] arrays = new int[][]{{1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
        List<List<Integer>> collect = Arrays.stream(arrays)
                .map(integers -> Arrays.stream(integers)
                        .boxed()
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        Solution01 solution01 = new Solution01();
        int maxDistance = solution01.maxDistance(collect);
        System.out.println(maxDistance);
    }

    /**
     * 思路：单词扫描，只需要记录最大、最小值，求最大距离。一次扫描 arrays
     * <p>
     * 时间复杂度: O(n). 我们只遍历了一次长度为 n 的 arrays。
     * 空间复杂度: O(1). 只使用了常数级的额外空间。
     */
    public static class Solution01 {

        private int maxDistance = 0;

        public int maxDistance(List<List<Integer>> arrays) {
            int size = arrays.size();
            int currMin = arrays.get(0).get(0);
            int currMax = arrays.get(0).get(arrays.get(0).size() - 1);

            for (int i = 1; i < size; i++) {
                int n = arrays.get(i).size();
                maxDistance = Math.max(maxDistance,
                        Math.max(Math.abs(arrays.get(i).get(n - 1) - currMin)
                                , Math.abs(currMax - arrays.get(i).get(0))));
                currMin = Math.min(currMin, arrays.get(i).get(0));
                currMax = Math.max(currMax, arrays.get(i).get(n - 1));

            }
            return maxDistance;
        }

    }

}
