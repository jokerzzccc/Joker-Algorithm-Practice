package com.joker.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 杨辉三角
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode118 {

    public static void main(String[] args) {
        int num = 5;

        Solution solution = new Solution();
        List<List<Integer>> generate = solution.generate(num);
        generate.stream().forEach(System.out::println);

    }

    /**
     * 解法一：
     * 画图，二维矩阵就更好理解了
     */
    private static class Solution {

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();

            for (int i = 0; i < numRows; ++i) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; ++j) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                    }
                }
                res.add(row);
            }

            return res;
        }

    }

}
