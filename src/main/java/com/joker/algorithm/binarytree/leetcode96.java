package com.joker.algorithm.binarytree;

/**
 * <p>
 * 不同的二叉搜索树
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode96 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        int numTrees01 = solution01.numTrees(n);
        System.out.println(numTrees01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        // 备忘录
        int[][] memo;

        public int numTrees(int n) {
            // 备忘录的值初始化为 0
            memo = new int[n + 1][n + 1];
            return count(1, n);
        }

        /**
         * 计算闭区间 [low, high] 组成的 BST 个数
         */
        private int count(int low, int high) {
            // base case
            // 虽然是空节点，但是也是一种情况，所以要返回 1 而不能返回 0。
            if (low > high) {
                return 1;
            }

            // 查备忘录
            if (memo[low][high] != 0) {
                return memo[low][high];
            }

            int res = 0;
            for (int i = low; i <= high; i++) {
                // i 的值作为根节点 root
                int left = count(low, i - 1);
                int right = count(i + 1, high);
                // 左右子树的组合数乘积是 BST 的总数
                res += left * right;
            }

            // 将结果存入备忘录
            memo[low][high] = res;

            return res;
        }

    }

}
