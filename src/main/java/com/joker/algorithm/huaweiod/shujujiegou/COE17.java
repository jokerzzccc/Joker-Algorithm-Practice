package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 敏感字段加密
 * 题目描述
 * 给定一个由多个命令字组成的命令字符串：
 * 1、字符串长度小于等于127字节，只包含大小写字母，数字，下划线和偶数个双引号；
 * 2、命令字之间以一个或多个下划线_进行分割；
 * 3、可以通过两个双引号””来标识包含下划线_的命令字或空命令字（仅包含两个双引号的命令字），双引号不会在命令字内部出现；
 * 请对指定索引的敏感字段进行加密，替换为******（6个*），并删除命令字前后多余的下划线_。
 * 如果无法找到指定索引的命令字，输出字符串ERROR。
 * 输入描述
 * 输入为两行，第一行为命令字索引K（从0开始），第二行为命令字符串S。
 * 输出描述
 * 输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
 * 示例1
 * 输入
 * 1
 * password__a12345678_timeout_100
 * 输出
 * password_******_timeout_100
 * 1
 * 说明
 * 示例2
 * 输入
 * 2
 * aaa_password_"a12_45678"_timeout__100_""_
 * 输出
 * aaa_password_******_timeout_100_""
 * 说明
 * ————————————————
 * 题型分析
 * 【简单】
 * 【字符串】 + 查找替换
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE17 {

    public static final String ERROR = "ERROR";
    public static final String ENCRYPT_STR = "******";
    public static final String QUOTATION_MARK = "\"";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine());
        String[] inputStrArr = scanner.nextLine().split("_");
        scanner.close();

        // "" 预处理
        List<String> validCommands = new ArrayList<>();
        int length = inputStrArr.length;
        for (int i = 0; i < length; i++) {
            String curr = inputStrArr[i];
            if (inputStrArr[i] == null || inputStrArr[i].length() == 0) {
                continue;
            }
            if (curr.startsWith(QUOTATION_MARK)) {
                while (!curr.endsWith(QUOTATION_MARK)) {
                    curr += inputStrArr[++i];
                }
            }
            validCommands.add(curr);
        }

        validCommands.set(index, ENCRYPT_STR);

        System.out.println(String.join("_", validCommands));

    }

}
