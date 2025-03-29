package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 数据分类
 * 对一个数据a进行分类，分类方法为：此数据a（四个字节大小）的四个字节相加对一个给定的值b取模，如果得到的结果小于一个给定的值c，则数据a为有效类型，其类型为取模的值；
 * 如果得到的结果大于或者等于c，则数据a为无效类型。
 * 比如一个数据a=0x01010101，b=3，按照分类方法计算（0x01+0x01+0x01+0x01）%3=1，所以如果c=2，则此a为有效类型，其类型为1，如果c=1，则此a为无效类型；
 * 又比如一个数据a=0x01010103，b=3，按照分类方法计算（0x01+0x01+0x01+0x03）%3=0，所以如果c=2，则此a为有效类型，其类型为0，如果c=0，则此a为无效类型。
 * 输入12个数据，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据，请找到有效类型中包含数据最多的类型，并输出该类型含有多少个数据。
 * 输入描述:
 * 输入12个数据，用空格分隔，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据。
 * 输出描述:
 * 输出最多数据的有效类型有多少个数据。
 * <p>
 * 示例1
 * 输入
 * 3 4 256 257 258 259 260 261 262 263 264 265
 * 输出
 * 3
 * 说明
 * 10个数据4个字节相加后的结果分别为1 2 3 4 5 6 7 8 9 10，故对4取模的结果为1 2 3 0 1 2 3 0 1 2，c为3，
 * 所以0 1 2都是有效类型，类型为1和2的有3个数据，类型为0的只有2个数据，故输出3
 * <p>
 * 示例2
 * 输入
 * 1 4 256 257 258 259 260 261 262 263 264 265
 * 输出
 * 2
 * 说明
 * 10个数据4个字节相加后的结果分别为1 2 3 4 5 6 7 8 9 10，故对4取模的结果为1 2 3 0 1 2 3 0 1 2，c为1，所以只有0是有效类型，类型为0的有2个数据，故输出2
 * -------------------
 * <p>
 * 题型分析：
 * 【中等】 进制转换（16，10, 2 进制，位运算）
 *
 * @author jokerzzccc
 * @date 2025/3/29
 */
public class OD25A22 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int b = scanner.nextInt();
        List<Integer> inputList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            // 以十六进制读入数据,转为10进制整数
            inputList.add(scanner.nextInt(16));
        }
        scanner.close();

        // K-数据类型，V-其有效数据类型个数
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < inputList.size(); i++) {
            int curr = inputList.get(i);
            // 四个字节相加的和
            int currSum = 0;
            // 取出每个字节，累加
            for (int j = 0; j < 4; j++) {
                // int 类型是4个字节，一个字节8位，
                // 0xff 表示二进制的 1111 1111
                // curr >> 8 & 0xff 表达式的意识：，curr 右移8位，取低8位(也就是一个字节，共四个字节)
                currSum += curr >> (8 * j) & 0xff;
            }
            // 取模，计算
            int mod = currSum % b;
            if (mod < c) {
                cntMap.put(mod, cntMap.getOrDefault(mod, 0) + 1);
            }
        }

        // 取最大次数
        int maxCnt = 0;
        for (Integer value : cntMap.values()) {
            maxCnt = Math.max(maxCnt, value);
        }
        // 输出结果
        System.out.println(maxCnt);

    }

}
