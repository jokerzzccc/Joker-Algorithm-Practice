# 目录

[toc]

# leetcode-11-盛最多水的容器

- 时间：2023-06-29
- 参考链接：
  - [如何高效解决接雨水问题](https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-gao--0d5eb/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/container-with-most-water/
- 难度：中等



给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。



示例 1：

![image-20230629214708714](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230629214708714.png)

```sh
输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
```





**提示：**

+ `n == height.length`
+ `2 <= n <= 10^5`
+ `0 <= height[i] <= 10^4`



# 2、题解

## 题目分析

- 类比 leetcode-42

  - 两道题的区别在于：

    **接雨水问题给出的类似一幅直方图，每个横坐标都有宽度，而本题给出的每个横坐标是一条竖线，没有宽度**。



## 解法一:双指针

我们前文讨论了半天 `l_max` 和 `r_max`，实际上都是为了计算 `height[i]` 能够装多少水；而本题中 `height[i]` 没有了宽度，那自然就好办多了。

举个例子，如果在接雨水问题中，你知道了 `height[left]` 和 `height[right]` 的高度，你能算出 `left` 和 `right` 之间能够盛下多少水吗？

不能，因为你不知道 `left` 和 `right` 之间每个柱子具体能盛多少水，你得通过每个柱子的 `l_max` 和 `r_max` 来计算才行。

反过来，就本题而言，你知道了 `height[left]` 和 `height[right]` 的高度，能算出 `left` 和 `right` 之间能够盛下多少水吗？

可以，因为本题中竖线没有宽度，**所以 `left` 和 `right` 之间能够盛的水**就是：

```python
min(height[left], height[right]) * (right - left)
```

类似接雨水问题，高度是由 `height[left]` 和 `height[right]` 较小的值决定的。

解决这道题的思路依然是双指针技巧：

**用 `left` 和 `right` 两个指针从两端向中心收缩，一边收缩一边计算 `[left, right]` 之间的矩形面积，取最大的面积值即是答案**。



为什么要移动较低的一边：

**其实也好理解，因为矩形的高度是由 `min(height[left], height[right])` 即较低的一边决定的**：

你如果移动较低的那一边，那条边可能会变高，使得矩形的高度变大，进而就「有可能」使得矩形的面积变大；相反，如果你去移动较高的那一边，矩形的高度是无论如何都不会变大的，所以不可能使矩形的面积变得更大。

### 代码

```java

/**
 * <p>
 * 盛最多水的容器
 * </p>
 *
 * @author admin
 * @date 2023/6/29
 */
public class leetcode11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        Solution01 solution01 = new Solution01();
        int maxArea01 = solution01.maxArea(height);
        System.out.println(maxArea01);

    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public int maxArea(int[] height) {
            // 两个指针
            int left = 0, right = height.length - 1;
            int res = 0;

            while (left < right) {
                // [left, right] 之间的矩形面积
                int curArea = Math.min(height[left], height[right]) * (right - left);
                res = Math.max(res, curArea);

                // 双指针技巧，移动较低的一边,保持高的一方指针不动
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n),双指针总计最多遍历整个数组一次。
- 空间复杂度：O(1),只需要额外的常数级别的空间。













# THE END