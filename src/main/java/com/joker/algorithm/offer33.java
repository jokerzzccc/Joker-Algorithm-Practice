package com.joker.algorithm;

/**
 * <p>
 * 二叉搜索树的后序遍历序列
 * </p>
 *
 * @author admin
 * @date 2023/7/3
 */
public class offer33 {

    public static void main(String[] args) {
        int[] postorder01 = {1, 6, 3, 2, 5};
        int[] postorder02 = {1, 3, 2, 6, 5};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.verifyPostorder(postorder01));
        System.out.println(solution01.verifyPostorder(postorder02));

    }

    /**
     * 解法一：递归 + 分治
     */
    private static class Solution01 {

        public boolean verifyPostorder(int[] postorder) {
            return recur(postorder, 0, postorder.length - 1);
        }

        /**
         * @param postorder
         * @param end 当前子树的根结点索引（因为是后序遍历(left + right + root)）
         */
        boolean recur(int[] postorder, int start, int end) {
            // base case, 到了叶结点
            if (start >= end) {
                // 说明此子树结点数 <= 1
                return true;
            }

            // 从左往右寻找第一个大于根节点的结点，索引记为 m
            int p = start;
            while (postorder[p] < postorder[end]) p++;
            int m = p;

            // 判断当前的右子树是否符合二叉搜索树的特性，即都大于 根节点
            while (postorder[p] > postorder[end]) p++;

            // 递归判断：
            // 1. 判断当前树是否为二叉搜索树
            // 2. 判断左子树是否为二叉搜索树
            // 3. 判断右子树是否为二叉搜索树
            return p == end
                    && recur(postorder, start, m - 1)
                    && recur(postorder, m, end - 1);

        }

    }

}
