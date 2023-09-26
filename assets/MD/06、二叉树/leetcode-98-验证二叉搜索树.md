# 目录

[toc]

# leetcode-98-验证二叉搜索树

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（基操篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-7b3e4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/validate-binary-search-tree/
- 难度：中等

+ 





# 2、题解

## 题目分析





## 解法一: *BST* 递归

### 算法分析

对于某一个节点 `root`，他只能管得了自己的左右子节点，怎么把 `root` 的约束传递给左右子树呢？

我们通过使用辅助函数，增加函数参数列表，在参数中携带额外信息，将这种约束传递给子树的所有节点，这也是二叉树算法的一个小技巧吧。

### 代码

```java

/**
 * <p>
 * 验证二叉搜索树
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode98 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isValidBST(root));
    }

    /**
     * 解法一：BST 递归
     */
    private static class Solution01 {

        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
        boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            // base case
            if (root == null) return true;
            // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
            if (min != null && root.val <= min.val) return false;
            if (max != null && root.val >= max.val) return false;
            // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left, min, root)
                    && isValidBST(root.right, root, max);
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n)$





# THE END