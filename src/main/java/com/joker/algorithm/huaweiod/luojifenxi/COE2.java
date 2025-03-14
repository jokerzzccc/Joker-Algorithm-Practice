package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 数大雁
 * 题目描述
 * 一群大雁往南飞，给定一个字符串记录地面上的游客听到的大雁叫声，请给出叫声最少由几只大雁发出。
 * <p>
 * 具体的:
 * <p>
 * ​ 1.大雁发出的完整叫声为”quack“，因为有多只大雁同一时间嘎嘎作响，所以字符串中可能会混合多个”quack”。
 * <p>
 * ​ 2.大雁会依次完整发出”quack”，即字符串中’q’ ,‘u’, ‘a’, ‘c’, ‘k’ 这5个字母按顺序完整存在才能计数为一只大雁。如果不完整或者没有按顺序则不予计数。
 * <p>
 * ​ 3.如果字符串不是由’q’, ‘u’, ‘a’, ‘c’, ‘k’ 字符组合而成，或者没有找到一只大雁，请返回-1。
 * <p>
 * 输入描述
 * 一个字符串，包含大雁quack的叫声。1 <= 字符串长度 <= 1000，字符串中的字符只有’q’, ‘u’, ‘a’, ‘c’, ‘k’。
 * <p>
 * 输出描述
 * 大雁的数量
 * <p>
 * 示例1
 * 输入
 * <p>
 * quackquack
 * 输出
 * <p>
 * 1
 * <p>
 * 题型分析：
 * 类似于题目：leetcode 1419. 数青蛙(还是有细微区别的)
 * https://leetcode.cn/problems/minimum-number-of-frogs-croaking/description/
 * 模拟题：字符串 + 计数
 *
 * @author jokerzzccc
 * @date 2025/3/11
 */
public class COE2 {

    public static final String INIT_QUACK = "quack";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String originStr = in.nextLine();

        // 定义一个数组，用于存储每个字符的状态
        int[] states = new int[INIT_QUACK.length()];
        // 定义一个ArrayList，用于存储每只大雁的叫声数量
        ArrayList<Integer> dp = new ArrayList<>();

        // 初始化最大值为0
        int max_ = 0;

        for (int i = 0; i < originStr.length(); i++) {
            int index = INIT_QUACK.indexOf(originStr.charAt(i));
            // 1. 如果索引为-1，表示输入的字符串包含非法字符，输出-1并退出程序
            if (index == -1) {
                System.out.println(-1);
                System.exit(0);
            }

            // 2. 如果索引为0，表示当前字符是'q'，更新状态数组
            if (index == 0) {
                states[index] += 1;
            }
            // 3. 如果索引大于0，表示当前字符不是'q'，需要判断前一个字符是否为'q'，如果是，则更新状态数组，否则输出-1并退出程序
            else {
                // 3.1 如果当前字符的前一个字符的状态大于0，更新状态数组
                if (states[index - 1] > 0) {
                    states[index - 1] -= 1;
                    states[index] += 1;
                }
                // 3.2 如果当前字符是'k'，表示一个完整的"quack"叫声已经结束
                if (INIT_QUACK.charAt(INIT_QUACK.length() - 1) == originStr.charAt(i)) {
                    // 如果状态数组的最后一个元素不为0，表示有大雁正在叫
                    if (states[states.length - 1] != 0) {
                        // 创建一个临时数组，用于计算当前大雁的叫声数量
                        int[] temp = Arrays.copyOf(states, states.length);
                        temp[states.length - 1] = 0;
                        max_ = Math.max(max_, Arrays.stream(temp).sum());
                        // 遍历剩余的字符，更新临时数组
                        for (int j = i; j < originStr.length(); j++) {
                            index = INIT_QUACK.indexOf(originStr.charAt(j));
                            if (index > 0 && temp[index - 1] > 0) {
                                temp[index - 1] -= 1;
                                temp[index] += 1;
                            }
                            if (temp[states.length - 1] == max_) {
                                break;
                            }
                        }

                        // 将当前大雁的叫声数量添加到ArrayList中
                        dp.add(temp[states.length - 1] + 1);
                        // 更新状态数组
                        states[states.length - 1] -= 1;

                    }

                }
            }

        }

        // 输出结果，如果dp为空，表示没有找到一只大雁，输出-1；否则输出最大值
        System.out.println(dp.isEmpty() ? -1 : (int) Collections.max(dp));

        in.close();

    }

}