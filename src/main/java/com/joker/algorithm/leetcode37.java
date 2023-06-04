package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 解数独
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/10
 */
public class leetcode37 {

    public static void main(String[] args) {
        System.out.println(6 >> 1);
        char[][] board = initBoard();
//        Solution solution = new Solution();
//        solution.solveSudoku(board);
//        Arrays.stream(board).forEach(System.out::println);

        Solution02 solution02 = new Solution02();
        solution02.solveSudoku(board);
        Arrays.stream(board).forEach(System.out::println);

    }

    private static char[][] initBoard() {
        return new char[][]{
                {
                        '5', '3', '.', '.', '7', '.', '.', '.', '.'
                },
                {
                        '6', '.', '.', '1', '9', '5', '.', '.', '.'
                },
                {
                        '.', '9', '8', '.', '.', '.', '.', '6', '.'
                },
                {
                        '8', '.', '.', '.', '6', '.', '.', '.', '3'
                },
                {
                        '4', '.', '.', '8', '.', '3', '.', '.', '1'
                },
                {
                        '7', '.', '.', '.', '2', '.', '.', '.', '6'
                },
                {
                        '.', '6', '.', '.', '.', '.', '2', '8', '.'
                },
                {
                        '.', '.', '.', '4', '1', '9', '.', '.', '5'
                },
                {
                        '.', '.', '.', '.', '8', '.', '.', '7', '9'
                }
        };
    }

}

class Solution {

    //储存每一行存在的数字
    private int[] line = new int[9];
    //储存 每一列存在的数字
    private int[] column = new int[9];
    //储存每一个 3*3宫存在的数字
    private int[][] block = new int[3][3];
    //这个布尔数组用来判断是否填完所有空格
    private boolean valid = false;
    //这个list用来记录所有空格的位置，整数数组第一个位置为行的位置 ，第二个位置为列的位置
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        //遍历所有位置
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                //如果位置为空，我们把该位置加入spaces中
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    //不为空则把该数字分别纳入对应的行，列，3*3宫中
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }
        //开始搜索
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        //如果spaces被遍历完了，说明我们填完了空格，将valid改为true，通过return结束这个函数
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        //获取第一个空格的位置
        int[] space = spaces.get(pos);
        //i，j分别为该空格的行数和列数
        int i = space[0], j = space[1];
        //|为or，通过3个或运算我们可以得到一个9位的二进制数字，从右到左分别代表1-9这个9个数是否可以填入该空格，通过~运算（取反），我们用1表示该数字是一个可填入的选项，0x1ff为十六进制 ，等同于111111111）
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        //当mask不为0并且 valid为false表示还有数待填入，继续这个循环，mask &= (mask - 1)把最低位的1变为0
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            //这个操作只保留最低位的1
            int digitMask = mask & (-mask);
            //最低位的1后面的位数，digitMask-1将原本1后面的0全部变为了1
            int digit = Integer.bitCount(digitMask - 1);
            //更新行，列，宫
            flip(i, j, digit);
            //把该数填入板中
            board[i][j] = (char) (digit + '0' + 1);
            //继续搜索
            dfs(board, pos + 1);
            //撤回操作（原理是flip中的~运算，两个 1则为0,0表示这个位置代表的1-9中的那个数 不再是一个可被填入的选项）
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        //^代表异或，只有1个1的时候才为1。比如0011^1001=1010
        //<<代表左移，比如 1<<2=4被加入到下面的三个数组中，在二进制4为100，意味着3这个数字被占用了
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }

}

class Solution02 {

    /**
     * 同一行
     */
    private boolean[][] line = new boolean[9][9];
    /**
     * 同一列
     */
    private boolean[][] column = new boolean[9][9];
    /**
     * 同一九宫格
     */
    private boolean[][][] block = new boolean[3][3][9];
    /**
     * 是否有解
     */
    private boolean valid = false;
    /**
     * 空格列表，存储空格的坐标，用于遍历
     */
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    // '0' 是因为 ASCII 码值 对应 52
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        // 遍历完了 spaces, 即所有空格都填入了符合规则的值，找到解了
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            // 同一行或者同一列或者同一九宫格已经有数字 digit 了
            if (line[i][digit] || column[j][digit] || block[i / 3][j / 3][digit]) {
                continue;
            }
            // 做选择：填入值 digit
            line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
            board[i][j] = (char) (digit + '0' + 1);
            // 递归
            dfs(board, pos + 1);
            // 撤销选择：回溯置为 false
            line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
        }
    }

}

