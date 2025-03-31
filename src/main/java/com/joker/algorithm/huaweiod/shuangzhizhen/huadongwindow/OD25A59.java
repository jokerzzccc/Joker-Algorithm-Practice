package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 阿里巴巴找黄金宝箱V
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，每个箱子上面贴有一个数字。
 * 阿里巴巴念出一个咒语数字k(k<N)，找出连续k个宝箱数字和的最大值，并输出该最大值。
 * 输入描述
 * 第一行输入一个数字字串，数字之间使用逗号分隔，例如：2,10,-3,-8,40,5
 * 1 ≤ 字串中数字的个数 ≤ 100000
 * -10000 ≤ 每个数字 ≤ 10000
 * 第二行输入咒语数字，例如：4，咒语数字大小小于宝箱的个数
 * 输出描述
 * 连续k个宝箱数字和的最大值，例如：39
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java 使用sacnner/system.out.print()
 * <p>
 * 用例1
 * 输入
 * 2,10,-3,-8,40,5
 * 4
 * 输出
 * 39
 * <p>
 * 用例2
 * 输入
 * 8
 * 1
 * 输出
 * 8
 * ————————————————
 * <p>
 * 题型分析：
 * 【简单】滑动窗口 + 一维数组
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A59 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        //
        int maxSubSum = Integer.MIN_VALUE;
        // 计算前k个数字之和
        int ksum = 0;
        for (int i = 0; i < k; i++) {
            ksum += inputArr[i];
        }
        maxSubSum = Math.max(maxSubSum, ksum);

        int left = 0;
        int right = k;
        while (right < inputArr.length) {
            maxSubSum = Math.max(maxSubSum, maxSubSum - inputArr[left] + inputArr[right]);
            left++;
            right++;
        }

        System.out.println(maxSubSum);

    }

}
