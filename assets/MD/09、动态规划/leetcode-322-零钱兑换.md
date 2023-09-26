# 目录

[toc]

# leetcode-322-零钱兑换

- 时间：2023-05-21
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/coin-change/
- 难度：中等。

给你一个整数数组 `coins` ，表示不同面额的硬币；以及一个整数 `amount` ，表示总金额。

计算并返回可以凑成总金额所需的 **最少的硬币个数** 。如果没有任何一种硬币组合能组成总金额，返回 `-1` 。

你可以认为每种硬币的数量是无限的。



**示例 1：**

```
输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1
```

**示例 2：**

```
输入：coins = [2], amount = 3
输出：-1
```

**示例 3：**

```
输入：coins = [1], amount = 0
输出：0
```



**提示：**

+ `1 <= coins.length <= 12`
+ `1 <= coins[i] <= 231 - 1`
+ `0 <= amount <= 104`

Related Topics

广度优先搜索

数组

动态规划



# 2、题解

## 题目分析



## 解法一 动态规划

### 算法分析

当然，我们也可以**自底向上**使用 dp table 来消除重叠子问题，关于「状态」「选择」和 base case 与之前没有区别，`dp` 数组的定义和刚才 `dp` 函数类似，也是把「状态」，也就是目标金额作为变量。不过 `dp` 函数体现在函数参数，而 `dp` 数组体现在数组索引：

**`dp` 数组的定义：当目标金额为 `i` 时，至少需要 `dp[i]` 枚硬币凑出**。



我们采用**自下而上**的方式进行思考。仍定义 *F*(*i*) 为组成金额 *i* 所需最少的硬币数量，假设在计算 *F*(*i*) 之前，我们已经计算出 *F*(0)−*F*(*i*−1) 的答案。 则 *F*(*i*) 对应的**转移方程**应为

*F*(*i*)=min*j*=0…*n*−1*F*(*i*−*c**j*)+1

其中$c_j$代表的是第 *j* 枚硬币的面值，即我们枚举最后一枚硬币面额是 $c_j$，那么需要从 *i*−*$c_j$* 这个金额的状态 *F*(*i*−*$c_j$*) 转移过来，再算上枚举的这枚硬币数量 1 的贡献，由于要硬币数量最少，所以 *F*(*i*) 为前面能转移过来的状态的最小值加上枚举的硬币数量 1 。

则，当 i==0 时无法用硬币组成，为 0 。当 i*<0 时，忽略F(i)



### 代码 debug 版

```java
/**
 * <p>
 * 零钱兑换
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/21
 */
public class leetcode322 {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        Solution solution = new Solution();
        int res = solution.coinChange(coins, amount);
        System.out.println(res);
    }

    private static class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            int max = amount + 1;
            // 数组大小为 amount + 1，初始值也为 amount + 1
            Arrays.fill(dp, amount + 1);
            // base case
            dp[0] = 0;

            // 外层 for 循环在遍历所有状态的所有取值
            for (int i = 0; i < dp.length; i++) {
                // 内层 for 循环再求所有选择的最小值
                for (int coin : coins) {
                    // 子问题无解，跳过
                    if (i - coin < 0) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], 1 + dp[i -coin]);
                }
            }

            return (dp[amount] == amount + 1) ? -1 : dp[amount];

        }
    }

}
```

- **`dp` 数组的定义：当目标金额为 `i` 时，至少需要 `dp[i]` 枚硬币凑出**。
- 
- 为啥 `dp` 数组中的值都初始化为 `amount + 1` 呢，因为凑成 `amount` 金额的硬币数最多只可能等于 `amount`（全用 1 元面值的硬币），所以初始化为 `amount + 1` 就相当于初始化为正无穷，便于后续取最小值。
- 为啥不直接初始化为 int 型的最大值 `Integer.MAX_VALUE` 呢？因为后面有 `dp[i - coin] + 1`，这就会导致整型溢出。



### 复杂度分析

- 时间复杂度：*O*(*S**n*)，其中 *S* 是金额，*n* 是面额数。我们一共需要计算 *O*(*S*) 个状态，*S* 为题目所给的总金额。对于每个状态，每次需要枚举 *n* 个面额来转移状态，所以一共需要 *O*(*S**n*) 的时间复杂度。

- 空间复杂度：*O*(*S*)。数组 *dp* 需要开长度为总金额 *S* 的空间。





## 解法二：记忆递归

### 算法分析

当然可以，利用动态规划，我们可以在多项式的时间范围内求解。首先，我们定义：

+ *F*(*S*)：组成金额 *S* 所需的最少硬币数量
+ [*c*0…*c**n*−1] ：可选的 *n* 枚硬币面额值

我们注意到这个问题有一个最优的子结构性质，这是解决动态规划问题的关键。最优解可以从其子问题的最优解构造出来。如何将问题分解成子问题？假设我们知道 *F*(*S*)，即组成金额 *S* 最少的硬币数，最后一枚硬币的面值是 *C*。那么由于问题的最优子结构，转移方程应为：

*F*(*S*)=*F*(*S*−*C*)+1

但我们不知道最后一枚硬币的面值是多少，所以我们需要枚举每个硬币面额值 *c*0,*c*1,*c*2…*c**n*−1 并选择其中的最小值。下列递推关系成立：

*F*(*S*)=*i*=0...*n*−1min*F*(*S*−*c**i*)+1 subject to *S*−*c**i*≥0

*F*(*S*)=0 ,when *S*=0

*F*(*S*)=−1 ,when *n*=0





### 代码：debug 版

```java
public class leetcode322 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        // 法一：动态规划
//        Solution solution = new Solution();
//        int res = solution.coinChange(coins, amount);

        // 法二：记忆递归
        Solution02 solution02 = new Solution02();
        int res = solution02.coinChange(coins, amount);

        System.out.println(res);
    }

    private static class Solution02 {

        // 记忆递归版
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            if ((coins == null && coins.length == 0) || amount < 0) {
                return -1;
            }
            int[] memo = new int[amount];
            return coinChange(coins, amount, memo);
        }

        int coinChange(int[] coins, int curAmount, int[] memo) {
            if (curAmount < 0) {
                return -1;
            }
            // base case
            if (curAmount == 0) {
                return 0;
            }
            // 查备忘录，防止重复计算
            if (memo[curAmount - 1] != 0) {
                return memo[curAmount - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 计算子问题的结果
                int subRes = coinChange(coins, curAmount - coin, memo);
                // 子问题无解则跳过
                if (subRes == -1) {
                    continue;
                }
                // 在子问题中选择最优解，然后加一
                min = Math.min(min, subRes + 1);
            }
            // 把计算结果存入备忘录
            memo[curAmount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return memo[curAmount - 1];
        }

    }
}
```



### 复杂度分析

- 时间复杂度：*O*(*Sn*)，其中 *S* 是金额，*n* 是面额数。我们一共需要计算 *S* 个状态的答案，且每个状态 *F*(*S*) 由于上面的记忆化的措施只计算了一次，而计算一个状态的答案需要枚举 *n* 个面额值，所以一共需要 *O*(*Sn*) 的时间复杂度。

- 空间复杂度：*O*(*S*)，我们需要额外开一个长为 *S* 的数组来存储计算出来的答案 *F*(*S*) 。





# THE END