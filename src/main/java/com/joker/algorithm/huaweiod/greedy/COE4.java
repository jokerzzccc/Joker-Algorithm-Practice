package com.joker.algorithm.huaweiod.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大利润/贪心的商人
 * 题目描述
 * 商人经营一家店铺，有number种商品，由于仓库限制每件商品的最大持有数量是 item[index]，每种商品的价格是 item_price[item_index][day]
 * 通过对商品的买进和卖出获取利润，请给出商人在days天内能获取的最大的利润
 * 注：同一件商品可以反复买进和卖出
 * 输入描述
 * 第一行输入商品的数量number，比如3
 * 第二行输入商品售货天数 days，比如3
 * 第三行输入仓库限制每件商品的最大持有数量是item[index]，比如4 5 6
 * 后面继续输入number行days列，含义如下：
 * 第一件商品每天的价格，比如1 2 3
 * 第二件商品每天的价格，比如4 3 2
 * 第三件商品每天的价格，比如1 5 3
 * 输出描述
 * 输出商人在这段时间内的最大利润。
 * 示例1
 * 输入
 * 3
 * 3
 * 4 5 6
 * 1 2 3
 * 4 3 2
 * 1 5 2
 * 输出
 * 32
 * 示例2
 * 输入
 * 1
 * 1
 * 1
 * 1
 * 输出
 * 0
 * ————————————————
 * 题型分析：
 * 类似 Leetcode 122 买卖股票的最佳时机 II
 * 这个题的利润就是赚差价，所以需要遍历每一天，计算当天的最大利润，然后累加得到最大利润
 * 【中等】二维数组 + 贪心
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE4 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        // 商品数量
        int number = Integer.parseInt(scanner.nextLine());
        // 售卖天数
        int days = Integer.parseInt(scanner.nextLine());
        // 最大持有量
        int[] items = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 价格
        int[][] prices = new int[number][days];
        for (int i = 0; i < number; i++) {
            prices[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        scanner.close();

        // 贪心：就是求出每件商品每天的最大利润
        // 最大利润：遍历每件商品的每一天，计算当天的最大利润，然后累加得到最大利润
        int maxProfits = 0;
        for (int i = 0; i < number; i++) {
            // 下标从 1 开始，因为第一天肯定是买进
            for (int j = 1; j < days; j++) {
                // 当前价格减去前一天价格，如果为负数则代表亏本，不计入利润
                int currProfit = Math.max(0, prices[i][j] - prices[i][j - 1]);
                maxProfits += currProfit * items[i];
            }
        }

        System.out.println(maxProfits);

    }

}
