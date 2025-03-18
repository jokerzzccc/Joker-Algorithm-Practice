package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.*;

/**
 * 磁盘容量排序
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE78 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        String[] inputStrs = new String[n];
        for (int i = 0; i < n; i++) {
            inputStrs[i] = scanner.nextLine().trim();
        }
        scanner.close();

        // 数据处理
        // K-原始字符串，V-磁盘容量
        // 用 LinkedHashMap，保证输出结果顺序不变
        Map<String, Integer> str2CapacityMap = new LinkedHashMap<>();
        // 外层循环，处理每一个磁盘容量
        for (int i = 0; i < n; i++) {
            String curr = inputStrs[i];
            int currDiskCapacity = 0;
            int left = 0;
            int right = 1;
            // 处理当前磁盘容量
            while (right < curr.length()) {
                char ch = curr.charAt(right);
                if (ch == 'T' || ch == 'G' || ch == 'M') {
                    int currSubCapacity = Integer.parseInt(curr.substring(left, right));
                    switch (ch) {
                        case 'T':
                            currDiskCapacity += currSubCapacity * 1024 * 1024;
                            break;
                        case 'G':
                            currDiskCapacity += currSubCapacity * 1024;
                            break;
                        case 'M':
                            currDiskCapacity += currSubCapacity;
                            break;
                    }
                    left = right + 1;
                }

                right++;
            }

            str2CapacityMap.put(curr, currDiskCapacity);
        }

        // 排序后输出结果
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(str2CapacityMap.entrySet());
        entries.sort(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey());
        }

    }

}
