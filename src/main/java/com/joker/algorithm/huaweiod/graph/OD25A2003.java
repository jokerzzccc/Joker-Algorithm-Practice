package com.joker.algorithm.huaweiod.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 战场索敌
 * 题目描述
 * 有一个大小是N*M的战场地图，被墙壁 ‘#’ 分隔成大小不同的区域，上下左右四个方向相邻的空地 ‘.’ 属于同一个区域，只有空地上可能存在敌人’E”，
 * 请求出地图上总共有多少区域里的敌人数小于K。
 * 输入描述
 * 第一行输入为N,M,K；
 * N表示地图的行数，M表示地图的列数， K表示目标敌人数量
 * N，M<=100
 * 之后为一个NxM大小的字符数组。
 * 输出描述
 * 敌人数小于K的区域数量
 * <p>
 * 示例1
 * 输入
 * 3 5 2
 * ..#EE
 * E.#E.
 * ###..
 * 输出
 * 1
 * 说明
 * 地图被墙壁分为两个区域，左边区域有1个敌人，右边区域有3个敌人，符合条件的区域数量是1
 * ————————————————
 * 题型分析
 * 【困难】 图 + 二维数组 + DFS（访问节点）
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A2003 {

    // 定义四个相邻方向的数组
    public static int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    // 定义标记访问状态的二维数组
    private static boolean[][] visited;
    // 定义存储地图的二维字符数组
    private static char[][] matrix;
    // 记录当前区域的敌人数量
    private static int enemyCount;

    // 初始参数
    public static int n;
    public static int m;
    public static int k;

    public static final Character OBSTACLE = '#';
    public static final Character EMPTY = '.';
    public static final Character ENEMY = 'E';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] init_params = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = init_params[0];
        m = init_params[1];
        k = init_params[2];
        matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] charArray = scanner.nextLine().toCharArray();
            matrix[i] = charArray;
        }
        scanner.close();

        //
        // 是否被访问过
        visited = new boolean[n][m];
        int ans = 0; // 初始化符合条件的区域计数
        // 整体思路是，遍历地图中的每个位置，如果该位置未被访问过且不是墙壁，则调用dfs函数计算以该位置为起点的区域中敌人的数量，
        // 如果该数量小于目标敌人数量k，则将区域数量加1。
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果当前格子已经访问过或是墙壁，跳过
                if (matrix[i][j] == OBSTACLE || visited[i][j]) {
                    continue;
                }
                // 当前区域的敌人计数，置0
                enemyCount = 0;
                // 每 DFS 查询一次，就是一个区域
                dfs(i, j); // 深度优先搜索该区域
                ans += enemyCount < k ? 1 : 0;

            }
        }

        System.out.println(ans);

    }

    /**
     * 深度优先搜索函数，从(rowIndex, columnIndex)位置开始计算敌人数
     */
    private static void dfs(int rowIndex, int columnIndex) {
        // 检查相邻位置是否在地图范围内，未访问过且不是墙壁，不满足，则退出 DFS
        if (!(rowIndex >= 0 && rowIndex < n && columnIndex >= 0 && columnIndex < m
                && !visited[rowIndex][columnIndex]
                && matrix[rowIndex][columnIndex] != OBSTACLE)) {
            return;
        }

        // 更新参数
        // 前序位置
        visited[rowIndex][columnIndex] = true;
        if (matrix[rowIndex][columnIndex] == ENEMY) {
            enemyCount++;
        }

        // 遍历四个相邻方向
        for (int[] dir : dirs) {
            int nextRow = rowIndex + dir[0];
            int nextColumn = columnIndex + dir[1];
            // 递归访问相邻位置
            dfs(nextRow, nextColumn);
        }

    }

}
