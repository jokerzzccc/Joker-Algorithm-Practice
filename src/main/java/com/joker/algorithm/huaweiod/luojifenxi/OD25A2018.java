package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 攀登者2
 * 攀登者喜欢寻找各种地图，并且尝试攀登到最高的山峰。
 * 地图表示为一维数组，数组的索引代表水平位置，数组的元素代表相对海拔高度。其中数组元素0代表地面。
 * <p>
 * 例如：[0,1,2,4,3,1,0,0,1,2,3,1,2,1,0]，代表如下图所示的地图，地图中有两个山脉位置分别为 1,2,3,4,5 和 8,9,10,11,12,13，最高峰高度分别为 4,3。最高峰位置分别为3,10。
 * 一个山脉可能有多座山峰(高度大于相邻位置的高度，或在地图边界且高度大于相邻的高度)。
 * 登山时会消耗登山者的体力(整数)，
 * 上山时，消耗相邻高度差两倍的体力
 * 下山时，消耗相邻高度差一倍的体力
 * 平地不消耗体力
 * 登山者体力消耗到零时会有生命危险。
 * 例如，上图所示的山峰：
 * 从索引0，走到索引1，高度差为1，需要消耗 2 * 1 = 2 的体力，
 * 从索引2，走到索引3，高度差为2，需要消耗 2 * 2 = 4 的体力。
 * 从索引3，走到索引4，高度差为1，需要消耗 1 * 1 = 1 的体力。
 * 攀登者想要评估一张地图内有多少座山峰可以进行攀登，且可以安全返回到地面，且无生命危险。
 * 例如上图中的数组，有3个不同的山峰，登上位置在3的山可以从位置0或者位置6开始，从位置0登到山顶需要消耗体力 1 * 2 + 1 * 2 + 2 * 2 = 8，从山顶返回到地面0需要消耗体力 2 * 1 + 1 * 1 + 1 * 1 = 4 的体力，按照登山路线 0 → 3 → 0 需要消耗体力12。攀登者至少需要12以上的体力（大于12）才能安全返回。
 * <p>
 * 输入描述
 * 第一行输入为地图一维数组
 * 第二行输入为攀登者的体力
 * <p>
 * 输出描述
 * 确保可以安全返回地面，且无生命危险的情况下，地图中有多少山峰可以攀登。
 * <p>
 * 用例1
 * 输入
 * 0,1,4,3,1,0,0,1,2,3,1,2,1,0
 * 13
 * 输出
 * 3
 * 说明
 * 登山者只能登上位置10和12的山峰，7 → 10 → 7，14 → 12 → 14
 * <p>
 * 用例2
 * 输入
 * 1,4,3
 * 999
 * 输出
 * 1,4,3
 * 999
 * 说明
 * 没有合适的起点和终点
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】逻辑分析 + 模拟 + 队列
 *
 * @author jokerzzccc
 * @date 2025/4/2
 */
public class OD25A2018 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] inputArr = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int capacity = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        //
        int n = inputArr.length;
        // 记录山峰的位置
        LinkedList<Integer> peakIndexList = new LinkedList<>();
        if (inputArr[0] > inputArr[1]) {
            peakIndexList.add(0);
        }
        if (inputArr[n - 1] > inputArr[n - 2]) {
            peakIndexList.add(n - 1);
        }
        for (int i = 1; i < n - 1; i++) {
            if (inputArr[i] > inputArr[i - 1] && inputArr[i] > inputArr[i + 1]) {
                peakIndexList.add(i);
            }
        }

        int can_peak = 0;
        while (!peakIndexList.isEmpty()) {
            Integer currPeakIndex = peakIndexList.poll();

            // 向左，左上左下，
            // 当前体力消耗
            int currLeftCapacityLose = 0;
            // 向左能否到平地
            boolean leftUpDown = false;
            int leftIndex = currPeakIndex - 1;
            while (leftIndex >= 0) {
                int chazhi = Math.abs(inputArr[leftIndex] - inputArr[leftIndex + 1]);
                currLeftCapacityLose += (1 * chazhi + 2 * chazhi);

                if (inputArr[leftIndex] == 0 && currLeftCapacityLose < capacity) {
                    leftUpDown = true;
                    can_peak++;
                    break;
                }

                leftIndex--;
            }

            // 如果左边可行，当前山峰可以攀爬，不再测试右边
            if (!leftUpDown) {
                continue;
            }

            // 向右，右上右下
            int currRightCapacityLose = 0;
            int rightIndex = currPeakIndex + 1;
            while (rightIndex < n) {
                int chazhi = Math.abs(inputArr[rightIndex] - inputArr[rightIndex - 1]);
                currRightCapacityLose += (1 * chazhi + 2 * chazhi);
                if (inputArr[rightIndex] == 0 && currRightCapacityLose < capacity) {
                    can_peak++;
                    break;
                }
                rightIndex++;
            }

        }

        System.out.println(can_peak);

    }

}
