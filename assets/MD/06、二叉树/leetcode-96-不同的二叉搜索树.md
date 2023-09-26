# 目录

[toc]

# leetcode-96-不同的二叉搜索树

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-b16de/)
  - [手把手带你刷通二叉搜索树（第三期）](https://mp.weixin.qq.com/s/kcwz2lyRxxOsC3n11qdVSw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/unique-binary-search-trees/
- 难度：中等



给你一个整数 `n` ，求恰由 `n` 个节点组成且节点值从 `1` 到 `n` 互不相同的 **二叉搜索树** 有多少种？返回满足题意的二叉搜索树的种数。



**提示：**

+ `1 <= n <= 19`



# 2、题解

## 题目分析



## 解法一: 动态规划

### 算法分析

举个例子，比如给算法输入`n = 5`，也就是说用`{1,2,3,4,5}`这些数字去构造 BST。

首先，这棵 BST 的根节点总共有几种情况？

显然有 5 种情况对吧，因为每个数字都可以作为根节点。

比如说我们固定`3`作为根节点，这个前提下能有几种不同的 BST 呢？

根据 BST 的特性，根节点的左子树都比根节点的值小，右子树的值都比根节点的值大。

所以如果固定`3`作为根节点，左子树节点就是`{1,2}`的组合，右子树就是`{4,5}`的组合。

**左子树的组合数和右子树的组合数乘积**就是`3`作为根节点时的 BST 个数。



注意 base case，显然当`lo > hi`闭区间`[lo, hi]`肯定是个空区间，也就对应着空节点 null，虽然是空节点，但是也是一种情况，所以要返回 1 而不能返回 0。

这样，题目的要求已经实现了，但是时间复杂度非常高，肯定存在重叠子问题。

前文动态规划相关的问题多次讲过消除重叠子问题的方法，无非就是加一个备忘录：

### 代码

```java

/**
 * <p>
 * 不同的二叉搜索树
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode96 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        int numTrees01 = solution01.numTrees(n);
        System.out.println(numTrees01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        // 备忘录
        int[][] memo;

        public int numTrees(int n) {
            // 备忘录的值初始化为 0
            memo = new int[n + 1][n + 1];
            return count(1, n);
        }

        /**
         * 计算闭区间 [low, high] 组成的 BST 个数
         */
        private int count(int low, int high) {
            // base case
            // 虽然是空节点，但是也是一种情况，所以要返回 1 而不能返回 0。
            if (low > high) {
                return 1;
            }

            // 查备忘录
            if (memo[low][high] != 0) {
                return memo[low][high];
            }

            int res = 0;
            for (int i = low; i <= high; i++) {
                // i 的值作为根节点 root
                int left = count(low, i - 1);
                int right = count(i + 1, high);
                // 左右子树的组合数乘积是 BST 的总数
                res += left * right;
            }

            // 将结果存入备忘录
            memo[low][high] = res;

            return res;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：$O(n^2)$
- 空间复杂度：$O(n)$





# THE END