package com.joker.algorithm.huaweiod.dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 检查是否存在满足条件的数字组
 * 题目描述
 * 给定一个正整数数组，检查数组中是否存在满足规则的数字组合
 * 规则：A = B + 2C
 * <p>
 * 输入描述
 * 第一行输出数组的元素个数。
 * 接下来一行输出所有数组元素，用空格隔开。
 * <p>
 * 输出描述
 * 如果存在满足要求的数，在同一行里依次输出规则里A/B/C的取值，用空格隔开。
 * 如果不存在，输出0。
 * <p>
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java 使用sacnner/system.out.print()
 * <p>
 * 用例
 * 输入
 * 4
 * 2 7 3 0
 * 输出
 * 7 3 2
 * 说明	7 = 3 + 2 * 2
 * 备注:
 * 数组长度在3-100之间。
 * 数组成员为0-65535，数组成员可以重复，但每个成员只能在结果算式中使用一次。如：数组成员为[0, 0, 1, 5]，0出现2次是允许的，但结果0 = 0 + 2 * 0是不允许的，因为算式中使用了3个0。
 * 用例保证每组数字里最多只有一组符合要求的解。
 * ————————————————
 * <p>
 * 题型分析：
 * 【简单】一维数组 + 排序 + 枚举子区间组合
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A60 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        Integer[] input_arr = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        scanner.close();

        int A = -1;
        int B = -1;
        int C = -1;
        // 降序排序
        Arrays.sort(input_arr, (a, b) -> b - a);
        boolean flag = false;
        // 使用双指针遍历数组，查找满足条件的组合
        int i = 0, j = n - 1;
        while (i < j) {
            int k = i + 1;
            while (k < j) {
                if (input_arr[i] == input_arr[k] + 2 * input_arr[j]) {
                    A = input_arr[i];
                    B = input_arr[k];
                    C = input_arr[j];
                    flag = true;
                    break;
                }
                if (input_arr[i] == input_arr[j] + 2 * input_arr[k]) {
                    A = input_arr[i];
                    B = input_arr[j];
                    C = input_arr[k];
                    flag = true;
                    break;
                }

                k++;
            }

            if (flag) {
                break;
            }

            // 移动指针
            if (input_arr[i] > input_arr[j] + 2 * input_arr[k]) {
                j--;
            } else {
                i++;
            }
        }

        // 输出结果
        if (!flag) {
            System.out.println(0);
        } else {
            System.out.println(A + " " + B + " " + C);
        }

    }

}
