package com.joker.algorithm.huaweiod.sorting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 运维日志排序
 * <p>
 * 题目描述
 * [运维工程师]采集到某产品线网运行一天产生的日志n条，现需根据日志时间先后顺序对日志进行排序，日志时间格式为H:M:S.N。
 * H表示小时(0~23)
 * M表示分钟(0~59)
 * S表示秒(0~59)
 * N表示毫秒(0~999)
 * 时间可能并没有补全，也就是说，01:01:01.001也可能表示为1:1:1.1。
 * 输入描述
 * 第一行输入一个整数n表示日志条数，1<=n<=100000，接下来n行输入n个时间。
 * 输出描述
 * 按时间升序排序之后的时间，如果有两个时间表示的时间相同，则保持输入顺序。
 * <p>
 * 示例1
 * 输入
 * 2
 * 01:41:8.9
 * 1:1:09.211
 * 输出
 * 1:1:09.211
 * 01:41:8.9
 * 说明
 * <p>
 * 示例2
 * 输入
 * 3
 * 23:41:08.023
 * 1:1:09.211
 * 08:01:22.0
 * 输出
 * 1:1:09.211
 * 08:01:22.0
 * 23:41:08.023
 * 说明
 * <p>
 * 示例3
 * 输入
 * 2
 * 22:41:08.023
 * 22:41:08.23
 * 输出
 * 22:41:08.023
 * 22:41:08.23
 * 说明 两个时间表示的时间相同，保持输入顺序
 * ————————————————
 * 题型分析：
 *
 * @author jokerzzccc
 * @date 2025/3/22
 */
public class COE79 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        // K- 原始字符串，V- 转换后的时间戳
        Map<String, Integer> map = new HashMap<>();
        for (int i = n; i > 0; i--) {
            String curr = scanner.nextLine();
            map.put(curr, convertTime(curr));
        }
        scanner.close();

        // 排序
        List<Map.Entry<String, Integer>> sortedList = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        // 输出结果
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey());
        }

    }

    private static int convertTime(String time) {
        String[] split = time.split(":");
        String[] strEnd = split[2].split("\\.");
        return Integer.parseInt(split[0]) * 60 * 60 * 1000
                + Integer.parseInt(split[1]) * 60 * 1000
                + Integer.parseInt(strEnd[0]) * 1000
                + Integer.parseInt(strEnd[1]);
    }

}
