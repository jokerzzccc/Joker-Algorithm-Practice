# 目录

[toc]

# leetcode-312-戳气球

- 时间：2023-06-23
- 参考链接：
  - [经典动态规划：戳气球](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/jing-dian--3c814/)
  - [经典动态规划：戳气球问题](https://mp.weixin.qq.com/s/I0yo0XZamm-jMpG-_B3G8g)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/burst-balloons/
- 难度：困难

有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

求所能获得硬币的最大数量。

**提示：**

+ `n == nums.length`
+ `1 <= n <= 300`
+ `0 <= nums[i] <= 100`



# 2、题解

## 题目分析

1. n 个气球[0, n - 1]
2. 

## 解法一: 动态规划

### 算法分析

这个动态规划问题和我们之前的动态规划系列文章相比有什么特别之处？为什么它比较难呢？

**原因在于，这个问题中我们每戳破一个气球`nums[i]`，得到的分数和该气球相邻的气球`nums[i-1]`和`nums[i+1]`是有相关性的**。

我们前文 [动态规划套路框架详解](http://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=1&sn=f1db6dee2c8e70c42240aead9fd224e6&chksm=9bd7fb33aca07225bee0b23a911c30295e0b90f393af75eca377caa4598ffb203549e1768336&scene=21#wechat_redirect) 说过运用动态规划算法的一个重要条件：**子问题必须独立**。所以对于这个戳气球问题，如果想用动态规划，必须巧妙地定义`dp`数组的含义，避免子问题产生相关性，才能推出合理的状态转移方程。

如何定义`dp`数组呢，这里需要对问题进行一个简单地转化。题目说可以认为`nums[-1] = nums[n] = 1`，那么我们先直接把这两个边界加进去，形成一个新的数组`points`：

```java
int maxCoins(int[] nums) {
    int n = nums.length;
    // 两端加入两个虚拟气球
    int[] points = new int[n + 2];
    points[0] = points[n + 1] = 1;
    for (int i = 1; i <= n; i++) {
        points[i] = nums[i - 1];
    }
    // ...
}
```

现在气球的索引变成了从`1`到`n`，`points[0]`和`points[n+1]`可以认为是两个「虚拟气球」。

那么我们可以改变问题：**在一排气球`points`中，请你戳破气球`0`和气球`n+1`之间的所有气球（不包括`0`和`n+1`），使得最终只剩下气球`0`和气球`n+1`两个气球，最多能够得到多少分**？

现在可以定义**`dp`数组的含义**：

**`dp[i][j] = x`表示，戳破气球`i`和气球`j`之间（开区间，不包括`i`和`j`）的所有气球，可以获得的最高分数为`x`**。



那么根据这个定义，题目要求的结果就是`dp[0][n+1]`的值，而 base case 就是`dp[i][j] = 0`，其中`0 <= i <= n+1, j <= i+1`，因为这种情况下，开区间`(i, j)`中间根本没有气球可以戳。

```
// base case 已经都被初始化为 0
int[][] dp = new int[n + 2][n + 2];
```

现在我们要根据这个`dp`数组来推导状态转移方程了，根据我们前文的套路，所谓的推导「状态转移方程」，实际上就是在思考怎么「做选择」，也就是这道题目最有技巧的部分：

其实气球`i`和气球`j`之间的所有气球都可能是最后被戳破的那一个，不防假设为`k`。回顾动态规划的套路，这里其实已经找到了「状态」和「选择」：`i`和`j`就是两个「状态」，最后戳破的那个气球`k`就是「选择」。

**根据刚才对`dp`数组的定义，如果最后一个戳破气球`k`，`dp[i][j]`的值应该为**：

```
dp[i][j] = dp[i][k] + dp[k][j] 
         + points[i]*points[k]*points[j]
```

你不是要最后戳破气球`k`吗？那得先把开区间`(i, k)`的气球都戳破，再把开区间`(k, j)`的气球都戳破；最后剩下的气球`k`，相邻的就是气球`i`和气球`j`，这时候戳破`k`的话得到的分数就是`points[i]*points[k]*points[j]`。

那么戳破开区间`(i, k)`和开区间`(k, j)`的气球最多能得到的分数是多少呢？嘿嘿，就是`dp[i][k]`和`dp[k][j]`，这恰好就是我们对`dp`数组的定义嘛！

![image-20230623211209359](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623211209359.png)

结合这个图，就能体会出`dp`数组定义的巧妙了。由于是开区间，`dp[i][k]`和`dp[k][j]`不会影响气球`k`；而戳破气球`k`时，旁边相邻的就是气球`i`和气球`j`了，最后还会剩下气球`i`和气球`j`，这也恰好满足了`dp`数组开区间的定义。

那么，对于一组给定的`i`和`j`，我们只要穷举`i < k < j`的所有气球`k`，选择得分最高的作为`dp[i][j]`的值即可，这也就是状态转移方程：

```java
// 最后戳破的气球是哪个？
for (int k = i + 1; k < j; k++) {
    // 择优做选择，使得 dp[i][j] 最大
    dp[i][j] = Math.max(
        dp[i][j], 
        dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
    );
}
```

写出状态转移方程就完成这道题的一大半了，但是还有问题：对于`k`的穷举仅仅是在做「选择」，但是应该如何穷举「状态」`i`和`j`呢？

```java
for (int i = ...; ; )
    for (int j = ...; ; )
        for (int k = i + 1; k < j; k++) {
            dp[i][j] = Math.max(
                dp[i][j], 
                dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
            );
return dp[0][n+1];
```

**关于「状态」的穷举，最重要的一点就是：状态转移所依赖的状态必须被提前计算出来**。

拿这道题举例，`dp[i][j]`所依赖的状态是`dp[i][k]`和`dp[k][j]`，那么我们必须保证：在计算`dp[i][j]`时，`dp[i][k]`和`dp[k][j]`已经被计算出来了（其中`i < k < j`）。

那么应该如何安排`i`和`j`的遍历顺序，来提供上述的保证呢？我们前文 [动态规划答疑篇](http://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484832&idx=1&sn=44ad2505ac5c276bf36eea1c503b78c3&chksm=9bd7fba8aca072be32f66e6c39d76ef4e91bdbf4ef993014d4fee82896687ad61da4f4fc4eda&scene=21#wechat_redirect) 写过处理这种问题的一个鸡贼技巧：**根据 base case 和最终状态进行推导**。

PS：最终状态就是指题目要求的结果，对于这道题目也就是`dp[0][n+1]`。

我们先把 base case 和最终的状态在 DP table 上画出来：

![image-20230623211443067](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623211443067.png)

对于任一`dp[i][j]`，我们希望所有`dp[i][k]`和`dp[k][j]`已经被计算，画在图上就是这种情况：

![image-20230623211518784](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623211518784.png)

那么，为了达到这个要求，可以有两种遍历方法，要么斜着遍历，要么从下到上从左到右遍历：

![image-20230623211627222](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623211627222.png)

![image-20230623211641275](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623211641275.png)

斜着遍历有一点难写，所以一般我们就从下往上遍历，下面看完整代码：



至此，这道题目就完全解决了，十分巧妙，但也不是那么难，对吧？

关键在于`dp`数组的定义，需要避免子问题互相影响，所以我们反向思考，将`dp[i][j]`的定义设为开区间，考虑最后戳破的气球是哪一个，以此构建了状态转移方程。

对于如何穷举「状态」，我们使用了小技巧，通过 base case 和最终状态推导出`i,j`的遍历方向，保证正确的状态转移。

### 代码

```java
package com.joker.algorithm.dp;

/**
 * <p>
 * 戳气球
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode312 {

    public static void main(String[] args) {

        int[] nums = {3, 1, 5, 8};

        Solution01 solution01 = new Solution01();
        int maxCoins01 = solution01.maxCoins(nums);
        System.out.println(maxCoins01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxCoins(int[] nums) {
            int n = nums.length;
            // 添加两侧的虚拟气球，即添加 nums[-1] = nums[n] = 1两个边界，形成一个新的数组 points
            // 现在气球的索引变成了从1到n，points[0]和points[n+1]可以认为是两个「虚拟气球」。
            // 同时，问题改变为：
            // 在一排气球points中，请你戳破气球0和气球n+1之间的所有气球（不包括0和n+1），使得最终只剩下气球0和气球n+1两个气球，最多能够得到多少分？
            int[] points = new int[n + 2];
            points[0] = points[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                points[i] = nums[i - 1];
            }

            // dp[i][j] = x表示，戳破气球i和气球j之间（开区间，不包括i和j）的所有气球，可以获得的最高分数为x。
            // base case 已经都被初始化为 0
            int[][] dp = new int[n + 2][n + 2];
            // 开始状态转移
            // i 应该从下往上
            for (int i = n; i >= 0; i--) {
                // j 应该从左往右
                for (int j = i + 1; j < n + 2; j++) {
                    // k （i, j）中最后戳破的气球是哪个？
                    for (int k = i + 1; k < j; k++) {
                        // 择优做选择，选最大
                        dp[i][j] = Math.max(
                                dp[i][j],
                                dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]
                        );
                    }
                }
            }

            return dp[0][n + 1];
        }

    }

}

```

输出：

```sh
167
```





### 复杂度分析

- 时间复杂度：$O(n^3)$, n 是气球数量
- 空间复杂度：$O(n^2)$







# THE END