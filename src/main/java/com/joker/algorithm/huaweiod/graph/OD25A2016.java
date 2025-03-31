package com.joker.algorithm.huaweiod.graph;

import java.util.*;

/**
 * 宜居星球改造计划
 * 题目描述
 * 2XXX年，人类通过对火星的大气进行宜居改造分析，使得火星已在理论上具备人类宜居的条件；
 * 由于技术原因，无法一次性将火星大气全部改造，只能通过局部处理形式；
 * 假设将火星待改造的区域为row *
 * column的网格，每个网格有3个值，宜居区、可改造区、死亡区，使用YES、NO、NA代替，YES表示该网格已经完成大气改造，NO表示该网格未进行改造，后期可进行改造，NA表示死亡区，不作为判断是否改造完的宜居，无法穿过；
 * 初始化下，该区域可能存在多个宜居区，并目每个宜居区能同时在每个大阳日单位向上下左右四个方向的相邻格子进行扩散，自动将4个方向相邻的真空区改造成宜居区；
 * 请计算这个待改造区域的网格中，可改造区是否能全部成宜居区，如果可以，则返回改造的大阳日天教，不可以则返回-1
 * 输入描述
 * 输入row * column个网格数据，每个网格值枚举值如下: YES，NO，NA；
 * 样例:、
 * YES YES NO
 * NO NO NO
 * NA NO YES
 * 备注
 * grid[i][j]只有3种情况，YES、NO、NA
 * row == grid.length
 * column == grid[i].length
 * 1 ≤ row, column ≤ 8
 * 输出描述
 * 可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1。
 * <p>
 * 示例1
 * 输入
 * YES YES NO
 * NO NO NO
 * YES NO NO
 * 输出
 * 2
 * 说明
 * 经过 2 个太阳日，完成宜居改造。
 * <p>
 * 示例2
 * 输入
 * YES NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 * 输出
 * 6
 * 说明
 * 经过 6 个太阳日，可完成改造
 * <p>
 * 示例3
 * 输入
 * NO NA
 * 1
 * 输出
 * -1
 * 说明
 * 无改造初始条件，无法进行改造
 * <p>
 * 示例4
 * 输入
 * YES NO NO YES
 * NO NO YES NO
 * NO YES NA NA
 * YES NO NA NO
 * 输出
 * -1
 * 说明
 * -1 ，右下角的区域，被周边三个死亡区挡住，无法实现改造
 * ————————————————
 * <p>
 * 题型分析
 * 【困难】图 + BFS(访问节点) + 二维数组
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A2016 {

    // 方向数组
    public static int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public static int n;
    public static int m;

    public static final String INIT_YES = "YES";
    public static final String INIT_NO = "NO";
    public static final String INIT_NA = "NA";

    // 访问标记
    public static boolean visited[][];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 表示图
        List<String[]> inputList = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.isEmpty()) {
                break;
            }
            String[] split = str.split(" ");
            inputList.add(split);
        }
        n = inputList.size();
        m = inputList.get(0).length;
        visited = new boolean[n][m];
        scanner.close();

        // 待改造的格子（NO）
        int readyModifyCnt = 0;
        //  找出 所有 YES，加入队列
        // queue 的内容是坐标
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inputList.get(i)[j].equals(INIT_YES)) {
                    queue.offer(new int[]{i, j});
                } else if (inputList.get(i)[j].equals(INIT_NO)) {
                    readyModifyCnt++;
                }
            }
        }

        // 无初始改造点或全不可改造
        if (queue.isEmpty()) {
            System.out.println(-1);
            return;
        }

        int days = 0;

        // BFS
        while (!queue.isEmpty() && readyModifyCnt > 0) {
            int currDaySize = queue.size();
            for (int i = 0; i < currDaySize; i++) {
                int[] curr = queue.poll();
                // 改造相邻节点
                for (int[] dir : dirs) {
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];

                    // 边界判断 && 是可以改造的
                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                            && inputList.get(nextRow)[nextCol].equals(INIT_NO)) {
                        // 这里更改值，就发挥了 visited 数组的作用
                        inputList.get(nextRow)[nextCol] = INIT_YES;
                        // 队列添加该节点为下一轮要遍历的节点
                        queue.add(new int[]{nextRow, nextCol});
                        readyModifyCnt--;
                    }
                }
            }

            days++;
        }

        if (readyModifyCnt == 0) {
            System.out.println(days);
        } else {
            System.out.println(-1);
        }
    }

}
