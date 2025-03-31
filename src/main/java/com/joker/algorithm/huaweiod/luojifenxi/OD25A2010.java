package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 区间交集
 * <p>
 * 题目描述
 * 给定一组闭区间，其中部分区间存在交集。
 * 任意两个给定区间的交集，称为公共区间(如:[1,2],[2,3]的公共区间为[2,2]，[3,5],[3,6]的公共区间为[3,5])。
 * 公共区间之间若存在交集，则需要合并(如:[1,3],[3,5]区间存在交集[3,3]，需合并为[1,5])。
 * 按升序排列输出合并后的区间列表。
 * <p>
 * 输入描述
 * 一组区间列表，
 * 区间数为 N: 0<=N<=1000;
 * 区间元素为 X: -10000<=X<=10000。
 * 输出描述
 * 升序排列的合并区间列表
 * 备注:
 * 1、区间元素均为数字，不考虑字母、符号等异常输入。
 * 2、单个区间认定为无公共区间。
 * 示例1
 * 输入
 * 4
 * 0 3
 * 1 3
 * 3 5
 * 3 6
 * 输出
 * <p>
 * 1 5
 * 说明
 * [0,3]和[1,3]的公共区间为[1,3]，
 * [0,3]和[3,5]的公共区间为[3,3]，
 * [0,3]和[3,6]的公共区间为[3,3]，
 * [1,3]和[3,5]的公共区间为[3,3]，
 * [1,3]和[3,6]的公共区间为[3,3]，
 * [3,5]和[3,6]的公共区间为[3,5]，
 * 公共区间列表为[[1,3],[3,3],[3,5]]；
 * [1,3],[3,3],[3,5]存在交集，须合并为[1,5]。
 * <p>
 * 示例2
 * 输入
 * 4
 * 0 3
 * 1 4
 * 4 7
 * 5 8
 * 输出
 * <p>
 * 1 3
 * 4 4
 * 5 7
 * 说明
 * ————————————————
 * 题型分析：
 * 【困难】模拟 + 区间交集
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2010 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<int[]> input_intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] array = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            input_intervals.add(array);
        }
        scanner.close();

        // 排序
        input_intervals.sort(Comparator.comparingInt(a -> a[0]));
        // 求公共区间
        List<int[]> public_intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] curr = input_intervals.get(i);
            int curr_start = curr[0];
            int curr_end = curr[1];
            for (int j = i + 1; j < n; j++) {
                int[] next = input_intervals.get(j);
                int next_start = next[0];
                int next_end = next[1];

                // 求公共区间
                int left = Math.max(curr_start, next_start), right = Math.min(curr_end, next_end);
                if (left <= right) {
                    public_intervals.add(new int[]{left, right});
                }
            }
        }

        if (public_intervals.isEmpty()) {
            System.out.println("None");
            return;
        }

        // 按照交集起始值升序排序
        public_intervals.sort(Comparator.comparingInt(a -> a[0]));

        // 合并公共区间
        LinkedList<int[]> merged_intervals = new LinkedList<>();
        // 初始化
        merged_intervals.add(public_intervals.get(0));
        for (int i = 1; i < public_intervals.size(); i++) {
            int[] curr = merged_intervals.getLast();
            int curr_start = curr[0];
            int curr_end = curr[1];
            int next_start = public_intervals.get(i)[0];
            int next_end = public_intervals.get(i)[1];

            if (curr_end >= next_start) {
                // 合并交集
                merged_intervals.removeLast();
                merged_intervals.add(new int[]{curr_start, Math.max(curr_end, next_end)});
            } else {
                // 不相交，直接添加
                merged_intervals.add(new int[]{next_start, next_end});
            }
        }

        for (int[] mergedInterval : merged_intervals) {
            System.out.println(mergedInterval[0] + " " + mergedInterval[1]);
        }

    }

}
