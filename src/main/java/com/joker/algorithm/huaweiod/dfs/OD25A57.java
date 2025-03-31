package com.joker.algorithm.huaweiod.dfs;

import java.util.*;

/**
 * 阿里巴巴找黄金宝箱II
 * <p>
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面贴有箱子中藏有金币的数量。
 * 从金币数量中选出一个数字集合，并销毁贴有这些数字的每个箱子，如果能销毁一半及以上的箱子，则返回这个数字集合的最小大小
 * 输入描述
 * 一个数字字串，数字之间使用逗号分隔，例如：6,6,6,6,3,3,3,1,1,5
 * 字串中数字的个数为偶数，并且
 * 1 ≤ 字串中数字的个数 ≤ 100000
 * 1 ≤ 每个数字 ≤ 100000
 * 输出描述
 * 一个数字字串，数字之间使用逗号分隔，例如：6,6,6,6,3,3,3,1,1,5
 * 字串中数字的个数为偶数，并且
 * 1 ≤ 字串中数字的个数 ≤ 100000
 * 1 ≤ 每个数字 ≤ 100000
 * <p>
 * 示例1
 * 输入
 * 1,1,1,1,3,3,3,6,6,8
 * 输出
 * 2
 * 说明
 * 选择集合{1,8}，销毁后的结果数组为[3,3,3,6,6]，长度为5，长度为原数组的一半。
 * 大小为 2 的可行集合还有{1,3},{1,6},{3,6}。
 * 选择 {6,8} 集合是不可行的，它销后的结果数组为[1,1,1,1,3,3,3]，新数组长度大于原数组的二分之一。
 * <p>
 * 示例2
 * 输入
 * 2,2,2,2
 * 输出
 * 1
 * 说明
 * 我们只能选择集合{2}，销毁后的结果数组为空。
 * ————————————————
 * <p>
 * 题型分析：
 * 【中等】贪心策略
 * 思路：贪心算法：我们总是优先选择出现次数最多的数字进行销毁，以最快地减少剩余箱子的数量。
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A57 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputNums = Arrays.stream(scanner.nextLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        scanner.close();

        //
        Map<Integer, Integer> num2Count = Arrays.stream(inputNums)
                .boxed()
                .collect(HashMap::new, (map, num) -> map.put(num, map.getOrDefault(num, 0) + 1), HashMap::putAll);
        // 最小集合大小
        int n = inputNums.length;
        // 将频次按降序排列
        List<Integer> sortedFreq = new ArrayList<>(num2Count.values());
        sortedFreq.sort(Collections.reverseOrder());

        // 数字集合的个数
        int count = 0;
        // 销毁了原数组的多少数字（每个数字次数的累和）
        int destroyed = 0;
        int target = n / 2;

        for (Integer f : sortedFreq) {
            destroyed += f;
            count++;
            if (destroyed >= target) {
                break;
            }
        }

        System.out.println(count);

    }

}
