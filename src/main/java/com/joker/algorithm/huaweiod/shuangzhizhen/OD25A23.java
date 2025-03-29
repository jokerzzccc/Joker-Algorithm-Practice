package com.joker.algorithm.huaweiod.shuangzhizhen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 租车骑绿岛
 * 题目描述
 * 部门组织绿岛骑行团建活动。租用公共双人自行车，每辆自行车最多坐两人，最大载重M。
 * 给出部门每个人的体重，请问最多需要租用多少双人自行车。
 * 输入描述
 * 第一行两个数字m、n，分别代表自行车限重，部门总人数。
 * 第二行，n个数字，代表每个人的体重，体重都小于等于自行车限重m。
 * 0<m<=200
 * 0<n<=1000000
 * 输出描述
 * 最小需要的双人自行车数量。
 * <p>
 * 示例1
 * 输入
 * 3 4
 * 3 2 2 1
 * 输出
 * 3
 * ————————————————
 * <p>
 * 题型分析
 * 【简单】 左右指针 + 一维数组排序
 *
 * @author jokerzzccc
 * @date 2025/3/29
 */
public class OD25A23 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        // 自行车限重
        int m = scanner.nextInt();
        // 部门总人数
        int n = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();

        // 升序排序
        Arrays.sort(weights);
        // 需要的车辆
        int needCar = 0;
        // 左右指针
        int left = 0;
        int right = weights.length - 1;
        while (left < right) {
            needCar++;
            if (weights[left] + weights[right] <= m) {
                left++;
            }
            right--;
        }
        if (left == right) {
            needCar++;
        }

        System.out.println(needCar);
    }

}
