# 目录

[toc]

# leetcode-1650-二叉树的最近公共祖先 III

- 时间：2023-06-22
- 参考链接：
  - [一文秒杀 5 道最近公共祖先问题](https://mp.weixin.qq.com/s/njl6nuid0aalZdH5tuDpqQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iii/
- 难度：中等



给定一棵二叉树中的两个节点 p 和 q，返回它们的最近公共祖先节点（LCA）。

每个节点都包含其父节点的引用（指针）。Node 的定义如下：

```sh
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
```

根据维基百科中对最近公共祖先节点的定义：“两个节点 p 和 q 在二叉树 T 中的最近公共祖先节点是后代节点中既包括 p 又包括 q 的最深节点（我们允许一个节点为自身的一个后代节点）”。一个节点 x 的后代节点是节点 x 到某一叶节点间的路径中的节点 y。





# 2、题解

## 题目分析



## 解法一: 

### 算法分析

- 类比 leetcode236 的区别就是，多了指向父节点的引用指针

这次输入的二叉树节点比较特殊，包含指向父节点的指针：

```
class Node {
    int val;
    Node left;
    Node right;
    Node parent;
};
```

给你输入一棵存在于二叉树中的两个节点`p`和`q`，请你返回它们的最近公共祖先，函数签名如下：

```
Node lowestCommonAncestor(Node p, Node q);
```

由于节点中包含父节点的指针，所以二叉树的根节点就没必要输入了。

**这道题其实不是公共祖先的问题，而是单链表相交的问题**，你把`parent`指针想象成单链表的`next`指针，题目就变成了：

给你输入两个单链表的头结点`p`和`q`，这两个单链表必然会相交，请你返回相交点。

我在前文 [单链表的六大解题套路](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247492022&idx=1&sn=35f6cb8ab60794f8f52338fab3e5cda5&scene=21#wechat_redirect) 中详细讲解过求链表交点的问题，具体思路在本文就不展开了，直接给出本题的解法代码：

### 代码

```java

/**
 * <p>
 * 二叉树的最近公共祖先 III
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode1650 {

    public static void main(String[] args) {

    }

    /**
     * 解法一：
     * 这道题其实不是公共祖先的问题，而是单链表相交的问题
     */
    private static class Solution01 {

        public Node lowestCommonAncestor(Node p, Node q) {
            // 链表双指针技巧
            Node a = p, b = q;
            while (a != b) {
                // a 走一步，如果走到根节点，转到 q 节点
                if (a == null) {
                    a = q;
                } else {
                    a = a.parent;
                }

                // b 走一步，如果走到根节点，转到 p 节点
                if (b == null) {
                    b = p;
                } else {
                    b = b.parent;
                }
            }
            return a;
        }

    }

    class Node {

        public int val;
        public Node left;
        public Node right;
        public Node parent;

    }

    ;

}

```

输入如下：

```java

```





### 复杂度分析





# THE END