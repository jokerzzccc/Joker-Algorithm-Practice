package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Scanner;

/**
 * 平均像素值
 * 题目描述
 * 一个图像有n个像素点，存储在一个长度为n的数组img里，每个像素点的取值范围[0,255]的正整数。
 * <p>
 * 请你给图像每个像素点值加上一个整数k（可以是负数），得到新图newImg，使得新图newImg的所有像素平均值最接近中位值128。
 * <p>
 * 请输出这个整数k。
 * <p>
 * 输入描述
 * n个整数，中间用空格分开
 * <p>
 * 备注
 * • 1 <= n <= 100
 * • 如有多个整数k都满足，输出小的那个k；
 * • 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
 * <p>
 * 例如newImg=”-1 -2 256″,会自动更改为”0 0 255″
 * <p>
 * 输出描述
 * 一个整数k
 * <p>
 * 示例1
 * 输入
 * <p>
 * 129 130 129 130
 * 输出
 * <p>
 * -2
 * 说明
 * <p>
 * -1的均值128.5，-2的均值为127.5，输出较小的数-2
 * <p>
 * 示例2
 * 输入
 * <p>
 * 0 0 0 0
 * 输出
 * <p>
 * 128
 * 说明
 * <p>
 * 四个像素值都为0
 * ————————————————
 *
 * 题型分析：
 * 贪心+暴力枚举
 *
 * @author jokerzzccc
 * @date 2025/3/12
 */
public class COE22 {

    public static final int K_NUM_UPPER = 128;
    public static final int K_NUM_LOWER = -127;
    public static final int MIDDLE_NUM = 128;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] split = input.nextLine().split("\\s+");
        int n = split.length;
        int[] img = new int[split.length];
        for (int i = 0; i < n; i++) {
            img[i] = Integer.parseInt(split[i]);
        }

        double min_diff = Integer.MAX_VALUE; // 定义双精度浮点型变量min_diff，并初始化为整型最大值
        int k_ans = 0; // 定义整型变量k_ans，并初始化为0

        // 暴力枚举 K
        for (int k = K_NUM_LOWER; k <= K_NUM_UPPER; k++) {
            double sumTemp = 0; // 定义双精度浮点型变量 sum，并初始化为0
            // 计算在该 K 值下，所有像素值的平均值与中位值的绝对差值
            for (int j = 0; j < n; j++) {
                int newVal = img[j] + k;
                // 将new_val的值限制在0到255之间
                newVal = Math.max(0, Math.min(255, newVal));
                sumTemp += newVal;
            }
            double diff = Math.abs(sumTemp / n - MIDDLE_NUM);

            // 更新结果 K
            if (diff < min_diff) {
                // 如果diff小于min_diff
                min_diff = diff;
                k_ans = k;
            } else if (diff == min_diff && k_ans != 0) {
                // 如果diff等于min_diff且k_ans不等于0
                k_ans = Math.min(k_ans, k);
            }

        }

        System.out.println(k_ans);

    }

}
