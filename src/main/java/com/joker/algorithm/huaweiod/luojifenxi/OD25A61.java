package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 报数游戏
 * 题目描述
 * 100个人围成一圈，每个人有一个编码，编号从1开始到100。
 * 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。
 * 请问最后剩余的人在原先的编号为多少？
 * <p>
 * 输入描述
 * 输入一个整数参数 M
 * <p>
 * 输出描述
 * 如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；
 * 否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
 * <p>
 * 示例1
 * 输入
 * 3
 * 输出
 * 58,91
 * 说明
 * <p>
 * 示例2
 * 输入
 * 4
 * 输出
 * 34,45,97
 * 说明
 * ————————————————
 * 题型分析
 * 【中等】 约瑟夫环问题 + 队列
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A61 {

    public static final int INIT_NUM = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        //
        if (m <= 1 || m >= 100) {
            System.out.println("ERROR!");
            return;
        }
        // 初始化人员编号
        LinkedList<Integer> numList = new LinkedList<>();
        for (int i = 1; i <= INIT_NUM; i++) {
            numList.add(i);
        }

        // 报数
        int point = 1;
        // 循环报数
        while (numList.size() >= m) {
            Integer curr = numList.poll();
            if (point == m) {
                point = 1;
            } else {
                numList.addLast(curr);
                point++;
            }
        }

        // 排序
        Collections.sort(numList);
        // 输出结果
        StringJoiner sj = new StringJoiner(",");
        for (Integer num : numList) {
            sj.add(String.valueOf(num));
        }
        System.out.println(sj.toString());
    }

}


