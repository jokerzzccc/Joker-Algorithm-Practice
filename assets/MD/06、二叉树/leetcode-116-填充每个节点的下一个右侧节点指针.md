# 目录

[toc]

# leetcode-116-填充每个节点的下一个右侧节点指针

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（思路篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-cbce8/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
- 难度：中等



给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。



**提示：**

+ 树中节点的数量在 `[0, 212 - 1]` 范围内
+ `-1000 <= node.val <= 1000`



# 2、题解

## 题目分析



## 解法一

### 算法分析

**1、这题能不能用「遍历」的思维模式解决**？

很显然，一定可以。

每个节点要做的事也很简单，把自己的 `next` 指针指向右侧节点就行了。

**传统的 `traverse` 函数是遍历二叉树的所有节点，但现在我们想遍历的其实是两个相邻节点之间的「空隙」**。

所以我们可以在二叉树的基础上进行抽象，你把图中的每一个方框看做一个节点：



![image-20230620221939617](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230620221939617.png)

**这样，一棵二叉树被抽象成了一棵三叉树，三叉树上的每个节点就是原先二叉树的两个相邻节点**。

现在，我们只要实现一个 `traverse` 函数来遍历这棵三叉树，每个「三叉树节点」需要做的事就是把自己内部的两个二叉树节点穿起来：



这样，`traverse` 函数遍历整棵「三叉树」，将所有相邻节的二叉树节点都连接起来，也就避免了我们之前出现的问题，把这道题完美解决。

**2、这题能不能用「分解问题」的思维模式解决**？

嗯，好像没有什么特别好的思路，所以这道题无法使用「分解问题」的思维来解决。

### 代码

```java

/**
 * <p>
 * 填充每个节点的下一个右侧节点指针
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode116 {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Solution01 solution01 = new Solution01();
        Node connected = solution01.connect(root);
        System.out.println(connected);

    }

    /**
     * 解法一：遍历思维
     */
    private static class Solution01 {

        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            // 遍历「三叉树」，连接相邻节点
            traverse(root.left, root.right);
            return root;
        }

        // 三叉树遍历框架
        private void traverse(Node left, Node right) {
            if (left == null && right == null) {
                return;
            }

            /**** 前序位置 ****/
            // 将传入的两个节点穿起来
            left.next = right;
            // 连接相同父节点的两个子节点
            traverse(left.left, left.right);
            traverse(right.left, right.right);
            // 连接跨越父节点的两个子节点
            traverse(left.right, right.left);
        }

    }

}

```





### 复杂度分析











# THE END