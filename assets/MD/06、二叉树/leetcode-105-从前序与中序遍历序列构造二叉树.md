# 目录

[toc]

# leetcode-105-从前序与中序遍历序列构造二叉树

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-172f0/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
- 难度：中等



给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的**先序遍历**， inorder 是同一棵树的**中序遍历**，请构造二叉树并返回其根节点。



提示:

- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder 和 inorder 均 **无重复** 元素
- inorder 均出现在 preorder
- preorder 保证 为二叉树的前序遍历序列
- inorder 保证 为二叉树的中序遍历序列





# 2、题解

## 题目分析

**我们肯定要想办法确定根节点的值，把根节点做出来，然后递归构造左右子树即可**。



## 解法一: 分解问题 + 递归

### 算法分析



### 代码

```java

/**
 * <p>
 * 从前序与中序遍历序列构造二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode105 {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.buildTree(preorder, inorder);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     */
    private static class Solution01 {

        // 存储 inorder 中值到索引的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) {
                valToIndex.put(inorder[i], i);
            }
            return build(preorder, 0, preorder.length - 1,
                    inorder, 0, inorder.length - 1);

        }

        /**
         * build 函数的定义：
         * 若前序遍历数组为 preorder[preStart..preEnd]，
         * 中序遍历数组为 inorder[inStart..inEnd]，
         * 构造二叉树，返回该二叉树的根节点
         */
        private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

            // base case
            if (preStart > preEnd) {
                return null;
            }
            // root 节点对应的值就是前序遍历数组的第一个元素
            int rootVal = preorder[preStart];

            // rootVal 在中序遍历数组中的索引
            int index = valToIndex.get(rootVal);

            int leftSize = index - inStart;

            // 先构造出当前根节点
            TreeNode root = new TreeNode(rootVal);

            // 递归构造左右子树
            root.left = build(preorder, preStart + 1, preStart + leftSize,
                    inorder, inStart, index - 1);

            root.right = build(preorder, preStart + leftSize + 1, preEnd,
                    inorder, index + 1, inEnd);

            return root;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：



# THE END