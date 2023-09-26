# 目录

[toc]

# leetcode-1139-最大的以 1 为边界的正方形

- 时间：2023-07-11
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/largest-1-bordered-square/
- 难度：中等

给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 **正方形** 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。

 

示例 1：

```sh
输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
输出：9
```

示例 2：

```sh
输入：grid = [[1,1,0,0]]
输出：1
```




提示：

- 1 <= grid.length <= 100
- 1 <= grid[0].length <= 100
- `grid[i][j] 为 0 或 1`





# 2、题解

## 题目分析

- 参考链接：https://leetcode.cn/problems/largest-1-bordered-square/solution/shu-ju-jie-gou-he-suan-fa-zui-da-de-yi-1-8l94/
- 



这题解题思路是这样的

- 第一步先计算每个网格中**横向和竖向**连续1的个数。
- 第二步遍历二维网格，以**每一个格子为正方形的右下角**，分别找出上边和左边连续1的个数，取最小值作为正方形的边长，然后判断正方形的左边和上边长度是否都大于等于正方形边长，如果都大于等于正方形边长就更新正方形的最大边长，否则缩小正方形的边长，继续判断……。





## 解法一: 动态规划

### 算法分析





### 代码

```java


/**
 * <p>
 * 最大的以 1 为边界的正方形
 * </p>
 *
 * @author admin
 * @date 2023/7/11
 */
public class leetcode1139 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.largest1BorderedSquare(grid));

    }

    /**
     * 解法一：动态规划
     * 核心点，就是以每一个单元格为正方形的右下顶点，这样才方便 dp 自底向上的状态转移
     */
    private static class Solution01 {

        public int largest1BorderedSquare(int[][] grid) {
            int m = grid.length, n = grid[0].length;

            // (i,j)左方连续1的个数
            int[][] left = new int[m + 1][n + 1];
            // (i,j)上方连续1的个数
            int[][] up = new int[m + 1][n + 1];

            // 记录正方形的最大边长
            int maxBorder = 0;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (grid[i - 1][j - 1] == 1) {
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = up[i - 1][j] + 1;
                        // 取左方和上方连续1的较小值作为临时边界
                        // curBorder 可以认为是以（i, j）为右下顶点的正方形 的 下边和右边的长度，
                        // 我们还需要根据正方形上边和左边的长度，来确认是否满足正方形的条件
                        int curBorder = Math.min(left[i][j], up[i][j]);
                        // 判断正方形的左边和上边的长度是否大于curSide，如果不大于，我们就缩小正方形
                        // 的长度 curBorder，然后继续判断
                        while (left[i - curBorder + 1][j] < curBorder || up[i][j - curBorder + 1] < curBorder) {
                            curBorder--;
                        }
                        maxBorder = Math.max(maxBorder, curBorder);
                    }

                }
            }

            return maxBorder * maxBorder;
        }

    }

}

```

输出：

```sh
9
```





### 复杂度分析

- 时间复杂度：`O(m×n×min(m,n)`,其中m表示矩阵的行数，n表示矩阵的列数。
- 空间复杂度：`O(m*n)`,其中m表示矩阵的行数，n表示矩阵的列数。需要保存矩阵中每个位置的最
  长连续1的数目，需要的空间为`O(m*n*2)`









# THE END