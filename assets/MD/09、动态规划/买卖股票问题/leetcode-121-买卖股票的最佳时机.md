# 目录

[toc]

# leetcode-121-买卖股票的最佳时机

- 时间：2023-06-23
- 参考链接：
  - [一个方法团灭 LeetCode 股票买卖问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-3b01b/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
- 难度：简单



给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 **某一天** 买入这只股票，并选择在 **未来的某一个不同的日子** 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。



**提示：**

+ `1 <= prices.length <= 10^5`
+ `0 <= prices[i] <= 10^4`



# 2、题解

## 题目分析

- 详见 leetcode-188

## 解法一：动态规划

### 算法分析

直接套状态转移方程，根据 base case，可以做一些化简：

```python
dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) 
            = max(dp[i-1][1][1], -prices[i])
解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。

现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
可以进行进一步化简去掉所有 k：
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], -prices[i])
```

直接写出代码：



显然 `i = 0` 时 `i - 1` 是不合法的索引，这是因为我们没有对 `i` 的 base case 进行处理，可以这样给一个特化处理：

```java
if (i - 1 == -1) {
    dp[i][0] = 0;
    // 根据状态转移方程可得：
    //   dp[i][0] 
    // = max(dp[-1][0], dp[-1][1] + prices[i])
    // = max(0, -infinity + prices[i]) = 0

    dp[i][1] = -prices[i];
    // 根据状态转移方程可得：
    //   dp[i][1] 
    // = max(dp[-1][1], dp[-1][0] - prices[i])
    // = max(-infinity, 0 - prices[i]) 
    // = -prices[i]
    continue;
}
```



### 代码

```java


/**
 * <p>
 * 买卖股票的最佳时机
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode121 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices);
        System.out.println(maxProfit01);

    }

    /**
     * 解法一：动态规划
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
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }

            return dp[n - 1][0];
        }

    }

}

```





### 复杂度分析





## 解法二：*动态规划* *+* *空间优化*

### 算法分析

注意一下状态转移方程，新状态只和相邻的一个状态有关，所以可以用前文 [动态规划的降维打击：空间压缩技巧](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/dong-tai-g-a223e/dui-dong-t-8e7bf/)，不需要用整个 `dp` 数组，只需要一个变量储存相邻的那个状态就足够了，这样可以把空间复杂度降到 O(1):



### 代码

```java


/**
 * <p>
 * 买卖股票的最佳时机
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode121 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution01 solution01 = new Solution01();
        int maxProfit01 = solution01.maxProfit(prices);
        System.out.println(maxProfit01);

    }


    /**
     * 解法二：动态规划 + 空间优化
     */
    private static class Solution02 {

        public int maxProfit(int[] prices) {
            int n = prices.length;
            // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                // dp[i][1] = max(dp[i-1][1], -prices[i])
                dp_i_1 = Math.max(dp_i_1, -prices[i]);
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