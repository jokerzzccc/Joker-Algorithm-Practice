package com.joker.algorithm.huaweiod.dfs;

import java.util.*;

/**
 * 找单词
 * 题目描述
 * <p>
 * 给一个字符串和一个二维字符数组，如果该字符串存在于该数组中，则按字符串的字符顺序输出字符串每个字符所在单元格的位置下标字符串，如果找不到返回字符串“N”
 * 1.需要按照字符串的字符组成顺序搜索，且搜索到的位置必须是相邻单元格，其中“相邻单元格”是指那些水平相邻或垂直相邻的单元格
 * 2.同一个单元格内的字母不允许被重复使用
 * 3.假定在数组中最多只存在一个可能的匹配
 * <p>
 * 输入描述
 * 第 1 行为一个数字 N 指示二维数组在后续输入所占的行数第 2 行到第 N+1 行输入为一个二维大写字符数组，每行字符用半角,分割第 N+2 行为待查找的字符串，由大写字符组成
 * 二维数组的大小为 N*N，0<N<=100.
 * 单词长度 K，0<K<1000.
 * 输出描述
 * 输出一个位置下标字符串，拼接格式为: 第 1 个字符行下标+””+第 1 个字符列下标+””+第 2 个字符行下标+””+第 2 个字符列下标...+””第 N 个字符行下标+”"+第 N 个字符列下标
 * 用例
 * 输入：
 * 4
 * A,C,C,F
 * C,D,E,D
 * B,E,S,S
 * F,E,C,A
 * ACCESS
 * 输出：
 * 0,0,0,1,0,2,1,2,2,2,2,3
 * <p>
 * ------------------
 * 题型分析：
 * 类似 leetcode-79 单词搜索
 * 【中等】 DFS + 二维数组
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE2011 {

    // 定义移动的四个方向的坐标值变化：上下左右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 记录每个单元格是否已被访问
    private static boolean[][] visited;
    // 存储结果路径索引
    private static LinkedList<String> resPathIndexList = new LinkedList<>();

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = scanner.nextLine().split(",");
        }
        String targetWord = scanner.nextLine();
        scanner.close();

        //
        visited = new boolean[n][n];
        String firstStr = targetWord.substring(0, 1);
        // 找到目标字符串
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Objects.equals(board[i][j], firstStr)) {
                    // 找到第一个字符，开始搜索
                    if (dfs(board, targetWord, i, j, 0)) {
                        System.out.println(String.join(",", resPathIndexList));
                        return;
                    }
                }
            }
        }

    }

    private static boolean dfs(String[][] board, String targetWord, int boardRow, int boardCol, int wordIndex) {
        if (wordIndex == targetWord.length()) {
            return true;
        }

        if (boardRow < 0 || boardRow >= board.length || boardCol < 0 || boardCol >= board[0].length
                || !Objects.equals(board[boardRow][boardCol], targetWord.substring(wordIndex, wordIndex + 1))
                || visited[boardRow][boardCol]) {
            return false;
        }

        // 做选择
        visited[boardRow][boardCol] = true;
        resPathIndexList.add(boardRow + "," + boardCol);

        // 搜索上下左右
        for (int[] direction : DIRECTIONS) {
            int newRow = boardRow + direction[0];
            int newCol = boardCol + direction[1];
            if (dfs(board, targetWord, newRow, newCol, wordIndex + 1)) {
                // 如果在某个方向上找到了字符串，返回 true
                return true;
            }
        }

        // 撤销选择
        visited[boardRow][boardCol] = false;
        resPathIndexList.removeLast();
        return false;

    }

}
