package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 区间交叠 / 最少数量线段覆盖
 * <p>
 * 题目描述
 * 给定坐标轴上的一组线段，线段的起点和终点均为整数并且长度不小于1，请你从中找到最少数量的线段，这些线段可以覆盖柱所有线段。
 * 输入描述
 * 第一行输入为所有线段的数量，不超过10000，后面每行表示一条线段，格式为"x,y"，x和y分别表示起点和终点，取值范围是[-105，105]。
 * 输出描述
 * 最少线段数量，为正整数
 * <p>
 * 示例1
 * 输入
 * 3
 * 1,4
 * 2,5
 * 3,6
 * 输出
 * 2
 * 说明
 * ————————————————
 * 题型分析
 * 【困难】模拟 + 区间交集
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2021 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<int[]> input_intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] array = Arrays.stream(scanner.nextLine().trim().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            input_intervals.add(array);
        }
        scanner.close();

        // 排序：先按起点升序
        input_intervals.sort(Comparator.comparingInt(a -> a[0]));

        // 里面的集合，可以覆盖所有线段
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(input_intervals.get(0));
        for (int i = 1; i < n; i++) {
            int[] curr = input_intervals.get(i);

            // 遍历队列区间，找到合适位置，插入当前区间的相关
            while (true) {
                if (queue.isEmpty()) {
                    queue.addLast(curr);
                    break;
                }

                int[] last = queue.getLast();
                int start1 = last[0], end1 = last[1];
                int start2 = curr[0], end2 = curr[1];
                if (start2 > end1) {
                    // 不相交，直接添加
                    queue.add(curr);
                    break;
                } else if (end2 <= end1) {
                    // 当前范围被队列里面的完全包含，不操作
                    break;
                } else if (start2 <= start1) {
                    // 当前范围起点在队列尾部前，调整队列尾部
                    queue.removeLast();
                } else {
                    // 部分重叠
                    queue.add(new int[]{end1, end2});
                    break;
                }
            }

        }

        // 输出结果
        System.out.println(queue.size());

    }

}
