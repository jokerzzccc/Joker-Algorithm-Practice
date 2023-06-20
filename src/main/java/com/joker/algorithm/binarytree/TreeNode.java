package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉树节点
 * </p>
 *
 * @author admin
 * @date 2023/6/18
 */

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
