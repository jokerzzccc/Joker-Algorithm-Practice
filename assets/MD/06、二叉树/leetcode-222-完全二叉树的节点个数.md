# 目录

[toc]

# leetcode-222-完全二叉树的节点个数

- 时间：2023-06-22
- 参考链接：
  - [如何计算完全二叉树的节点数](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/ru-he-ji-s-2320e/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/count-complete-tree-nodes/
- 难度：中等

给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。

完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^h 个节点。



**提示：**

+ 树中节点的数目范围是`[0, 5 * 10^4]`
+ `0 <= Node.val <= 5 * 10^4`
+ 题目数据保证输入的树是 **完全二叉树**



# 2、题解

## 题目分析



## 解法一: 

### 算法分析

现在回归正题，如何求一棵完全二叉树的节点个数呢？

```java
// 输入一棵完全二叉树，返回节点总数
int countNodes(TreeNode root);
```

如果是一个**普通**二叉树，显然只要向下面这样遍历一边即可，时间复杂度 O(N)：

```java
public int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

那如果是一棵**满**二叉树，节点总数就和树的高度呈指数关系：

```java
public int countNodes(TreeNode root) {
    int h = 0;
    // 计算树的高度
    while (root != null) {
        root = root.left;
        h++;
    }
    // 节点总数就是 2^h - 1
    return (int)Math.pow(2, h) - 1;
}
```

**完全**二叉树比普通二叉树特殊，但又没有满二叉树那么特殊，计算它的节点总数，可以说是普通二叉树和完全二叉树的结合版

### 代码

```java


/**
 * <p>
 * 完全二叉树的节点个数
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode222 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(new Solution01().countNodes(root));

    }

    /**
     * 解法一：递归
     */
    private static class Solution01 {

        public int countNodes(TreeNode root) {
            TreeNode l = root, r = root;
            // 沿最左侧和最右侧分别计算高度
            int hl = 0, hr = 0;
            while (l != null) {
                l = l.left;
                hl++;
            }
            while (r != null) {
                r = r.right;
                hr++;
            }
            // 如果左右侧计算的高度相同，则是一棵满二叉树（完全二叉树）
            if (hl == hr) {
                return (int) Math.pow(2, hl) - 1;
            }
            // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

    }

}

```

输入如下：

```java
6
```





### 复杂度分析

- 时间复杂度：$O(logn * logn)$
- 空间复杂度：

开头说了，这个算法的时间复杂度是 O(logN*logN)，这是怎么算出来的呢？

直觉感觉好像最坏情况下是 O(N*logN) 吧，因为之前的 while 需要 logN 的时间，最后要 O(N) 的时间向左右子树递归：

```java
return 1 + countNodes(root.left) + countNodes(root.right);
```

**关键点在于，这两个递归只有一个会真的递归下去，另一个一定会触发 `hl == hr` 而立即返回，不会递归下去**。

为什么呢？原因如下：

**一棵完全二叉树的两棵子树，至少有一棵是满二叉树**：

![image-20230622190932339](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622190932339.png)

看图就明显了吧，由于完全二叉树的性质，其子树一定有一棵是满的，所以一定会触发 `hl == hr`，只消耗 O(logN) 的复杂度而不会继续递归。

综上，算法的递归深度就是树的高度 O(logN)，每次递归所花费的时间就是 while 循环，需要 O(logN)，所以总体的时间复杂度是 O(logN*logN)。

所以说，「完全二叉树」这个概念还是有它存在的原因的，不仅适用于数组实现二叉堆，而且连计算节点总数这种看起来简单的操作都有高效的算法实现。



# THE END