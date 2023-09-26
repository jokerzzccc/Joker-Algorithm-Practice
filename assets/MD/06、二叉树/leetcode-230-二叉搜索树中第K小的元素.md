# 目录

[toc]

# leetcode-230-二叉搜索树中第K小的元素

- 时间：2023-06-21
- 参考链接：
  - [东哥带你刷二叉搜索树（特性篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-8f403/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
- 难度：中等

给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 个最小元素（从 1 开始计数）。

**提示：**

+ 树中的节点数为 `n` 。
+ `1 <= k <= n <= 104`
+ `0 <= Node.val <= 104`



# 2、题解

## 题目分析





## 解法一: *BST* *的中序遍历特性*

### 算法分析

BST 的特性大家应该都很熟悉了：

1、对于 BST 的每一个节点 `node`，左子树节点的值都比 `node` 的值要小，右子树节点的值都比 `node` 的值大。

2、对于 BST 的每一个节点 `node`，它的左侧子树和右侧子树都是 BST。



**从做算法题的角度来看 BST，除了它的定义，还有一个重要的性质：BST 的中序遍历结果是有序的（升序）**。



### 代码

```java
package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树中第K小的元素
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode230 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(new Solution01().kthSmallest(root, 2));

    }

    /**
     * 解法一：BST 的中序遍历特性
     */
    private static class Solution01 {

        // 记录结果
        int res = 0;
        // 记录当前元素的排名
        int rank = 0;

        public int kthSmallest(TreeNode root, int k) {
            // 利用 BST 的中序遍历特性
            traverse(root, k);
            return res;

        }

        void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }

            traverse(root.left, k);
            /* 中序遍历代码位置 */
            rank++;
            if (k == rank) {
                // 找到第 k 小的元素
                res = root.val;
                return;
            }
            /*****************/
            traverse(root.right, k);
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O()$
- 空间复杂度：$O()$





# THE END