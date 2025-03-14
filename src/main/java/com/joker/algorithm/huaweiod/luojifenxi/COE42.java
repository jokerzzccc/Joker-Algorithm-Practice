package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 找等值元素（找数字）
 * 题目描述
 * 给一个二维数组 nums，对于每一个元素 nums[i]，找出距离最近的且值相等的元素，
 * 输出横纵坐标差值的绝对值之和，如果没有等值元素，则输出-1。
 * 输入描述
 * 输入第一行为二维数组的行
 * 输入第二行为二维数组的列
 * 输入的数字以空格隔开。
 * 备注
 * 针对数组 nums[i][j]，满足 0 < i ≤ 100，0 < j ≤ 100
 * 对于每个数字，最多存在 100 个与其相等的数字
 * 输出描述
 * 数组形式返回所有坐标值。
 * 示例1
 * 输入
 * 3
 * 5
 * 0 3 5 4 2
 * 2 5 7 8 3
 * 2 5 4 2 4
 * 输出
 * [[-1, 4, 2, 3, 3], [1, 1, -1, -1, 4], [1, 1, 2, 3, 2]]
 * 说明
 * ————————————————
 * 题型分析：
 * 【中等】二维数组 + BFS + 链表
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE42 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = Integer.parseInt(scanner.nextLine().trim());
        int column = Integer.parseInt(scanner.nextLine().trim());
        int[][] array = new int[row][column];
        // K-数字，V-值为该数字的坐标列表
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int val = scanner.nextInt(); // 读取当前元素
                array[i][j] = val;

                map.putIfAbsent(val, new ArrayList<>());
                map.get(val).add(new int[]{i, j});
            }
        }

        // 输出结果
        int[][] res = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int val = array[i][j];
                if (map.get(val).size() == 1) {
                    // 如果当前数字没有相同的数字，则输出-1
                    res[i][j] = -1;
                } else {
                    // 否则调用 BFS 函数，计算到最近相同元素的距离
                    res[i][j] = bfs(map.get(val), i, j);

                }
            }
        }

        // 输出转换
        String[] resultStrings = new String[res.length];
        for (int i = 0; i < res.length; i++) {
            resultStrings[i] = Arrays.toString(res[i]);
        }
        System.out.println(Arrays.toString(resultStrings));

        scanner.close();
    }

    private static int bfs(List<int[]> posList, int currX, int currY) {
        int minDistance = Integer.MAX_VALUE;

        for (int[] pos : posList) {
            int px = pos[0];
            int py = pos[1];
            // 如果这个相同数字的位置与当前坐标不同
            if (px != currX || py != currY) {
                // 计算当前元素与相同数字的曼哈顿距离
                int tempDistance = Math.abs(px - currX) + Math.abs(py - currY);
                // 更新最小距离
                minDistance = Math.min(minDistance, tempDistance);
            }
        }

        return minDistance;
    }

}
