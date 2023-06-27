package com.joker.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 滑动谜题
 * </p>
 *
 * @author admin
 * @date 2023/6/27
 */
public class leetcode773 {

    public static void main(String[] args) {
        int[][] board = {{4, 1, 2}, {5, 0, 3}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.slidingPuzzle(board));
    }

    /**
     * 解法一: BFS
     */
    private static class Solution01 {

        public int slidingPuzzle(int[][] board) {
            int m = 2, n = 3;
            StringBuilder sb = new StringBuilder();
            String target = "123450";
            // 将 2x3 的数组转化成字符串作为 BFS 的起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
            }
            String start = sb.toString();
            // 记录一维字符串的相邻索引
            // 在一维字符串中，索引 i 在二维数组中的的相邻索引为 neighbor[i]
            int[][] neighbor = new int[][]{
                    {1, 3},
                    {0, 4, 2},
                    {1, 5},
                    {0, 4},
                    {3, 1, 5},
                    {4, 2}
            };

            /******* BFS 算法框架开始 *******/
            Queue<String> queue = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();
            // 从起点开始 BFS 搜索
            queue.offer(start);
            visited.add(start);

            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    // 判断是否达到目标局面 target
                    if (cur.equals(target)) {
                        return step;
                    }

                    // 找到数字 0 的索引
                    int idx = 0;
                    for (; cur.charAt(idx) != '0'; idx++) ;
                    // 将数字 0 和相邻的数字交换位置
                    for (int adj : neighbor[idx]) {
                        String new_board = swap(cur.toCharArray(), adj, idx);
                        // 防止走回头路
                        if (!visited.contains(new_board)) {
                            queue.offer(new_board);
                            visited.add(new_board);
                        }
                    }
                }
                step++;
            }
            /******* BFS 算法框架结束 *******/

            // 找不到结果
            return -1;
        }

        private String swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            return new String(chars);
        }

    }

}
