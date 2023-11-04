package com.joker.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组中两个数的最大异或值
 *
 * @author jokerzzccc
 * @date 2023/11/4
 */
public class leetcode421 {

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findMaximumXOR(nums));

    }

    /**
     * 解法一：哈希表法
     */
    private static class Solution01 {

        /**
         * 最高位的二进制位编号为 30:
         * 因为最多有31为数，[0,30]
         */
        private static final int HIGH_BIT = 30;

        public int findMaximumXOR(int[] nums) {
            // 返回值；
            int x = 0;

            // 判断 x 的某一位是否能取到 1
            for (int k = HIGH_BIT; k >= 0; k--) {
                Set<Integer> seen = new HashSet<>();
                for (int num : nums) {
                    seen.add(num >> k);
                }

                // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
                // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
                int xNext = x * 2 + 1;
                boolean found = false;

                for (int num : nums) {
                    if (seen.contains(xNext ^ (num >> k))) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    x = xNext;
                } else {
                    x = xNext - 1;
                }
            }

            return x;
        }

    }

}
