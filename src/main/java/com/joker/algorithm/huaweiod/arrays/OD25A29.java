package com.joker.algorithm.huaweiod.arrays;

import java.util.*;

/**
 * 查找接口成功率最优时间段
 * <p>
 * 题目描述
 * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
 * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
 * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
 * 找出数组中最长时间段，如果未找到则直接返回NULL。
 * 输入描述
 * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
 * minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
 * 输出描述
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
 * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
 * <p>
 * 用例
 * 用例1
 * 输入
 * 1
 * 0 1 2 3 4
 * 输出
 * 0-2
 * 说明
 * 输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
 * 前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
 * <p>
 * 用例2
 * 输入
 * 2
 * 0 0 100 2 2 99 0 2
 * 输出
 * 0-1 3-4 6-7
 * 说明
 * 输入解释：minAverageLost=2，数组[0, 0, 100, 2, 2, 99, 0, 2]
 * 通过计算小于等于2的最长时间段为：
 * 数组下标为0-1即[0, 0]，数组下标为3-4即[2, 2]，数组下标为6-7即[0, 2]，这三个部分都满足平均值小于等于2的要求，
 * 因此输出0-1 3-4 6-7
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】前缀和数组
 *
 * @author jokerzzccc
 * @date 2025/3/30
 */
public class OD25A29 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int average = Integer.parseInt(scanner.nextLine().trim());
        int[] inputArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        int n = inputArr.length;
        // 前缀和数组
        int[] prefix_sums = new int[n + 1];
        // 第一个元素之前加一个 0
        prefix_sums[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefix_sums[i] = prefix_sums[i - 1] + inputArr[i - 1];
        }

        // 最大区间长度
        int maxSubLength = 0;
        List<int[]> maxRes = new LinkedList<>();
        // 遍历所有区间
        for (int left = 0; left < n; left++) {
            // 计算原输入数组 [left, right) 的区间和
            for (int right = left + 1; right <= n; right++) {
                int currSubSum = prefix_sums[right] - prefix_sums[left];
                int subLength = right - left;
                int currAvg = subLength * average;
                // 符合条件，则加入map中
                if (currSubSum <= currAvg) {
                    if (subLength > maxSubLength) {
                        maxRes.clear();
                        maxRes.add(new int[]{left, right - 1});
                        maxSubLength = subLength;
                    } else if (subLength == maxSubLength) {
                        maxRes.add(new int[]{left, right - 1});
                    }
                }

            }
        }

        // 输出结果
        if (maxRes.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int[] arr : maxRes) {
            sj.add(arr[0] + "-" + arr[1]);
        }
        System.out.println(sj.toString());

    }

}
