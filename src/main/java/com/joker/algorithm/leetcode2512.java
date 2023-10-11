package com.joker.algorithm;

import java.util.*;

/**
 * 奖励最顶尖的 K 名学生
 *
 * @author jokerzzccc
 * @date 2023/10/11 20:37
 */
public class leetcode2512 {

    public static void main(String[] args) {
        String[] positive_feedback = {"smart", "brilliant", "studious"};
        String[] negative_feedback = {"not"};
        String[] report = {"this student is not studious", "the student is smart"};
        int[] student_id = {1, 2};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.topStudents(positive_feedback, negative_feedback, report, student_id, k));
    }

    /**
     * 解法一：哈希表 + 排序
     */
    private static class Solution01 {

        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            // 单词分数缓存
            Map<String, Integer> wordScoreMap = new HashMap<>();
            for (String s : positive_feedback) {
                wordScoreMap.put(s, 3);
            }
            for (String s : negative_feedback) {
                wordScoreMap.put(s, -1);
            }

            // 按照规则计算分数
            int len = report.length;
            int[][] students = new int[len][2];
            for (int i = 0; i < len; i++) {
                int currScore = 0;
                for (String s : report[i].split(" ")) {
                    currScore += wordScoreMap.getOrDefault(s, 0);
                }
                students[i] = new int[]{student_id[i], currScore};
            }

            // 按照规则排序
            Arrays.sort(students, (a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
            // 返回 top K
            List<Integer> topK = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                topK.add(students[i][0]);
            }

            return topK;

        }

    }

}
