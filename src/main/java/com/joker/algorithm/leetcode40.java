package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 组合总和 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/18
 */
public class leetcode40 {

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> res;

        Solution solution = new Solution();
        res = solution.combinationSum2(candidates, target);

        System.out.println(res);
    }

    private static class Solution {

        /**
         * 结果集
         */
        private List<List<Integer>> res = new ArrayList<>();

        /**
         * 路径
         */
        private LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }

            // 关键步骤:排序
            Arrays.sort(candidates);
            int start = 0;
            backtrack(candidates, start, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
                if (target - candidates[i] < 0) {
                    break;
                }
                // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                track.addLast(candidates[i]);
                System.out.println("递归之前 => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i + 1, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 => " + track + "，剩余 = " + (target - candidates[i]));
            }
        }

    }

}
