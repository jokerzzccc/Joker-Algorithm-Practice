# 目录

[toc]

# leetcode-701-二叉搜索树中的插入操作

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（基操篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-7b3e4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/insert-into-a-binary-search-tree/
- 难度：中等



给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。



提示：

- 树中的节点数将在 [0, 104]的范围内。
- -10^8 <= Node.val <= 10^8
- 所有值 Node.val 是 **独一无二** 的。
- -10^8 <= val <= 10^8
- 保证 val 在原始BST中不存在。





# 2、题解

## 题目分析



## 解法一: *BST* 递归

### 算法分析

对数据结构的操作无非遍历 + 访问，遍历就是「找」，访问就是「改」。具体到这个问题，插入一个数，就是先找到插入位置，然后进行插入操作。

上一个问题，我们总结了 BST 中的遍历框架，就是「找」的问题。直接套框架，加上「改」的操作即可。**一旦涉及「改」，就类似二叉树的构造问题，函数要返回 `TreeNode` 类型，并且要对递归调用的返回值进行接收**。

### 代码

```java

/**
 * <p>
 * 二叉搜索树中的插入操作
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode701 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        TreeNode result01 = solution01.insertIntoBST(root, 5);
        System.out.println(result01);

    }

    /**
     * 解法一：遍历递归
     */
    private static class Solution01 {

        public TreeNode insertIntoBST(TreeNode root, int val) {
            // 找到空位置插入新节点
            if (root == null) {
                return new TreeNode(val);
            }
            if (val < root.val) {
                root.left = insertIntoBST(root.left, val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }

            return root;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(1)$





# THE END