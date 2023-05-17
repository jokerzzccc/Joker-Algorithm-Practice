package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 全排列 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/17
 */
public class leetcode47 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res;

        Solution solution = new Solution();
        res = solution.permuteUnique(nums);

        System.out.println(res);
    }

    private static class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> track = new LinkedList<>();
        private boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums.length == 0) {
                return res;
            }
            // 先排序，让相同的元素靠在一起
            Arrays.sort(nums);

            used = new boolean[nums.length];

            backtrack(nums);
            return res;
        }

        void backtrack(int[] nums) {
            if (track.size() == nums.length) {
                res.add(new ArrayList<>(track));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i] == true) {
                    continue;
                }
                // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                track.addLast(nums[i]);
                used[i] = true;
                System.out.println("递归之前 track: => " + track + " ; 下标：i = " + i);

                backtrack(nums);

                track.removeLast();
                used[i] = false;
                System.out.println("递归之后 track: => " + track + "后--------------");
            }
        }
    }

}
