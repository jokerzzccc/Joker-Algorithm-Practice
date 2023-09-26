# 目录

[toc]

# leetcode-450-删除二叉搜索树中的节点

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（基操篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-7b3e4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/delete-node-in-a-bst/
- 难度：中等

给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

- 首先找到需要删除的节点；
- 如果找到了，删除它。



提示:

- 节点数的范围 [0, 10^4].
- -10^5 <= Node.val <= 10^5
- 节点值唯一
- root 是合法的二叉搜索树
- -10^5 <= key <= 10^5



# 2、题解

## 题目分析



## 解法一: *BST* 递归

### 算法分析

这个问题稍微复杂，跟插入操作类似，先「找」再「改」

找到目标节点了，比方说是节点 `A`，如何删除这个节点，这是难点。因为删除节点的同时不能破坏 BST 的性质。有三种情况，用图片来说明。

**情况 1**：`A` 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。

**情况 2**：`A` 只有一个非空子节点，那么它要让这个孩子接替自己的位置。

**情况 3**：`A` 有两个子节点，麻烦了，为了不破坏 BST 的性质，`A` 必须找到左子树中最大的那个节点，或者**右子树中最小的那个节点来接替自己**。我们以第二种方式讲解。

- ![image-20230622122931323](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622122931323.png)

- ```java
  if (root.left != null && root.right != null) {
      // 找到右子树的最小节点
      TreeNode minNode = getMin(root.right);
      // 把 root 改成 minNode
      root.val = minNode.val;
      // 转而去删除 minNode
      root.right = deleteNode(root.right, minNode.val);
  }
  
  ```





最后总结一下吧，通过这篇文章，我们总结出了如下几个技巧：

1、如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息。

2、在二叉树递归框架之上，扩展出一套 BST 代码框架：

```java
void BST(TreeNode root, int target) {
    if (root.val == target)
        // 找到目标，做点什么
    if (root.val < target) 
        BST(root.right, target);
    if (root.val > target)
        BST(root.left, target);
}
```

3、根据代码框架掌握了 BST 的增删查改操作。



### 代码

```java

/**
 * <p>
 * 删除二叉搜索树中的节点
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode450 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.deleteNode(root, 3);
        System.out.println(treeNode01);

    }

    /**
     * 解法一： 递归遍历
     * 找到目标节点了，比方说是节点 `A`，如何删除这个节点，这是难点。因为删除节点的同时不能破坏 BST 的性质。有三种情况，用图片来说明。
     * <p>
     * **情况 1**：`A` 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
     * <p>
     * **情况 2**：`A` 只有一个非空子节点，那么它要让这个孩子接替自己的位置。
     * <p>
     * **情况 3**：`A` 有两个子节点，麻烦了，为了不破坏 BST 的性质，`A` 必须找到左子树中最大的那个节点，或者**右子树中最小的那个节点来接替自己**。我们以第二种方式讲解。
     */
    private static class Solution01 {

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;

            if (root.val == key) {
                // 这两个 if 把情况 1 和 2 都正确处理了
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;

                // 处理情况 3
                // 获得右子树最小的节点
                TreeNode minNode = getMin(root.right);
                // 删除右子树最小的节点
                root.right = deleteNode(root.right, minNode.val);
                // 用右子树最小的节点替换 root 节点
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }

            return root;
        }

        TreeNode getMin(TreeNode node) {
            // BST 最左边的就是最小的
            while (node.left != null) node = node.left;
            return node;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n)$





# THE END