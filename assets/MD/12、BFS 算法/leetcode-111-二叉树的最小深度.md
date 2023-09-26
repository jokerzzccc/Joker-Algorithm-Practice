# 目录

[toc]

# leetcode-111-二叉树的最小深度

- 时间：2023-06-25
- 参考链接：
  - [BFS 算法解题套路框架](https://labuladong.gitee.io/algo/di-san-zha-24031/bao-li-sou-96f79/bfs-suan-f-463fd/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/minimum-depth-of-binary-tree/
- 难度：简单



给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明：**叶子节点是指没有子节点的节点。

 

**提示：**

+ 树中节点数的范围在 `[0, 10^5]` 内
+ `-1000 <= Node.val <= 1000`



# 2、题解

## 题目分析

怎么套到 BFS 的框架里呢？首先明确一下起点 `start` 和终点 `target` 是什么，怎么判断到达了终点？

**显然起点就是 `root` 根节点，终点就是最靠近根节点的那个「叶子节点」嘛**，叶子节点就是两个子节点都是 `null` 的节点：

```java
if (cur.left == null && cur.right == null) 
    // 到达叶子节点
```

这里注意这个 `while` 循环和 `for` 循环的配合，**`while` 循环控制一层一层往下走，`for` 循环利用 `sz` 变量控制从左到右遍历每一层二叉树节点**：

![image-20230625210545737](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230625210545737.png)

## 解法一: BFS

### 算法分析





### 代码

```java

/**
 * <p>
 * 二叉树的最小深度
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode111 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution01 solution01 = new Solution01();
        int minDepth01 = solution01.minDepth(root);
        System.out.println(minDepth01);

    }

    /**
     * 解法一： BFS
     */
    private static class Solution01 {

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // root 本身就是一层，depth 初始化为 1
            int depth = 1;
            // while 循环控制一层一层往下走，for 循环利用 sz 变量控制从左到右遍历每一层二叉树节点
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curNode = queue.poll();
                    /* 判断是否到达终点 */
                    if (curNode.left == null && curNode.right == null)
                        return depth;

                    /* 将 curNode 的相邻节点加入队列 */
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
                /* 这里增加步数 */
                depth++;
            }

            return depth;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(N),其中 N 是树的节点数。对每个节点访问一次。
- 空间复杂度：O(N),其中 N 是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不
  会超过树的节点数。









# THE END