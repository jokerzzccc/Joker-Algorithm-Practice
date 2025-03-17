package com.joker.algorithm.huaweiod.shujujiegou;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数组拼接
 * 题目描述
 * 现在有多组整数数组，需要将它们合并成一个新的数组。
 * 合并规则，从每个数组里按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，
 * 如果该行不足固定长度或者已经为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
 * 输入描述
 * 第一行是每次读取的固定长度，0<长度<10
 * 第二行是整数数组的数目，0<数目<1000
 * 第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
 * 输出描述
 * 输出一个新的数组，用逗号分隔。
 * 示例1
 * 输入
 * 3
 * 2
 * 2,5,6,7,9,5,7
 * 1,7,4,3,4
 * 输出
 * 2,5,6,1,7,4,7,9,5,3,4,7
 * 说明
 * 1、获得长度3和数组数目2
 * 2、先遍历第一行，获得2,5,6
 * 3、再遍历第二行，获得1,7,4
 * 4、再循环回到第一行，获得7,9,5
 * 5、再遍历第二行，获得3,4
 * 6、再回到第一行，获得7，按顺序拼接成最终结果
 * 示例2
 * 输入
 * 4
 * 3
 * 1,2,3,4,5,6
 * 1,2,3
 * 1,2,3,4
 * 输出
 * 1,2,3,4,1,2,3,1,2,3,4,5,6
 * ————————————————
 * 题型分析：
 * 【简单】字符串 + 集合
 *
 * @author jokerzzccc
 * @date 2025/3/16
 */
public class COE39 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> inputList = new ArrayList<>();
        // 元素的总数量
        int totalNum = 0;
        for (int i = 0; i < n; i++) {
            int[] intArr = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<Integer> currRow = Arrays.stream(intArr)
                    .boxed()
                    .collect(Collectors.toList());
            inputList.add(currRow);
            totalNum += currRow.size();
        }

        // 合并数组
        List<Integer> resultList = new ArrayList<>();
        // 已合并元素的数量
        int meredNum = 0;
        while (meredNum < totalNum) {
            // 遍历所有数组
            for (int i = 0; i < inputList.size(); i++) {
                // 取出当前数组的前 K 个元素
                for (int j = 0; j < k; j++) {
                    if (inputList.get(i).size() > 0) {
                        // 如果当前数组不为空
                        // 将第一个元素添加到结果中,并删除
                        resultList.add(inputList.get(i).remove(0));
                        meredNum++;
                    } else {
                        // 如果当前数组已经为空，则跳过
                        break;
                    }
                }
            }
        }

        // 输出结果
        for (int i = 0; i < resultList.size() - 1; i++) {
            System.out.print(resultList.get(i) + ",");
        }
        // 输出最后一个元素时不加逗号
        System.out.println(resultList.get(resultList.size() - 1));
        scanner.close();

    }

}
