# 目录

[toc]

# leetcode-114-二叉树展开为链表

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（思路篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-cbce8/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
- 难度：中等



给你二叉树的根结点 root ，请你将它展开为一个单链表：

- 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
- 展开后的单链表应该与二叉树 **先序遍历** 顺序相同。



**提示：**

+ 树中结点数在范围 `[0, 2000]` 内
+ `-100 <= Node.val <= 100`





# 2、题解

## 题目分析



## 解法一: 分解问题思维

### 算法分析

**1、这题能不能用「遍历」的思维模式解决**？

乍一看感觉是可以的：对整棵树进行前序遍历，一边遍历一边构造出一条「链表」就行了：

```java
// 虚拟头节点，dummy.right 就是结果
TreeNode dummy = new TreeNode(-1);
// 用来构建链表的指针
TreeNode p = dummy;

void traverse(TreeNode root) {
    if (root == null) {
        return;
    }
    // 前序位置
    p.right = new TreeNode(root.val);
    p = p.right;

    traverse(root.left);
    traverse(root.right);
}
```

但是注意 `flatten` 函数的签名，返回类型为 `void`，也就是说题目希望我们在原地把二叉树拉平成链表。

这样一来，没办法通过简单的二叉树遍历来解决这道题了。

**2、这题能不能用「分解问题」的思维模式解决**？

我们尝试给出 `flatten` 函数的定义：



```java
// 定义：输入节点 root，然后 root 为根的二叉树就会被拉平为一条链表
void flatten(TreeNode root);
```

有了这个函数定义，如何按题目要求把一棵树拉平成一条链表？

对于一个节点 `x`，可以执行以下流程：

1、先利用 `flatten(x.left)` 和 `flatten(x.right)` 将 `x` 的左右子树拉平。

2、将 `x` 的右子树接到左子树下方，然后将整个左子树作为右子树。



![image-20230620223017887](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230620223017887.png)

这样，以 `x` 为根的整棵二叉树就被拉平了，恰好完成了 `flatten(x)` 的定义。

直接看代码实现：



你看，这就是递归的魅力，你说 `flatten` 函数是怎么把左右子树拉平的？

不容易说清楚，但是只要知道 `flatten` 的定义如此并利用这个定义，让每一个节点做它该做的事情，然后 `flatten` 函数就会按照定义工作。

### 代码

```java

/**
 * <p>
 * 二叉树展开为链表
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode114 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        Solution01 solution01 = new Solution01();
        solution01.flatten(root);
        System.out.println(root);

    }

    /**
     * 解法一：分解问题思维
     */
    private static class Solution01 {

        // 定义：将以 root 为根的树拉平为链表
        public void flatten(TreeNode root) {
            // base case
            if (root == null) {
                return;
            }
            // 利用定义，把左右子树拉平
            flatten(root.left);
            flatten(root.right);

            /**** 后序遍历位置 ****/
            // 1、左右子树已经被拉平成一条链表
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 2、将左子树作为右子树
            root.left = null;
            root.right = left;

            // 3、将原先的右子树接到当前右子树的末端
            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            p.right = right;
        }

    }

}

```





### 复杂度分析











# THE END