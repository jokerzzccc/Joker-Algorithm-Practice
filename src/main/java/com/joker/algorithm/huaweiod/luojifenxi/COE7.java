package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 猜数字
 * 题目描述
 * 一个人设定一组四码的数字作为谜底，另一方猜。
 * <p>
 * 每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。
 * <p>
 * 其中X表示位置正确的数的个数（数字正确且位置正确），而Y表示数字正确而位置不对的数的个数。
 * <p>
 * 例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。
 * <p>
 * 例如，当谜底为5637，而猜谜者才4931时，出题者必须提示1A0B。
 * <p>
 * 当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。
 * <p>
 * 输入描述
 * 第一行输入一个正整数，0＜N ＜ 100。
 * <p>
 * 接下来N行，每一行包含一个猜测的数字与提示结果。
 * <p>
 * 输出描述
 * 输出最后的答案，答案不确定则输出NA。
 * <p>
 * 示例1
 * 输入
 * <p>
 * 6
 * 4815 1A1B
 * 5716 0A1B
 * 7842 0A1B
 * 4901 0A0B
 * 8585 3A0B
 * 8555 2A1B
 * 输出
 * <p>
 * 3585
 * <p>
 * 题型分析：
 * 暴力枚举 + 剪枝
 *
 * @author jokerzzccc
 * @date 2025/3/11
 */
public class COE7 {

    /**
     * 因为数字范围为 0000-9999
     */
    public static final int GUSS_MAX = 10000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 猜测的次数
        int n = Integer.parseInt(input.nextLine().trim());
        List<String[]> guessInfos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 输入猜测的数字
            String num = input.next();
            // 输入猜测的结果
            String tips = input.next();
            guessInfos.add(new String[]{num, tips});
        }
        input.close();

        // 记录符合条件的答案数量
        int validCnt = 0;
        // 存储符合条件的答案
        String validAnswer = "";
        for (int num = 0; num < GUSS_MAX; num++) {
            String currNum = String.format("%04d", num);
            boolean isValid = true; // 标记当前答案是否有效

            // 遍历每个猜测的数字和结果
            for (String[] guessInfo : guessInfos) {
                String guessNum = guessInfo[0]; // 获取猜测的数字
                String expectResult = guessInfo[1]; // 获取猜测的结果

                int countA = 0; // 记录数字和位置都正确的个数
                int countB = 0; // 记录数字正确但位置不正确的个数

                int[] answerArr = new int[10]; // 存储答案中每个数字出现的次数
                int[] guessArr = new int[10]; // 存储猜测中每个数字出现的次数

                // 遍历每个位置
                for (int i = 0; i < guessNum.length(); i++) {
                    // 获取猜测中该位置上的数字
                    int c1Int = guessNum.charAt(i) - '0';
                    // 获取答案中该位置上的数字
                    int c2Int = currNum.charAt(i) - '0';

                    if (c1Int == c2Int) {
                        countA++;
                    } else {
                        // 在 guessArr 中记录该数字出现的次数
                        guessArr[c1Int]++;
                        // 在 answerArr 中记录该数字出现的次数
                        answerArr[c2Int]++;
                    }
                }

                // 计算数字正确但位置不正确的个数
                for (int i = 0; i < 10; i++) {
                    countB += Math.min(guessArr[i], answerArr[i]);
                }

                String currTip = countA + "A" + countB + "B"; // 根据猜测和答案计算真实结果
                if (!currTip.equals(expectResult)) {
                    isValid = false; // 如果真实结果和猜测结果不一致，标记当前答案为无效
                    break;
                }

            }

            if (isValid) {
                validCnt++;
                validAnswer = currNum;

                if (validCnt > 1) {
                    break; // 如果符合条件的答案数量大于1，跳出循环
                }
            }
        }

        if (validCnt != 1) {
            System.out.println("NA"); // 如果符合条件的答案不唯一，输出 NA
        } else {
            System.out.println(validAnswer); // 如果符合条件的答案唯一，输出答案
        }

    }

}
