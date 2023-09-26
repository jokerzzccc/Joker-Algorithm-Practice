# 目录

[toc]

# leetcode-700-二叉搜索树中的搜索

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（基操篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-7b3e4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/search-in-a-binary-search-tree/
- 难度：简单



给定二叉搜索树（BST）的根节点 root 和一个整数值 val。

你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。



**提示：**

+ 数中节点数在 `[1, 5000]` 范围内
+ `1 <= Node.val <= 10^7`
+ `root` 是二叉搜索树
+ `1 <= val <= 10^7`





# 2、题解

## 题目分析





## 解法一: *BST* 递归

### 算法分析

对于某一个节点 `root`，他只能管得了自己的左右子节点，怎么把 `root` 的约束传递给左右子树呢？

我们通过使用辅助函数，增加函数参数列表，在参数中携带额外信息，将这种约束传递给子树的所有节点，这也是二叉树算法的一个小技巧吧。

### 代码

```java
package com.joker.algorithm.binarytree;

/**
 * <p>
 * 二叉搜索树中的搜索
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode700 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(new Solution01().searchBST(root, 2).val);

    }

    /**
     * 解法一：递归遍历，利用 BST 的左小右大的特性
     */
    private static class Solution01 {

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }

            if (root.val > val) {
                return searchBST(root.left, val);
            }

            if (root.val < val) {
                return searchBST(root.right, val);
            }

            return root;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n)$





# THE END