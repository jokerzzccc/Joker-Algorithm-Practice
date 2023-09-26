# 目录

[toc]

# leetcode-95-不同的二叉搜索树 II

- 时间：2023-06-22
- 参考链接：
  - [东哥带你刷二叉搜索树（构造篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-b16de/)
  - [手把手带你刷通二叉搜索树（第三期）](https://mp.weixin.qq.com/s/kcwz2lyRxxOsC3n11qdVSw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/unique-binary-search-trees-ii/
- 难度：中等



给你一个整数 `n` ，请你生成并返回所有由 `n` 个节点组成且节点值从 `1` 到 `n` 互不相同的不同 **二叉搜索树** 。可以按 **任意顺序** 返回答案。





**提示：**

+ `1 <= n <= 8`



# 2、题解

## 题目分析



## 解法一: 

### 算法分析

**明白了 leetcode96 构造合法 BST 的方法，这道题的思路也是一样的**：

1、穷举`root`节点的所有可能。

2、递归构造出左右子树的所有合法 BST。

3、给`root`节点穷举所有左右子树的组合。

### 代码

```java


/**
 * <p>
 * 不同的二叉搜索树 II
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode95 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        List<TreeNode> treeNodes01 = solution01.generateTrees(n);
        System.out.println(treeNodes01);

    }

    /**
     * 解法一：回溯
     */
    private static class Solution01 {

        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new LinkedList<>();
            // 构造闭区间 [1, n] 组成的 BST
            return build(1, n);

        }

        /**
         * 构造闭区间 [low, high] 组成的 BST
         */
        private List<TreeNode> build(int low, int high) {
            List<TreeNode> res = new LinkedList<>();
            // base case
            // 即 【low, high】 为空
            if (low > high) {
                res.add(null);
                return res;
            }

            // 1、穷举 root 节点的所有可能。
            for (int i = low; i <= high; i++) {
                // 2、递归构造出左右子树的所有合法 BST。
                List<TreeNode> leftTree = build(low, i - 1);
                List<TreeNode> rightTree = build(i + 1, high);
                // 3、给 root 节点穷举所有左右子树的组合。
                for (TreeNode leftNode : leftTree) {
                    for (TreeNode rightNode : rightTree) {
                        // i 作为根节点 root 的值
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        root.right = rightNode;
                        res.add(root);
                    }
                }
            }
            return res;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：
- 空间复杂度：



![image-20230622161409608](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622161409608.png)



# THE END