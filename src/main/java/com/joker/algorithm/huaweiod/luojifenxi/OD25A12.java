package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 找出两个整数数组中同时出现的整数
 * <p>
 * 题目描述
 * 现有两个整数数组，需要你找出两个数组中同时出现的整数，并按照如下要求输出：
 * 1、有同时出现的整数时，先按照同时出现次数（整数在两个数组中都出现并且出现次数较少的那个）进行归类，然后按照出现次数从小到大依次按行输出。
 * 2、没有同时出现的整数时，输出NULL。
 * 输入描述
 * 第一行为第一个整数数组，第二行为第二个整数数组，每行数据中整数与整数之间以英文逗号分隔，整数的取值范围为[-200,200]，数组长度的范围为[1,10000]之间的整数。
 * 输出描述
 * 按照出现次数从小到大依次按行输出，每行输出的格式为:出现次数:该出现次数下的整数升序排序的结果。
 * 格式中的":"为英文冒号，整数间以英文逗号分隔。
 * 示例1
 * 输入
 * 5,3,6,-8,0,11
 * 2,8,8,8,-1,15
 * 输出
 * NULL
 * 说明
 * 两个整数数组没有同时出现的整数，输出NULL。
 * <p>
 * 示例2
 * 输入
 * 5,8,11,3,6,8,8,-1,11,2,11,11
 * 11,2,11,8,6,8,8,-1,8,15,3,-9,11
 * 输出
 * <p>
 * 1:-1,2,3,6
 * 3:8,11
 * 说明
 * 两个整数数组中同时出现的整数为-1、2、3、6、8、11，其中同时出现次数为1的整数为-1,2,3,6(升序排序)，同时出现次数为3的整数为8,11(升序排序)，先升序输出出现次数为1的整数，再升序输出出现次数为3的整数。
 * ————————————————
 * 题型分析
 * 【简单】逻辑分析（数组 + Map + List + 排序）
 *
 * @author jokerzzccc
 * @date 2025/3/28
 */
public class OD25A12 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int[] array1 = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] array2 = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        // K-数字，V-次数
        Map<Integer, Integer> cntMap1 = new HashMap<>();
        for (int num : array1) {
            cntMap1.put(num, cntMap1.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> cntMap2 = new HashMap<>();
        for (int num : array2) {
            cntMap2.put(num, cntMap2.getOrDefault(num, 0) + 1);
        }

        // K-出现次数，V-数字链表
        Map<Integer, ArrayList<Integer>> res = new HashMap<>();
        // 对比次数
        for (Integer num1 : cntMap1.keySet()) {
            int cnt1 = cntMap1.get(num1);
            int cnt2 = cntMap2.getOrDefault(num1, 0);
            int currMinCnt = 0;
            if (cnt2 > 0) {
                currMinCnt = Math.min(cnt1, cnt2);
                ArrayList<Integer> currChain = res.get(currMinCnt);
                if (currChain == null) {
                    currChain = new ArrayList<>();
                    currChain.add(num1);
                    res.put(cnt1, currChain);
                } else {
                    currChain.add(num1);
                }
            }
        }

        // 按出现次数排序
        res.entrySet().stream()
                .sorted((o1, o2) -> o2.getKey() - o1.getKey());
        // 输出结果
        if (res.isEmpty()) {
            System.out.println("NULL");
        } else {
            for (Integer currMinCnt : res.keySet()) {
                ArrayList<Integer> currChain = res.get(currMinCnt);
                Collections.sort(currChain);
                System.out.println(currMinCnt + ":"
                        + String.join(",", currChain.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))));
            }
        }

    }

}
