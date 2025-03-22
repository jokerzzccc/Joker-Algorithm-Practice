package com.joker.algorithm.huaweiod.sorting;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 免单统计
 * <p>
 * 题目描述
 * <p>
 * 华为商城举办了一个促销活动，如果某顾客是某一秒内最早时刻下单的顾客(可能是多个人)，则可以获取免单。
 * <p>
 * 请你编程计算有多少顾客可以获取免单
 * <p>
 * 输入描述
 * <p>
 * 输入为n行数据，每一行表示一位顾客的下单时间
 * <p>
 * 以(年-月-日时-分-秒.毫秒)yyyy-MM-ddHH:mm:ss.fff形式给出。
 * <p>
 * 0<n<50000
 * 2000<yyyy<2020
 * O<MM<=12
 * 0<dd<=28
 * 0<=HH<=23
 * 0<=mm<=59
 * 0<=ss<=59
 * 0<=fff<=999
 * 所有输入保证合法
 * <p>
 * 输出描述
 * <p>
 * 输出一个整数，表示有多少顾客可以获取免单
 * <p>
 * 用例
 * 输入：
 * 3
 * 2019-01-01 00:00:00.001
 * 2019-01-01 00:00:00.002
 * 2019-01-01 00:00:00.003
 * 输出
 * 1
 * ---------------
 * 题型分析：
 * 【简单】排序
 *
 * @author jokerzzccc
 * @date 2025/3/22
 */
public class COE74 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        // K- 时间点，V-人数(排序)
        TreeMap<String, Integer> time2Count = new TreeMap<>();
        for (int i = n; i > 0; i--) {
            String time = scanner.nextLine();
            time2Count.put(time, time2Count.getOrDefault(time, 0) + 1);
        }
        scanner.close();

        // 免单人数
        int maxFreeCount = 0;
        // 存储上一个订单的秒数
        String prevTime = "";
        for (Map.Entry<String, Integer> entry : time2Count.entrySet()) {
            String currentSecond = entry.getKey().substring(0, 19);
            if (!prevTime.equals(currentSecond)) {
                prevTime = currentSecond;
                maxFreeCount += entry.getValue();
            }
        }

        System.out.println(maxFreeCount);
    }

}
