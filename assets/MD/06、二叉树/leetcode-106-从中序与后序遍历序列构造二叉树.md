# 目录

[toc]

# leetcode-106-从中序与后序遍历序列构造二叉树

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-172f0/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
- 难度：中等



给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。



提示:

- 1 <= inorder.length <= 3000
- postorder.length == inorder.length
- -3000 <= inorder[i], postorder[i] <= 3000
- inorder 和 postorder 都由 不同 的值组成
- postorder 中每一个值都在 inorder 中
- inorder 保证是树的中序遍历
- postorder 保证是树的后序遍历



# 2、题解

## 题目分析





## 解法一: 分解问题 + 递归

### 算法分析



### 代码

```java

/**
 * <p>
 * 从中序与后序遍历序列构造二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode106 {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.buildTree(inorder, postorder);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     * 画图理解
     */
    private static class Solution01 {

        // 存储 inorder 中值到索引的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                valToIndex.put(inorder[i], i);
            }
            return build(inorder, 0, inorder.length - 1,
                    postorder, 0, postorder.length - 1);

        }

        /**
         * build 函数的定义：
         * 后序遍历数组为 postorder[postStart..postEnd]，
         * 中序遍历数组为 inorder[inStart..inEnd]，
         * 构造二叉树，返回该二叉树的根节点
         */
        TreeNode build(int[] inorder, int inStart, int inEnd,
                int[] postorder, int postStart, int postEnd) {

            if (inStart > inEnd) {
                return null;
            }
            // root 节点对应的值就是后序遍历数组的最后一个元素
            int rootVal = postorder[postEnd];
            // rootVal 在中序遍历数组中的索引
            int index = valToIndex.get(rootVal);
            // 左子树的节点个数
            int leftSize = index - inStart;
            TreeNode root = new TreeNode(rootVal);

            // 递归构造左右子树
            root.left = build(inorder, inStart, index - 1,
                    postorder, postStart, postStart + leftSize - 1);

            root.right = build(inorder, index + 1, inEnd,
                    postorder, postStart + leftSize, postEnd - 1);
            return root;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：



# THE END