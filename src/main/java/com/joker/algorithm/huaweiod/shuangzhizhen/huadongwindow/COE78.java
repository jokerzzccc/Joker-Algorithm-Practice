package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.*;

/**
 * 磁盘容量排序
 * 题目描述
 * 磁盘的容量单位常用的有 M，G，T 这三个等级，它们之间的换算关系为：
 * 1T = 1024G
 * 1G = 1024M
 * 现在给定 n 块磁盘的容量，请对它们按从小到大的顺序进行稳定排序。
 * 例如给定5块盘的容量：
 * 1T，20M，3G，10G6T，3M12G9M
 * 排序后的结果为：
 * 20M，3G，3M12G9M，1T，10G6T
 * 注意单位可以重复出现，上述 3M12G9M 表示的容量即为：3M+12G+9M，和 12M12G 相等。
 * 输入描述
 * 输入第一行包含一个整数 n，表示磁盘的个数
 * 2 ≤ n ≤ 100
 * 接下的 n 行，每行一个字符串（长度大于2，小于30），表示磁盘的容量，由一个或多个格式为mv的子串组成，其中 m 表示容量大小，v 表示容量单位，例如：20M，1T，30G，10G6T，3M12G9M。
 * 磁盘容量 m 的范围为 1 到 1024 的正整数
 * 容量单位 v 的范围只包含题目中提到的 M，G，T 三种，换算关系如题目描述
 * 输出描述
 * 输出 n 行，表示 n 块磁盘容量排序后的结果。
 * <p>
 * 示例1
 * 输入
 * <p>
 * 3
 * 1G
 * 2G
 * 1024M
 * 输出
 * 1G
 * 1024M
 * 2G
 * 说明
 * 1G和1024M容量相等，稳定排序要求保留它们原来的相对位置，故1G在1024M之前。
 * <p>
 * 示例2
 * 输入
 * 3
 * 2G4M
 * 3M2G
 * 1T
 * 输出
 * 3M2G
 * 2G4M
 * 1T
 * 说明
 * 1T的容量大于2G4M，2G4M的容量大于3M2G。
 * ————————————————
 * 题型分析：
 * 【中等】滑动窗口 + 稳定性排序
 * 总结：LinkedHashMap 可以保证原先输入的顺序性
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
