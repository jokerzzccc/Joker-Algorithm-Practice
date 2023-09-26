# 目录

[toc]

# leetcode-863-二叉树中所有距离为 K 的结点

- 时间：2023-06-27
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree/?company_slug=alibaba
- 难度：中等

给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。

返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 **任何顺序** 返回。



提示:

- 节点数在 [1, 500] 范围内
- 0 <= Node.val <= 500
- Node.val 中所有值 不同
- 目标结点 target 是树上的结点。
- 0 <= k <= 1000



# 2、题解

## 题目分析



## 解法一：DFS + 树变图 +  哈希缓存

### 算法分析

若将target当作树的根结点，我们就能从target出发，使用深度优先搜索去寻找与target距离为k的所有结点，即深度为k的所有结点。
由于输入的二叉树没有记录父结点，为此，我们从根结点o0t出发，使用深度优先搜索遍历整棵树，同时**用一个哈希表记录每个结点的父结点**。
然后从target出发，使用深度优先搜索遍历整棵树，**除了搜索左右儿子外，还可以顺着父结点向上搜索**。代码实现时，由于每个结点值都是唯一的，哈希表的键可以用结点值代替。

此外，**为避免在深度优先搜索时重复问结点**，递归时额外传入来源结点 from,在递归前**比较目标结点是否与来源结点相同，不同的情况下才进行递归**。



### 代码

```java

/**
 * <p>
 * 二叉树中所有距离为 K 的结点
 * </p>
 *
 * @author admin
 * @date 2023/6/27
 */
public class leetcode863 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        int k = 2;
        TreeNode target = root.left;

        Solution01 solution01 = new Solution01();
        List<Integer> distanceK = solution01.distanceK(root, target, k);
        distanceK.stream().forEach(System.out::println);

    }

    /**
     * 解法一： DFS + 树变图 +  哈希缓存
     */
    private static class Solution01 {

        /**
         * 记录每个结点对应的父结点
         */
        Map<Integer, TreeNode> parents = new HashMap<>();

        List<Integer> ans = new ArrayList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 从 root 出发 DFS，记录每个结点的父结点
            findParents(root);

            // 从 target 出发 DFS，寻找所有深度为 k 的结点（左右儿子 + 父节点）
            findAns(target, null, 0, k);

            return ans;
        }

        private void findAns(TreeNode node, TreeNode from, int depth, int k) {
            if (node == null) {
                return;
            }
            if (depth == k) {
                ans.add(node.val);
                return;
            }
            // 左儿子递归
            if (node.left != from) {
                findAns(node.left, node, depth + 1, k);
            }
            // 右儿子递归
            if (node.right != from) {
                findAns(node.right, node, depth + 1, k);
            }
            // 父节点递归
            if (parents.get(node.val) != from) {
                findAns(parents.get(node.val), node, depth + 1, k);
            }
        }

        /**
         * 记录每个结点的父结点, 到缓存 map
         */
        private void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }

    }

}

```

输出：

```sh
7
4
1

```





### 复杂度分析











# THE END