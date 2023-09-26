# 目录

[toc]

# leetcode-174-地下城游戏

- 时间：2023-06-04
- 参考链接：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/dong-tai-g-f43a3/
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/dungeon-game/solution/di-xia-cheng-you-xi-by-leetcode-solution/
- 难度：困难



恶魔们抓住了公主并将她关在了地下城 `dungeon` 的 **右下角** 。地下城是由 `m x n` 个房间组成的二维网格。我们英勇的骑士最初被安置在 **左上角** 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为*负整数*，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 *0*），要么包含增加骑士健康点数的魔法球（若房间里的值为*正整数*，则表示骑士将增加健康点数）。

为了尽快解救公主，骑士决定每次只 **向右** 或 **向下** 移动一步。

返回确保骑士能够拯救到公主所需的最低初始健康点数。

**注意：**任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。



**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/03/13/dungeon-grid-1.jpg)

```
输入：dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
输出：7
解释：如果骑士遵循最佳路径：右 -> 右 -> 下 -> 下 ，则骑士的初始健康点数至少为 7 。
```

**示例 2：**

```
输入：dungeon = [[0]]
输出：1
```



**提示：**

+ `m == dungeon.length`
+ `n == dungeon[i].length`
+ `1 <= m, n <= 200`
+ `-1000 <= dungeon[i][j] <= 1000`

Related Topics

数组

动态规划

矩阵



# 2、题解

## 题目分析



## 解法一

### 算法分析

**dp 函数定义：**

```java
int dp(int[][] grid, int i, int j);
```

**从 `grid[i][j]` 到达终点（右下角）所需的最少生命值是 `dp(grid, i, j)`**。

这样定义的话，base case 就是:

```java
int dp(int[][] grid, int i, int j) {
    int m = grid.length;
    int n = grid[0].length;
    // base case
    if (i == m - 1 && j == n - 1) {
        return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
    }
    ...
}
```

- **为了简洁，之后 `dp(grid, i, j)` 就简写为 `dp(i, j)`**

根据新的 `dp` 函数定义和 base case，我们想求 `dp(0, 0)`，那就应该试图通过 `dp(i, j+1)` 和 `dp(i+1, j)` 推导出 `dp(i, j)`，这样才能不断逼近 base case，正确进行状态转移。



**状态转移方程：**

```java
int res = min(
    dp(i + 1, j),
    dp(i, j + 1)
) - grid[i][j];

dp(i, j) = res <= 0 ? 1 : res;

```



根据这个核心逻辑，加一个备忘录消除重叠子问题，就可以直接写出最终的代码了



### 代码

```java
/**
 * <p>
 * 地下城游戏
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/4
 */
public class leetcode174 {

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        Solution01 solution01 = new Solution01();
        int minimumHP01 = solution01.calculateMinimumHP(dungeon);
        System.out.println(minimumHP01);
    }

    /**
     * 动态规划+备忘录
     */
    private static class Solution01 {

        int[][] memo;

        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(dungeon, 0, 0);
        }

        /**
         * 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少
         */
        private int dp(int[][] dungeon, int i, int j) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            // base case
            if (i == m - 1 && j == n - 1) {
                return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
            }
            if (i == m || j == n) {
                return Integer.MAX_VALUE;
            }
            // 避免重复计算
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            // 状态转移逻辑
            int res = Math.min(
                    dp(dungeon, i, j + 1),
                    dp(dungeon, i + 1, j)
            ) - dungeon[i][j];
            // 骑士的生命值至少为 1
            memo[i][j] = res <= 0 ? 1 : res;

            return memo[i][j];
        }

    }

}
```



### 复杂度分析

- 时间复杂度：O(MN). 其中 N*,*M 为给定矩阵的长宽。
- 空间复杂度：O(MN).其中 N*,*M 为给定矩阵的长宽，注意这里可以利用滚动数组进行优化，优化后空间复杂度可以达到 O(N)。









# THE END