# 目录

[toc]

# leetcode-1140-石子游戏 II

- 时间：2023-08-06
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/stone-game-ii/
- 难度：中等

爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 **排成一行**，每堆都有正整数颗石子 `piles[i]`。游戏以谁手中的石子最多来决出胜负。

爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，`M = 1`。

在每个玩家的回合中，该玩家可以拿走剩下的 **前** `X` 堆的所有石子，其中 `1 <= X <= 2M`。然后，令 `M = max(M, X)`。

游戏一直持续到所有石子都被拿走。

假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。

 

**示例 1：**

```
输入：piles = [2,7,9,4,4]
输出：10
解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
```

**示例 2:**

```
输入：piles = [1,2,3,4,5,100]
输出：104
```

 

**提示：**

+ `1 <= piles.length <= 100`
+ `1 <= piles[i] <= 10^4`



# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

![image-20230806201437806](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230806201437806.png)



### 代码

```java
package com.joker.algorithm;

/**
 * <p>
 * 石子游戏 II
 * </p>
 *
 * @author admin
 * @date 2023/8/6
 */
public class leetcode1140 {

    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.stoneGameII(piles));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int stoneGameII(int[] piles) {
            int len = piles.length;
            int sum = 0;

            // dp[i][j] 表示剩余 [i : len - 1] 堆时，M = j 的情况下，先取的人能获得的最多石子数
            int[][] dp = new int[len][len + 1];

            // 从后往前
            for (int i = len - 1; i >= 0; i--) {
                sum += piles[i];
                // 状态转移
                for (int M = 1; M <= len; M++) {
                    if (i + 2 * M >= len) {
                        dp[i][M] = sum;
                    } else {
                        for (int x = 1; x <= 2 * M; x++) {
                            dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                        }
                    }
                }

            }

            return dp[0][1];
        }

    }

}

```





### 复杂度分析











# THE END