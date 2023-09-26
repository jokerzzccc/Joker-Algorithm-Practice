# 目录

[toc]

# labuladong-快速排序的正确理解方式及运用

- 时间：2023-06-22
- 参考链接：
  - [快速排序的正确理解方式及运用](https://mp.weixin.qq.com/s/8ZTMhvHJK_He48PpSt_AmQ)





首先我们看一下快速排序的代码框架：

```
void sort(int[] nums, int lo, int hi) {
    if (lo >= hi) {
        return;
    }
    // 对 nums[lo..hi] 进行切分
    // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
    int p = partition(nums, lo, hi);
    // 去左右子数组进行切分
    sort(nums, lo, p - 1);
    sort(nums, p + 1, hi);
}
```

其实你对比之后可以发现，快速排序就是一个二叉树的前序遍历：

```
/* 二叉树遍历框架 */
void traverse(TreeNode root) {
    if (root == null) {
        return;
    }
    /****** 前序位置 ******/
    print(root.val);
    /*********************/
    traverse(root.left);
    traverse(root.right);
}
```

另外，前文 [归并排序详解](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247495989&idx=1&sn=30e34ac75dd1c724205e9c8b0f488e35&scene=21#wechat_redirect) 用一句话总结了归并排序：先把左半边数组排好序，再把右半边数组排好序，然后把两半数组合并。

同时我提了一个问题，让你一句话总结快速排序，这里说一下我的答案：

**快速排序是先将一个元素排好序，然后再将剩下的元素排好序**。

为什么这么说呢，且听我慢慢道来。

快速排序的核心无疑是 `partition` 函数， `partition` 函数的作用是在 `nums[lo..hi]` 中寻找一个分界点 `p`，通过交换元素使得 `nums[lo..p-1]` 都小于等于 `nums[p]`，且 `nums[p+1..hi]` 都大于 `nums[p]`：

![image-20230622162149756](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622162149756.png)



**从二叉树的视角，我们可以把子数组 `nums[lo..hi]` 理解成二叉树节点上的值，`srot` 函数理解成二叉树的遍历函数**。

这应该不难理解吧，因为 `partition` 函数每次都将数组切分成左小右大两部分，恰好和二叉搜索树左小右大的特性吻合。

**你甚至可以这样理解：快速排序的过程是一个构造二叉搜索树的过程**。







# THE END