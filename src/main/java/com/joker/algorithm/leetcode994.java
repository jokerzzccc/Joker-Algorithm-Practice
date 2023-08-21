package com.joker.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 腐烂的橘子
 * </p>
 *
 * @author admin
 * @date 2023/8/21
 */
public class leetcode994 {

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.orangesRotting(grid));

    }

    /**
     * 解法一：BFS
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。
     * 翻译一下，
     * 实际上就是求腐烂橘子到所有新鲜橘子的最短路径。那么这道题使用 BFS，应该是毫无疑问的了。
     */
    private static class Solution01 {

        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // 表示所有腐烂的橘子的坐标
            Queue<int[]> rotQueue = new LinkedList<>();
            // count 表示新鲜橘子的数量
            int count = 0;

            // 遍历二维数组 找出所有的新鲜橘子和腐烂的橘子
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) { // 新鲜橘子计数
                        count++;
                    } else if (grid[i][j] == 2) {
                        rotQueue.add(new int[]{i, j});
                    }
                }
            }

            // round 表示腐烂的轮数，或者分钟数(即 BFS 的深度)
            int round = 0;

            // 如果有新鲜橘子 并且 队列不为空
            // 直到上下左右都触及边界 或者 被感染的橘子已经遍历完
            while (count > 0 && !rotQueue.isEmpty()) {
                round++;
                // 拿到当前层级的腐烂橘子数量， 因为每个层级会更新队列
                int k = rotQueue.size();
                for (int i = 0; i < k; i++) {
                    int[] orange = rotQueue.poll();

                    int curRow = orange[0];
                    int curCol = orange[1];

                    // ↑ 上邻点 判断是否边界 并且 上方是否是健康的橘子
                    if (curRow - 1 >= 0 && grid[curRow - 1][curCol] == 1) {
                        // 感染它
                        grid[curRow - 1][curCol] = 2;
                        // 好橘子 -1
                        count--;
                        // 把被感染的橘子放进队列 并缓存
                        rotQueue.add(new int[]{curRow - 1, curCol});
                    }
                    // ↓ 下邻点 同上
                    if (curRow + 1 < m && grid[curRow + 1][curCol] == 1) {
                        grid[curRow + 1][curCol] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow + 1, curCol});
                    }
                    // ← 左邻点 同上
                    if (curCol - 1 >= 0 && grid[curRow][curCol - 1] == 1) {
                        grid[curRow][curCol - 1] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow, curCol - 1});
                    }
                    // → 右邻点 同上
                    if (curCol + 1 < n && grid[curRow][curCol + 1] == 1) {
                        grid[curRow][curCol + 1] = 2;
                        count--;
                        rotQueue.add(new int[]{curRow, curCol + 1});
                    }
                }
            }

            // 如果此时还有健康的橘子, 返回 -1
            // 否则 返回层级()
            if (count > 0) {
                return -1;
            } else {
                return round;
            }
        }

    }

}
