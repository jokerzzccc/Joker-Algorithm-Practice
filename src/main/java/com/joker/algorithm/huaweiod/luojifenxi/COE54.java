package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 数字涂色
 * 题目描述
 * 疫情过后，希望小学终于又重新开学了，三年二班开学第一天的任务是将后面的黑板报重新制作。
 * 黑板上已经写上了N个正整数，同学们需要给这每个数分别上一种颜色。
 * 为了让黑板报既美观又有学习意义，老师要求同种颜色的所有数都可以被这种颜色中最小的那个数整除。
 * 现在请你帮帮小朋友们，算算最少需要多少种颜色才能给这N个数进行上色。
 * 输入描述
 * 第一行有一个正整数N，其中。
 * 第二行有N个int型数(保证输入数据在[1,100]范围中)，表示黑板上各个正整数的值。
 * 输出描述
 * 输出只有一个整数，为最少需要的颜色种数。
 * 示例1
 * 输入
 * 3
 * 2 4 6
 * 输出
 * 3
 * 2 4 6
 * 说明
 * 有数都能被2整除
 * 示例2
 * 输入
 * 4
 * 2 3 4 9
 * 输出
 * 2
 * 说明
 * 与4涂一种颜色，4能被2整除；3与9涂另一种颜色，9能被3整除。不能4个数涂同一个颜色，因为3与9不能被2整除。所以最少的颜色是两种。
 * ————————————————
 * 题型分析：
 * 【简单】 一维数组 + 排序 + 便利
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE54 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] inputArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 排序
        Arrays.sort(inputArray);

        // K-最小数字, V-及能被整除它的数字的链表
        Map<Integer, List<Integer>> num2SameColor = new HashMap<>();
        // 标识该数字是否被使用过
        Map<Integer, Boolean> isVisited = new HashMap<>();
        for (int i = 0; i < inputArray.length; i++) {
            int curr = inputArray[i];
            if (Boolean.FALSE.equals(isVisited.getOrDefault(curr, false))) {
                for (int j = i + 1; j < inputArray.length; j++) {
                    if (inputArray[j] % curr == 0) {
                        num2SameColor.putIfAbsent(curr, new ArrayList<>());
                        num2SameColor.get(curr).add(inputArray[j]);
                        isVisited.put(inputArray[j], true);
                    }
                }
            }
        }
        // 最少涂色个数
        int size = num2SameColor.size();
        System.out.println(size);

        scanner.close();
    }

}
