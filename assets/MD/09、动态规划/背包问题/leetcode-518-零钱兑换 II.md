# 目录

[toc]

# leetcode-518-零钱兑换 II

- 时间：2023-05-30
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/coin-change-ii/
- 难度：中等



给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

请你计算并返回**可以凑成总金额的硬币组合数**。如果任何硬币组合都无法凑出总金额，返回 0 。

假设每一种面额的硬币有无限个。 

题目数据保证结果符合 32 位带符号整数。



# 2、题解

## 题目分析

有一个背包，最大容量为 `amount`，有一系列物品 `coins`，每个物品的重量为 `coins[i]`，**每个物品的数量无限**。请问有多少种方法，能够把背包恰好装满？

这个问题和我们前面讲过的两个背包问题，有一个最大的区别就是，每个物品的数量是无限的，这也就是传说中的「**完全背包问题**」，没啥高大上的，无非就是状态转移方程有一点变化而已。





## 解法一：动态规划+二维数组

### 算法分析

**第一步要明确两点，「状态」和「选择」**。

状态有两个，就是「背包的容量」和「可选择的物品」，选择就是「装进背包」或者「不装进背包」嘛，背包问题的套路都是这样。

**第二步要明确 `dp` 数组的定义**。

首先看看刚才找到的「状态」，有两个，也就是说我们需要一个二维 `dp` 数组。

`dp[i][j]` 的定义如下：

若只使用前 `i` 个物品（可以重复使用），当背包容量为 `j` 时，有 `dp[i][j]` 种方法可以装满背包。

换句话说，翻译回我们题目的意思就是：

**若只使用 `coins` 中的前 `i` 个（`i` 从 1 开始计数）硬币的面值，若想凑出金额 `j`，有 `dp[i][j]` 种凑法**。

经过以上的定义，可以得到：

base case 为 `dp[0][..] = 0, dp[..][0] = 1`。`i = 0` 代表不使用任何硬币面值，这种情况下显然无法凑出任何金额；`j = 0` 代表需要凑出的目标金额为 0，那么什么都不做就是唯一的一种凑法。

我们最终想得到的答案就是 `dp[N][amount]`，其中 `N` 为 `coins` 数组的大小。

**第三步，根据「选择」，思考状态转移的逻辑**。

注意，我们这个问题的特殊点在于物品的数量是无限的，所以这里和之前写的 [0-1 背包问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/bei-bao-le-34bd4/jing-dian--28f3c/) 文章有所不同。

**如果你不把这第 `i` 个物品装入背包**，也就是说你不使用 `coins[i-1]` 这个面值的硬币，那么凑出面额 `j` 的方法数 `dp[i][j]` 应该等于 `dp[i-1][j]`，继承之前的结果。

**如果你把这第 `i` 个物品装入了背包**，也就是说你使用 `coins[i-1]` 这个面值的硬币，那么 `dp[i][j]` 应该等于 `dp[i][j-coins[i-1]]`。



由于定义中的 `i` 是从 1 开始计数的，所以 `coins` 的索引是 `i-1` 时表示第 `i` 个硬币的面值。

`dp[i][j-coins[i-1]]` 也不难理解，如果你决定使用这个面值的硬币，那么就应该关注如何凑出金额 `j - coins[i-1]`。



比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的硬币，不就可以凑出 5 了嘛。

**综上就是两种选择，而我们想求的 `dp[i][j]` 是「共有多少种凑法」，所以 `dp[i][j]` 的值应该是以上两种选择的结果之和**：

```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= amount; j++) {
        if (j - coins[i-1] >= 0)
            dp[i][j] = dp[i - 1][j] 
                     + dp[i][j-coins[i-1]];
return dp[N][W]
```



有的读者在这里可能会有疑问，不是说可以重复使用硬币吗？那么如果我确定「使用第 `i` 个面值的硬币」，我怎么确定这个面值的硬币被使用了多少枚？简单的一个 `dp[i][j-coins[i-1]]` 可以包含重复使用第 `i` 个硬币的情况吗？

对于这个问题，建议你再仔回头细阅读一下我们对 `dp` 数组的定义，然后把这个定义代入 `dp[i][j-coins[i-1]]` 看看：



若只使用前 `i` 个物品（可以重复使用），当背包容量为 `j-coins[i-1]` 时，有 `dp[i][j-coins[i-1]]` 种方法可以装满背包。

看到了吗，`dp[i][j-coins[i-1]]` 也是允许你使用第 `i` 个硬币的，所以说已经包含了重复使用硬币的情况，你一百个放心。

### 代码

```java
/**
 * <p>
 * 零钱兑换 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/30
 */
public class leetcode518 {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        Solution01 solution01 = new Solution01();
        int change01 = solution01.change(amount, coins);
        System.out.println(change01);

    }

    /**
     * 解法一：动态规划 + 二维数组
     */
    private static class Solution01 {

        public int change(int amount, int[] coins) {
            int n = coins.length;
            // dp 定义：若只使用 `coins` 中的前 `i` 个（`i` 从 1 开始计数）硬币的面值，若想凑出金额 `j`，有 `dp[i][j]` 种凑法
            int[][] dp = new int[n + 1][amount + 1];
            // base case
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= amount; j++) {
                    if (j - coins[i - 1] >= 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[n][amount];
        }

    }

}
```



### 复杂度分析

- 时间复杂度：O(amount×n),其中 amount 是总金额，n 是数组 coins 的长度。需要使用数组 coins 中
  的每个元素遍历并更新数组 dp 中的每个元素的值。
- 空间复杂度：O(amountxn)。



## 解法二：动态规划+一维数组（空间优化）

### 算法分析



### 代码

```java
/**
 * <p>
 * 零钱兑换 II
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/30
 */
public class leetcode518 {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        Solution02 solution02 = new Solution02();
        int change02 = solution02.change(amount, coins);
        System.out.println(change02);

    }

    /**
     * 解法二：动态规划 + 一维数组（空间优化）
     */
    private static class Solution02 {

        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1; // base case
            for (int coin : coins) {
                for (int j = 1; j <= amount; j++) {
                    if (j - coin >= 0) {
                        dp[j] = dp[j] + dp[j - coin];
                    }
                }
            }

            return dp[amount];
        }

    }

}
```

### 复杂度分析

- 时间复杂度：O(amount×n),其中 amount 是总金额，n 是数组 coins 的长度。需要使用数组 coins 中
  的每个元素遍历并更新数组 dp 中的每个元素的值。
- 空间复杂度：O(amount),其中 amount 是总金额。需要创建长度为 amount+1 的数组 dp。



# THE END