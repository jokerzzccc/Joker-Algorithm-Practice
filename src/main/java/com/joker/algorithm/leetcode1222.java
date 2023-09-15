package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 可以攻击国王的皇后
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/15
 */
public class leetcode1222 {

    public static void main(String[] args) {
        int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
        int[] king = {0, 0};

        Solution01 solution01 = new Solution01();
        List<List<Integer>> ans = solution01.queensAttacktheKing(queens, king);
        for (List<Integer> coordinate : ans) {
            System.out.println(coordinate);
        }

    }

    /**
     * 解法一：从国王出发。
     * 能攻击到国王的皇后，需要满足：
     * 皇后与国王在同一行，或者同一列，或者同一斜线。
     * 皇后与国王之间没有棋子。换句话说，皇后不能被其它皇后挡住。
     */
    private static class Solution01 {

        /**
         * 八个方向移动
         */
        private final static int[][] directions = {
                {1, 0}, {1, 1}, {0, 1}, {-1, 1},
                {-1, 0}, {-1, -1}, {0, -1}, {1, -1}
        };

        public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
            // 数组效率比哈希表高
            boolean[][] isQueen = new boolean[8][8];
            // 初始化皇后的位置
            for (int[] queen : queens) {
                isQueen[queen[0]][queen[1]] = true;
            }

            List<List<Integer>> ans = new ArrayList<>();
            // 在八个方向上，都去找所能遇见的第一个皇后
            for (int[] direction : directions) {
                int x = king[0] + direction[0];
                int y = king[1] + direction[1];
                // 在一个方向上一直往下走，直到找到第一个皇后或达到边界
                while (0 <= x && x < 8 && 0 <= y && y < 8) {
                    if (isQueen[x][y]) {
                        ans.add(Arrays.asList(x, y));
                        break;
                    }
                    x += direction[0];
                    y += direction[1];
                }
            }

            return ans;
        }
    }

}
