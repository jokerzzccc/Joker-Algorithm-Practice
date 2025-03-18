package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最长连续子序列
 * <p>
 * 题目描述
 * 有N个正整数组成的一个序列。给定整数sum，求长度最长的连续子序列，使他们的和等于sum，返回此子序列的长度，
 * 如果没有满足要求的序列，返回-1。
 * 输入描述
 * 第一行输入是：N个正整数组成的一个序列
 * 第二行输入是：给定整数sum
 * 输出描述
 * 最长的连续子序列的长度
 * 备注
 * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分隔
 * 序列长度：1 <= N <= 200
 * 输入序列不考虑异常情况
 * 示例1
 * 输入
 * 1,2,3,4,2
 * 6
 * 输出
 * 3
 * 说明
 * 1,2,3和4,2两个序列均能满足要求，所以最长的连续序列为1,2,3，因此结果为3。
 * <p>
 * 示例2
 * 输入
 * 1,2,3,4,2
 * 20
 * 输出
 * -1
 * 说明
 * 没有满足要求的子序列，返回-1
 * ————————————————
 * 题型分析：
 * 【简单】滑动窗口
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE15 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputNums = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sumTarget = Integer.parseInt(scanner.nextLine());
        scanner.close();

        //
        int left = 0;
        int right = 0;
        // 连续子序列的最长长度
        int maxSubLength = -1;
        // 当前窗口的和
        int currSum = 0;

        while (right < inputNums.length) {
            // 不断扩大窗口，增加右边界
            currSum += inputNums[right];
            right++;

            // 如果当前窗口内的和大于目标值，收缩左边界
            while (currSum > sumTarget && left < right) {
                currSum -= inputNums[left];
                left++;
            }

            // 检查是否等于目标值，并更新最大长度
            if (currSum == sumTarget) {
                maxSubLength = Math.max(maxSubLength, right - left);
            }
        }

        // 输出结果
        System.out.println(maxSubLength);

    }

}
