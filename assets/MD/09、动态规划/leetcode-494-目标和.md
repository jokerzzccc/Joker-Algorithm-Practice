# 目录

[toc]

# leetcode-494-目标和

- 时间：2023-05-31
- 参考链接：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/bei-bao-le-34bd4/dong-tai-g-35341/
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/target-sum/
- 难度：中等



给你一个整数数组 `nums` 和一个整数 `target` 。

向数组中的每个整数前添加 `'+'` 或 `'-'` ，然后串联起所有整数，可以构造一个 **表达式** ：

+ 例如，`nums = [2, 1]` ，可以在 `2` 之前添加 `'+'` ，在 `1` 之前添加 `'-'` ，然后串联起来得到表达式 `"+2-1"` 。

返回可以通过上述方法构造的、运算结果等于 `target` 的不同 **表达式** 的数目。



**示例 1：**

```sh
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
```

**示例 2：**

```sh
输入：nums = [1], target = 1
输出：1
```



**提示：**

+ `1 <= nums.length <= 20`
+ `0 <= nums[i] <= 1000`
+ `0 <= sum(nums[i]) <= 1000`
+ `-1000 <= target <= 1000`

Related Topics

数组

动态规划

回溯



# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

其实，这个问题可以转化为一个子集划分问题，而子集划分问题又是一个典型的背包问题。动态规划总是这么玄学，让人摸不着头脑……

首先，如果我们把 `nums` 划分成两个子集 `A` 和 `B`，分别代表分配 `+` 的数和分配 `-` 的数，那么他们和 `target` 存在如下关系：

```python
sum(A) - sum(B) = target
sum(A) = target + sum(B)
sum(A) + sum(A) = target + sum(B) + sum(A)
2 * sum(A) = target + sum(nums)
```

综上，可以推出 `sum(A) = (target + sum(nums)) / 2`，也就是把原问题转化成：**`nums` 中存在几个子集 `A`，使得 `A` 中元素的和为 `(target + sum(nums)) / 2`**？



**第一步要明确两点，「状态」和「选择」**。

对于背包问题，这个都是一样的，状态就是「背包的容量」和「可选择的物品」，选择就是「装进背包」或者「不装进背包」。

**第二步要明确 `dp` 数组的定义**。

按照背包问题的套路，可以给出如下定义：

`dp[i][j] = x` 表示，若只在前 `i` 个物品中选择，若当前背包的容量为 `j`，则最多有 `x` 种方法可以恰好装满背包。

翻译成我们探讨的子集问题就是，若只在 `nums` 的前 `i` 个元素中选择，若目标和为 `j`，则最多有 `x` 种方法划分子集。

根据这个定义，显然 `dp[0][..] = 0`，因为没有物品的话，根本没办法装背包；但是 `dp[0][0]` 应该是个例外，因为如果背包的最大载重为 0，「什么都不装」也算是一种装法，即 `dp[0][0] = 1`。

我们所求的答案就是 `dp[N][sum]`，即使用所有 `N` 个物品，有几种方法可以装满容量为 `sum` 的背包。



**第三步，根据「选择」，思考状态转移的逻辑**。

回想刚才的 `dp` 数组含义，可以根据「选择」对 `dp[i][j]` 得到以下状态转移：

如果不把 `nums[i]` 算入子集，**或者说你不把这第 `i` 个物品装入背包**，那么恰好装满背包的方法数就取决于上一个状态 `dp[i-1][j]`，继承之前的结果。

如果把 `nums[i]` 算入子集，**或者说你把这第 `i` 个物品装入了背包**，那么只要看前 `i - 1` 个物品有几种方法可以装满 `j - nums[i-1]` 的重量就行了，所以取决于状态 `dp[i-1][j-nums[i-1]]`。



**由于 `dp[i][j]` 为装满背包的总方法数，所以应该以上两种选择的结果求和，得到状态转移方程**：



```java
dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
```



### 代码一：二维数组

```java

/**
 * <p>
 * 目标和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/31
 */
public class leetcode494 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        Solution01 solution01 = new Solution01();
        int targetSumWays01 = solution01.findTargetSumWays(nums, target);
        System.out.println(targetSumWays01);

        Solution011 solution011 = new Solution011();
        int targetSumWays011 = solution011.findTargetSumWays(nums, target);
        System.out.println(targetSumWays011);

    }

    /**
     * 解法一：动态规划 + 二维数组
     * 转化为背包问题(子集划分)
     * <p>
     * 原理：
     * 把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，那么他们和 target 存在如下关系
     * sum(A) - sum(B) = target;
     * sum(A) = target + sum(B);
     * sum(A) + sum(A) = target + sum(B) + sum(A);
     * 2 * sum(A) = target + sum(nums);
     * </p>
     */
    private static class Solution01 {

        public int findTargetSumWays(int[] nums, int target) {
            int sum = Arrays.stream(nums).sum();
            // 这两种情况，不可能存在合法的子集划分
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                return 0;
            }
            return subsets(nums, (sum + target) / 2);
        }

        /**
         * 计算 nums 中有几个子集的和为 sum
         */
        private int subsets(int[] nums, int sum) {
            int n = nums.length;
            int[][] dp = new int[n + 1][sum + 1];
            // base case
            dp[0][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    if (j >= nums[i - 1]) {
                        // 两种选择的结果之和
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    } else {
                        // 背包的空间不足，只能选择不装物品 i
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n][sum];
        }

    }

    /**
     * 解法一优化：动态规划 + 一维数组
     */
    private static class Solution011 {

        public int findTargetSumWays(int[] nums, int target) {
            int sum = Arrays.stream(nums).sum();
            // 这两种情况，不可能存在合法的子集划分
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                return 0;
            }
            return subsets(nums, (sum + target) / 2);
        }

        /**
         * 计算 nums 中有几个子集的和为 sum
         */
        private int subsets(int[] nums, int sum) {
            int n = nums.length;
            int[] dp = new int[sum + 1];
            // base case
            dp[0] = 1;

            for (int i = 1; i <= n; i++) {
                // j 要从后往前遍历
                for (int j = sum; j >= 0; j--) {
                    if (j >= nums[i - 1]) {
                        // 两种选择的结果之和
                        dp[j] = dp[j] + dp[j - nums[i - 1]];
                    } else {
                        // 背包的空间不足，只能选择不装物品 i
                        dp[j] = dp[j];
                    }
                }
            }
            return dp[sum];
        }

    }

}

```



### 复杂度分析

![image-20230604180801810](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230604180801810.png)









# THE END