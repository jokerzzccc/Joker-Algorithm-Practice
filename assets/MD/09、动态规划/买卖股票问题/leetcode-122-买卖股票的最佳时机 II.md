# 目录

[toc]

# leetcode-122-买卖股票的最佳时机 II

- 时间：2023-06-24
- 参考链接：
  - [一个方法团灭 LeetCode 股票买卖问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-3b01b/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
- 难度：中等



给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 **最多** 只能持有 一股 股票。你也可以先购买，然后在 **同一天** 出售。

返回 你能获得的 **最大** 利润 。

 



**提示：**

+ `1 <= prices.length <= 3 * 10^4`
+ `0 <= prices[i] <= 10^4`



# 2、题解

## 题目分析

- 详见 leetcode-188，区别：
  - 题目还专门强调可以在同一天出售，但我觉得这个条件纯属多余，如果当天买当天卖，那利润当然就是 0，这不是和没有进行交易是一样的吗？这道题的特点在于没有给出交易总数 `k` 的限制，也就**相当于 `k` 为正无穷**。
  - 

## 解法一：动态规划

### 算法分析

如果 `k` 为正无穷，那么就可以认为 `k` 和 `k - 1` 是一样的。可以这样改写框架：

```python
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
            = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])

我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
```

直接翻译成代码：

### 代码

```java

/**
 * <p>
 * 买卖股票的最佳时机 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode122 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

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
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                // base case
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                // 今天没有持有股票，max(昨天就没有-今天保持不动，昨天就持有-今天卖出)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                // 今天持有股票，max(昨天就持有-今天保持不动，昨天没有-今天买进)
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }

            return dp[n - 1][0];
        }

    }

}


```





### 复杂度分析





## 解法二：*动态规划* *+* *空间优化*

### 算法分析





### 代码

```java


/**
 * <p>
 * 买卖股票的最佳时机 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode122 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices);
        System.out.println(maxProfit02);

    }


    /**
     * 解法二: 动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
            }

            return dp_i_0;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(1)







# THE END