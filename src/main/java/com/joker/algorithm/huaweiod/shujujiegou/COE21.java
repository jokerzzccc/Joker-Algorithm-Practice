package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 字符串分割转换
 * 题目描述
 * 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 * 对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
 * 反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 * 输入描述
 * 输入为两行，第一行为参数K，第二行为字符串S。
 * 输出描述
 * 输出转换后的字符串。
 * 示例1
 * 输入
 * 3
 * 12abc-abCABc-4aB@
 * 输出
 * 12abc-abc-ABC-4aB-@
 * 说明
 * 子串为12abc、abCABc、4aB@，第一个子串保留，
 * 后面的子串每3个字符一组为abC、ABc、4aB、@，
 * abC中小写字母较多，转换为abc，
 * ABc中大写字母较多，转换为ABC，
 * 4aB中大小写字母都为1个，不做转换，
 * <p>
 * \@中没有字母，连起来即12abc-abc-ABC-4aB-@ 示例2
 * 输入
 * 12
 * 12abc-abCABc-4aB@
 * 输出
 * 12abc-abCABc4aB@
 * 说明
 * 子串为12abc、abCABc、4aB@，第一个子串保留，
 * 后面的子串每12个字符一组为abCABc4aB@，
 * 这个子串中大小写字母都为4个，不做转换，
 * 连起来即12abc-abCABc4aB@
 * ————————————————
 * 题型分析：
 * 【简单】字符串处理（大小写）+转为 char 数组
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE21 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine().trim());
        String[] inputArr = scanner.nextLine().trim().split("-");
        scanner.close();

        // 存储除第一个外的字符串
        StringBuilder tailStr = new StringBuilder();
        for (int i = 1; i < inputArr.length; i++) {
            tailStr.append(inputArr[i]);
        }

        // 按照 K 值，重新组成新字符数组
        List<String> newArrList = new ArrayList<>();
        newArrList.add(inputArr[0]);
        for (int i = 0; i < tailStr.length(); i += k) {
            String currStr = "";
            if (i + k > tailStr.length()) {
                currStr = tailStr.substring(i);
            } else {
                currStr = tailStr.substring(i, i + k);
            }
            char[] currCharArray = currStr.toCharArray();

            // 统计大写字符数量
            int currUpperCaseCount = 0;
            int currLowerCaseCount = 0;
            for (char ch : currCharArray) {
                if (Character.isUpperCase(ch)) {
                    currUpperCaseCount++;
                }
                if (Character.isLowerCase(ch)) {
                    currLowerCaseCount++;
                }
            }
            // 大小写转换
            if (currUpperCaseCount > currLowerCaseCount) {
                for (int j = 0; j < currCharArray.length; j++) {
                    currCharArray[j] = Character.toUpperCase(currCharArray[j]);
                }
            } else if (currLowerCaseCount > currUpperCaseCount) {
                for (int j = 0; j < currCharArray.length; j++) {
                    currCharArray[j] = Character.toLowerCase(currCharArray[j]);
                }
            }
            newArrList.add(currStr);
        }

        System.out.println(String.join("-", newArrList));

    }

}
