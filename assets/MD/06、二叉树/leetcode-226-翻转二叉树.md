# 目录

[toc]

# leetcode-226-翻转二叉树

- 时间：2023-06-20
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/invert-binary-tree/
- 难度：简单

给你一棵二叉树的根节点 `root` ，翻转这棵二叉树，并返回其根节点。



**提示：**

+ 树中节点数目范围在 `[0, 100]` 内
+ `-100 <= Node.val <= 100`



# 2、题解

## 题目分析



## 解法一：遍历思维

### 算法分析

**1、这题能不能用「遍历」的思维模式解决**？

可以，我写一个 `traverse` 函数遍历每个节点，让每个节点的左右子节点颠倒过来就行了。

单独抽出一个节点，需要让它做什么？让它把自己的左右子节点交换一下。

需要在什么时候做？好像前中后序位置都可以。

综上，可以写出如下解法代码：



你把前序位置的代码移到后序位置也可以，但是直接移到中序位置是不行的，需要稍作修改，这应该很容易看出来吧，我就不说了。



### 代码

```java

/**
 * <p>
 * 翻转二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode226 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Solution01 solution01 = new Solution01();
        TreeNode invertTree01 = solution01.invertTree(root);
        System.out.println(invertTree01);

    }

    /**
     * 解法一：遍历思维
     */
    private static class Solution01 {

        public TreeNode invertTree(TreeNode root) {
            // 遍历二叉树，交换每个节点的子节点
            traverse(root);
            return root;
        }

        // 二叉树遍历函数
        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }

            /**** 前序位置 ****/
            // 每一个节点需要做的事就是交换它的左右子节点
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
            // 遍历框架，去遍历左右子树的节点
            traverse(root.left);
            traverse(root.right);
        }

    }


}

```





### 复杂度分析





## 解法二：分解问题思维

### 算法分析

**2、这题能不能用「分解问题」的思维模式解决**？

我们尝试给 `invertTree` 函数赋予一个定义：

```java
// 定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
TreeNode invertTree(TreeNode root);
```

然后思考，对于某一个二叉树节点 `x` 执行 `invertTree(x)`，你能利用这个递归函数的定义做点啥？

我可以用 `invertTree(x.left)` 先把 `x` 的左子树翻转，再用 `invertTree(x.right)` 把 `x` 的右子树翻转，最后把 `x` 的左右子树交换，这恰好完成了以 `x` 为根的整棵二叉树的翻转，即完成了 `invertTree(x)` 的定义。

直接写出解法代码：



这种「分解问题」的思路，核心在于你要给递归函数一个合适的定义，然后用函数的定义来解释你的代码；如果你的逻辑成功自恰，那么说明你这个算法是正确的。

好了，这道题就分析到这，「遍历」和「分解问题」的思路都可以解决，



### 代码

```java

/**
 * <p>
 * 翻转二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode226 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Solution02 solution02 = new Solution02();
        TreeNode invertTree02 = solution02.invertTree(root);
        System.out.println(invertTree02);

    }


    /**
     * 解法二：分解问题思维
     */
    private static class Solution02 {

        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            // 利用函数定义，先翻转左右子树
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);

            // 然后交换左右子节点
            root.left = right;
            root.right = left;

            // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
            return root;
        }

    }

}

```





### 复杂度分析







# THE END