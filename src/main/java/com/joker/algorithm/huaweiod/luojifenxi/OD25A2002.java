package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 优选核酸检测点
 * <p>
 * 题目描述
 * 张三要去外地出差，需要做核酸，需要在指定时间点前做完核酸，请帮他找到满足条件的核酸检测点。
 * 给出一组核酸检测点的距离和每个核酸检测点当前的人数
 * 给出张三要去做核酸的出发时间 出发时间是10分钟的倍数，同时给出张三做核酸的最晚结束时间
 * 题目中给出的距离是整数，单位是公里，时间1分钟为一基本单位
 * 去找核酸点时，有如下的限制：
 * 去往核酸点的路上，每公里距离花费时间10分钟，费用是10元
 * 核酸点每检测一个人的时间花费是1分钟
 * 每个核酸点工作时间都是8点到20点中间不休息，核酸点准时工作，早到晚到都不检测
 * 核酸检测结果可立刻知道
 * 在张三去某个核酸点的路上花费的时间内，此核酸检测点的人数是动态变化的，变化规则是
 * 在非核酸检测时间内，没有人排队
 * 8点-10点每分钟增加3人
 * 12点-14点每分钟增加10人
 * 18点-20点每分钟增加20人。
 * 其他时间每5分钟增加1人。
 * 要求将所有满足条件的核酸检测点按照优选规则排序列出 ：
 * 优选规则：
 * <p>
 * 花费时间最少的核酸检测点排在前面。
 * 花费时间一样,花费费用最少的核酸检测点排在前面。
 * 时间和费用一样，则ID值最小的排在前面
 * 输入描述
 * H1 M1
 * H2 M2
 * N
 * ID1 D1 C1
 * ID2 D2 C2
 * …
 * IDn Dn Cn
 * H1: 当前时间的小时数。
 * M1：当前时间的分钟数，
 * H2：指定完成核算时间的小时数。
 * M2：指定完成核算时间的分钟数。
 * N：所有核酸检测点个数。
 * ID1：核酸点的ID值。
 * D1：核酸检测点距离张三的距离。
 * C1：核酸检测点当前检测的人数。
 * <p>
 * 输出描述
 * N
 * I2 T2 M2
 * I3 T3 M3
 * N：满足要求的核酸检测点个数
 * I2:选择后的核酸检测点ID
 * T2:做完核酸花费的总时间(分钟)
 * M3:去该核算点花费的费用
 * <p>
 * 示例1
 * 输入
 * 10 30
 * 14 50
 * 3
 * 1 10 19
 * 2 8 20
 * 3 21 3
 * 输出
 * 2
 * 2 80 80
 * 1 190 100
 * 说明
 * ————————————————
 * <p>
 * 题型分析
 * 【困难】模拟 + 时间区间交集
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A2002 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] start = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int startTime = start[0] * 60 + start[1];
        int[] target = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int targetTime = target[0] * 60 + target[1];
        int n = Integer.parseInt(scanner.nextLine().trim());
        // ID-DISTANCE-People(ID-距离-排队人数)
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] curr = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            points.add(curr);
        }
        scanner.close();

        // ID-TIME-FEE（ID-时间-费用）
        List<int[]> res = new ArrayList<>();
        for (int[] point : points) {
            int id = point[0];
            int distance = point[1];
            int peopleCount = point[2];

            // 金钱花费
            int fee = distance * 10;
            // 路上花费的时间
            int distanceTime = distance * 10;
            // 到达的时间
            int arriveTime = startTime + distanceTime;

            // 在 8 点前到达，需要等待到 8点
            if (arriveTime < 8 * 60) {
                arriveTime = 8 * 60;
                distanceTime = arriveTime - startTime;
            }

            // 计算在不同时间段内排队的人数
            int[] timeRange1 = new int[]{startTime, arriveTime};
            int[] timeRange2 = new int[]{8 * 60, 10 * 60};
            int add1 = getIntersection(timeRange1, timeRange2); // 交集长度
            if (add1 != -1) {
                peopleCount += 2 * add1; // 每分钟净增2人(增3-检1)
            }

            int[] timeRange3 = {10 * 60, 12 * 60}; // 10:00-12:00
            int min1 = getIntersection(timeRange1, timeRange3); // 交集长度
            if (min1 != -1) {
                peopleCount -= min1; // 每分钟净减1人
                peopleCount = Math.max(0, peopleCount); // 注意至多减到0
            }

            int[] timeRange4 = {12 * 60, 14 * 60}; // 12:00-14:00
            int add2 = getIntersection(timeRange1, timeRange4); // 交集长度
            if (add2 != -1) {
                peopleCount += 9 * add2; // 每分钟净增9人
            }

            int[] timeRange5 = {14 * 60, 18 * 60}; // 14:00-20:00
            int min2 = getIntersection(timeRange1, timeRange5); // 交集长度
            if (min2 != -1) {
                peopleCount -= min2; // 每分钟净减1人
                peopleCount = Math.max(0, peopleCount); // 注意至多减到0
            }
            int[] timeRange6 = {18 * 60, 20 * 60};
            int add3 = getIntersection(timeRange1, timeRange6); // 交集长度
            if (add3 != -1) {
                peopleCount += 19 * add3; // 每分钟净增19人
            }

            // 总花费时间(路上的时间，到达时排队的人数)
            int totalTime = peopleCount + distanceTime;
            if (startTime + totalTime <= targetTime) {
                res.add(new int[]{id, totalTime, fee});
            }

        }

        // 按规则排序
        res.sort((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else if (o1[2] != o2[2]) {
                return o1[2] - o2[2];
            } else {
                return o1[0] - o2[0];
            }
        });

        System.out.println(res.size());
        for (int[] re : res) {
            System.out.println(re[0] + " " + re[1] + " " + re[2]);
        }

    }

    /**
     * 获取两个时间段的交集长度
     *
     * @param timeRange1
     * @param timeRange2
     * @return
     */
    private static int getIntersection(int[] timeRange1, int[] timeRange2) {
        int start1 = timeRange1[0];
        int end1 = timeRange1[1];
        int start2 = timeRange2[0];
        int end2 = timeRange2[1];
        if (start1 < start2) {
            if (start2 >= end1) {
                return -1;
            } else {
                return Math.min(end1, end2) - start2;
            }
        } else if (start1 > start2) {
            if (start1 >= end2) {
                return -1;
            } else {
                return Math.min(end1, end2) - start1;
            }
        } else {
            return Math.min(end1, end2) - start1;
        }

    }

}
