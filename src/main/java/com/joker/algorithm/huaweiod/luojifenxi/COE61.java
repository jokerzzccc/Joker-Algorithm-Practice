package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Scanner;

/**
 * 货币单位换算
 * 题目描述
 * 记账本上记录了若干条多国货币金额，需要转换成人民币分（fen），汇总后输出。
 * 每行记录一条金额，金额带有货币单位，格式为数字+单位，可能是单独元，或者单独分，或者元与分的组合。
 * 要求将这些货币全部换算成人民币分（fen）后进行汇总，汇总结果仅保留整数，小数部分舍弃。
 * 元和分的换算关系都是1:100，如下：
 * 1CNY=100fen（1元=100分）
 * 1HKD=100cents（1港元=100港分）
 * 1JPY=100sen（1日元=100仙）
 * 1EUR=100eurocents（1欧元=100欧分）
 * 1GBP=100pence（1英镑=100便士）
 * 汇率表如下：
 * 即：100CNY = 1825JPY = 123HKD = 14EUR = 12GBP
 * CNY	JPY	HKD	EUR	GBP
 * 100	1825	123	14	12
 * 输入描述
 * 第一行输入为N，N表示记录数。0<N<100
 * 之后N行，每行表示一条货币记录，且该行只会是一种货币。
 * 输出描述
 * 将每行货币转换成人民币分（fen）后汇总求和，只保留整数部分。
 * 输出格式只有整数数字，不带小数，不带单位。
 * 示例1
 * 输入
 * 1
 * 100CNY
 * 输出
 * 10000
 * 说明
 * 100CNY转换后是10000fen，所以输出结果为10000
 * 示例2
 * 输入
 * 1
 * 3000fen
 * 输出
 * 3000
 * 说明
 * 3000fen，结果就是3000
 * ————————————————
 * 题型分析：
 * 【简单】字符串 （数字和字符串转换）
 *
 * @author jokerzzccc
 * @date 2025/3/14
 */
public class COE61 {

    public static final String CNY = "CNY";
    public static final String FEN = "fen";
    public static final String JPY = "JPY";
    public static final String SEN = "sen";
    public static final String HKD = "HKD";
    public static final String CENTS = "cents";
    public static final String EUR = "EUR";
    public static final String EUROCENTS = "eurocents";
    public static final String GBP = "GBP";
    public static final String PENCE = "pence";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine().trim());

        // 最终结果（人民币分）
        double totalFen = 0;
        for (int i = 0; i < N; i++) {
            String record = scanner.nextLine();
            int currAmount = 0; // 保存金额
            StringBuilder currUnit = new StringBuilder(); // 保存单位

            // 遍历当前记录，逐个提取金额和单位
            for (int j = 0; j < record.length(); j++) {
                char c = record.charAt(j);
                if (Character.isDigit(c)) {
                    currAmount = currAmount * 10 + (c - '0');
                } else {
                    currUnit.append(c);
                }

                // 当遇到最后一个字符 或者 一个完整的金额+单位时，开始换算
                if (j == record.length() - 1 || (Character.isDigit(record.charAt(j + 1)) && currUnit.length() > 0)) {
                    totalFen += currAmount * unitExchangeRate(currUnit.toString());
                    // 金额、单位清零
                    currAmount = 0;
                    currUnit.setLength(0);
                }
            }

        }

        System.out.println((int) totalFen);
        scanner.close();
    }

    private static double unitExchangeRate(String unit) {
        switch (unit) {
            case CNY:
                return 100.0;
            case FEN:
                return 1.0;
            case JPY:
                return 100.0 / 1825 * 100;
            case SEN:
                return 100.0 / 1825;
            case HKD:
                return 100.0 / 123 * 100;
            case CENTS:
                return 100.0 / 123;
            case EUR:
                return 100.0 / 14 * 100;
            case EUROCENTS:
                return 100.0 / 14;
            case GBP:
                return 100.0 / 12 * 100;
            case PENCE:
                return 100.0 / 12;
            default:
                return 0.0;
        }
    }

}
