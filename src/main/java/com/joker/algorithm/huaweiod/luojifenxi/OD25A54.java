package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 座位调整
 * 题目描述：座位调整
 * 疫情期间课堂的座位进行了特殊的调整，不能出现两个同学紧挨着，必须隔至少一个空位。
 * 给你一个整数数组 desk表示当前座位的占座情况，由若干 0 和 1 组成，其中 0 表示没有占位，1 表示占位。在不改变原有座位秩序情况下，还能安排坐几个人？
 * 输入描述：
 * 第一行是个子数组表示作为占座情况，由若干 0 和 1 组成，其中 0 表示没有占位，1 表示占位
 * 1 <= desk.length <= 2 * 10^4
 * 输出描述：
 * 输出数值表示还能坐几个人
 * <p>
 * 用例1
 * 输入：
 * 1,0,0,0,1
 * 输出：
 * 1
 * 说明：
 * 只有desk[2]的位置可以坐一个人
 * <p>
 * 用例2
 * 输入：
 * 0,0,0,0,0
 * 输出：
 * 3
 * ————————————————
 * 题型分析
 * 【简单】逻辑分析
 *
 * @author jokerzzccc
 * @date 2025/3/30
 */
public class OD25A54 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        // 还可以坐的人数
        int canSeat = 0;
        for (int i = 0; i < inputArr.length; i++) {
            int prev = 0;
            if (i != 0) {
                prev = inputArr[i - 1];
            }
            int curr = inputArr[i];
            int next = 0;
            if (i != inputArr.length - 1) {
                next = inputArr[i + 1];
            }
            if (prev == 0 && curr == 0 && next == 0) {
                canSeat++;
                inputArr[i] = 1;
            }
        }

        // 输出结果
        System.out.println(canSeat);
    }

}
