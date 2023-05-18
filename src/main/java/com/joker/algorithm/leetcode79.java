package com.joker.algorithm;

import java.util.List;

/**
 * <p>
 * 单词搜索
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/18
 */
public class leetcode79 {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word01 = "ABCCED";
        String word02 = "ABCB";
        Solution solution = new Solution();

        boolean exist01 = solution.exist(board, word01);
        System.out.println(exist01);

        boolean exist02 = solution.exist(board, word02);
        System.out.println(exist02);

    }

    private static class Solution {

        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0) {
                return false;
            }
            if (word == null || word.length() == 0) {
                return true;
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] used = new boolean[m][n];
            char[] words = word.toCharArray();

            // 遍历矩阵
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 矩阵元素与字符串第一个字符相等的，才开始递归
                    if (board[i][j] == words[0]) {
                        if (backTrack(i, j, used, board, words, 0)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        /**
         * @param i board row index
         * @param j board col index
         * @param used 当前元素是否使用过
         * @param board board 入参
         * @param words word char 数组
         * @param offset words 下标
         * @return
         */
        boolean backTrack(int i, int j, boolean[][] used, char[][] board, char[] words, int offset) {
            // 剪枝：不超出矩阵边界
            if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
                return false;
            }
            // 剪枝：已走过的路径
            if (used[i][j]) {
                return false;
            }
            // 剪枝：矩阵当前元素与字符串当前元素是否相等
            if (board[i][j] != words[offset]) {
                return false;
            }

            // 终结条件：找到了 word
            if (offset == words.length - 1) {
                return true;
            }

            used[i][j] = true;

            // 四个方向递归
            boolean res = backTrack(i + 1, j, used, board, words, offset + 1)
                    || backTrack(i - 1, j, used, board, words, offset + 1)
                    || backTrack(i, j + 1, used, board, words, offset + 1)
                    || backTrack(i, j - 1, used, board, words, offset + 1);

            used[i][j] = false;

            return res;
        }

    }

}
