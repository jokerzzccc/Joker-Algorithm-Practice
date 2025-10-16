package com.joker.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 子集
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/15
 */
public class leetcode78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets;

        // 法一
       Solution solution = new Solution();
       subsets = solution.subsets(nums);

        // 法二
        // Solution02 solution02 = new Solution02();
        // subsets = solution02.subsets(nums);

        System.out.println(subsets);

    }

    static class Solution {

        private List<List<Integer>> res = new ArrayList<>();

        /**
         * 用 track 记录根节点到每个节点的路径的值
         */
        private LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backTrack(nums, 0);
            return res;
        }

        /**
         * @param start start 参数控制树枝的生长避免产生重复的子集
         */
        void backTrack(int[] nums, int start) {
            res.add(new ArrayList<>(track));

            for (int i = start; i < nums.length; i++) {
                track.add(nums[i]);

                backTrack(nums, i + 1);

                track.removeLast();
            }
        }

    }

    static class Solution02 {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();

            for (int i = 0; i < (1 << nums.length); i++) {
                List<Integer> sub = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    if (((i >> j) & 1) == 1) {
                        sub.add(nums[j]);
                    }
                }
                res.add(sub);
            }
            return res;
        }

    }

}
