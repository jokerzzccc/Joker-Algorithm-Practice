package com.joker.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 带因子的二叉树
 * </p>
 *
 * @author admin
 * @date 2023/8/30
 */
public class leetcode823 {

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 10};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numFactoredBinaryTrees(arr));

    }

    /**
     * 解法一：动态规划 + 记忆化搜索
     */
    private static class Solution01 {

        private final long MOD = (long) (Math.pow(10, 9) + 7);

        /**
         * 入参
         */
        private int[] arrIn;

        /**
         * 存储值对应的下标
         */
        Map<Integer, Integer> valueToIdx;

        /**
         * 备忘录
         */
        private long[] memo;

        public int numFactoredBinaryTrees(int[] arr) {
            this.arrIn = arr;
            Arrays.sort(arrIn);
            int n = arrIn.length;

            valueToIdx = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                valueToIdx.put(arrIn[i], i);
            }

            // 备忘录初始化，表示没有计算到的
            memo = new long[n];
            Arrays.fill(memo, -1);

            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += dfs(i);
            }

            return (int) (ans % MOD);
        }

        /**
         * dfs(index) 表示根节点值为 arr[index] 的二叉树的个数
         */
        private long dfs(int index) {
            if (memo[index] != -1) {
                return memo[index];
            }
            int val = arrIn[index];
            long res = 1;
            // val 的因子一定比 val 小，因为之前 arr 排过序
            for (int j = 0; j < index; j++) {
                int x = arrIn[j];
                // x 为 val 的因子，且另一个因子 val/x 必须在 arr 中
                if (val % x == 0 && valueToIdx.containsKey(val / x)) {
                    res += dfs(j) * dfs(valueToIdx.get(val / x));
                }
            }

            // 记忆化
            memo[index] = res;

            return res;
        }

    }

}
