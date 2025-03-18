package com.joker.algorithm.huaweiod.shuangzhizhen.huadongwindow;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 高矮个子排队
 * 题目描述
 * 现在有一队小朋友，他们高矮不同，我们以正整数数组表示这一队小朋友的身高，如数组{5,3,1,2,3}。
 * 我们现在希望小朋友排队，以“高”“矮”“高”“矮”顺序排列，每一个“高”位置的小朋友要比相邻的位置高或者相等；每一个“矮”位置的小朋友要比相邻的位置矮或者相等；
 * 要求小朋友们移动的距离和最小，第一个从“高”位开始排，输出最小移动距离即可。
 * 例如，在示范小队{5,3,1,2,3}中，{5, 1, 3, 2, 3}是排序结果。
 * {5, 2, 3, 1, 3} 虽然也满足“高”“矮”“高”“矮”顺序排列，但小朋友们的移动距离大，所以不是最优结果。
 * 移动距离的定义如下所示：
 * 第二位小朋友移到第三位小朋友后面，移动距离为1，若移动到第四位小朋友后面，移动距离为2；
 * 输入描述
 * 排序前的小朋友，以英文空格的正整数：
 * 4 3 5 7 8
 * 注：小朋友<100个
 * 输出描述
 * 排序后的小朋友，以英文空格分割的正整数：4 3 7 5 8
 * 备注：4（高）3（矮）7（高）5（矮）8（高）， 输出结果为最小移动距离，只有5和7交换了位置，移动距离都是1。
 * 示例1
 * 输入
 * 4 1 3 5 2
 * 输出
 * 4 1 5 2 3
 * 说明
 * <p>
 * 示例2
 * 输入
 * 1 1 1 1 1
 * 输出
 * 1 1 1 1 1
 * 说明
 * 相邻位置可以相等
 * <p>
 * 示例3
 * 输入
 * xxx
 * 输出
 * [ ]
 * 说明
 * 出现非法参数情况， 返回空数组。
 * ————————————————
 * 题型分析
 * 【中等】滑动窗口
 * 思路：其实看着有很多情况，关键点在于 “第一个从‘高’位开始排”。
 * 这句话的意思是，我们只需要从第一个小朋友开始排列，并在发现不符合要求的排队顺序时，就进行交换。这样大大降低了题目难度。
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE36 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        // 输入字符串合格性校验
        if (!inputStr.matches("[0-9\\s]+")) {
            System.out.println("[]");
            return;
        }
        scanner.close();

        // 核心逻辑
        int[] inputNums = Arrays.stream(inputStr.trim().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = inputNums.length;
        // 两个指针：分别指向相邻的两个小朋友
        // 窗口大小固定为1
        int left = 0;
        int right = 1;

        while (right < n) {
            // 判断当前两个相邻小朋友的身高是否满足要求
            // 条件解释：
            // 1. inputNums[i] != inputNums[j] ： 确保在不相等的前提下才进行交换；
            // 2. (inputNums[left] > inputNums[right]) != (left % 2 == 0)：
            // 如果 inputNums[i] > inputNums[j]且i是奇数，或者 inputNums[i] < inputNums[j] 且 i 是偶数
            // 则需要交换 inputNums[i] 和 inputNums[j] 的值，以符合"高矮交替"的规则。
            // 因为偶数位一定是”高“，奇数位一定是”矮“。
            if (inputNums[left] != inputNums[right]
                    && (inputNums[left] > inputNums[right]) != (left % 2 == 0)) {
                int temp = inputNums[left];
                inputNums[left] = inputNums[right];
                inputNums[right] = temp;
            }
            left++;
            right++;
        }

        // 输出结果
        StringJoiner sj = new StringJoiner(" ");
        for (int h : inputNums) {
            sj.add(String.valueOf(h));
        }
        // 输出最终排序结果
        System.out.println(sj.toString());

    }

}
