# 目录

[toc]

# leetcode-337-打家劫舍 III

- 时间：2023-06-23
- 参考链接：
  - [一个方法团灭 LeetCode 打家劫舍问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-f3df7/)
  - [经典动态规划：打家劫舍系列问题](https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/house-robber-iii/
- 难度：中等



小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。

除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 **两个直接相连的房子在同一天晚上被打劫** ，房屋将自动报警。

给定二叉树的 root 。返回 **在不触动警报的情况下** ，小偷能够盗取的最高金额 。



**提示：**

+ 树的节点数在 `[1, 10^4]` 范围内
+ `0 <= Node.val <= 10^4`



# 2、题解

## 题目分析

- 类比 leetcdoe-198

整体的思路完全没变，还是做抢或者不抢的选择，取收益较大的选择



## 解法一: 动态规划 + 备忘录

### 算法分析



### 代码

```java
package com.joker.algorithm.dp;

import com.joker.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 打家劫舍 III
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode337 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        Solution01 solution01 = new Solution01();
        int rob01 = solution01.rob(root);
        System.out.println(rob01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        // 备忘录
        // K: 从节点 node 开始，V: 能够抢到的最多钱
        Map<TreeNode, Integer> memo = new HashMap<>();

        public int rob(TreeNode root) {
            if (root == null) return 0;
            if (memo.containsKey(root)) {
                return memo.get(root);
            }
            // 抢，然后去下下家
            int rob = root.val
                    + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                    + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
            // 不抢，然后去下家
            int notRob = rob(root.left) + rob(root.right);

            int res = Math.max(rob, notRob);
            memo.put(root, res);
            return res;
        }
    }


}

```





### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：O(n)



## 解法一: 动态规划 + 无备忘录（更优）

### 算法分析

- 更改了 dp 函数的定义，得到了更优的解



### 代码

```java

/**
 * <p>
 * 打家劫舍 III
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode337 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        Solution02 solution02 = new Solution02();
        int rob02 = solution02.rob(root);
        System.out.println(rob02);

    }

    /**
     * 解法二：动态规划（更优）
     * 时间复杂度 O(N)，空间复杂度只有递归函数堆栈所需的空间，不需要备忘录的额外空间。
     */
    private static class Solution02 {

        public int rob(TreeNode root) {
            int[] res = dp(root);
            return Math.max(res[0], res[1]);

        }

        /**
         * 返回一个大小为 2 的数组 arr
         * arr[0] 表示不抢 root 的话，得到的最大钱数
         * arr[1] 表示抢 root 的话，得到的最大钱数
         */
        int[] dp(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] left = dp(root.left);
            int[] right = dp(root.right);
            // 抢，下家就不能抢了
            int rob = root.val + left[0] + right[0];
            // 不抢，下家可抢可不抢，取决于收益大小
            int notRob = Math.max(left[0], left[1])
                    + Math.max(right[0], right[1]);

            return new int[]{notRob, rob};
        }

    }

}


```





### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：O(N)。只有递归函数堆栈所需的空间，不需要备忘录的额外空间。





# THE END