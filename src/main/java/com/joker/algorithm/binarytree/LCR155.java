package com.joker.algorithm.binarytree;

/**
 * <p>
 * 将二叉搜索树转化为排序的双链表
 * </p>
 *
 * @author: chenbolin
 * @date:  2023/10/8
 **/
public class LCR155 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.treeToDoublyList(root).val);
    }

    /**
     * 解法一：中序遍历
     */
    static class Solution01 {

        private TreeNode pre;
        private TreeNode head;

        public TreeNode treeToDoublyList(TreeNode root) {
            if (root == null) {
                return null;
            }

            dfs_inorder(root);
            // 构建循环链表：头尾指针相连接
            head.left = pre;
            pre.right = head;

            return head;

        }

        /**
         * 递归，中序遍历 BST；并原地构建双向链表；
         */
        private void dfs_inorder(TreeNode curr) {
            if (curr == null) {
                return;
            }

            dfs_inorder(curr.left);
            // 中序遍历的位置
            if (pre != null) {
                pre.right = curr;
            } else {
                head = curr;
            }

            curr.left = pre;
            pre = curr;

            dfs_inorder(curr.right);
        }
    }

}
