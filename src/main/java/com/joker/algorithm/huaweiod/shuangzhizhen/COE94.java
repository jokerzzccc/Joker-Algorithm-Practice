package com.joker.algorithm.huaweiod.shuangzhizhen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求最多可以派出多少支团队
 * 题目描述
 * 用数组代表每个人的能力 一个比赛活动要求参赛团队的最低能力值为N 每个团队可以由一人或者两人组成 且一个人只能参加一个团队 计算出最多可以派出多少只符合要求的队伍。
 * 输入描述
 * 第一行代表总人数，范围1-500000
 * 第二行数组代表每个人的能力
 * - 数组大小，范围1-500000
 * - 元素取值，范围1-500000
 * 第三行数值为团队要求的最低能力值，范围1-500000
 * 输出描述
 * 最多可以派出的团队数量
 * 用例1
 * 输入
 * 5
 * 3 1 5 7 9
 * 8
 * 输出
 * 3
 * 说明
 * 3、5组成一队 1、7一队 9自己一队 输出3
 * <p>
 * 用例2
 * 输入
 * 7
 * 3 1 5 7 9 2 6
 * 8
 * 输出
 * 4
 * 说明
 * 3、5组成一队，1、7一队，9自己一队，2、6一队，输出4
 * <p>
 * 用例3
 * 输入
 * 3
 * 1 1 9
 * 8
 * 输出
 * 1
 * 说明
 * 9自己一队，输出1
 * ————————————————
 * 题型分析：
 * 【中等】左右指针 + 一维数组排序
 *
 * @author jokerzzccc
 * @date 2025/3/19
 */
public class COE94 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[] abilities = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int expected = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        Arrays.sort(abilities);
        int left = 0, right = n - 1;
        // 最多可以派出的团队数量
        int maxTeamNum = 0;

        // 左右指针遍历数组
        while (left < right) {
            if (abilities[right] >= expected) {
                maxTeamNum++;
                right--;
            } else if (abilities[left] + abilities[right] >= expected) {
                maxTeamNum++;
                right--;
                left++;
            } else {
                left++;
            }
        }

        // 如果左指针和右指针指向同一个人
        if (left == right) {
            if (abilities[left] >= expected) {
                maxTeamNum++;
            }
        }

        // 输出结果
        System.out.println(maxTeamNum);

    }

}
