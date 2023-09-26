# 目录

[toc]

# leetcode-42-接雨水

- 时间：2023-06-29
- 参考链接：
  - [如何高效解决接雨水问题](https://labuladong.gitee.io/algo/di-san-zha-24031/jing-dian--a94a0/ru-he-gao--0d5eb/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/trapping-rain-water/
- 难度：困难



给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

示例 1：

![image-20230629212208001](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230629212208001.png)

```sh
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
```



**提示：**

+ `n == height.length`
+ `1 <= n <= 2 * 10^4`
+ `0 <= height[i] <= 10^5`



# 2、题解

## 题目分析

所以对于这种问题，我们不要想整体，而应该去想局部；就像之前的文章写的动态规划问题处理字符串问题，不要考虑如何处理整个字符串，而是去思考应该如何处理每一个字符。

这么一想，可以发现这道题的思路其实很简单。具体来说，仅仅对于位置 `i`，能装下多少水呢？

## 解法一: 暴力解法

### 算法分析

这么一想，可以发现这道题的思路其实很简单。具体来说，仅仅对于位置 `i`，能装下多少水呢？

![image-20230629212336346](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230629212336346.png)

能装 2 格水，因为 `height[i]` 的高度为 0，而这里最多能盛 2 格水，2-0=2。

为什么位置 `i` 最多能盛 2 格水呢？因为，位置 `i` 能达到的水柱高度和其左边的最高柱子、右边的最高柱子有关，我们分别称这两个柱子高度为 `l_max` 和 `r_max`；**位置 i 最大的水柱高度就是 `min(l_max, r_max)`**。

更进一步，对于位置 `i`，能够装的水为：

```python
water[i] = min(
               # 左边最高的柱子
               max(height[0..i]),  
               # 右边最高的柱子
               max(height[i..end]) 
            ) - height[i]
    
```

![image-20230629212358196](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230629212358196.png)

![image-20230629212407711](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230629212407711.png)

这就是本问题的核心思路，我们可以简单写一个暴力算法：



### 代码

```java


/**
 * <p>
 * 接雨水
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        Solution01 solution01 = new Solution01();
        int trap01 = solution01.trap(height);
        System.out.println(trap01);

    }

    /**
     * 解法一：暴力算法
     */
    private static class Solution01 {

        public int trap(int[] height) {
            int n = height.length;
            int res = 0;
            for (int i = 1; i < n - 1; i++) {
                int l_max = 0, r_max = 0;
                // 找右边最高的柱子 [i,n]
                for (int j = i; j < n; j++) {
                    r_max = Math.max(r_max, height[j]);
                }
                // 找左边最高的柱子 [0,i]
                for (int j = i; j >= 0; j--) {
                    l_max = Math.max(l_max, height[j]);
                }
                // 如果自己就是最高的话，
                // l_max == r_max == height[i]
                res += Math.min(r_max, l_max) - height[i];
            }

            return res;
        }

    }


}

```





### 复杂度分析







## 解法二: 备忘录优化

之前的暴力解法，不是在每个位置 `i` 都要计算 `r_max` 和 `l_max` 吗？我们直接把结果都提前计算出来，别傻不拉几的每次都遍历，这时间复杂度不就降下来了嘛。

**我们开两个数组 `r_max` 和 `l_max` 充当备忘录，`l_max[i]` 表示位置 `i` 左边最高的柱子高度，`r_max[i]` 表示位置 `i` 右边最高的柱子高度**。预先把这两个数组计算好，避免重复计算：



这个优化其实和暴力解法思路差不多，就是避免了重复计算，把时间复杂度降低为 O(N)，已经是最优了，但是空间复杂度是 O(N)。下面来看一个精妙一些的解法，能够把空间复杂度降低到 O(1)。

### 代码

```java


/**
 * <p>
 * 接雨水
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        Solution02 solution02 = new Solution02();
        int trap02 = solution02.trap(height);
        System.out.println(trap02);

    }

    /**
     * 解法二：备忘录优化
     */
    private static class Solution02 {

        public int trap(int[] height) {
            if (height.length == 0) {
                return 0;
            }

            int n = height.length;
            int res = 0;

            // 数组充当备忘录
            int[] l_max = new int[n];
            int[] r_max = new int[n];
            // 初始化 base case
            l_max[0] = height[0];
            r_max[n - 1] = height[n - 1];

            // 从左向右计算 l_max
            for (int i = 1; i < n; i++) {
                l_max[i] = Math.max(height[i], l_max[i - 1]);
            }
            // 从右向左计算 r_max
            for (int j = n - 2; j >= 0; j--) {
                r_max[j] = Math.max(height[j], r_max[j + 1]);
            }

            // 计算答案
            for (int i = 1; i < n - 1; i++) {
                res += Math.min(r_max[i], l_max[i]) - height[i];
            }

            return res;
        }

    }


}

```





### 复杂度分析

- 时间复杂度：O(n),其中n是数组height的长度。计算数组leftMax和rightMax的元素值各需要遍历
  数组height一次，计算能接的雨水总量还需要遍历一次。
- 空间复杂度：O(n),其中n是数组height的长度。需要创建两个长度为n的数组leftMax和rightMax



## 解法三: 双指针

这种解法的思路是完全相同的，但在实现手法上非常巧妙，我们这次也不要用备忘录提前计算了，而是用双指针**边走边算**，节省下空间复杂度。

首先，看一部分代码：

```java
int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int l_max = 0, r_max = 0;
    
    while (left < right) {
        l_max = Math.max(l_max, height[left]);
        r_max = Math.max(r_max, height[right]);
        // 此时 l_max 和 r_max 分别表示什么？
        left++; right--;
    }
}
```

对于这部分代码，请问 `l_max` 和 `r_max` 分别表示什么意义呢？

很容易理解，**`l_max` 是 `height[0..left]` 中最高柱子的高度，`r_max` 是 `height[right..end]` 的最高柱子的高度**。

### 代码

```java


/**
 * <p>
 * 接雨水
 * </p>
 *
 * @author admin
 * @date 2023/6/28
 */
public class leetcode42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        Solution03 solution03 = new Solution03();
        int trap03 = solution03.trap(height);
        System.out.println(trap03);

    }

    /**
     * 解法三：双指针（最优）
     */
    private static class Solution03 {

        public int trap(int[] height) {
            // 两个指针
            int left = 0, right = height.length - 1;
            // 最大值变量
            // leftMax 是 `height[0..left]` 中最高柱子的高度，rightMax 是 height[right..end]
            int leftMax = 0, rightMax = 0;
            int res = 0;

            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);

                // res += min(l_max, r_max) - height[i]
                if (leftMax < rightMax) {
                    res += leftMax - height[left];
                    left++;
                } else {
                    res += rightMax - height[right];
                    right--;
                }
            }

            return res;
        }

    }

}

```

**加深理解：**

```sh
可算看懂了，原来双指针同时开两个柱子接水。大家题解没说清楚，害得我也没看懂。 对于每一个柱子接的水，那么它能接的水=min(左右两边最高柱子）-当前柱子高度，这个公式没有问题。同样的，两根柱子要一起求接水，同样要知道它们左右两边最大值的较小值。

问题就在这，假设两柱子分别为 i，j。那么就有 iLeftMax,iRightMax,jLeftMx,jRightMax 这个变量。由于 j>i ，故 jLeftMax>=iLeftMax，iRigthMax>=jRightMax.

那么，如果 iLeftMax>jRightMax，则必有 jLeftMax >= jRightMax，所有我们能接 j 点的水。

如果 jRightMax>iLeftMax，则必有 iRightMax >= iLeftMax，所以我们能接 i 点的水。

而上面我们实际上只用到了 iLeftMax，jRightMax 两个变量，故我们维护这两个即可。（题解都没说清楚，就说个 LeftMax，RightMax，谁知道为什么就可以这么做了。)
```





### 复杂度分析

- 时间复杂度：O(n),其中n是数组height的长度。两个指针的移动总次数不超过n.
- 空间复杂度：O(1)。只需要使用常数的额外空间。









# THE END