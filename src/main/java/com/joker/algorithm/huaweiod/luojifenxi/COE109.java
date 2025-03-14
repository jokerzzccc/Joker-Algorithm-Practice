package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 告警抑制
 * 题目描述
 * 告警抑制，是指高优先级告警抑制低优先级告警的规则。高优先级告警产生后，低优先级告警不再产生。请根据原始告警列表和告警抑制关系，给出实际产生的告警列表。
 * 不会出现循环抑制的情况。
 * 告警不会传递，比如A->B,B->C，这种情况下A不会直接抑制C。但被抑制的告警仍然可以抑制其他低优先级告警。
 * 输入描述
 * 第一行为数字N，表示告警抑制关系个数，0 ≤ N ≤ 120
 * 接下来N行，每行是由空格分隔的两个告警ID，例如: id1 id2，表示id1抑制id2，告警ID的格式为：
 * 大写字母+0个或者1个数字
 * 最后一行为告警产生列表，列表长度[1,100]
 * 输出描述
 * 真实产生的告警列表
 * 备注
 * 告警ID之间以单个空格分隔
 * 示例1
 * 输入
 * 2
 * A B
 * B C
 * A B C D E
 * 输出
 * A D E
 * 说明
 * A抑制了B，B抑制了C，最后实际的告警为A D E
 * 示例2
 * 输入
 * 4
 * F G
 * C B
 * A G
 * A0 A
 * A B C D E
 * 输出
 * A C D E
 * 说明
 * 第一个告警A，能够抑制它的只有A0，而当前告警列表中没有A0，因此告警A可以正常发生
 * 第二个告警B，能够抑制它的只有C，而当前告警列表中有C，因此告警B被抑制，不可以发生
 * 第三个告警C，没有能抑制它的告警，因此正常发生
 * 第四个告警D，没有能抑制它的告警，因此正常发生
 * 第五个告警E，没有能抑制它的告警，因此正常发生
 * ————————————————
 * 题型分析：
 * 【简单】Map + 模拟
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE109 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        // K-抑制，V-被抑制集合
        Map<String, Set<String>> suppressionMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] currRow = scanner.nextLine().trim().split("\\s+");
            suppressionMap.computeIfAbsent(currRow[0], k -> new HashSet<>()).add(currRow[1]);
        }
        // 原始告警列表
        String[] originalAlertString = scanner.nextLine().trim().split("\\s+");
        // 转为列表，方便快速查询
        List<String> originalAlertList = new ArrayList<>(Arrays.asList(originalAlertString));
        // 存储原始告警列表里被抑制的告警ID
        HashSet<String> suppressedAlerts = new HashSet<>();
        for (String item : originalAlertString) {
            // 检查当前告警是否抑制其他告警
            if (suppressionMap.containsKey(item)) {
                for (String suppressedItem : suppressionMap.get(item)) {
                    if (originalAlertList.contains(suppressedItem)) {
                        suppressedAlerts.add(suppressedItem);
                    }
                }
            }
        }

        // 构建实际产生的告警列表
        List<String> finalAlerts = new ArrayList<>();
        for (String item : originalAlertString) {
            if (!suppressedAlerts.contains(item)) {
                finalAlerts.add(item);
            }
        }
        System.out.println(String.join(" ", finalAlerts));
        scanner.close();
    }

}
