package com.joker.algorithm;

import java.util.*;

/**
 * <p>
 * N 皇后
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/9
 */
public class leetcode51 {

    public static void main(String[] args) {
        List<List<String>> solveNQueens = solveNQueens(4);
        for (List<String> solveNQueen : solveNQueens) {
            System.out.println(solveNQueen.toString());
        }

//        backtraceTest(10, 0);
    }

//    public static void backtraceTest(int a, int b){
//        if (b > 5){
//            return;
//        }
//        for (int i = 0; i < a; i++) {
//            System.out.println("递归前：" + b);
//            backtraceTest( a, b + 1);
//            System.out.println("递归后：" + b);
//        }
//    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];

        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagnoals1 = new HashSet<>();
        Set<Integer> diagnoals2 = new HashSet<>();

        backtrak(solutions, queens, n, 0, columns, diagnoals1, diagnoals2);
        return solutions;
    }

    public static void backtrak(List<List<String>> solutions, int[] queens, int n,
            int row, Set<Integer> columns,
            Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) {
                continue;
            }
            int diagonal1 = row - i;
            if (diagonals1.contains(diagonal1)) {
                continue;
            }
            int diagonal2 = row + i;
            if (diagonals2.contains(diagonal2)) {
                continue;
            }

            queens[row] = i;
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);
            System.out.println(i);

            backtrak(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

            queens[row] = -1;
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
            System.out.println("递归后[ " + row + " , " + i + " ]");
            System.out.println("--------------------------------");
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
