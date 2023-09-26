# 目录

[toc]

# leetcode-889-根据前序和后序遍历构造二叉树

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-172f0/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
- 难度：中等



给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 **无重复** 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。

如果存在多个答案，您可以返回其中 **任何 一个**。



提示：

- 1 <= preorder.length <= 30
- 1 <= preorder[i] <= preorder.length
- preorder 中所有值都 不同
- postorder.length == preorder.length
- 1 <= postorder[i] <= postorder.length
- postorder 中所有值都 不同
- 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历





# 2、题解

## 题目分析





## 解法一: 分解问题 + 递归

### 算法分析



### 代码

```java

/**
 * <p>
 * 根据前序和后序遍历构造二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode889 {

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.constructFromPrePost(preorder, postorder);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     */
    private static class Solution01 {

        // 存储 postorder 中值到索引的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            for (int i = 0; i < postorder.length; i++) {
                valToIndex.put(postorder[i], i);
            }
            return build(preorder, 0, preorder.length - 1,
                    postorder, 0, postorder.length - 1);
        }

        // 定义：根据 preorder[preStart..preEnd] 和 postorder[postStart..postEnd]
        // 构建二叉树，并返回根节点。
        TreeNode build(int[] preorder, int preStart, int preEnd,
                int[] postorder, int postStart, int postEnd) {
            if (preStart > preEnd) {
                return null;
            }
            if (preStart == preEnd) {
                return new TreeNode(preorder[preStart]);
            }

            // root 节点对应的值就是前序遍历数组的第一个元素
            int rootVal = preorder[preStart];
            // root.left 的值是前序遍历第二个元素
            // 通过前序和后序遍历构造二叉树的关键在于通过左子树的根节点
            // 确定 preorder 和 postorder 中左右子树的元素区间
            int leftRootVal = preorder[preStart + 1];
            // leftRootVal 在后序遍历数组中的索引
            int index = valToIndex.get(leftRootVal);
            // 左子树的元素个数
            int leftSize = index - postStart + 1;

            // 先构造出当前根节点
            TreeNode root = new TreeNode(rootVal);

            // 递归构造左右子树
            // 根据左子树的根节点索引和元素个数推导左右子树的索引边界
            root.left = build(preorder, preStart + 1, preStart + leftSize,
                    postorder, postStart, index);
            root.right = build(preorder, preStart + leftSize + 1, preEnd,
                    postorder, index + 1, postEnd - 1);

            return root;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：



# THE END