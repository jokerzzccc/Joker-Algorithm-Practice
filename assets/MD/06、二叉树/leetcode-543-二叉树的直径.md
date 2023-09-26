# 目录

[toc]

# leetcode-543-二叉树的直径

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（纲领篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-334dd/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/diameter-of-binary-tree/
- 难度：简单



给你一棵二叉树的根节点，返回该树的 **直径** 。

二叉树的 **直径** 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 **长度** 由它们之间边数表示。



**提示：**

+ 树中节点数目在范围 `[1, 104]` 内
+ `-100 <= Node.val <= 100`





# 2、题解

## 题目分析

解决这题的关键在于，**每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和**。



## 解法一: 前序位置

### 算法分析

现在让我求整棵树中的最长「直径」，那直截了当的思路就是遍历整棵树中的每个节点，然后通过每个节点的左右子树的最大深度算出每个节点的「直径」，最后把所有「直径」求个最大值即可。



这个解法是正确的，但是运行时间很长，原因也很明显，`traverse` 遍历每个节点的时候还会调用递归函数 `maxDepth`，而 `maxDepth` 是要遍历子树的所有节点的，所以最坏时间复杂度是 O(N^2)。

这就出现了刚才探讨的情况，**前序位置无法获取子树信息，所以只能让每个节点调用 `maxDepth` 函数去算子树的深度**。

### 代码

```java

/**
 * <p>
 * 二叉树的直径
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode543 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution01 solution01 = new Solution01();
        int diameter01 = solution01.diameterOfBinaryTree(root);
        System.out.println(diameter01);

    }

    /**
     * 解法一：
     * 直截了当的思路就是遍历整棵树中的每个节点，
     * 然后通过每个节点的左右子树的最大深度算出每个节点的「直径」，最后把所有「直径」求个最大值即可
     */
    private static class Solution01 {

        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // 对每个节点计算直径，求最大直径
            traverse(root);
            return maxDiameter;
        }

        /**
         * 遍历二叉树
         */
        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 对每个节点计算直径
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            int curDiameter = leftMax + rightMax;
            // 更新全局最大直径
            maxDiameter = Math.max(maxDiameter, curDiameter);

            traverse(root.left);
            traverse(root.right);
        }

        /**
         * 计算二叉树的最大深度
         */
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            return 1 + Math.max(leftMax, rightMax);
        }

    }

}

```



输出：

```sh
3
```



### 复杂度分析

- 时间复杂度：$O(N^2)$
- 空间复杂度：



## 解法二: 后序位置

### 算法分析

那如何优化？我们应该把计算「直径」的逻辑放在后序位置，准确说应该是放在 `maxDepth` 的后序位置，因为 `maxDepth` 的后序位置是知道左右子树的最大深度的.



这下时间复杂度只有 `maxDepth` 函数的 O(N) 了。

讲到这里，照应一下前文：遇到子树问题，首先想到的是给函数设置返回值，然后在后序位置做文章。



### 代码

```java

/**
 * <p>
 * 二叉树的直径
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode543 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution02 solution02 = new Solution02();
        int diameter02 = solution02.diameterOfBinaryTree(root);
        System.out.println(diameter02);

    }

    /**
     * 解法一：后序位置 (更优)
     * 我们应该把计算「直径」的逻辑放在后序位置，
     * 准确说应该是放在 `maxDepth` 的后序位置，因为 `maxDepth` 的后序位置是知道左右子树的最大深度的
     */
    private static class Solution02 {

        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // 对每个节点计算直径，求最大直径
            maxDepth(root);
            return maxDiameter;
        }

        /**
         * 计算二叉树的最大深度
         */
        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            int curDiameter = leftMax + rightMax;
            maxDiameter = Math.max(maxDiameter, curDiameter);

            return 1 + Math.max(leftMax, rightMax);
        }

    }

}

```



输出：

```sh
3
```



### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：



# THE END

