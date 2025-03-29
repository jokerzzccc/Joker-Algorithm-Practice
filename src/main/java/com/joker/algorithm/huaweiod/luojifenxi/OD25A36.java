package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 矩阵最大值
 * 题目描述
 * 给定一个仅包含0和1的N*N二维矩阵，请计算二维矩阵的最大值，计算规则如下：
 * 1、 每行元素按下标顺序组成一个二进制数（下标越大越排在低位），二进制数的值就是该行的值。矩阵各行值之和为矩阵的值。
 * 2、允许通过向左或向右整体循环移动每行元素来改变各元素在行中的位置。
 * 比如：
 * [1,0,1,1,1]向右整体循环移动2位变为[1,1,1,0,1]，二进制数为11101，值为29。
 * [1,0,1,1,1]向左整体循环移动2位变为[1,1,1,1,0]，二进制数为11110，值为30。
 * 输入描述
 * 1、输入的第一行为正整数，记录了N的大小，0 < N <= 20。
 * 2、输入的第2到N+1行为二维矩阵信息，行内元素边角逗号分隔。
 * 输出描述
 * 矩阵的最大值。
 * <p>
 * 示例1
 * 输入
 * 5
 * 1,0,0,0,1
 * 0,0,0,1,1
 * 0,1,0,1,0
 * 1,0,0,1,1
 * 1,0,1,0,1
 * 输出
 * 122
 * 说明
 * 第一行向右整体循环移动1位，得到本行的最大值[1,1,0,0,0]，二进制值为11000，十进制值为24。
 * 第二行向右整体循环移动2位，得到本行的最大值[1,1,0,0,0]，二进制值为11000，十进制值为24。
 * 第三行向左整体循环移动1位，得到本行的最大值[1,0,1,0,0]，二进制值为10100，十进制值为20。
 * 第四行向右整体循环移动2位，得到本行的最大值[1,1,1,0,0]，二进制值为11100，十进制值为28。
 * 第五行向右整体循环移动1位，得到本行的最大值[1,1,0,1,0]，二进制值为11010，十进制值为26。
 * 因此，矩阵的最大值为122。
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】二维数组 + 二进制字符串转换 + 暴力遍历
 *
 * @author jokerzzccc
 * @date 2025/3/29
 */
public class OD25A36 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        // 各行的最大值
        int[] matrixSum = new int[n];
        // 遍历所有行
        for (int i = 0; i < n; i++) {
            int[] currRow = matrix[i];
            int currMax = binaryIntArray(currRow);

            // 如果当前行没有1，则跳过当前行
            if (currMax != 0) {
                // 遍历当前行的所有循环状态(最多右移 n - 1 次，加上不移动的，共 n 种情况)
                for (int j = 0; j < n; j++) {
                    // 当前行的最后一个数
                    int temp = matrix[i][n - 1];
                    // 当前行原地向右移一位
                    for (int k = n - 1; k > 0; k--) {
                        matrix[i][k] = matrix[i][k - 1];
                    }
                    matrix[i][0] = temp;
                    // 取当前行最大值
                    int stateSum = binaryIntArray(matrix[i]);
                    currMax = Math.max(currMax, stateSum);
                }
            }

            matrixSum[i] = currMax;
        }

        // 输出结果
        int resSum = Arrays.stream(matrixSum).sum();
        System.out.println(resSum);

    }

    /**
     * 一维整形数组（只有0，1）》转为二进制字符串》转为整数
     *
     * @param nums
     * @return
     */
    public static int binaryIntArray(int[] nums) {
        return Integer.parseInt(Arrays.stream(nums)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString()
                , 2);
    }

}
