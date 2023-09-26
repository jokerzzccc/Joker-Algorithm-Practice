# 目录

[toc]

# leetcode-236-二叉树的最近公共祖先

- 时间：2023-06-22
- 参考链接：
  - [一文秒杀 5 道最近公共祖先问题](https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
- 难度：中等



给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”



提示：

- 树中节点数目在范围 [2, 10^5] 内。
- -10^9 <= Node.val <= 10^9
- 所有 Node.val 互不相同 。
- p != q
- p 和 q 均存在于给定的二叉树中。



# 2、题解

## 题目分析



## 解法一: 

### 算法分析



### 代码

```java


/**
 * <p>
 * 二叉树的最近公共祖先
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode236 {

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

        System.out.println(new Solution01().lowestCommonAncestor(root, root.left, root.right).val);
    }

    /**
     * 解法一: 递归
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return find(root, p.val, q.val);
        }

        /**
         * 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, int val1, int val2) {
            if (root == null) return null;

            // 前序位置
            if (root.val == val1 || root.val == val2) {
                // 如果遇到目标值，直接返回
                return root;
            }
            TreeNode left = find(root.left, val1, val2);
            TreeNode right = find(root.right, val1, val2);
            // 后序位置，已经知道左右子树是否存在目标值
            if (left != null && right != null) {
                // 当前节点是 LCA 节点
                return root;
            }

            return left != null ? left : right;
        }

    }

}


```

输入如下：

```java
3
```





### 复杂度分析

![image-20230622181258889](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622181258889.png)



# THE END