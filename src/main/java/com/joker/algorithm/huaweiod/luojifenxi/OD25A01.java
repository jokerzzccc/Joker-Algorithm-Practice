package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 统计匹配的二元组个数
 * 题目描述
 * 给定两个数组A和B，若数组A的某个元素A[i]与数组B中某个元素B[j]满足 A[i] == B[j]，则寻找到一个值匹配的二元组(i,j)。 请统计在这两个数组A和B中，一共存在多少个这样的二元组。
 * 输入描述
 * 第一行输入数组A的长度M
 * 第二行输入数组B的长度N
 * 第三行输入数组A的值
 * 第四行输入数组B的值
 * 备注： 若不存在相等的值，则输出0。 所采用的算法复杂度需小于O(N^2),否则会超时。 输入数组中允许出现重复数字，一个数字可以匹配多次。
 * 输出描述
 * 输出匹配的二元组个数
 * <p>
 * 示例1
 * 输入
 * 5
 * 4
 * 1 2 3 4 5
 * 4 3 2 1
 * 输出
 * 4
 * 示例2
 * 输入
 * 6
 * 3
 * 1 2 4 4 2 1
 * 1 2 3
 * 输出
 * 4
 * 示例3
 * 输入
 * 4
 * 1
 * 1 2 3 4
 * 1
 * 输出
 * 1
 * 示例4
 * 输入
 * 6
 * 3
 * 1 1 2 2 4 5
 * 2 2 4
 * 输出
 * 5
 * ————————————————
 * 【简单】 逻辑分析 + 遍历（数组转 Map）
 * 思路：空间（Map） 换时间
 *
 * @author jokerzzccc
 * @date 2025/3/27
 */
public class OD25A01 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        int[] array_A = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] array_B = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();
        //

        // 统计 A 中数字的出现次数
        Map<Integer, Integer> aCountMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            aCountMap.put(array_A[i], aCountMap.getOrDefault(array_A[i], 0) + 1);
        }
        // 统计 B 中数字的出现次数
        Map<Integer, Integer> bCountMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            bCountMap.put(array_B[i], bCountMap.getOrDefault(array_B[i], 0) + 1);
        }

        // 二元组 的个数
        int res = 0;
        // 统计输出个数
        for (Integer a_key : aCountMap.keySet()) {
            res += aCountMap.get(a_key) * bCountMap.getOrDefault(a_key, 0);
        }

        System.out.println(res);

    }

}
