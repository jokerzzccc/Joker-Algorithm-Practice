package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 在一个国家仅有1分，2分，3分硬币，将钱N分兑换成硬币有很多种兑法。请你编程序计算出共有多少种兑法。
 *
 * 输入
 * 输入每行包含一个正整数N(0<N<32768)。输入到文件末尾结束。
 *
 * 输出
 * 输出对应的兑换方法数。
 *
 * 样例
 * 输入样例 1 复制
 *
 * 3
 * 5
 * 7
 * 9
 * 20
 * 输出样例 1
 *
 * 3
 * 5
 * 8
 * 12
 * 44
 *
 * 提示：
 * 3分钱的硬币组合可以是(1,1,1)，(1,2)，(3)，所以是三种
 * @author jokerzzccc
 * @date 2025/10/31
 */
public class JokerReal04 {

    private static int[] initCoinList = new int[]{1, 2, 3};

    public static void main(String[] args) {
        // 输入处理
        Scanner inputScanner = new Scanner(System.in);
        LinkedList<Integer> inputNumList = new LinkedList<>();
        while (inputScanner.hasNextLine()) {
            String curr = inputScanner.nextLine();
            if (curr.isEmpty()) {
                break;
            }
            int num = Integer.parseInt(curr);
            inputNumList.add(num);
        }
        inputScanner.close();
        int n = inputNumList.size();

        // 结果集
        LinkedList<Integer> resChangeList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            resChangeList.addLast(0);
        }
        // DFS 处理
        for (int i = 0; i < n; i++) {
            coinChange(inputNumList.get(i), i, resChangeList);
        }

        // 结果处理
        for (Integer res : resChangeList) {
            System.out.println(res);
        }
    }

    private static void coinChange(int target, int index, List<Integer> resPathList) {
        for (int coin : initCoinList) {
            int next = target - coin;
            if (next < 0) {
                return;
            }
            if (next == 0) {
                resPathList.set(index, resPathList.get(index) + 1);
                return;
            }
            coinChange(next, index, resPathList);
        }

    }

}
