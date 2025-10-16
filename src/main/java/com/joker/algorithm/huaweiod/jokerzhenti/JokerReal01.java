package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.*;

/**
 * 华为 OD 面试真题（技术一面真题）
 * <p>
 * 题目描述：
 * 给定一个 N 行 M 列的二维矩阵，矩阵中每个位置的数字取值为 0 或 1，矩阵示例如：
 * 1 1 0 0
 * 0 0 0 1
 * 0 0 1 1
 * 1 1 1 1
 * 现需要将矩阵中所有的 1 进行反转为 0，规则如下：
 * 1）当点击一个 1 时，该 1 被反转为 0，同时相邻的上、下、左、右，
 * 以及左上、左下、右上、右下 8 个方向的 1 （如果存在 1）均会自动反转为 0；
 * 2）进一步地，一个位置上的 1 被反转为 0 时，与其相邻的 8 个方向的 1 （如果存在 1）均会自动反转为 0；
 * 按照上述规则示例中的矩阵只最少需要点击 2 次后，所有均值 0 。请问，给定一个矩阵，最少需要点击几次后，所有数字均为 0
 * <p>
 * 输入
 * 第一行输入两个整数，分别表示矩阵的行数 N 和列数 M，取值范围均为 [1,100] 接下来 N 行表示矩阵的初始值，
 * 每行均为 M 个数，取值范围 [0,1]
 * <p>
 * 输出
 * 输出一个整数，表示最少需要点击的次数
 * <p>
 * 示例1
 * 输入
 * 3 3
 * 1 0 1
 * 0 1 0
 * 1 0 1
 * 输出
 * 1
 * 说明：上述4个角的1均在中间位置1的相邻8个方向上，因此点击一次即可
 *
 * @author jokerzzccc
 * @date 2025/4/14
 */
public class JokerReal01 {

    /**
     * 方向
     */
    public static int[][] dirs = {
            {-1, 0}, {-1, 1}, {-1, -1},
            {1, 0}, {1, -1}, {1, 1},
            {0, 1}, {0, -1}
    };

    /**
     * 输入数组
     */
    public static int[][] inputNumMatrix;
    /**
     * 已访问数组
     */
    public static boolean[][] visitedMatrix;

    /**
     * 点击次数
     */
    private static int clickCount = 0;

    public static void main(String[] args) {
        // 输入处理
        Scanner inputScanner = new Scanner(System.in);
        int[] inputFirstRow = Arrays.stream(inputScanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = inputFirstRow[0];
        int m = inputFirstRow[1];
        inputNumMatrix = new int[n][m];
        visitedMatrix = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            inputNumMatrix[i] = Arrays.stream(inputScanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        inputScanner.close();

        // 执行逻辑
        // DFS 遍历
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inputNumMatrix[i][j] == 1 && !visitedMatrix[i][j]) {
                    dfs(i, j);
                    // 更新状态值
                    clickCount++;
                }
            }
        }

        // 输出结果
        System.out.println(clickCount);

    }

    /**
     * DFS 遍历输入数组，查找符合规则的连续 1 的区域
     *
     * @param currX
     * @param currY
     */
    private static void dfs(int currX, int currY) {
        // 边界判断
        if (currX < 0 || currX >= inputNumMatrix.length || currY < 0 || currY >= inputNumMatrix[0].length) {
            return;
        }
        if (inputNumMatrix[currX][currY] == 0) {
            return;
        }
        if (visitedMatrix[currX][currY]) {
            return;
        }

        visitedMatrix[currX][currY] = true;
        // 等值判断
        if (inputNumMatrix[currX][currY] == 1) {
            inputNumMatrix[currX][currY] = 0;
        }

        // 遍历8个方向
        for (int[] dir : dirs) {
            int nextX = currX + dir[0];
            int nextY = currY + dir[1];
            dfs(nextX, nextY);
        }

    }

}
