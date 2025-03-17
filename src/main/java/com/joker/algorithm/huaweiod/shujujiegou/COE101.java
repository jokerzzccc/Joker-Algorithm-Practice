package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 字符串摘要
 * 题目描述
 * 给定一个字符串的摘要算法，请输出给定字符串的摘要值
 * 去除字符串中非字母的符号。
 * 如果出现连续字符(不区分大小写) ，则输出：该字符 (小写) + 连续出现的次数。
 * 如果是非连续的字符(不区分大小写)，则输出：该字符(小写) + 该字母之后字符串中出现的该字符的次数
 * 对按照以上方式表示后的字符串进行排序：字母和紧随的数字作为一组进行排序，数字大的在前，数字相同的，则按字母进行排序，字母小的在前。
 * 输入描述
 * 一行字符串，长度为[1,200]
 * 输出描述
 * 摘要字符串
 * <p>
 * 用例1
 * 输入
 * aabbcc
 * 输出
 * a2b2c2
 * <p>
 * 用例2
 * 输入
 * bAaAcBb
 * 输出
 * a3b2b2c0
 * 说明
 * bAaAcBb:
 * 第一个b非连续字母，该字母之后字符串中还出现了2次(最后的两个Bb) ，所以输出b2
 * a连续出现3次，输出a3，
 * c非连续，该字母之后字符串再没有出现过c，输出c0
 * Bb连续2次，输出b2
 * 对b2a3c0b2进行排序，最终输出a3b2b2c0
 * ————————————————
 * 题型分析
 * 【简单】字符串处理（转 char 数组） + 排序（List）
 *
 * @author jokerzzccc
 * @date 2025/3/17
 */
public class COE101 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        scanner.close();
        // 去除非字母的字符,全部转为小写
        String filterStr = inputStr.replaceAll("[^a-zA-Z]", "")
                .toLowerCase();
        char[] inputCharArr = filterStr.toCharArray();

        // 存储摘要值
        List<String> resultList = new ArrayList<>();
        // 1. 遍历计算当前字符摘要值
        for (int i = 0; i < inputCharArr.length; ) {
            char currChar = inputCharArr[i];
            StringBuilder currStr = new StringBuilder();

            // 1.1 连续的数量
            int currContinuesCount = 1;
            for (int j = i + 1; j < inputCharArr.length; j++) {
                if (currChar == inputCharArr[j]) {
                    currContinuesCount++;
                } else {
                    break;
                }
            }
            if (currContinuesCount > 1) {
                resultList.add(currStr.append(currChar).append(currContinuesCount).toString());
                i += currContinuesCount;
                continue;
            }
            // 1.2 之后的出现次数
            int currAfterCount = 0;
            for (int k = i + 1; k < inputCharArr.length; k++) {
                if (currChar == inputCharArr[k]) {
                    currAfterCount++;
                }
            }
            if (currAfterCount > 0) {
                resultList.add(currStr.append(currChar).append(currAfterCount).toString());
                i++;
                continue;
            }

            // 1.3 两个都没有则
            resultList.add(currStr.append(currChar).append(0).toString());
            i++;
        }

        // 2. 排序
        resultList.sort((o1, o2) -> {
            char currChar1 = o1.charAt(0);
            char currChar2 = o2.charAt(0);
            int currCount1 = Integer.parseInt(o1.substring(1));
            int currCount2 = Integer.parseInt(o2.substring(1));
            if (currCount1 != currCount2) {
                return currCount2 - currCount1;
            } else {
                return currChar1 - currChar2;
            }
        });

        System.out.println(String.join("", resultList));

    }

}
