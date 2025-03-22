package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 双十一 最大花费金额
 * 题目描述
 * 双十一众多商品进行打折销售，小明想购买自己心仪的一些物品，但由于受购买资金限制，所以他决定从众多心仪商品中购买三件，而且想尽可能的花完资金。
 * 现在请你设计一个程序帮助小明计算尽可能花费的最大资金数额。
 * 输入描述
 * 输入第一行为一维整型数组M，数组长度小于100，数组元素记录单个商品的价格，单个商品价格小于1000。
 * 输入第二行为购买资金的额度R，R小于100000。
 * 输入格式是正确的，无需考虑格式错误的情况。
 * 输出描述
 * 输出为满足上述条件的最大花费额度。
 * 如果不存在满足上述条件的商品，请返回-1。
 * <p>
 * 示例1
 * 输入
 * 23,26,36,27
 * 78
 * 输出
 * 76
 * 说明
 * 金额23、26和27相加得到76，而且最接近且小于输入金额78。
 * <p>
 * 示例2
 * 输入
 * 23,30,40
 * 26
 * 输出
 * -1
 * 说明
 * 因为输入的商品，无法组合出来满足三件之和小于26.故返回-1。
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口或者暴力搜索
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE98 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int R = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        //
        int n = inputArr.length;
        int maxSum = -1;
        for (int first = 0; first < n - 2; first++) {
            for (int second = first + 1; second < n - 1; second++) {
                for (int third = second + 1; third < n; third++) {
                    int currSum = inputArr[first] + inputArr[second] + inputArr[third];
                    if (currSum <= R) {
                        maxSum = Math.max(maxSum, currSum);
                    }
                }
            }
        }

        System.out.println(maxSum);
    }

}
