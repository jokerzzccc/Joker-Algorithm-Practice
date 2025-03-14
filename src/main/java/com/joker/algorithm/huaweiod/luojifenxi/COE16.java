package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Scanner;

/**
 * 绘图机器
 * 题目描述
 * 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
 * <p>
 * 1. 尝试沿着横线坐标正向绘制直线直到给定的终点E
 * <p>
 * 2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移
 * <p>
 * 给定的横坐标终点值E 以及若干条绘制指令，
 * <p>
 * 请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
 * <p>
 * 输入描述
 * 首行为两个整数 N 和 E
 * 表示有N条指令,机器运行的横坐标终点值E
 * 接下来N行 每行两个整数表示一条绘制指令x offsetY
 * 用例保证横坐标x以递增排序的方式出现
 * 且不会出现相同横坐标x
 * 取值范围
 * <p>
 * 0<N<=10000
 * 0<=x<=E<=20000
 * -10000<=offsetY<=10000
 * 输出描述
 * 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
 * <p>
 * 示例1
 * 输入
 * <p>
 * 4 10
 * 1 1
 * 2 1
 * 3 1
 * 4 -2
 * 输出
 * <p>
 * 12
 * ————————————————
 * 题目分析：
 * 有点类似使用动态规划做 斐波那契数列
 *
 * @author jokerzzccc
 * @date 2025/3/11
 */
public class COE16 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int E = input.nextInt();
        if (E == 0) { // 如果终点横坐标为0
            System.out.println(0); // 输出面积为0
            return;
        }
        int[] offsets = new int[E]; // 创建一个长度为终点横坐标的整数数组，用于存储纵坐标偏移量
        for (int i = 0; i < N; i++) {
            int cur_x = input.nextInt(); // 当前点的横坐标
            int offset_y = input.nextInt(); // 当前点纵坐标相较于上一个点纵坐标的偏移量
            offsets[cur_x] = offset_y; // 将偏移量存储在对应横坐标位置上
        }

        input.close();

        // 创建一个长度为终点横坐标的整数数组，用于存储每个横坐标位置的纵坐标偏移量之和
        int[] dp = new int[E];
        // 第一个位置的纵坐标偏移量为指令中的纵坐标偏移量
        dp[0] = offsets[0];
        for (int i = 1; i < E; i++) {
            // 当前位置的纵坐标偏移量为指令中的纵坐标偏移量加上前一个位置的纵坐标偏移量之和
            dp[i] = dp[i - 1] + offsets[i];
        }

        int sum = 0;
        for (int num : dp) {
            sum += Math.abs(num);
        }

        System.out.println(sum);
    }

}
