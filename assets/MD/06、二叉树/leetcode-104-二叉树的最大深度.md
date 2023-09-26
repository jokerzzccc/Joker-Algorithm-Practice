# 目录

[toc]

# leetcode-104-二叉树的最大深度

- 时间：2023-06-18
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-depth-of-binary-tree/
- 难度：简单



给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:** 叶子节点是指没有子节点的节点。



# 2、题解

## 题目分析



## 解法一

### 算法分析





### 代码

```java

/**
 * <p>
 * 二叉树的最大深度
 * </p>
 *
 * @author admin
 * @date 2023/6/18
 */
public class leetcode104 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        int maxDepth01 = solution01.maxDepth(root);
        System.out.println(maxDepth01);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        // 记录最大深度
        int maxDepth = 0;
        // 记录遍历到的节点的深度
        int depth = 0;

        public int maxDepth(TreeNode root) {
            traverse(root);
            return maxDepth;

        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            depth++;
            if (root.left == null && root.right == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
            traverse(root.left);
            traverse(root.right);
            // 后序位置
            depth--;
        }

    }



}

```





### 复杂度分析





## 解法二

### 算法分析





### 代码

```java

/**
 * <p>
 * 二叉树的最大深度
 * </p>
 *
 * @author admin
 * @date 2023/6/18
 */
public class leetcode104 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution02 solution02 = new Solution02();
        int maxDepth02 = solution02.maxDepth(root);
        System.out.println(maxDepth02);

    }



    /**
     * 解法二：DFS
     */
    private static class Solution02 {

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;

        }

    }

}

```



### 复杂度分析

- 时间复杂度：O(n),其中n为二叉树节点的个数。每个节点在递归中只被遍历一次。
- 空间复杂度：O(height),其中height表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。



# THE END