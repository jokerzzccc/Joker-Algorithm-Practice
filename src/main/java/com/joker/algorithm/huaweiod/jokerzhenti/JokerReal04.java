package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 在一个国家仅有1分，2分，3分硬币，将钱N分兑换成硬币有很多种兑法。请你编程序计算出共有多少种兑法。
 * <p>
 * 输入
 * 输入每行包含一个正整数N(0<N<32768)。输入到文件末尾结束。
 * <p>
 * 输出
 * 输出对应的兑换方法数。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 3
 * 5
 * 7
 * 9
 * 20
 * 输出样例 1
 * <p>
 * 3
 * 5
 * 8
 * 12
 * 44
 * <p>
 * 提示：
 * 3分钱的硬币组合可以是(1,1,1)，(1,2)，(3)，所以是三种
 * ---------------
 * 解题思路：
 * 完全背包问题-动态规划
 * 类似 leetcode-518 零钱兑换II
 *
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
            int path = coinChange(inputNumList.get(i));
            resChangeList.set(i, path);
        }

        // 结果处理
        for (Integer res : resChangeList) {
            System.out.println(res);
        }
    }

    private static int coinChange(int target) {
        // dp 定义：若只使用 `coins` 中的前 `i` 个（`i` 从 1 开始计数）硬币的面值，若想凑出金额 `j`，有 `dp[i][j]` 种凑法
        int n = initCoinList.length;
        int[][] dp = new int[n + 1][target + 1];
        // base case
        // 总金额为 0 时，不放进去就是一种方法
        for (int i = 0; i <= 3; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - initCoinList[i - 1] >= 0) {
                    // 两种选择，将第 i 个 coin 不放进背包，或放进背包
                    dp[i][j] = dp[i - 1][j] + dp[i][j - initCoinList[i - 1]];
                } else {
                    // 不放进背包，因为放不进去
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];

    }

}
