package com.joker.algorithm.huaweiod.sorting;

import java.util.*;

/**
 * 预定酒店
 * <p>
 * 题目描述
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店(长度为 n 的数组 A)，
 * 他的心理价位是 x 元，请帮他筛选出 k 个最接近 x 元的酒店 (n>=k>0) 并由低到高打印酒店的价格
 * 输入描述
 * 第一行: n,k,x
 * 第二行: A[0] A[1]A[2]...A[n-1]
 * 输出描述
 * 从低到高打印筛选出的酒店价格
 * 用例1：
 * 输入
 * 10 5 6
 * 1 2 3 4 5 6 7 8 9 10
 * 输出：
 * 4 5 6 7 8
 * <p>
 * 用例2
 * 输入：
 * 10 4 6
 * 10 9 8 7 6 5 4 3 2 1
 * 输出：
 * 4 5 6 7
 * <p>
 * 用例3：
 * 输入：
 * 6 3 1000
 * 30 30 200 500 70 300
 * 输出
 * 200 300 500
 * ----------------
 * 题型分析：
 * 【中等】排序
 *
 * @author jokerzzccc
 * @date 2025/3/22
 */
public class COE59 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] firstRow = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = firstRow[0];
        int k = firstRow[1];
        int x = firstRow[2];
        int[] inputPrices = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        // 存储原始价格和与期望的差值
        List<Integer[]> sortedPrices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer[] price = new Integer[2];
            price[0] = inputPrices[i];
            price[1] = Math.abs(inputPrices[i] - x);
            sortedPrices.add(price);
        }

        // 按需排序：差值升序，相同差值价格升序
        sortedPrices.sort((a, b) -> {
            if (a[1].equals(b[1])) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // 排序
        List<Integer> selectedPrices = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            selectedPrices.add(sortedPrices.get(i)[0]);
        }
        Collections.sort(selectedPrices);

        // 输出结果
        StringBuilder sb = new StringBuilder();
        for (Integer price : selectedPrices) {
            sb.append(price).append(" ");
        }
        System.out.println(sb.toString().trim());

    }

}
