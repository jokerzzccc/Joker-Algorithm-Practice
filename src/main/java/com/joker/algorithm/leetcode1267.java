package com.joker.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 统计参与通信的服务器
 * </p>
 *
 * @author admin
 * @date 2023/8/24
 */
public class leetcode1267 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.countServers(grid));

    }

    /**
     * 解法一：哈希表
     */
    private static class Solution01 {

        public int countServers(int[][] grid) {
            int m = grid.length, n = grid[0].length;

            // row 中存储行的编号以及每一行服务器的数量
            Map<Integer, Integer> rows = new HashMap<>();
            // col 存储列的编号以及每一列服务器的数量
            Map<Integer, Integer> cols = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.put(i, rows.getOrDefault(i, 0) + 1);
                        cols.put(j, cols.getOrDefault(j, 0) + 1);
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果 grid(i,j) 的值为 1，并且 row[i] 和 col[j] 中至少有一个严格大于 1，
                    // 就说明位置 (i,j) 的服务器能与同一行或者同一列的另一台服务器进行通信
                    if (grid[i][j] == 1 && (rows.get(i) > 1 || cols.get(j) > 1)) {
                        ++ans;
                    }
                }
            }

            return ans;
        }

    }

}
