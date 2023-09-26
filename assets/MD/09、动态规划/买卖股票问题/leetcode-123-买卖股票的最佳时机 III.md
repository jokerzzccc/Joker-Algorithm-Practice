# 目录

[toc]

# leetcode-123-买卖股票的最佳时机 III

- 时间：2023-06-24
- 参考链接：
  - [一个方法团灭 LeetCode 股票买卖问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-3b01b/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
- 难度：困难

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。



**提示：**

+ `1 <= prices.length <= 10^5`
+ `0 <= prices[i] <= 10^5`





# 2、题解

## 题目分析

- 类比，leetcoce-188
  - **也就是 `k = 2` 的情况**

`k = 2` 和前面题目的情况稍微不同，因为上面的情况都和 `k` 的关系不太大：要么 `k` 是正无穷，状态转移和 `k` 没关系了；要么 `k = 1`，跟 `k = 0` 这个 base case 挨得近，最后也没有存在感。

这道题 `k = 2` 和后面要讲的 `k` 是任意正整数的情况中，对 `k` 的处理就凸显出来了，我们直接写代码，边写边分析原因。

```java
原始的状态转移方程，没有可化简的地方
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
```

按照之前的代码，我们可能想当然这样写代码（错误的）：

```java
int k = 2;
int[][][] dp = new int[n][k + 1][2];
for (int i = 0; i < n; i++) {
    if (i - 1 == -1) {
        // 处理 base case
        dp[i][k][0] = 0;
        dp[i][k][1] = -prices[i];
        continue;
    }
    dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
    dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
}
return dp[n - 1][k][0];
```

为什么错误？我这不是照着状态转移方程写的吗？

还记得前面总结的「穷举框架」吗？就是说我们**必须穷举所有状态**。其实我们之前的解法，都在穷举所有状态，只是之前的题目中 `k` 都被化简掉了。

比如说第一题，`k = 1` 时的代码框架：

```java
int n = prices.length;
int[][] dp = new int[n][2];
for (int i = 0; i < n; i++) {
    dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
    dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
}
return dp[n - 1][0];
```

但当 `k = 2` 时，由于没有消掉 `k` 的影响，所以必须要对 `k` 进行穷举：

```java
// 原始版本
int maxProfit_k_2(int[] prices) {
    int max_k = 2, n = prices.length;
    int[][][] dp = new int[n][max_k + 1][2];
    for (int i = 0; i < n; i++) {
        for (int k = max_k; k >= 1; k--) {
            if (i - 1 == -1) {
                // 处理 base case
                dp[i][k][0] = 0;
                dp[i][k][1] = -prices[i];
                continue;
            }
            dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
            dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
        }
    }
    // 穷举了 n × max_k × 2 个状态，正确。
    return dp[n - 1][max_k][0];
}
```

> Note
>
> **这里肯定会有读者疑惑，`k` 的 base case 是 0，按理说应该从 `k = 1, k++` 这样穷举状态 `k` 才对？而且如果你真的这样从小到大遍历 `k`，提交发现也是可以的**。

这个疑问很正确，因为我们前文 [动态规划答疑篇](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/dong-tai-g-a223e/zui-you-zi-fbef6/) 有介绍 `dp` 数组的遍历顺序是怎么确定的，主要是根据 base case，以 base case 为起点，逐步向结果靠近。

但为什么我从大到小遍历 `k` 也可以正确提交呢？因为你注意看，`dp[i][k][..]` 不会依赖 `dp[i][k - 1][..]`，而是依赖 `dp[i - 1][k - 1][..]`，而 `dp[i - 1][..][..]`，都是已经计算出来的，所以不管你是 `k = max_k, k--`，还是 `k = 1, k++`，都是可以得出正确答案的。

那为什么我使用 `k = max_k, k--` 的方式呢？因为这样符合语义：

你买股票，初始的「状态」是什么？应该是从第 0 天开始，而且还没有进行过买卖，所以最大交易次数限制 `k` 应该是 `max_k`；而随着「状态」的推移，你会进行交易，那么交易次数上限 `k` 应该不断减少，这样一想，`k = max_k, k--` 的方式是比较合乎实际场景的。



## 解法一：动态规划

### 算法分析



### 代码

```java


/**
 * <p>
 * 买卖股票的最佳时机 III
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode123 {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices);
        System.out.println(maxProfit01);

    }

    /**
     * 解法一: 动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            int maxK = 2;
            int[][][] dp = new int[n][maxK + 1][2];
            for (int i = 0; i < n; i++) {
                // 穷举状态 k
                for (int k = maxK; k > 0; k--) {
                    // base case
                    if (i - 1 == -1) {
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }
                    // 今天没有持有股票，max(昨天就没有-今天保持不动，昨天就持有-今天卖出)
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    // 今天持有股票，max(昨天就持有-今天保持不动，昨天没有-今天买进)
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }

            return dp[n - 1][maxK][0];
        }

    }

}
```





### 复杂度分析





## 解法二：*动态规划* *+* *空间优化*

### 算法分析

*这里* *k* *取值范围比较小，所以也可以不用* *for* *循环，直接把* *k = 1* *和* *2* *的情况全部列举出来也可以。*

```sh
状态转移方程：
dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
```



### 代码

```java

/**
 * <p>
 * 买卖股票的最佳时机 III
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode123 {

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices);
        System.out.println(maxProfit02);

    }

    /**
     * 解法二: 动态规划 + 空间优化
     * 这里 k 取值范围比较小，所以也可以不用 for 循环，直接把 k = 1 和 2 的情况全部列举出来也可以。
     * 状态转移方程：
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case
            int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
            int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
            for (int price : prices) {
                dp_i20 = Math.max(dp_i20, dp_i21 + price);
                dp_i21 = Math.max(dp_i21, dp_i10 - price);
                dp_i10 = Math.max(dp_i10, dp_i11 + price);
                dp_i11 = Math.max(dp_i11, -price);
            }

            return dp_i20;
        }

    }

}
```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(1)







# THE END