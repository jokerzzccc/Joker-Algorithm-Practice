package com.joker.algorithm.huaweiod.dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 游戏分组
 * 一、题目描述
 * 部门准备举办一场王者荣耀表演赛，有 10 名游戏爱好者参与，分为两队，每队 5 人。
 * 每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把 10 名参赛者分为实力尽量相近的两队。
 * 一队的实力可以表示为这一队 5 名队员的评分总和。
 * 现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队，最后输出这两组的实力差绝对值。
 * 二、输入描述
 * 10 个整数，表示 10 名参与者的游戏水平评分。范围在 [1,10000] 之间。
 * 三、输出描述
 * 实力最相近两队的实力差绝对值。
 * <p>
 * 1、输入
 * 1 2 3 4 5 6 7 8 9 10
 * 2、输出
 * 1
 * 3、说明
 * 10 名队员分为两组，两组实力差绝对值最小为 1
 * <p>
 * 四、测试用例
 * 测试用例1
 * 1、输入
 * 10 20 30 40 50 60 70 80 90 100
 * 2、输出
 * 10
 * ————————————————
 * 题型分析：
 * 【中等】DFS + 子集组合
 * 思路：
 * 在每一步有两种选择：将当前玩家分配给第一队，或者不分配给第一队（即默认分配给第二队）。
 * 1. 选择当前玩家加入第一队：这是通过dfs(nums, idx + 1, count + 1, currentSum + nums[idx]);实现的。
 * 这里idx + 1表示考虑下一个玩家，count + 1表示第一队的玩家数增加了1，currentSum + nums[idx]表示第一队的总评分增加了当前玩家的评分。
 * <p>
 * 2. 不选择当前玩家加入第一队：即留给第二队，通过dfs(nums, idx + 1, count, currentSum);实现。
 * 这里只将idx增加1，移动到下一个玩家，而count和currentSum保持不变，因为没有新的玩家加入第一队。
 *
 * @author jokerzzccc
 * @date 2025/3/21
 */
public class COE67 {

    private static int TOTAL_SUM = 0;

    private static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputNums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        TOTAL_SUM = Arrays.stream(inputNums).sum();
        dfs(inputNums, 0, 0, 0);

        System.out.println(minDiff);

    }

    /**
     * @param inputNums 输入原始数组
     * @param index 当前索引
     * @param count 当前第一队伍已选择人数
     * @param currSum 当前第一队伍的
     */
    private static void dfs(int[] inputNums, int index, int count, int currSum) {
        // 当我们为一个队伍选择了5名玩家时
        if (count == 5) {
            int diff = Math.abs(currSum - (TOTAL_SUM - currSum));
            minDiff = Math.min(minDiff, diff);
            return;
        }

        // 如果我们已经考虑了所有玩家，停止递归
        if (index == inputNums.length) {
            return;
        }

        // 为第一个队伍选择当前玩家
        dfs(inputNums, index + 1, count + 1, currSum + inputNums[index]);

        // 不为第一个队伍选择当前玩家
        dfs(inputNums, index + 1, count, currSum);
    }

}
