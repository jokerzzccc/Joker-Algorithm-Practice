package com.joker.algorithm.huaweiod.sorting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 智能成绩表
 * 问题描述
 * 小明来到学校当老师, 需要将学生按考试总分或单科分数进行排名, 你能帮帮他吗?
 * 输入格式
 * 第 1 行输入两个整数, 学生人数 n 和科目数量 m
 * 0 < n < 100
 * 0 < m < 10
 * 第 2 行输入 m 个科目名称, 彼此之间用空格隔开
 * 科目名称只由英文字母构成, 单个长度不超过10个字符
 * 科目的出现顺序和后续输入的学生成绩一一对应
 * 不会出现重复的科目名称
 * 第 3 行开始的 n 行, 每行包含一个学生的姓名和该生 m 个科目的成绩 (空格隔开)
 * 学生不会重名
 * 学生姓名只由英文字母构成, 长度不超过10个字符
 * 成绩是0~100的整数, 依次对应第2行种输入的科目
 * 第n+2行, 输入用作排名的科目名称. 若科目不存在, 则按总分进行排序
 * 输出格式
 * 输出一行, 按成绩排序后的学生名字, 空格隔开. 成绩相同的按照学生姓名字典顺序排序.
 * 示例输入1
 * 3 2
 * yuwen shuxue
 * fangfang 95 90
 * xiaohua 88 98
 * minmin 100 82
 * shuxue
 * 示例输出1
 * xiaohua fangfang minmin
 * <p>
 * 示例输入2
 * 3 2
 * yuwen shuxue
 * fangfang 95 90
 * xiaohua 88 95
 * minmin 90 95
 * zongfen
 * 示例输出2
 * fangfang minmin xiaohua
 * ------------
 * 题型分析
 * 【中等】排序
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE27 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        String[] subjects = scanner.nextLine().split(" ");
        Map<String, Integer[]> user2Score = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] curr = scanner.nextLine().split(" ");
            String userId = curr[0];
            Integer[] currScore = new Integer[m + 1];
            user2Score.put(userId, currScore);
            int currSum = 0;
            for (int j = 0; j < m; j++) {
                currScore[j] = Integer.parseInt(curr[j + 1]);
                currSum += currScore[j];
            }
            currScore[m] = currSum;
        }

        String sortSubject = scanner.nextLine();
        scanner.close();

        //
        LinkedList<String> subjectList = new LinkedList<>(Arrays.asList(subjects));
        // 找到排序字段的索引(课程的索引，总分就是 M)
        int sortIndex = -1;

        if (!subjectList.contains(sortSubject)) {
            sortIndex = m;
        } else {
            sortIndex = subjectList.indexOf(sortSubject);
        }

        // 按总分排序
        int finalSortIndex = sortIndex;
        List<String> results = user2Score.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (Objects.equals(o1.getValue()[finalSortIndex], o2.getValue()[finalSortIndex])) {
                        // 成绩相同则按姓名升序排序
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    // 分数降序排序
                    return o2.getValue()[finalSortIndex] - o1.getValue()[finalSortIndex];
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(String.join(" ", results));

    }

}
