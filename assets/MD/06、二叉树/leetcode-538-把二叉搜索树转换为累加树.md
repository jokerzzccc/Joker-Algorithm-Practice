# 目录

[toc]

# leetcode-538-把二叉搜索树转换为累加树

- 时间：2023-06-21
- 参考链接：
  - [东哥带你刷二叉搜索树（特性篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-8f403/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/convert-bst-to-greater-tree/
- 难度：中等

给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

- 节点的左子树仅包含键 小于 节点键的节点。
- 节点的右子树仅包含键 大于 节点键的节点。
- 左右子树也必须是二叉搜索树。



注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同



**提示：**

+ 树中的节点数介于 `0` 和 `10^4` 之间。
+ 每个节点的值介于 `-10^4` 和 `10^4` 之间。
+ 树中的所有值 **互不相同** 。
+ 给定的树为二叉搜索树。



# 2、题解

## 题目分析





## 解法一: *反序中序遍历*

### 算法分析

按照二叉树的通用思路，需要思考每个节点应该做什么，但是这道题上很难想到什么思路。



**其实，正确的解法很简单，还是利用 BST 的中序遍历特性**。

那如果我想降序打印节点的值怎么办？

很简单，只要把递归顺序改一下就行了：

```java
void traverse(TreeNode root) {
    if (root == null) return;
    // 先递归遍历右子树
    traverse(root.right);
    // 中序遍历代码位置
    print(root.val);
    // 后递归遍历左子树
    traverse(root.left);
}
```

**这段代码可以降序打印 BST 节点的值，如果维护一个外部累加变量 `sum`，然后把 `sum` 赋值给 BST 中的每一个节点，不就将 BST 转化成累加树了吗**？



### 代码

```java


/**
 * <p>
 * 把二叉搜索树转换为累加树
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode538 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        Solution01 solution = new Solution01();
        TreeNode result01 = solution.convertBST(root);
        System.out.println(result01);

    }

    /**
     * 解法一：反序中序遍历
     * BST 中序遍历特性（有序递增）
     */
    private static class Solution01 {

        /**
         * 记录累加和
         */
        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            traverse(root);
            return root;
        }

        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.right);
            // 维护累加和
            sum += root.val;
            // 将 BST 转化成累加树
            root.val = sum;
            traverse(root.left);
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n)$





# THE END