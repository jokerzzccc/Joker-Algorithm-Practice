package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 整数对最小和
 * 题目描述
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 * 并对取出的所有元素求和，计算和的最小值。
 * 注意：
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 * 输入描述
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * 0 < array1[i] <= 1000
 * 0 < array2[i] <= 1000
 * 接下来一行为正整数k
 * 0 < k <= array1.size() * array2.size()
 * 输出描述
 * 满足要求的最小和
 * <p>
 * 示例1
 * 输入
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * ————————————————
 * 题型分析：
 * 【简单】模拟题 + 数组+ 循环 + 排序
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE40 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 数组一
        int[] array1 = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 数组一的长度
        int cnt1 = array1[0];
        // 数组二
        int[] array2 = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int cnt2 = array2[0];
        // 数组二的长度
        int k = Integer.parseInt(scanner.nextLine().trim());

        // 计算所有的和
        List<Integer> allMulti = new ArrayList<>();
        for (int i = 1; i < cnt1; i++) {
            for (int j = 1; j < cnt2; j++) {
                allMulti.add(array1[i] + array2[j]);
            }
        }

        // 求前 K 最小和
        Collections.sort(allMulti);
        int kSum = 0;
        for (int i = 0; i < k; i++) {
            kSum += allMulti.get(i);
        }
        System.out.println(kSum);
        scanner.close();

    }

}
