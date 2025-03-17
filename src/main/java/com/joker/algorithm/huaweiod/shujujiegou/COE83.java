package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 数组组成的最小数字
 * 题目描述
 * 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出
 * （如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
 * 输入描述
 * 一行用半角逗号分割的字符串记录的整型数组，0 < 数组长度 <= 100，0 < 整数的取值范围 <= 10000。
 * 输出描述
 * 由3个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
 * 示例1
 * 输入
 * 21,30,62,5,31
 * 输出
 * 21305
 * 说明
 * 数组长度超过3，需要选3个元素组成最小数字，21305由21,30,5三个元素组成的数字，为所有组合中最小的数字。
 * <p>
 * 示例2
 * 输入
 * 5,21
 * 输出
 * 215
 * 说明
 * 数组长度小于3， 选择所有元素来组成最小值，215为最小值。
 * ————————————————
 * 题型分析
 * 【中等】字符串 + 字典序排列
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE83 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        // 将输入的字符串按逗号分隔成字符串数组
        List<String> inputNumStrArr = Arrays.asList(input.split(","));

        // 排序一：先按数字大小排序（正序，从小到大）
        Collections.sort(inputNumStrArr, Comparator.comparingInt(Integer::parseInt));
        // 实际需要的数字数量
        List<String> minNums = inputNumStrArr.subList(0, Math.min(3, inputNumStrArr.size()));
        // 排序二：拼接后的字典序排序
        // 主要是看个位数，两位数，三位数等这种混合情况；（比如：如果都是两位数，直接数字排序就够了）
        Collections.sort(minNums, (a, b) -> (a + b).compareTo(b + a));

        // 输出结果
        System.out.println(String.join("", minNums));

    }

}
