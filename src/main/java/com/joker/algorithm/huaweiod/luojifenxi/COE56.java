package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * IPv4地址转换成整数
 * 题目描述
 * 存在一种虚拟IPv4地址，由4小节组成，每节的范围为0~255，以#号间隔，虚拟IPv4地址可以转换为一个32位的整数，例如：
 * 128#0#255#255，转换为32位整数的结果为2147549183（0x8000FFFF）
 * 1#0#0#0，转换为32位整数的结果为16777216（0x01000000）
 * 现以字符串形式给出一个虚拟IPv4地址，限制第1小节的范围为1-128，即每一节范围分别为(1-128)#(0-255)#(0-255)#(0~255)，要求每个IPv4地址只能对应到唯一的整数上。如果是非法IPv4，返回invalid IP
 * 输入描述
 * 输入一行，虚拟IPv4地址格式字符串
 * 输出描述
 * 输出一行，按照要求输出整型或者特定字符
 * 示例1
 * 输入
 * 100#101#1#5
 * 输出
 * 1684340997
 * 示例2
 * 输入
 * 1#2#3
 * 输出
 * invalid IP
 * 说明
 * ————————————————
 * 题型分析：
 * 【中等】 字符串 + 进制转换（十进制与十六进制互转）
 *
 * @author jokerzzccc
 * @date 2025/3/14
 */
public class COE56 {

    public static final String INVALID_IP = "invalid IP";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStrArr = scanner.nextLine().trim().split("#");

        // 合法性校验
        boolean isValid = validCheck(inputStrArr);
        if (!isValid) {
            System.out.println(INVALID_IP);
            return;
        }

        int[] inputArray = Arrays.stream(inputStrArr)
                .mapToInt(Integer::parseInt)
                .toArray();
        // 地址转换整数
        long ipValue = 0;
        for (int j : inputArray) {
            ipValue = ipValue * 256 + j; // 每个小节对应一个字节，计算最终的整数值
        }

        // 输出结果
        System.out.println(ipValue);

    }

    private static boolean validCheck(String[] inputArray) {
        int length = inputArray.length;
        if (length != 4) {
            return false;
        }
        for (String s : inputArray) {
            // 检查是否都是数字
            if (!s.matches("[0-9]+")) {
                return false;
            }
            // 检查是否有空字符
            if (s.isEmpty()) {
                return false;
            }
            // 检查前导0
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false;
            }
        }

        int[] numbers = Arrays.stream(inputArray)
                .mapToInt(Integer::parseInt)
                .toArray();
        // 数字范围检验
        if (!(1 <= numbers[0] && numbers[0] <= 128)) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (!(0 <= numbers[i] && numbers[i] <= 255)) {
                return false;
            }
        }
        return true;
    }

}
