package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 不等式是否满足约束并输出最大差
 * 题目描述
 * 给定一组不等式，判断是否成立并输出不等式的最大差(输出浮点数的整数部分)
 * 要求:
 * 不等式系数为 double类型，是一个二维数组
 * 不等式的变量为 int类型，是一维数组;
 * 不等式的目标值为 double类型，是一维数组
 * 不等式约束为字符串数组，只能是:“>”,“>=”,“<”,“<=”,“=”，
 * 例如，不等式组:
 * a11x1 + a12x2 + a13x3 + a14x4 + a15x5 <= b1;
 * a21x1 + a22x2 + a23x3 + a24x4 + a25x5 <= b2;
 * a31x1 + a32x2 + a33x3 + a34x4 + a35x5 <= b3;
 * 最大差 = max{(a11x1+a12x2+a13x3+a14x4+a15x5-b1),(a21x1+a22x2+a23x3+a24x4+ a25x5-b2),(a31x1+a32x2+a33x3+a34x4+a35x5-b3)},
 * 类型为整数(输出浮点数的整数部分)
 * 输入描述
 * a11,a12,a13,a14,a15,a21,a22,a23,a24,a25, a31,a32,a33,a34,a35,x1,x2,x3,x4,x5,b1,b2,b3,<=,<=,<=
 * 不等式组系数(double类型):
 * a11,a12,a13,a14,a15
 * a21,a22,a23,a24,a25
 * a31,a32,a33,a34,a35
 * 不等式变量(int类型):x1,x2,x3,x4,x5
 * 不等式目标值(double类型):b1,b2,b3
 * 不等式约束(字符串类型):<=,<=,<=
 * 输出描述
 * true或者 false，最大差
 * 示例1
 * 输入
 * 2.3,3,5.6,7,6;11,3,8.6,25,1;0.3,9,5.3,66,7.8;1,3,2,7,5;340,670,80.6;<=,<=,<=
 * 输出
 * false 458
 * 示例2
 * 输入
 * 2.36,3,6,7.1,6;1,30,8.6,2.5,21;0.3,69,5.3,6.6,7.8;1,13,2,17,5;340,67,300.6;<=,>=,<=
 * 输出
 * false 758
 * 说明
 * ————————————————
 * 题型分析：
 * 【简单】模拟 + 二维数组 + 字符串 + double 数值精度处理
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE49 {

    public static void main(String[] args) {
        // 1. 输入处理
        Scanner input = new Scanner(System.in);
        String[][] inputArr = Arrays.stream(input.nextLine().split(";"))
                .map(s -> s.split(","))
                .toArray(String[][]::new);
        // 将不等式系数转换为 Double 类型的二维数组
        Double[][] coefficients = new Double[3][5];
        for (int i = 0; i < 3; i++) {
            coefficients[i] = Arrays.stream(inputArr[i])
                    .map(Double::parseDouble)
                    .toArray(Double[]::new);
        }
        // 将不等式的变量转换为 Double 类型的一维数组
        Double[] variables = Arrays.stream(inputArr[3])
                .map(Double::parseDouble)
                .toArray(Double[]::new);
        // 将不等式的目标值转换为 Double 类型的一维数组
        Double[] targets = Arrays.stream(inputArr[4])
                .map(Double::parseDouble)
                .toArray(Double[]::new);
        // 将不等式约束转换为字符串数组
        String[] constraints = inputArr[5];

        // 2. 开始计算：
        // 每个不等式的差值
        double[] diffs = new double[3];
        // 是否所有不等式都成立
        boolean isSatisfied = true;
        for (int i = 0; i < 3; i++) {
            // 计算不等值左侧的值
            double leftSum = leftMultiplication(coefficients[i], variables[i]);
            // 计算差值
            diffs[i] = leftSum - targets[i];
            // 判断不等式是否成立
            if (!compareWithZero(diffs[i], constraints[i])) {
                isSatisfied = false;
            }
        }

        double maxDiff = Arrays.stream(diffs)
                .max()
                .orElse(0.0);

        System.out.println(isSatisfied + " " + (int) Math.floor(maxDiff));

        input.close();
    }

    private static boolean compareWithZero(double diff, String constraint) {
        boolean isSatisfied = false;
        switch (constraint) {
            case "<=":
                isSatisfied = diff <= 0;
                break;
            case ">=":
                isSatisfied = diff >= 0;
                break;
            case "=":
                isSatisfied = Math.abs(diff) < 1e-6; // 处理浮点数精度
                break;
            case "<":
                isSatisfied = diff < 0;
                break;
            case ">":
                isSatisfied = diff > 0;
                break;
            default:
                break;
        }

        return isSatisfied;
    }

    private static double leftMultiplication(Double[] coefficient, Double variable) {
        double result = 0.0;
        for (Double aDouble : coefficient) {
            result += aDouble * variable;
        }
        return result;
    }

}
