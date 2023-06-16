package com.joker.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 黑名单中的随机数
 * </p>
 *
 * @author admin
 * @date 2023/6/16
 */
public class leetcode710 {

    public static void main(String[] args) {
        int n = 7;
        int[] blacklist = {2, 3, 5};

        Solution solution = new Solution(n, blacklist);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(solution.pick());
        }
        res.stream().forEach(System.out::println);

    }

    /**
     * 解法一：黑白名单映射
     */
    private static class Solution {

        // 黑白名单的边界
        int bound;
        // 用于记录哪些黑名单索引需要被替换成白名单索引
        HashMap<Integer, Integer> blackToWhiteMapping;

        public Solution(int n, int[] blacklist) {
            bound = n - blacklist.length;
            blackToWhiteMapping = new HashMap<>();

            // 先将所有黑名单数字加入 map
            for (int black : blacklist) {
                // 这里赋值多少都可以
                // 目的仅仅是把键存进哈希表
                // 方便快速判断数字是否在黑名单内
                blackToWhiteMapping.put(black, 666);
            }

            int last = n - 1;
            for (int black : blacklist) {
                // 如果 black 已经在区间 [bound, N) 可以直接忽略
                if (black >= bound) {
                    continue;
                }
                // 跳过所有黑名单中的数字
                while (blackToWhiteMapping.containsKey(last)) {
                    last--;
                }
                // 将黑名单中的索引映射到合法数字
                blackToWhiteMapping.put(black, last);
                last--;
            }

        }

        public int pick() {
            // 随机选取一个索引
            int index = (int) (Math.random() * bound);
            // 这个索引命中了黑名单，
            // 需要被映射到其他位置
            if (blackToWhiteMapping.containsKey(index)) {
                return blackToWhiteMapping.get(index);
            }
            // 若没命中黑名单，则直接返回
            return index;
        }

    }

}
