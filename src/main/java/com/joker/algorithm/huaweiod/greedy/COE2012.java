package com.joker.algorithm.huaweiod.greedy;

import java.util.*;

/**
 * 导师请吃火锅
 * 一、题目描述
 * 入职后，导师会请你吃饭，你选择了火锅。 火锅里会在不同时间下很多菜. 不同食材要煮不同的时间，才能变得刚好合适。 你希望吃到最多的刚好合适的菜，但你的手速不够快，用 m 代表手速，每次下手捞菜后至少要过 m 秒才能再捞(每次只能捞一个)。 那么用最合理的策略，最多能吃到多少刚好合适的菜?
 * 二、输入描述
 * 第一行两个整数 n，m，其中 n 代表往锅里下的菜的个数，m 代表手速。 (1<n，m< 1000)
 * 接下来有 n 行，每行有两个数 x，y 代表第 x 秒下的菜过 y 秒才能变得刚好合适。
 * (1<x,y< 1000)
 * 三、输出描述
 * 输出一个整数代表用最合理的策略，最多能吃到刚好合适的菜的数量。
 * 四、测试用例
 * 测试用例1
 * 1、输入
 * 3 2
 * 1 2
 * 2 1
 * 3 2
 * 2、输出
 * 2
 * <p>
 * 用例2
 * 输入
 * 2 1
 * 1 2
 * 2 1
 * 输出
 * 1
 * ————————————————
 * 题型分析：
 * 【中等】贪心
 * 贪心策略：将所有合适吃的菜按照时间排序，然后从第一个菜开始，逐步遍历，只要满足时间间隔，就可以直接计数
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE2012 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] firstRow = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstRow[0]);
        int m = Integer.parseInt(firstRow[1]);
        int[][] matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        // 每道菜，在什么时间可以捞
        List<Integer> readyTimes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            readyTimes.add(matrix[i][0] + matrix[i][1]);
        }

        Collections.sort(readyTimes);

        // 贪心策略，第一个菜必须捞
        int count = 1;
        // 上一次的捞菜时间
        int lastTime = readyTimes.get(0);

        for (int i = 1; i < n; i++) {
            // 如果当前菜的合适时间满足时间间隔要求
            if (readyTimes.get(i) >= m + lastTime) {
                count++;
                lastTime = readyTimes.get(i);
            }
        }

        System.out.println(count);
    }

}
