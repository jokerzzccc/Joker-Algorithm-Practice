# 目录

[toc]

# leetcode-309-最佳买卖股票时机含冷冻期

- 时间：2023-06-24
- 参考链接：
  - [一个方法团灭 LeetCode 股票买卖问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-3b01b/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
- 难度：中等



给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

- 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。



注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。





**提示：**

+ `1 <= prices.length <= 5000`
+ `0 <= prices[i] <= 1000`



# 2、题解

## 题目分析

和 leetcode122 一样的，只不过每次 `sell` 之后要等一天才能继续交易，只要把这个特点融入上一题的状态转移方程即可：

```python
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
```

翻译成代码：



## 解法一：动态规划

### 算法分析



### 代码

```java

package com.joker.algorithm.dp;

/**
 * <p>
 * 最佳买卖股票时机含冷冻期
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode309 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};

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
                // base case 1
                if (i - 1 == -1) {

                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                // base case 2
                if (i - 2 == -1) {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                    // i - 2 小于 0 时根据状态转移方程推出对应 base case
                    dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                    //   dp[i][1]
                    // = max(dp[i-1][1], dp[-1][0] - prices[i])
                    // = max(dp[i-1][1], 0 - prices[i])
                    // = max(dp[i-1][1], -prices[i])
                    continue;
                }

                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
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
package com.joker.algorithm.dp;

/**
 * <p>
 * 最佳买卖股票时机含冷冻期
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode309 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};

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
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            // 代表 dp[i-2][0]
            int dp_pre_0 = 0;
            for (int i = 0; i < n; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
                dp_pre_0 = temp;
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