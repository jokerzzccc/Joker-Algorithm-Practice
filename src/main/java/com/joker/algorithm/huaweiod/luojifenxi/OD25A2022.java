package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 阿里巴巴找黄金宝箱Ⅳ
 * <p>
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面有一个数字，箱子排列成一个环，编号最大的箱子的下一个是编号为0的箱子。
 * 请输出每个箱了贴的数字之后的第一个比它大的数，如果不存在则输出-1。
 * 输入描述
 * 输入一个数字字串，数字之间使用逗号分隔，例如: 1,2,3,1
 * 1 ≤ 字串中数字个数 ≤ 10000:
 * -100000 ≤ 每个数字值 ≤ 100000
 * 输出描述
 * 下一个大的数列表，以逗号分隔，例如: 2,3,6,-1,6
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java 使用sacnner/system.out.print()
 * <p>
 * 用例1
 * 输入
 * 2,5,2
 * 输出
 * 5,-1,5
 * 说明
 * 第一个2的下一个更大的数是5;
 * 数字5找不到下一个更大的数;
 * 第二个2的下一个最大的数需要循环搜索，结果也是 5
 * ————————————————
 * 题型分析
 * 【中等】 一维数组 + 环 + 单调栈
 * 类似 leetcode-503 下一个更大元素 II
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2022 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] input_arr = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        int n = input_arr.length;

        int[] result_arr = new int[n];
        Arrays.fill(result_arr, -1);
        for (int i = 0; i < n; i++) {
            int curr = input_arr[i];
            for (int j = 1; j <= n - 1; j++) {
                // 处理环的问题
                if (input_arr[(i + j) % n] > curr) {
                    result_arr[i] = input_arr[(i + j) % n];
                    break;
                }
            }
        }

        // 输出结果
        StringJoiner sj = new StringJoiner(",");
        for (int num : result_arr) {
            sj.add(String.valueOf(num));
        }
        System.out.println(sj.toString());

    }

}
