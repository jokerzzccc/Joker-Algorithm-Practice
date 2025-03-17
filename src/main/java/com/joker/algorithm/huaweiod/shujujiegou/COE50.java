package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 字符统计及重排
 * 题目描述
 * 给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母（区分大小写）出现的次数，
 * 并按照字母出现次数从大到小的顺序。输出各个字母及其出现次数。
 * 如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 * 输入描述
 * 输入一行，为一个仅包含字母的字符串。
 * 输出描述
 * 按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；
 * 字母和次数间用英文冒号分隔。
 * 示例1
 * 输入
 * xyxyXX
 * 输出
 * x:2;y:2;X:2;
 * 说明
 * 无
 * 示例2
 * 输入
 * abababb
 * 输出
 * b:4;a:3;
 * 说明
 * b的出现个数比a多，故b排在a之前
 * ————————————————
 * 题型分析
 * 【简单】字符串
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE50 {

    public static void main(String[] args) {
        // 输入处理
        Scanner input = new Scanner(System.in);
        String inputStr = input.nextLine().trim();
        input.close();

        //
        char[] inputStrCharArray = inputStr.toCharArray();
        // K-字符，V-出现次数
        Map<Character, Integer> charMap = new HashMap<>();
        int maxCnt = -1;
        for (char ch : inputStrCharArray) {
            Integer cnt = charMap.getOrDefault(ch, 0) + 1;
            maxCnt = Math.max(maxCnt, cnt);
            charMap.put(ch, cnt);
        }

        // 从出现次数最多的开始遍历
        for (int cnt = maxCnt; cnt > 0; cnt--) {
            // 满足自然顺序
            // 先输出小写字母
            for (int i = 'a'; i <= 'z'; i++) {
                if (charMap.getOrDefault((char) i, 0) == cnt) {
                    System.out.print((char) i + ":" + charMap.get((char) i) + ";");
                }
            }
            // 再输出大写字母
            for (int i = 'A'; i <= 'Z'; i++) {
                if (charMap.getOrDefault((char) i, 0) == cnt) {
                    System.out.print((char) i + ":" + charMap.get((char) i) + ";");
                }
            }
        }

    }

}
