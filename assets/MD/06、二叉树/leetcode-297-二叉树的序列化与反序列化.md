# 目录

[toc]

# leetcode-297-二叉树的序列化与反序列化

- 时间：2023-06-21
- 参考链接：
  - [二叉树的题，就那几个框架，枯燥至极](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485871&idx=1&sn=bcb24ea8927995b585629a8b9caeed01&chksm=9bd7f7a7aca07eb1b4c330382a4e0b916ef5a82ca48db28908ab16563e28a376b5ca6805bec2&scene=21#wechat_redirect)
  - [东哥带你刷二叉树（序列化篇）](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/dong-ge-da-d14d3/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
- 难度：困难

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。



**提示：**

+ 树中结点数在范围 `[0, 104]` 内
+ `-1000 <= Node.val <= 1000`



# 2、题解

## 题目分析

`serialize` 方法也许会把它序列化成字符串 `2,1,#,6,3,#,#`，其中 `#` 表示 `null` 指针，那么把这个字符串再输入 `deserialize` 方法，依然可以还原出这棵二叉树。也就是说，这两个方法会成对儿使用，你只要保证他俩能够自洽就行了。

想象一下，二叉树结该是一个二维平面内的结构，而序列化出来的字符串是一个线性的一维结构。**所谓的序列化不过就是把结构化的数据「打平」，其实就是在考察二叉树的遍历方式**。

二叉树的遍历方式有哪些？递归遍历方式有前序遍历，中序遍历，后序遍历；迭代方式一般是层级遍历。本文就把这些方式都尝试一遍，来实现 `serialize` 方法和 `deserialize` 方法。



## 解法一: 前序遍历

### 算法分析

`StringBuilder` 可以用于高效拼接字符串，所以也可以认为是一个列表，用 `,` 作为分隔符，用 `#` 表示空指针 null，



现在，思考一下如何写 `deserialize` 函数，将字符串反过来构造二叉树。

首先我们可以把字符串转化成列表：

```
String data = "1,2,#,4,#,#,3,#,#,";
String[] nodes = data.split(",");
```

这样，`nodes` 列表就是二叉树的前序遍历结果，问题转化为：如何通过二叉树的前序遍历结果还原一棵二叉树？

PS：一般语境下，单单前序遍历结果是不能还原二叉树结构的，因为缺少空指针的信息，至少要得到前、中、后序遍历中的两种才能还原二叉树。但是这里的 `node` 列表包含空指针的信息，所以只使用 `node` 列表就可以还原二叉树。

根据我们刚才的分析，`nodes` 列表就是一棵打平的二叉树：



那么，反序列化过程也是一样，**先确定根节点 `root`，然后遵循前序遍历的规则，递归生成左右子树即可**：

我们发现，根据树的递归性质，`nodes` 列表的第一个元素就是一棵树的根节点，所以只要将列表的第一个元素取出作为根节点，剩下的交给递归函数去解决即可。

### 代码

```java

package com.joker.algorithm.binarytree;

import java.util.LinkedList;

/**
 * <p>
 * 二叉树的序列化与反序列化
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode297 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec01 codec01 = new Codec01();
        String serialize01 = codec01.serialize(root);
        System.out.println(serialize01);
        System.out.println(codec01.deserialize(serialize01));

    }

    /**
     * 解法一：前序遍历
     */
    static class Codec01 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            // 用于拼接字符串
            StringBuilder sb = new StringBuilder();
            doSerialize(root, sb);
            return sb.toString();
        }

        /* 辅助函数，将二叉树存入 StringBuilder */
        void doSerialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }

            /****** 前序遍历位置 ******/
            sb.append(root.val).append(SEP);
            /***********************/

            doSerialize(root.left, sb);
            doSerialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {

            // 将字符串转化成列表
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.addLast(s);
            }
            return doDeserialize(nodes);

        }

        /* 辅助函数，通过 nodes 列表构造二叉树 */
        private TreeNode doDeserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;

            String first = nodes.removeFirst();
            if (first.equals(NULL)) {
                return null;
            }
            /****** 前序遍历位置 ******/
            // 列表最左侧就是根节点
            TreeNode root = new TreeNode(Integer.parseInt(first));
            /***********************/

            root.left = doDeserialize(nodes);
            root.right = doDeserialize(nodes);

            return root;
        }

    }

}

```

输出如下：

```sh
1,2,#,#,3,4,#,#,5,#,#,

```



### 复杂度分析

- 时间复杂度：$O(n)$
- 空间复杂度：$O(n)$



## 解法二: 后序遍历

### 算法分析

明白了前序遍历的解法，后序遍历就比较容易理解了，我们首先实现 `serialize` 序列化方法，只需要稍微修改辅助方法即可：



关键的难点在于，如何实现后序遍历的 `deserialize` 方法呢？

**`deserialize` 方法首先寻找 `root` 节点的值，然后递归计算左右子节点**。那么我们这里也应该顺着这个基本思路走，后续遍历中，`root` 节点的值能不能找到？

![image-20230621222731398](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230621222731398.png)

可见，`root` 的值是列表的最后一个元素。我们应该从后往前取出列表元素，先用最后一个元素构造 `root`，然后递归调用生成 `root` 的左右子树。**注意，根据上图，从后往前在 `nodes` 列表中取元素，一定要先构造 `root.right` 子树，后构造 `root.left` 子树**。

### 代码

```java
package com.joker.algorithm.binarytree;

import java.util.LinkedList;

/**
 * <p>
 * 二叉树的序列化与反序列化
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode297 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec02 codec02 = new Codec02();
        String serialize02 = codec02.serialize(root);
        System.out.println(serialize02);
        System.out.println(codec02.deserialize(serialize02));

    }

    /**
     * 解法二：后序遍历
     */
    static class Codec02 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            // 用于拼接字符串
            StringBuilder sb = new StringBuilder();
            doSerialize(root, sb);
            return sb.toString();
        }

        /* 辅助函数，将二叉树存入 StringBuilder */
        void doSerialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }

            doSerialize(root.left, sb);
            doSerialize(root.right, sb);

            /****** 后序遍历位置 ******/
            sb.append(root.val).append(SEP);
            /***********************/
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {
            // 将字符串转化成列表
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.addLast(s);
            }
            return doDeserialize(nodes);

        }

        /* 辅助函数，通过 nodes 列表构造二叉树 */
        private TreeNode doDeserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;

            // 从后往前取出元素
            String last = nodes.removeLast();
            if (last.equals(NULL)) {
                return null;
            }
            // 因为是后序遍历的字符串，列表最右侧就是根节点
            TreeNode root = new TreeNode(Integer.parseInt(last));

            // 先构造右子树，后构造左子树
            root.right = doDeserialize(nodes);
            root.left = doDeserialize(nodes);

            return root;
        }

    }

}

```

输出如下：

```sh
#,#,2,#,#,4,#,#,5,3,1,
```





### 复杂度分析

- 时间复杂度：$O()$
- 空间复杂度：$O()$



## 解法三: 层序遍历

### 算法分析

序列化的思路：





层级遍历序列化得出的结果如下图：

![image-20230621224809637](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230621224809637.png)



可以看到，每一个非空节点都会对应两个子节点，**那么反序列化的思路也是用队列进行层级遍历，同时用索引 `i` 记录对应子节点的位置**



### 代码

```java
package com.joker.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 二叉树的序列化与反序列化
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode297 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec03 codec03 = new Codec03();
        String serialize03 = codec03.serialize(root);
        System.out.println(serialize03);
        System.out.println(codec03.deserialize(serialize03));

    }


    /**
     * 解法三：层序遍历
     */
    static class Codec03 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            // 初始化队列，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                /* 层级遍历代码位置 */
                if (cur == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(cur.val).append(SEP);
                /*****************/

                q.offer(cur.left);
                q.offer(cur.right);
            }

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] nodes = data.split(SEP);
            // 第一个元素就是 root 的值
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

            // 队列 q 记录父节点，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            for (int i = 1; i < nodes.length;) {
                // 队列中存的都是父节点
                TreeNode parent = q.poll();
                // 父节点对应的左侧子节点的值
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                } else {
                    parent.left = null;
                }

                // 父节点对应的右侧子节点的值
                String right = nodes[i++];
                if (!right.equals(NULL)) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    q.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }

            return root;
        }

    }

}

```

输出如下：

```sh
1,2,3,#,#,4,5,#,#,#,#,
```





### 复杂度分析

- 时间复杂度：$O()$
- 空间复杂度：$O()$

# THE END