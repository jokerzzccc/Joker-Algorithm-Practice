package com.joker.algorithm.binarytree;

/**
 * <p>
 * 删除二叉搜索树中的节点
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode450 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.deleteNode(root, 3);
        System.out.println(treeNode01);

    }

    /**
     * 解法一： 递归遍历
     * 找到目标节点了，比方说是节点 `A`，如何删除这个节点，这是难点。因为删除节点的同时不能破坏 BST 的性质。有三种情况，用图片来说明。
     * <p>
     * **情况 1**：`A` 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
     * <p>
     * **情况 2**：`A` 只有一个非空子节点，那么它要让这个孩子接替自己的位置。
     * <p>
     * **情况 3**：`A` 有两个子节点，麻烦了，为了不破坏 BST 的性质，`A` 必须找到左子树中最大的那个节点，或者**右子树中最小的那个节点来接替自己**。我们以第二种方式讲解。
     */
    private static class Solution01 {

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;

            if (root.val == key) {
                // 这两个 if 把情况 1 和 2 都正确处理了
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;

                // 处理情况 3
                // 获得右子树最小的节点
                TreeNode minNode = getMin(root.right);
                // 删除右子树最小的节点
                root.right = deleteNode(root.right, minNode.val);
                // 用右子树最小的节点替换 root 节点
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }

            return root;
        }

        TreeNode getMin(TreeNode node) {
            // BST 最左边的就是最小的
            while (node.left != null) node = node.left;
            return node;
        }

    }

}
