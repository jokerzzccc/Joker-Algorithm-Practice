package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大值
 * 题目描述
 * 给定一组整数（非负），重排顺序后输出一个最大的整数。
 * 示例1
 * 输入：[10,9]
 * 输出：910
 * 说明:输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 输入描述
 * 数字组合
 * 输出描述
 * 最大的整数
 * 示例1
 * 输入
 * 10 9
 * 输出
 * 910
 * ————————————————
 * 题型分析：
 * 类似 leetcode-179 最大数
 * 【中等】 模拟+贪心+字符串
 * 贪心策略，局部最优解：如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面。
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE53 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStrArr = scanner.nextLine().trim().split("\\s+");

        // 自定义排序规则，比较两个字符串拼接后的大小
        Arrays.sort(inputStrArr, (a, b) -> {
            // 贪心策略，局部最优 -》全局最优
            String ab = a + b, ba = b + a;
            return ba.compareTo(ab);
        });

        // 拼接排序后的字符串
        StringBuilder sb = new StringBuilder();
        for (String str : inputStrArr) {
            sb.append(str);
        }
        // 去除多余的前导零(保证题目通过，预防有多个 0 ，而预期输出只有一个0)
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') {
            k++;
        }

        System.out.println(sb.substring(k));
        scanner.close();
    }

}
