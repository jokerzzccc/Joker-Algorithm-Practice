package com.joker.algorithm.huaweiod.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 数字游戏
 * <p>
 * 题目描述
 * 小明玩一个游戏。
 * 系统发1+n张牌，每张牌上有一个整数。
 * 第一张给小明，后n张按照发牌顺序排成连续的一行。
 * 需要小明判断，后n张牌中，是否存在连续的若干张牌，其和可以整除小明手中牌上的数字。
 * 输入描述
 * 输入数据有多组，每组输入数据有两行，输入到文件结尾结束。
 * 第一行有两个整数n和m，空格隔开。m代表发给小明牌上的数字。
 * 第二行有n个数，代表后续发的n张牌上的数字，以空格隔开。
 * <p>
 * 备注
 * 1 ≤ n ≤ 1000
 * 1 ≤ 牌上的整数 ≤ 400000
 * 输入的组数，不多于1000
 * 用例确保输入都正确，不需要考虑非法情况。
 * 输出描述
 * 对每组输入，如果存在满足条件的连续若干张牌，则输出1;否则，输出0
 * <p>
 * 示例1
 * 输入
 * 6 7
 * 2 12 6 3 5 5
 * 10 11
 * 1 1 1 1 1 1 1 1 1 1
 * 输出
 * 1
 * 0
 * 说明
 * 第一组小明牌的数字为7，再发了6张牌。第1、2两张牌教字和为14，可以整除7，输出1，第二组小明牌的教字为11，再发了10张牌，这10张牌数字和为10，无法整除11，输出0。
 * ————————————————
 * 题型分析
 * 【困难】前缀和
 * 数学理论：两个数a,b,他们的差a-b可以被k整除，则必然a%k==b%k。跟结合律一样。(a-b)%k=0 变成a%k=b%k
 *
 * @author jokerzzccc
 * @date 2025/4/2
 */
public class OD25A2013 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int[] input_arr = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = input_arr[0];
            int m = input_arr[1];
            int[] nums = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 能否找到答案：1-true, 0-false
            int isFound = 0;
            int sum = 0;
            // 存储余数
            HashSet<Integer> remainderSet = new HashSet<>();
            // 处理初始和为0的情况
            remainderSet.add(0);
            // 理论：(a-b)%k=0 变成a%k=b%k
            for (int num : nums) {
                sum += num;
                int remainder = sum % m;

                if (remainderSet.contains(remainder)) {
                    isFound = 1;
                    break;
                } else {
                    remainderSet.add(remainder);
                }
            }

            System.out.println(isFound);
        }

        scanner.close();
    }

}
