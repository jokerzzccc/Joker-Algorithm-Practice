package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 阿里巴巴找黄金宝箱III
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面贴有一个数字。
 * 阿里巴巴念出一个咒语数字，查看宝箱是否存在两个不同箱子，这两个箱子上贴的数字相同，同时这两个箱了的编号之差的绝对值小于等于咒语数字，
 * 如果存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1.
 * 输入描述
 * 第一行输入一个数字字串，数字之间使用逗号分隔，例如: 1,2,3,1
 * 1 ≤ 字串中数字个数 ≤ 100000
 * -100000 ≤ 每个数字值 ≤ 100000
 * 第二行输入咒语数字，例如: 3 1 ≤ 咒语数字 ≤ 100000
 * 输出描述
 * 存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1
 * <p>
 * 示例1
 * 输入
 * 6,3,1,6
 * 3
 * 输出
 * 0
 * 说明
 * 示例2
 * 输入
 * 5,6,7,5,6,7
 * 2
 * 输出
 * -1
 * 说明
 * ————————————————
 * <p>
 * 题型分析：
 * 【简单】模拟+哈希表
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A58 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input_arr = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();

        // K-数组数字，V-数字的下标；
        Map<Integer, Integer> num2Index = new HashMap<>();

        int resIndex = -1;
        for (int i = 0; i < input_arr.length; i++) {
            if (num2Index.containsKey(input_arr[i])) {
                if (i - num2Index.get(input_arr[i]) <= n) {
                    resIndex = num2Index.get(input_arr[i]);
                    break;
                }
            }
            num2Index.put(input_arr[i], i);
        }

        System.out.println(resIndex);
    }

}
