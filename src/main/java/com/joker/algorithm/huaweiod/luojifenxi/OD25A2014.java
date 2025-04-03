package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 最大相连男生数 / 学生方阵
 * 题目描述
 * 学校组织活动，将学生排成一个矩形方阵。
 * 请在矩形方阵中找到最大的位置相连的男生数量。
 * 这个相连位置在一个直线上，方向可以是水平的，垂直的，成对角线的或者呈反对角线的。
 * 注：学生个数不会超过10000
 * <p>
 * 输入描述
 * 输入的第一行为矩阵的行数和列数，接下来的n行为矩阵元素，元素间用”,”分隔。
 * <p>
 * 输出描述
 * 输出一个整数，表示矩阵中最长的位置相连的男生个数。
 * <p>
 * 示例1
 * 输入
 * 3,4
 * F,M,M,F
 * F,M,M,F
 * F,F,F,M
 * 输出
 * 3
 * ————————————————
 * <p>
 * 题型分析
 * 【中等】 逻辑分析 + 二维数组 + BFS
 *
 * @author jokerzzccc
 * @date 2025/4/2
 */
public class OD25A2014 {

    /**
     * 移动方向，只用四个方向：右，下，右下，左下
     */
    public static final int[][] dirs = {
            {0, 1},
            {1, 0},
            {1, 1},
            {-1, 1},
    };

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    public static String[][] input_matrix;
    public static int n;
    public static int m;

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] init_param = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = init_param[0];
        m = init_param[1];
        input_matrix = new String[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().split(",");
            input_matrix[i] = row;
        }
        scanner.close();

        //
        // 记录男性的坐标
        Queue<int[]> maleIndicesQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (MALE.equals(input_matrix[i][j])) {
                    maleIndicesQueue.offer(new int[]{i, j});
                }
            }
        }

        int max_count = 0;
        while (!maleIndicesQueue.isEmpty()) {
            int[] curr = maleIndicesQueue.poll();
            int currMaxCnt = 0;

            // 从当前位置为起点，遍历每个方向
            for (int[] dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];

                // 当前的连续数量
                int continuous_count = 1;

                // 沿着当前固定方向寻找
                while (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                        && MALE.equals(input_matrix[nextRow][nextCol])) {
                    continuous_count++;

                    nextRow += dir[0];
                    nextCol += dir[1];
                }

                currMaxCnt = Math.max(currMaxCnt, continuous_count);

            }

            max_count = Math.max(max_count, currMaxCnt);
        }

        // 输出结果
        System.out.println(max_count);

    }

}
