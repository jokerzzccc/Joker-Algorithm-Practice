# 目录

[toc]

# leetcode-63-不同路径 II

- 时间：2023-08-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/unique-paths-ii/description/
- 难度：中等

一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg)

```
输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/11/04/robot2.jpg)

```
输入：obstacleGrid = [[0,1],[0,0]]
输出：1
```

 

**提示：**

+ `m == obstacleGrid.length`
+ `n == obstacleGrid[i].length`
+ `1 <= m, n <= 100`
+ `obstacleGrid[i][j]` 为 `0` 或 `1`





# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

![image-20230813214703999](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230813214703999.png)



### 代码

```java


/**
 * <p>
 * 不同路径 II
 * </p>
 *
 * @author admin
 * @date 2023/8/13
 */
public class leetcode63 {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.uniquePathsWithObstacles(obstacleGrid));

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.uniquePathsWithObstacles(obstacleGrid));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;

            // dp[i][j] 表示，(0,0) 到 (i,j) 的路径路数
            int[][] dp = new int[m][n];
            // 初始化第一列，只要碰到1，后面都无法到达（只能通过向下走）
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    break;
                }
                dp[i][0] = 1;
            }

            // 初始化第一行，只要碰到1，后面都无法到达（只能通过向右走）
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[0][j] == 1) {
                    break;
                }
                dp[0][j] = 1;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // 自动跳过路障(有路障的路径数都是0)
                    if (obstacleGrid[i][j] != 1) {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }

            return dp[m - 1][n - 1];
        }

    }

    /**
     * 解法二：动态规划 + 空间优化（滚动数组）
     */
    private static class Solution02 {

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[] dp = new int[n];

            dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[j] = 0;
                        continue;
                    }
                    if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                        dp[j] += dp[j - 1];
                    }
                }
            }

            return dp[n - 1];
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(nm)，其中 m 为网格的行数，n 为网格的列数。我们只需要遍历所有网格一次即可。

- 空间复杂度：O(n)。利用滚动数组优化，我们可以只用 O(n) 大小的空间来记录当前行的 dp 值。









# THE END