package com.joker.algorithm;

import com.joker.algorithm.binarytree.TreeNode;

/**
 * <p>
 * 树的子结构
 * </p>
 *
 * @author admin
 * @date 2023/7/4
 */
public class offer26 {

    public static void main(String[] args) {
        TreeNode A = new TreeNode(3);
        A.left = new TreeNode(4);
        A.right = new TreeNode(5);
        A.left.left = new TreeNode(1);
        A.left.right = new TreeNode(2);

        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isSubStructure(A, B));
    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (B == null || A == null) {
                return false;
            }

            // 三个判断，
            // 1. B 是以A为根节点的子结构
            // 2. B 是 A 左子树的子结构
            // 3. B 是 A 右子树的子结构
            return dfs(A, B)
                    || isSubStructure(A.left, B)
                    || isSubStructure(A.right, B);
        }

        public boolean dfs(TreeNode A, TreeNode B) {
            // B 已经遍历完，越过了叶节点
            if (B == null) {
                return true;
            }
            // A 已经遍历完，越过了叶结点，找不到匹配的
            // 或，值不匹配
            if (A == null || A.val != B.val) {
                return false;
            }
            // 判断A,B的左右节点是否相等
            return dfs(A.left, B.left) && dfs(A.right, B.right);
        }

    }

}
