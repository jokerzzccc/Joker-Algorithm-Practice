package com.joker.algorithm.huaweiod.jokerzhenti;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给你一个非负整数数组 nums，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
 * <p>
 * 输入：
 * 第一行为数组大小N
 * 第二行为N个元素的值
 * 输出：
 * 最小的跳跃次数
 * <p>
 * 样例1：
 * 输入1:
 * 5
 * 2 3 1 1 4
 * 输出1:
 * 2
 * 解释:
 * 最小跳跃次数是2。从下标为0跳到下标为1的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * <p>
 * 样例2：
 * 输入2:
 * 5
 * 2 3 0 1 4
 * 输出2:
 * 2
 * 解答要求时间限制：1000ms, 内存限制：256MB
 *
 * ---------------------
 * 类似 [leetcode-45 跳跃游戏II](https://leetcode.cn/problems/jump-game-ii/description/)
 * 题解思路：
 * 贪心
 *
 * @author jokerzzccc
 * @date 2025/10/30
 */
public class JokerReal03 {

    public static void main(String[] args) {
        // 输入处理
        Scanner inputScanner = new Scanner(System.in);
        int[] firstRow = Arrays.stream(inputScanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = firstRow[0];
        int[] nums = Arrays.stream(inputScanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        inputScanner.close();

        if (n <= 1) {
            System.out.println(0);
        }

        // 返回结果：最少的跳步数
        int resMinJump = 0;
        // 当前走到哪里了
        int currTotalIndex = 0;
        // 当前能跳的最大步数
        int currMaxStep = nums[0];

        // 贪心
        while (currTotalIndex < n) {
            // (currTotalIndex, currTotalIndex + currMaxStep] 范围内的元素中，最大的一个
            int maxStep = nums[currTotalIndex];
            for (int i = currTotalIndex + 1; i < currTotalIndex + currMaxStep; i++) {
                if (i < n && nums[i] > maxStep) {
                    maxStep = nums[i];
                }
            }
            currTotalIndex += maxStep;

            resMinJump++;
        }

        // 结果处理
        System.out.println("result: " + resMinJump);
    }

}
