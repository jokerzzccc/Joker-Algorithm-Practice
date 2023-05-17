package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 组合总和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/17
 */
public class leetcode39 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res;

        // 法一
//        Solution01 solution01 = new Solution01();
//        res = solution01.combinationSum(candidates, target);

        // 法二
        Solution02 solution02 = new Solution02();
        res = solution02.combinationSum(candidates, target);

        System.out.println(res);
    }

    /**
     * 未剪枝
     */
    private static class Solution02 {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }

            backtrack(candidates, 0, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                track.addLast(candidates[i]);
                System.out.println("递归之前 track: => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 track: => " + track);
            }
        }

    }

    /**
     * 剪枝了
     */
    private static class Solution01 {

        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }
            // 排序是剪枝的前提
            Arrays.sort(candidates);

            backtrack(candidates, 0, target);
            return res;
        }

        void backtrack(int[] candidates, int start, int target) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 剪枝
                if (target - candidates[i] < 0) {
                    break;
                }
                track.addLast(candidates[i]);
                System.out.println("递归之前 track: => " + track + "，剩余 = " + (target - candidates[i]));

                backtrack(candidates, i, target - candidates[i]);

                track.removeLast();
                System.out.println("递归之后 track: => " + track);
            }
        }

    }

}




