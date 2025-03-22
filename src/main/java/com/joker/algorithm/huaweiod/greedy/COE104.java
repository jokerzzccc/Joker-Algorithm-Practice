package com.joker.algorithm.huaweiod.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 最短木板长度
 * <p>
 * 一、题目描述
 * 小明有 n 块木板，第 i ( 1 ≤ i ≤ n ) 块木板长度为 ai。 小明买了一块长度为 m 的木料，这块木料可以切割成任意块，拼接到已有的木板上，用来加长木板。
 * 小明想让最短的木板尽量长。 请问小明加长木板后，最短木板的长度可以为多少？
 * 二、输入描述
 * 输入的第一行包含两个正整数，n(1≤n≤103)、m(1≤m≤106)；
 * n表示木板数，m表示木板长度。输入的第二行包含n个正整数，a1,a2,…an(1≤ai≤106)。
 * 三、输出描述
 * 输出的唯一一行包含一个正整数，表示加长木板后，最短木板的长度最大可以为多少？
 * 四、测试用例
 * 测试用例1
 * 1、输入
 * 1 10
 * 5
 * 2、输出
 * 15
 * 3、说明
 * 只有一块木板，初始长度 5，加上所有 10 的木料可达到 15。
 * ————————————————
 * 题型分析：
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE104 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[] inputStrArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        // 优先级队列，存储木板长度并排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));
        for (int i = 0; i < n; i++) {
            queue.add(inputStrArr[i]);
        }

        // 贪心策略：每次最小的都 + 1，然后重新排序，重复
        for (int i = 0; i < m; i++) {
            Integer currMin = queue.poll();
            queue.add(currMin + 1);
        }

        System.out.println(queue.peek());

    }

}
