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

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
