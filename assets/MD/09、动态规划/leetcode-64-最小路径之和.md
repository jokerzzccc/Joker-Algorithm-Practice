# 目录

[toc]

# leetcode-64-最小路径之和

- 时间：2023-06-01
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-path-sum/
- 难度：中等



给定一个包含非负整数的 `*m* x *n*` 网格 `grid` ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。



**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg)

```
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
```

**示例 2：**

```
输入：grid = [[1,2,3],[4,5,6]]
输出：12
```



**提示：**

+ `m == grid.length`
+ `n == grid[i].length`
+ `1 <= m, n <= 200`
+ `0 <= grid[i][j] <= 200`

Related Topics

数组

动态规划

矩阵



# 2、题解

## 题目分析



## 解法一：动态规划+备忘录+自顶向下

### 算法分析

**一般来说，让你在二维矩阵中求最优化问题（最大值或者最小值），肯定需要递归 + 备忘录，也就是动态规划技巧**。



![image-20230604185554503](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230604185554503.png)

把「从 `D` 走到 `B` 的最小路径和」这个问题转化成了「从 `D` 走到 `A` 的最小路径和」和 「从 `D` 走到 `C` 的最小路径和」这两个问题。



这个 `dp` 函数的定义如下：

```java
int dp(int[][] grid, int i, int j);

```

**从左上角位置 `(0, 0)` 走到位置 `(i, j)` 的最小路径和为 `dp(grid, i, j)`**。





### 代码

```java
/**
 * <p>
 * 最小路径和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/4
 */
public class leetcode64 {
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        Solution01 solution01 = new Solution01();
        int minPathSum01 = solution01.minPathSum(grid);
        System.out.println(minPathSum01);

        Solution02 solution02 = new Solution02();
        int minPathSum02 = solution02.minPathSum(grid);
        System.out.println(minPathSum02);

    }

    /**
     * 动态规划+备忘录
     * 自顶向下
     */
    private static class Solution01 {

        /**
         * 备忘录
         */
        int[][] memo;

        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(grid, m - 1, n - 1);
        }


        /**
         * dp 函数的定义：从左上角位置（0,0）走到位置（i,j）的最小路径和为 dp(int[][] grid, int i, int j)
         */
        int dp(int[][] grid, int i, int j) {

            if (i == 0 && j == 0) {
                return grid[0][0];
            }

            if (i < 0 || j < 0) {
                return Integer.MAX_VALUE;
            }
            // 避免重复计算
            if (memo[i][j] != -1) {
                return memo[i][j];
            }
            memo[i][j] = Math.min(
                    dp(grid, i - 1, j),
                    dp(grid, i, j - 1)
            ) + grid[i][j];

            return memo[i][j];
        }
    }

    /**
     * 自底向上
     */
    private static class Solution02 {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];


            // base case
            dp[0][0] = grid[0][0];

            for (int i = 1; i < m; i++)
                dp[i][0] = dp[i - 1][0] + grid[i][0];

            for (int j = 1; j < n; j++)
                dp[0][j] = dp[0][j - 1] + grid[0][j];

            //
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            return dp[m - 1][n - 1];
        }
    }
}
```



### 复杂度分析

- 时间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是网格的行数和列数。需要对整个网格遍历一次，计算 *dp* 的每个元素的值。

- 空间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是网格的行数和列数。创建一个二维数组 *dp*，和网格大小相同。
  - 空间复杂度可以优化，例如每次只存储上一行的 *dp* 值，则可以将空间复杂度优化到 *O*(*n*)。









# THE END