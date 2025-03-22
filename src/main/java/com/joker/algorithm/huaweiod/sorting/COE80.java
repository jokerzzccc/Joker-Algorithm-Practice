package com.joker.algorithm.huaweiod.sorting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 优秀学员统计
 * <p>
 * 题目描述
 * 公司某部门软件教导团正在组织新员工每日打卡学习活动，他们开展这项学习活动已经一个月了，所以想统计下这个月优秀的打卡员工。每个员工会对应一个id，每天的打卡记录记录当天打卡员工的id集合，一共30天。
 * 请你实现代码帮助统计出打卡次数top5的员工。加入打卡次数相同，将较早参与打卡的员工排在前面，如果开始参与打卡的时间还是一样，将id较小的员工排在前面。
 * 注：不考虑并列的情况，按规则返回前5名员工的id即可，如果当月打卡的员工少于5个，按规则排序返回所有有打卡记录的员工id。
 * 输入描述
 * 第一行输入为新员工数量N，表示新员工编号id为0到N-1，N的范围为[1,100]
 * 第二行输入为30个整数，表示每天打卡的员工数量，每天至少有1名员工打卡。
 * 之后30行为每天打卡的员工id集合，id不会重复。
 * 输出描述
 * 按顺序输出打卡top5员工的id，用空格隔开。
 * <p>
 * 示例1
 * 输入
 * 11
 * 4 4 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 2
 * 0 1 7 10
 * 0 1 6 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 10
 * 6 10
 * 7 10
 * 输出
 * 10 0 1 7 6
 * 说明
 * 员工编号范围为0~10，id为10的员工连续打卡30天，排第一，id为0,1,6,7的员工打卡都是两天，id为0,1,7的员工在第一天就打卡，比id为6的员工早，排在前面，0,1,7按id升序排列，所以输出[10,0,1,7,6]
 * <p>
 * 示例2
 * 输入
 * 7
 * 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 0 1 2 3 4 5
 * 输出
 * 0 1 2 3 4
 * 说明
 * 员工编号范围为0-6，id为0，1，2，3，4，5的员工打卡次数相同，最早开始打卡的时间也一样，所以按id升序返回前5个id
 * ————————————————
 * 题型分析：
 * 【简单】 排序
 *
 * @author jokerzzccc
 * @date 2025/3/22
 */
public class COE80 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] signedNumsPerDay = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 员工的打卡次数，下标代表 id, 数值对应打卡次数
        int[] stuffSignedCounts = new int[n];
        // 员工开始打开的时间，下标代表 id, 数值对应打卡开始时间
        int[] stuffSignedStartedDay = new int[n];
        // 打过卡的员工
        Set<Integer> stuffSignedStuffSet = new TreeSet<>();
        // 遍历 30 天
        for (int i = 1; i <= 30; i++) {
            int[] todaySignedStuff = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int id : todaySignedStuff) {
                stuffSignedStuffSet.add(id);
                stuffSignedCounts[id] += 1;
                if (stuffSignedStartedDay[id] == 0) {
                    stuffSignedStartedDay[id] = i;
                }
            }
        }
        scanner.close();

        // 排序
        List<Integer> top5 = stuffSignedStuffSet.stream()
                .sorted((o1, o2) -> {
                    if (stuffSignedCounts[o1] != stuffSignedCounts[o2]) {
                        return stuffSignedCounts[o2] - stuffSignedCounts[o1];
                    } else if (stuffSignedStartedDay[o1] != stuffSignedStartedDay[o2]) {
                        return stuffSignedStartedDay[o1] - stuffSignedStartedDay[o2];
                    } else {
                        return o1 - o2;
                    }
                })
                .limit(5)
                .collect(Collectors.toList());

        // 输出结果
        StringJoiner sj = new StringJoiner(" ");
        for (Integer id : top5) {
            sj.add(String.valueOf(id));
        }
        System.out.println(sj.toString());
    }

}
