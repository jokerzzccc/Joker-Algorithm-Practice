# 目录

[toc]

# leetcode-1676-二叉树的最近公共祖先 IV

- 时间：2023-06-22
- 参考链接：
  - [一文秒杀 5 道最近公共祖先问题](https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iv/
- 难度：中等



给定一棵二叉树的根节点 root 和 TreeNode 类对象的数组（列表） nodes，返回 nodes 中所有节点的最近公共祖先（LCA）。数组（列表）中所有节点都存在于该二叉树中，且二叉树中所有节点的值都是互不相同的。

我们扩展二叉树的最近公共祖先节点在维基百科上的定义：“对于任意合理的 i 值， n 个节点 p1 、 p2、...、 pn 在二叉树 T 中的最近公共祖先节点是后代中包含所有节点 pi 的最深节点（我们允许一个节点是其自身的后代）”。一个节点 x 的后代节点是节点 x 到某一叶节点间的路径中的节点 y。



提示:

- 树中节点个数的范围是 [1, 10^4] 。
- -10^9 <= Node.val <= 10^9
- 所有的 Node.val 都是**互不相同**的。
- 所有的 nodes[i] 都存在于该树中。
- 所有的 nodes[i] 都是互不相同的。





# 2、题解

## 题目分析



## 解法一: 

### 算法分析

- 类比 leetcode236

### 代码

```java

/**
 * <p>
 * 二叉树的最近公共祖先 IV
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1676 {

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

        System.out.println(new Solution01()
                .lowestCommonAncestor(root,
                        new TreeNode[]{new TreeNode(7)
                                , new TreeNode(6)
                                , new TreeNode(2)
                                , new TreeNode(4)}).val);

    }

    /**
     * 解法一：递归
     * 类比 leetcode236
     */
    private static class Solution01 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            // 将列表转化成哈希集合，便于判断元素是否存在
            HashSet<Integer> values = new HashSet<>();
            for (TreeNode node : nodes) {
                values.add(node.val);
            }

            return find(root, values);
        }

        /**
         * 在二叉树中寻找 values 的最近公共祖先节点
         */
        TreeNode find(TreeNode root, HashSet<Integer> values) {
            if (root == null) {
                return null;
            }

            // 前序位置
            if (values.contains(root.val)) {
                return root;
            }

            TreeNode left = find(root.left, values);
            TreeNode right = find(root.right, values);

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
5
```





### 复杂度分析





# THE END