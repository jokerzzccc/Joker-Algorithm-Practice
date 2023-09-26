# 目录

[toc]

# leetcode-114-二叉树展开为链表

- 时间：2023-06-20
- 参考链接：
  - [东哥带你刷二叉树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-172f0/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-binary-tree/
- 难度：中等



给定一个不重复的整数数组 nums 。 **最大二叉树** 可以用下面的算法从 nums 递归地构建:

1. 创建一个根节点，其值为 nums 中的最大值。
2. 递归地在最大值 **左边** 的 **子数组前缀上** 构建左子树。
3. 递归地在最大值 **右边** 的 **子数组后缀上** 构建右子树。
   

返回 nums 构建的 **最大二叉树** 。



**提示：**

+ `1 <= nums.length <= 1000`
+ `0 <= nums[i] <= 1000`
+ `nums` 中的所有整数 **互不相同**





# 2、题解

## 题目分析

**二叉树的构造问题一般都是使用「分解问题」的思路：构造整棵树 = 根节点 + 构造左子树 + 构造右子树**。



每个二叉树节点都可以认为是一棵子树的根节点，对于根节点，首先要做的当然是把想办法把自己先构造出来，然后想办法构造自己的左右子树。

所以，我们要遍历数组把找到最大值 `maxVal`，从而把根节点 `root` 做出来，然后对 `maxVal` 左边的数组和右边的数组进行递归构建，作为 `root` 的左右子树。



**当前 `nums` 中的最大值就是根节点，然后根据索引递归调用左右数组构造左右子树即可**。



明确了思路，我们可以重新写一个辅助函数 `build`，来控制 `nums` 的索引

## 解法一: *分解问题* *+* *递归*

### 算法分析



### 代码

```java

/**
 * <p>
 * 最大二叉树
 * </p>
 *
 * @author admin
 * @date 2023/6/20
 */
public class leetcode654 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};

        Solution01 solution01 = new Solution01();
        TreeNode treeNode01 = solution01.constructMaximumBinaryTree(nums);
        System.out.println(treeNode01);

    }

    /**
     * 解法一：分解问题 + 递归
     */
    private static class Solution01 {

        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        /**
         * 定义：将 nums[low..high] 构造成符合条件的树，返回根节点
         */
        private TreeNode build(int[] nums, int low, int high) {
            // base case
            if (low > high) {
                return null;
            }

            // 找到数组中的最大值和对应的索引
            int index = -1, maxVal = Integer.MIN_VALUE;
            for (int i = low; i <= high; i++) {
                if (maxVal < nums[i]) {
                    index = i;
                    maxVal = nums[i];
                }
            }

            // 先构造出根节点
            TreeNode root = new TreeNode(maxVal);
            // 递归调用构造左右子树
            root.left = build(nums, low, index - 1);
            root.right = build(nums, index + 1, high);

            return root;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：$O(N^2)$
- 空间复杂度：O(N)



# THE END