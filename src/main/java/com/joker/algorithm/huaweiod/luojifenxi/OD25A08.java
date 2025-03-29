package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 投篮大赛
 * <p>
 * 题目描述
 * 你现在是一场采用特殊赛制投篮大赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 * 比赛开始时，记录是空白的。
 * 你会得到一个记录操作的字符串列表 ops，其中ops[i]是你需要记录的第i项操作，ops遵循下述规则：
 * 整数x-表示本回合新获得分数x
 * “+” – 表示本回合新获得的得分是前两次得分的总和。
 * “D” – 表示本回合新获得的得分是前一次得分的两倍。
 * “C” – 表示本回合没有分数，并且前一次得分无效，将其从记录中移除。
 * 请你返回记录中所有得分的总和。
 * 输入描述
 * 输入为一个字符串数组
 * 提示
 * 1 <= ops.length <= 1000
 * ops[i] 为 “C”、“D”、“+”，或者一个表示整数的字符串。整数范围是 [-3 * 10^4, 3 * 10^4]
 * 需要考虑异常的存在，如有异常情况，请返回-1
 * 对于“+”操作，题目数据不保证记录此操作时前面总是存在两个有效的分数
 * 对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数
 * 题目输出范围不会超过整型的最大范围，不超过2^63 - 1
 * 输出描述
 * 输出为一个整形数字
 * <p>
 * 示例1
 * 输入
 * 5 2 C D +
 * 输出
 * 30
 * 说明
 * “5”-记录加5，记录现在是[5]
 * “2”-记录加2，记录现在是[5,2]
 * “C”-使前一次得分的记录无效并将其移除，记录现在是[5].
 * “D”-记录加2*5=10，记录现在是[5，10].
 * “+”-记录加5+10=15，记录现在是[5，10，15].
 * 所有得分的总和5+10+15=30
 * <p>
 * 示例2
 * 输入
 * 5 -2 4 C D 9 + +
 * 输出
 * 27
 * 说明
 * “5”-记录加5，记录现在是[5]
 * “-2”-记录加-2，记录现在是[5,-2]
 * “4”-记录加4，记录现在是[5,-2,4]
 * “C”-使前一次得分的记录无效并将其移除，记录现在是[5,-2].
 * “D”-记录加2*-2=4，记录现在是[5，-2, -4].
 * “9”-记录加9，记录现在是[5，-2, -4, 9].
 * “+”-记录加-4+9=5，记录现在是[5，-2, -4, 9, 5].
 * “+”-记录加-9+5=14，记录现在是[5，-2, -4, 9, 5, 14].
 * 所以得分的总和 5 - 2 - 4 + 9 + 5 + 14 = 27
 * ————————————————
 * 题型分析：
 * 【简单】逻辑分析 + 一维字符串数组
 *
 * @author jokerzzccc
 * @date 2025/3/28
 */
public class OD25A08 {

    public static final String[] INIT_OPS = {"+", "D", "C"};

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] inputStrs = scanner.nextLine().split(" ");
        scanner.close();

        //
        int n = inputStrs.length;
        // 当前有效长度
        int currValidLength = 0;
        // 转化后的数字数字
        int[] validNums = new int[n];

        // 转换输入字符为整数数组
        for (String ops : inputStrs) {
            if (ops.equals(INIT_OPS[0])) {
                if (currValidLength - 2 >= 0) {
                    validNums[currValidLength] = validNums[currValidLength - 1] + validNums[currValidLength - 2];
                } else if (currValidLength - 1 >= 0) {
                    validNums[currValidLength] = validNums[currValidLength - 1];
                } else {
                    validNums[currValidLength] = 0;
                }
                currValidLength++;
            } else if (ops.equals(INIT_OPS[1])) {
                if (currValidLength - 1 >= 0) {
                    validNums[currValidLength] = validNums[currValidLength - 1] * 2;
                    currValidLength++;
                } else {
                    validNums[currValidLength++] = 0;
                }
            } else if (ops.equals(INIT_OPS[2])) {
                if (currValidLength - 1 >= 0) {
                    currValidLength--;
                    validNums[currValidLength] = 0;
                } else {
                    validNums[currValidLength] = 0;
                }
            } else {
                try {
                    int curr = Integer.parseInt(ops);
                    validNums[currValidLength++] = curr;
                } catch (NumberFormatException e) {
                    System.out.println("-1");
                    return;
                }

            }
        }

        // 输出分数结果
        int score = Arrays.stream(validNums).sum();
        System.out.println(score);

    }

}
