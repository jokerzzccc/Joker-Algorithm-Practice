# 目录

[toc]

# leetcode-235-二叉搜索树的最近公共祖先

- 时间：2023-06-22
- 参考链接：
  - [一文秒杀 5 道最近公共祖先问题](https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
- 难度：中等



给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”



**说明:**

+ 所有节点的值都是唯一的。
+ p、q 为不同节点且均存在于给定的二叉搜索树中。





# 2、题解

## 题目分析



## 解法一: 

### 算法分析

- 类比 leetcode236 的区别就是，这个是 BST，左小右大的特性。



把leetcode236 的解法代码复制过来肯定也可以解决这道题，但没有用到 BST「左小右大」的性质，显然效率不是最高的。

在标准的最近公共祖先问题中，我们要在后序位置通过左右子树的搜索结果来判断当前节点是不是`LCA`：

```
TreeNode left = find(root.left, val1, val2);
TreeNode right = find(root.right, val1, val2);

// 后序位置，判断当前节点是不是 LCA 节点
if (left != null && right != null) {
    return root;
}
```

**但对于 BST 来说，根本不需要老老实实去遍历子树，由于 BST 左小右大的性质，将当前节点的值与`val1`和`val2`作对比即可判断当前节点是不是`LCA`**：

假设`val1 < val2`，那么`val1 <= root.val <= val2`则说明当前节点就是`LCA`；若`root.val`比`val1`还小，则需要去值更大的右子树寻找`LCA`；若`root.val`比`val2`还大，则需要去值更小的左子树寻找`LCA`。



### 代码

```java

/**
 * <p>
 * 二叉搜索树的最近公共祖先
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode235 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        System.out.println(new Solution01().lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val);
    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 保证 val1 较小，val2 较大
            int val1 = Math.min(p.val, q.val);
            int val2 = Math.max(p.val, q.val);
            return find(root, val1, val2);
        }

        /**
         * 在 BST 中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) {
                return null;
            }
            if (root.val > val2) {
                // 当前节点太大，去左子树找
                return find(root.left, val1, val2);
            }
            if (root.val < val1) {
                // 当前节点太小，去右子树找
                return find(root.right, val1, val2);
            }
            // val1 <= root.val <= val2
            // 则当前节点就是最近公共祖先
            return root;
        }

    }

}

```

输入如下：

```java
6
```



### 复杂度分析





# THE END