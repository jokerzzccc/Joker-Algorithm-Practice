package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Scanner;

/**
 * 考勤信息
 * 题目描述
 * 公司用一个字符串来表示员工的出勤信息
 * <p>
 * absent：缺勤
 * late：迟到
 * leaveearly：早退
 * present：正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 * <p>
 * 缺勤不超过一次；
 * 没有连续的迟到/早退；
 * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
 * 输入描述
 * 用户的考勤数据字符串
 * <p>
 * 记录条数 >= 1；
 * 输入字符串长度 < 10000；
 * 不存在非法输入；
 * 如：
 * <p>
 * 2
 * present
 * present absent present present leaveearly present absent
 * 输出描述
 * 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”，
 * 对于输入示例的结果应为：
 * true false
 * 示例1
 * 输入
 * 2
 * present
 * present present
 * 输出
 * true true
 * 说明
 * 无
 * 示例2
 * 输入
 * 2
 * present
 * present absent present present leaveearly present absent
 * 输出
 * true false
 * <p>
 * <p>
 * ————————————————
 * 题目分析：
 * 【简单】模拟题 + 枚举
 *
 * @author jokerzzccc
 * @date 2025/3/12
 */
public class COE37 {

    public static final String ABSENT = "absent";
    public static final String LATE = "late";
    public static final String LEAVE_EARLY = "leaveearly";
    public static final String PRESENT = "present";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 测试用例数（考勤记录数）
        int caseCnt = Integer.parseInt(scanner.nextLine().trim());
        // 构建所有测试用例的输出结果
        StringBuilder results = new StringBuilder();
        while (caseCnt-- > 0) {
            String[] attendanceRecords = scanner.nextLine().trim().split("\\s+");
            results.append(isAttendanceAward(attendanceRecords) ? "true" : "false");
            if (caseCnt > 0) {
                results.append(" ");
            }
        }

        System.out.println(results);
        scanner.close();
    }

    private static boolean isAttendanceAward(String[] attendanceRecords) {
        int absentCnt = 0;
        for (int i = 0; i < attendanceRecords.length; i++) {
            String currRecord = attendanceRecords[i];
            // 条件一：缺勤不超过一次
            if (currRecord.equals(ABSENT)) {
                absentCnt++;
                if (absentCnt > 1) {
                    return false;
                }
            }
            // 条件二：没有连续的迟到/早退
            if (currRecord.equals(LATE) || currRecord.equals(LEAVE_EARLY)) {
                if (i > 0 && (attendanceRecords[i - 1].equals(LATE) || attendanceRecords[i - 1].equals(LEAVE_EARLY))) {
                    return false;
                }
            }
            // 条件三：任意连续7次考勤，缺勤/迟到/早退不超过3次
            if (i >= 6) {
                int countIn7Days = 0;
                for (int j = i; j >= i - 6; j--) {
                    if (!PRESENT.equals(attendanceRecords[j])) {
                        countIn7Days++;
                    }
                }
                if (countIn7Days > 3) {
                    return false;
                }
            }
        }
        // 如果所有条件都满足，返回true
        return true;
    }

}
