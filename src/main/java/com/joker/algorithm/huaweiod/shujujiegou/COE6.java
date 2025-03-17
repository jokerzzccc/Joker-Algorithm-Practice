package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;

/**
 * 猜字谜
 * <p>
 * 题目描述
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 * 对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 * 变换顺序以后一样的，比如通过变换w和e的顺序，“nwes”跟“news”是可以完全对应的；
 * 字母去重以后是一样的，比如“woood”和“wood”是一样的，它们去重后都是“wod”
 * 请你写一个程序帮忙在谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回”not found”
 * 输入描述
 * 谜面单词列表，以“,”分隔
 * 谜底库单词列表，以","分隔
 * 输出描述
 * 匹配到的正确单词列表，以","分隔
 * 如果找不到，返回"not found"
 * 备注
 * 单词的数量N的范围：0 < N < 1000
 * 词汇表的数量M的范围：0 < M < 1000
 * 单词的长度P的范围：0 < P < 20
 * 输入的字符只有小写英文字母，没有其他字符
 * 示例1
 * 输入
 * conection
 * connection,today
 * 输出
 * connection
 * 示例2
 * 输入
 * bdni,wooood
 * bind,wrong,wood
 * 输出
 * bind,wood
 * ————————————————
 * 题型分析：
 * 【简单】字符串 + 一维数组 + 排序
 * 思考：学会利用数据结构（比如:TreeSet） 来做去重、排序等
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE6 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String[] puzzleWords = scanner.nextLine().trim().split(",");
        String[] answerWords = scanner.nextLine().trim().split(",");
        scanner.close();

        // 存储找到谜底的谜底列表结果
        List<String> matchedAnswers = new ArrayList<>();
        // 遍历谜面
        for (String puzzleWord : puzzleWords) {
            boolean isFound = false;
            // 将字符串转换为字符数组，然后排序，去重，再对比
            // 方向一：乱序（排序，使用 TreeSet 的特性）
            // 方向二：字母重复（去重，使用 Set 特性）
            String puzzleCompareStr = String.join("", new TreeSet<>(Arrays.asList(puzzleWord.split(""))));
            // 遍历谜底
            for (String answerWord : answerWords) {
                String answerCompareStr = String.join("", new TreeSet<>(Arrays.asList(answerWord.split(""))));
                // 对比
                if (puzzleCompareStr.equals(answerCompareStr)) {
                    matchedAnswers.add(answerWord);
                    isFound = true;
                    break;
                }
            }

            // 如果没有找到匹配的单词，则将 "not found" 添加到结果列表中
            if (!isFound) {
                matchedAnswers.add("not found");
            }
        }

        // 输出结果
        if (matchedAnswers.isEmpty()) {
            System.out.println("not found");
        } else {
            System.out.println(String.join(",", matchedAnswers));
        }

    }

}
