package com.joker.algorithm.dp;

import com.joker.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 打家劫舍 III
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode337 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        Solution01 solution01 = new Solution01();
        int rob01 = solution01.rob(root);
        System.out.println(rob01);

        Solution02 solution02 = new Solution02();
        int rob02 = solution02.rob(root);
        System.out.println(rob02);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        // 备忘录
        // K: 从节点 node 开始，V: 能够抢到的最多钱
        Map<TreeNode, Integer> memo = new HashMap<>();

        public int rob(TreeNode root) {
            if (root == null) return 0;
            if (memo.containsKey(root)) {
                return memo.get(root);
            }
            // 抢，然后去下下家
            int rob = root.val
                    + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                    + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
            // 不抢，然后去下家
            int notRob = rob(root.left) + rob(root.right);

            int res = Math.max(rob, notRob);
            memo.put(root, res);
            return res;
        }

    }

    /**
     * 解法二：动态规划（更优）
     * 时间复杂度 O(N)，空间复杂度只有递归函数堆栈所需的空间，不需要备忘录的额外空间。
     */
    private static class Solution02 {

        public int rob(TreeNode root) {
            int[] res = dp(root);
            return Math.max(res[0], res[1]);

        }

        /**
         * 返回一个大小为 2 的数组 arr
         * arr[0] 表示不抢 root 的话，得到的最大钱数
         * arr[1] 表示抢 root 的话，得到的最大钱数
         */
        int[] dp(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] left = dp(root.left);
            int[] right = dp(root.right);
            // 抢，下家就不能抢了
            int rob = root.val + left[0] + right[0];
            // 不抢，下家可抢可不抢，取决于收益大小
            int notRob = Math.max(left[0], left[1])
                    + Math.max(right[0], right[1]);

            return new int[]{notRob, rob};
        }

    }

}
