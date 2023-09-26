# 目录

[toc]

# leetcode-102-二叉树的层序遍历

- 时间：2023-07-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/binary-tree-level-order-traversal/
- 难度：中等

给你二叉树的根节点 `root` ，返回其节点值的 **层序遍历** 。 （即逐层地，从左到右访问所有节点）。

 ![image-20230708234231902](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230708234231902.png)



# 2、题解

## 题目分析



## 解法一：BFS

### 算法分析





### 代码

```java


/**
 * <p>
 * 二叉树的层序遍历
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode102 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        List<List<Integer>> levelOrder01 = solution01.levelOrder(root);
        levelOrder01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return res;
            }

            // 队列，存储每一层的节点，跟着 while 变化
            LinkedList<TreeNode> queue = new LinkedList<>();
            // 初始化，加入 root，第一层
            queue.offer(root);

            // while 是纵向，for 是横向
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();

                int curLevel = queue.size();
                for (int i = 0; i < curLevel; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                res.add(level);

            }

            return res;
        }

    }

}

```





### 复杂度分析

记树上所有节点的个数为n。

- 时间复杂度：每个点进队出队各一次，故渐进时间复杂度为O(n)。
- 空间复杂度：队列中元素的个数不超过个，故渐进空间复杂度为O(n)。







# THE END