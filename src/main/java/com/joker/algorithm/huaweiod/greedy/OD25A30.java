package com.joker.algorithm.huaweiod.greedy;

import java.util.Scanner;

/**
 * 分糖果
 * 题目描述
 * 小明从糖果盒中随意抓一把糖果，每次小明会取出一半的糖果分给同学们。
 * 当糖果不能平均分配时，小明可以选择从糖果盒中（假设盒中糖果足够）取出一个糖果或放回一个糖果。
 * 小明最少需要多少次（取出、放回和平均分配均记一次），能将手中糖果分至只剩一颗。
 * 输入描述
 * 抓取的糖果数（<10000000000）：15
 * 输出描述
 * 最少分至一颗糖果的次数：5
 * <p>
 * 示例1
 * 输入
 * 15
 * 输出
 * 5
 * 说明
 * 15+1=16;16/2=8;8/2=4;4/2=2;2/2=1;
 * <p>
 * 示例2
 * 输入
 * 6
 * 输出
 * 3
 * 说明
 * 6/2=3,3-1=2,2/2=1
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】贪心
 *
 * @author jokerzzccc
 * @date 2025/3/30
 */
public class OD25A30 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        // 次数
        int count = 0;
        // 循环直到只剩一颗糖果
        for (long leaveCnt = n; leaveCnt != 1; leaveCnt /= 2, count++) {
            if (leaveCnt == 3) {
                count += 2;
                break;
            }

            // 当剩余糖果数量为奇数时，需要进行调整
            if (leaveCnt % 2 != 0) {
                // 贪心策略：如果将剩余糖果数量加1再除以2后是偶数，则取出一个糖果
                if ((leaveCnt + 1) / 2 % 2 == 0) {
                    leaveCnt++;
                } else {
                    // 否则放回一个糖果
                    leaveCnt--;
                }
                count++;
            }
        }

        System.out.println(count);
    }

}
