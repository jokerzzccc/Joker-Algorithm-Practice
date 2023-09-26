# 目录

[toc]

# leetcode-1644-二叉树的最近公共祖先 II

- 时间：2023-06-22
- 参考链接：
  - [一文秒杀 5 道最近公共祖先问题](https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-ii/
- 难度：中等

给定一棵二叉树的根节点 root，返回给定节点 p 和 q 的最近公共祖先（LCA）节点。如果 p 或 q 之一 **不存在** 于该二叉树中，返回 null。树中的每个节点值都是互不相同的。

根据维基百科中对最近公共祖先节点的定义：“两个节点 p 和 q 在二叉树 T 中的最近公共祖先节点是 后代节点 中既包括 p 又包括 q 的最深节点（我们允许 一个节点为自身的一个后代节点 ）”。一个节点 x 的 后代节点 是节点 x 到某一叶节点间的路径中的节点 y。



**提示:**

+ 树中节点个数的范围是 `[1, 10^4]`
+ `-10^9 <= Node.val <= 10^9`
+ 所有节点的值 `Node.val` **互不相同**
+ `p != q`



**进阶：** 在不检查节点是否存在的情况下，你可以遍历树找出最近公共祖先节点吗



# 2、题解

## 题目分析



## 解法一: 

### 算法分析

- 类比 leetcode236 的区别就是，p,q 有可能不存在

对于这道题来说，`p`和`q`不一定存在于树中，所以你不能遇到一个目标值就直接返回，而应该对二叉树进行**完全搜索**（遍历每一个节点），如果发现`p`或`q`不存在于树中，那么是不存在`LCA`的。



对二叉树进行完全搜索，同时记录`p`和`q`是否同时存在树中，从而满足题目的要求。

### 代码

```java

/**
 * <p>
 * 二叉树的最近公共祖先 II
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1644 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        System.out.println(new Solution01().lowestCommonAncestor(root, root.left, new TreeNode(10)));

    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        // 用于记录 p 和 q 是否存在于二叉树中
        boolean foundP = false;
        boolean foundQ = false;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res = find(root, p.val, q.val);
            if (!foundP || !foundQ) {
                return null;
            }
            // p 和 q 都存在二叉树中，才有公共祖先
            return res;
        }

        /**
         * 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) {
                return null;
            }
            TreeNode left = find(root.left, val1, val2);
            TreeNode right = find(root.right, val1, val2);

            // 后序位置，判断当前节点是不是 LCA 节点
            if (left != null && right != null) {
                return root;
            }

            // 后序位置，判断当前节点是不是目标值
            if (root.val == val1 || root.val == val2) {
                // 找到了，记录一下
                if (root.val == val1) foundP = true;
                if (root.val == val2) foundQ = true;
                return root;
            }

            return left != null ? left : right;
        }

    }

}

```

输入如下：

```java
null
```





### 复杂度分析





# THE END
