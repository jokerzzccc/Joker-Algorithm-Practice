package com.joker.algorithm.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 寻找重复的子树
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode652 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);

        Solution01 solution01 = new Solution01();
        List<TreeNode> duplicateSubtrees01 = solution01.findDuplicateSubtrees(root);
        System.out.println(duplicateSubtrees01);

    }

    /**
     * 解法一：二叉树序列化 + 后序遍历
     */
    private static class Solution01 {

        /**
         * 记录所有子树以及出现的次数
         */
        HashMap<String, Integer> memo = new HashMap<>();
        /**
         * 记录重复的子树根节点
         */
        LinkedList<TreeNode> res = new LinkedList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return res;
        }

        private String traverse(TreeNode root) {
            // 对于空节点，可以用一个特殊字符表示
            if (root == null) {
                return "#";
            }

            // 将左右子树序列化成字符串
            String left = traverse(root.left);
            String right = traverse(root.right);
            /* 后序遍历代码位置 */
            // 左右子树加上自己，就是以自己为根的二叉树序列化结果
            String subTree = left + "," + right + "," + root.val;

            // 子树出现的次数
            int freq = memo.getOrDefault(subTree, 0);
            // 多次重复也只会被加入结果集一次
            // 即第一次发现重复的时候，才加进结果集，后面再发现重复的，就不用管了。
            if (freq == 1) {
                res.add(root);
            }
            // 给子树对应的出现次数加一
            memo.put(subTree, freq + 1);

            return subTree;
        }

    }

}
