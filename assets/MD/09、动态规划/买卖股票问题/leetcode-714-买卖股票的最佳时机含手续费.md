# 目录

[toc]

# leetcode-714-买卖股票的最佳时机含手续费

- 时间：2023-06-24
- 参考链接：
  - [一个方法团灭 LeetCode 股票买卖问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-3b01b/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
- 难度：中等



给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。



**提示：**

+ `1 <= prices.length <= 5 * 10^4`
+ `1 <= prices[i] < 5 * 10^4`
+ `0 <= fee < 5 * 10^4`



# 2、题解

## 题目分析

- 类比，leetcoce-188
- **也就是 `k` 为正无穷且考虑交易手续费的情况**

每次交易要支付手续费，只要把手续费从利润中减去即可，改写方程：

```python
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
解释：相当于买入股票的价格升高了。
在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
```

> Note
>
> 如果直接把 `fee` 放在第一个式子里减，会有一些测试用例无法通过，错误原因是整型溢出而不是思路问题。一种解决方案是把代码中的 `int` 类型都改成 `long` 类型，避免 `int` 的整型溢出。



直接翻译成代码，注意状态转移方程改变后 base case 也要做出对应改变：

## 解法一：动态规划

### 算法分析



### 代码

```java


/**
 * <p>
 * 买卖股票的最佳时机含手续费
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode714 {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices, fee);
        System.out.println(maxProfit01);
    }

    /**
     * 解法一: 动态规划
     */
    private static class Solution01 {

        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    //   dp[i][1]
                    // = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
                    // = max(dp[-1][1], dp[-1][0] - prices[i] - fee)
                    // = max(-inf, 0 - prices[i] - fee)
                    // = -prices[i] - fee
                    dp[i][1] = -prices[i] - fee;
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
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
 * 买卖股票的最佳时机含手续费
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode714 {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        Solution02 solution02 = new Solution02();
        int maxProfit02 = solution02.maxProfit(prices, fee);
        System.out.println(maxProfit02);

    }

    /**
     * 解法二: 动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int tmp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, tmp - prices[i] - fee);
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