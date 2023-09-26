# 目录

[toc]

# leetcode-652-寻找重复的子树

- 时间：2023-06-21
- 参考链接：
  - [东哥手把手带你刷二叉树|第三期](https://mp.weixin.qq.com/s/LJbpo49qppIeRs-FbgjsSQ)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/find-duplicate-subtrees/
- 难度：中等



给你一棵二叉树的根节点 root ，返回所有 **重复的子树** 。

对于同一类的重复子树，你只需要返回其中任意 **一棵** 的根结点即可。

如果两棵树具有 **相同的结构** 和 **相同的结点值** ，则认为二者是 **重复** 的。



**提示：**

+ 树中的结点数在 `[1, 5000]` 范围内。
+ `-200 <= Node.val <= 200`



# 2、题解

## 题目分析





## 解法一: 二叉树序列化 + 后序遍历

### 算法分析

这题咋做呢？**还是老套路，先思考，对于某一个节点，它应该做什么**。



**你需要知道以下两点**：

**1、以我为根的这棵二叉树（子树）长啥样**？

**2、以其他节点为根的子树都长啥样**？



其实看到这个问题，就可以判断本题要使用「后序遍历」框架来解决

```java
void traverse(TreeNode root) {
    traverse(root.left);
    traverse(root.right);
    /* 解法代码的位置 */
}
```

为什么？很简单呀，我要知道以自己为根的子树长啥样，是不是得先知道我的左右子树长啥样，再加上自己，就构成了整棵子树的样子？



**这样，我们第一个问题就解决了，对于每个节点，递归函数中的`subTree`变量就可以描述以该节点为根的二叉树**。

**现在我们解决第二个问题，我知道了自己长啥样，怎么知道别人长啥样**？这样我才能知道有没有其他子树跟我重复对吧。

如果出现多棵重复的子树，结果集`res`中必然出现重复，而题目要求不希望出现重复。

为了解决这个问题，可以把`HashSet`升级成`HashMap`，额外记录每棵子树的出现次数.



### 代码

```java

/**
 * <p>
 * 寻找重复的子树
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode652 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);

        Solution01 solution01 = new Solution01();
        List<TreeNode> duplicateSubtrees01 = solution01.findDuplicateSubtrees(root);
        System.out.println(duplicateSubtrees01);

    }

    /**
     * 解法一：二叉树序列化 + 后序遍历
     */
    private static class Solution01 {

        /**
         * 记录所有子树以及出现的次数
         */
        HashMap<String, Integer> memo = new HashMap<>();
        /**
         * 记录重复的子树根节点
         */
        LinkedList<TreeNode> res = new LinkedList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return res;
        }

        private String traverse(TreeNode root) {
            // 对于空节点，可以用一个特殊字符表示
            if (root == null) {
                return "#";
            }

            // 将左右子树序列化成字符串
            String left = traverse(root.left);
            String right = traverse(root.right);
            /* 后序遍历代码位置 */
            // 左右子树加上自己，就是以自己为根的二叉树序列化结果
            String subTree = left + "," + right + "," + root.val;

            // 子树出现的次数
            int freq = memo.getOrDefault(subTree, 0);
            // 多次重复也只会被加入结果集一次
            // 即第一次发现重复的时候，才加进结果集，后面再发现重复的，就不用管了。
            if (freq == 1) {
                res.add(root);
            }
            // 给子树对应的出现次数加一
            memo.put(subTree, freq + 1);

            return subTree;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：$O(n^2)$
- 空间复杂度：$O(n^2)$





# THE END