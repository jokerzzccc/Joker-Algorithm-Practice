package com.joker.algorithm.huaweiod.luojifenxi;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 分披萨
 * 题目描述
 * "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
 * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
 * 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
 * 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
 * 输入描述
 * 第 1 行为一个正整数奇数 N，表示披萨小块数量。
 * 3 ≤ N < 500
 * 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
 * 1 ≤ i ≤ N
 * 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
 * 每块披萨的大小范围为 [1, 2147483647]
 * 输出描述
 * "吃货"能分得到的最大的披萨大小的总和。
 * 用例
 * 输入
 * 5
 * 8
 * 2
 * 10
 * 5
 * 7
 * 输出
 * 19
 * 说明：
 * 此例子中，有 5 块披萨。每块大小依次为 8、2、10、5、7。
 * 按照如下顺序拿披萨，可以使"吃货"拿到最多披萨：
 * “吃货” 拿大小为 10 的披萨
 * “馋嘴” 拿大小为 5 的披萨
 * “吃货” 拿大小为 7 的披萨
 * “馋嘴” 拿大小为 8 的披萨
 * “吃货” 拿大小为 2 的披萨
 * 至此，披萨瓜分完毕，"吃货"拿到的披萨总大小为 10 + 7 + 2 = 19
 * 可能存在多种拿法，以上只是其中一种。
 * ————————————————
 * 题型分析
 * 【中等】环形排列 + 回溯 + 备忘录
 *
 * @author jokerzzccc
 * @date 2025/3/14
 */
public class COE65 {

    // 数组长度
    public static int N;
    // 输入数组
    public static int[] inputArr;
    // 记忆化数组，用于存储已计算过的状态
    // 其中 memo[L][R] 表示从左边界 L 到右边界 R ，能够获得的最大美味值。
    public static int[][] memo;

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine().trim());
        inputArr = new int[N];
        for (int i = 0; i < N; i++) {
            inputArr[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        memo = new int[N][N];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // “吃货”能分得到的最大的披萨大小的总和
        int maxRole1 = 0;
        for (int i = 0; i < N; i++) {
            maxRole1 = Math.max(maxRole1, allocationDP((i + 1) % N, (i + N - 1) % N) + inputArr[i]);
        }

        System.out.println(maxRole1);
        scanner.close();
    }

    private static int allocationDP(int left, int right) {
        // 剪枝操作：
        // 避免重复计算：如果当前状态已经计算过，则直接返回结果
        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        // 根据规则，选择当前美味值较大的披萨
        if (inputArr[left] > inputArr[right]) {
            left = (left + 1) % N;
        } else {
            right = (right + N - 1) % N;// 如果右边的披萨更美味，则吃掉右边的披萨
        }
        // 只剩一块披萨
        if (left == right) {
            memo[left][right] = inputArr[left];
        } else {
            // 否则，递归计算剩下披萨的最大美味值，并更新记忆化数组
            memo[left][right] = Math.max(inputArr[left] + allocationDP((left + 1) % N, right)
                    , inputArr[right] + allocationDP(left, (right + N - 1) % N));
        }
        return memo[left][right];
    }

}
