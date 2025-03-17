package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 比赛 / N个选手比赛前三名
 * 题目描述
 * 一个有N个选手参加比赛，选手编号为1~N（3<=N<=100），有M（3<=M<=10）个评委对选手进行打分。
 * 打分规则为每个评委对选手打分，最高分10分，最低分1分。
 * 请计算得分最多的3位选手的编号。
 * 如果得分相同，则得分高分值最多的选手排名靠前
 * (10分数量相同，则比较9分的数量，以此类推，用例中不会出现多个选手得分完全相同的情况)。
 * 输入描述
 * 第一行为半角逗号分割的两个正整数，第一个数字表示M（3<=M<=10）个评委，第二个数字表示N（3<=N<=100）个选手。
 * 第2到M+1行是半角逗号分割的整数序列，表示评委为每个选手的打分，0号下标数字表示1号选手分数，1号下标数字表示2号选手分数，依次类推。
 * 输出描述
 * 选手前3名的编号。
 * **注：**若输入为异常，输出-1，如M、N、打分不在范围内。
 * <p>
 * 示例1
 * 输入
 * 4,5
 * 10,6,9,7,6
 * 9,10,6,7,5
 * 8,10,6,5,10
 * 9,10,8,4,9
 * 输出
 * 2,1,5
 * 说明
 * 第一行代表有4个评委，5个选手参加比赛
 * 矩阵代表是4*5，每个数字是选手的编号，每一行代表一个评委对选手的打分排序，
 * 2号选手得分36分排第1，1号选手36分排第2，5号选手30分(2号10分值有3个，1号10分值只有1个，所以2号排第一)
 * <p>
 * 示例2
 * 输入
 * 2,5
 * 7,3,5,4,2
 * 8,5,4,4,3
 * 输出
 * -1
 * 说明
 * 只有2个评委，要求最少为3个评委
 * <p>
 * 示例3
 * 输入
 * 4,2
 * 8,5
 * 5,6
 * 10,4
 * 8,9
 * 输出
 * -1
 * 说明
 * 只有2名选手参加，要求最少为3名
 * 示例4
 * 输入
 * 4,5
 * 11,6,9,7,8
 * 9,10,6,7,8
 * 8,10,6,9,7
 * 9,10,8,6,7
 * 输出
 * -1
 * 说明
 * 第一个评委给第一个选手打分11，无效分数
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】字符串 + 排序（Map）
 * 思考：示例中也有异常情况的输出内容
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE86 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] inputFirstRow = scanner.nextLine().split(",");
        // 评委
        int m = Integer.parseInt(inputFirstRow[0]);
        // 选手
        int n = Integer.parseInt(inputFirstRow[1]);
        if (m < 3 || m > 10 || n < 3 || n > 100) { // 判断输入是否合法
            System.out.println("-1");
            return;
        }
        int[][] scores = new int[m][n];
        for (int i = 0; i < m; i++) {
            scores[i] = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        // 学院编号，总分，得分列表
        Map<Integer, List<Integer>> studentScores = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> currScores = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (scores[j][i] < 1 || scores[j][i] > 10) {
                    System.out.println("-1");
                    return;
                }
                currScores.add(scores[j][i]);
            }
            // 降序排序
            Collections.sort(currScores, (a, b) -> b - a);

            studentScores.put(i + 1, currScores);
        }
        StringBuilder result = new StringBuilder();
        studentScores.entrySet().stream()
                .sorted((o1, o2) -> {
                    List<Integer> value1 = o1.getValue();
                    List<Integer> value2 = o2.getValue();
                    int sum1 = value1.stream().reduce(Integer::sum).get();
                    int sum2 = value2.stream().reduce(Integer::sum).get();
                    if (sum1 != sum2) {
                        // 按总分降序排列
                        return sum2 - sum1;
                    }
                    // 按得分列表降序排列
                    for (int i = 0; i < m; i++) {
                        if (value1.get(i) == value2.get(i)) {
                            continue;
                        }
                        return value2.get(i) - value1.get(i);
                    }
                    return 0;
                })
                .limit(3)
                .forEach(entry -> {
                    result.append(entry.getKey()).append(",");
                });

        System.out.println(result.substring(0, result.length() - 1));

    }

}
