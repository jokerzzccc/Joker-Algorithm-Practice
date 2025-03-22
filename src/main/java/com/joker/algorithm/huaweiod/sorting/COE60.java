package com.joker.algorithm.huaweiod.sorting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 选修课
 * 题目描述
 * 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，需要你找出同时选修了两门选修课的学生，先按照班级进行划分，班级编号小的先输出，每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
 * 输入
 * 第一行为第一门选修课学生的成绩，
 * 第二行为第二门选修课学生的成绩，每行数据中学生之间以英文分号分隔，每个学生的学号和成绩以英文逗号分隔，学生学号的格式为8位数字(2位院系编号+入学年份后2位+院系内部1位专业编号+所在班级3位学号),学生成绩的取值范围为 [0,100] 之间的整数，两门选修课选修学生数的取值范围为 [1-2000] 之间的整数。
 * 输出
 * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出 NULL, 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位),
 * 然后另起一行输出这个班级同时选修两门选修课的学生学号，学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序),学生之间以英文分号分隔。
 *
 * 用例1：
 * 输入：
 * 01202021,75;01201033,95;01202008,80;01203006,90;01203088,100
 * 01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
 * 输出
 * 01202
 * 01202008;01202021
 * 01203
 * 01203088
 * ----------
 * 题型分析：
 * 【简单】
 *
 * @author jokerzzccc
 * @date 2025/3/22
 */
public class COE60 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(";");
        String[] secondLine = scanner.nextLine().split(";");
        scanner.close();

        // 数据预处理
        Map<String, Integer> firstMap = new HashMap<>();
        for (int i = 0; i < firstLine.length; i++) {
            String[] curr = firstLine[i].split(",");
            String userId = curr[0];
            Integer score = Integer.parseInt(curr[1]);
            firstMap.put(userId, score);
        }
        Map<String, Integer> secondMap = new HashMap<>();
        for (int i = 0; i < secondLine.length; i++) {
            String[] curr = secondLine[i].split(",");
            String userId = curr[0];
            Integer score = Integer.parseInt(curr[1]);
            secondMap.put(userId, score);
        }

        // 选修了两门课的
        // K-编号，V-分数
        Map<String, Integer> bothMap = new HashMap<>();
        // 存储班级列表(自带排序、去重)
        Set<String> classList = new TreeSet<>();
        for (String userId : firstMap.keySet()) {
            if (secondMap.containsKey(userId)) {
                bothMap.put(userId, firstMap.get(userId) + secondMap.get(userId));
                classList.add(userId.substring(0, 5));
            }
        }

        if (bothMap.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        for (String clazz : classList) {
            System.out.println(clazz);
            List<String> currClass = bothMap.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith(clazz))
                    .sorted((o1, o2) -> {
                        if (Objects.equals(o1.getValue(), o2.getValue())) {
                            // 成绩相同则按姓名升序排序
                            return o1.getKey().compareTo(o2.getKey());
                        }
                        // 分数降序排序
                        return o2.getValue() - o1.getValue();
                    })
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            System.out.println(String.join(";", currClass));
        }

    }

}
