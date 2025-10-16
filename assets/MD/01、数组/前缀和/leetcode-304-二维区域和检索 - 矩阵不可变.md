# 目录

[toc]

# leetcode-304-二维区域和检索 - 矩阵不可变

- 时间：2023-06-11

- 参考链接：
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/range-sum-query-2d-immutable/
- 难度：中等



给定一个二维矩阵 matrix，以下类型的多个请求：

- 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。



实现 NumMatrix 类：

- NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
- int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。



提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
最多调用 104 次 sumRegion 方法





# 2、题解

## 题目分析



## 解法一: 二维数组的前缀和

### 算法分析

因为会多次调用 `sumRegion` 函数

这道题的最优解法是使用**前缀和**技巧，将 `sumRegion` 函数的时间复杂度降为 `O(1)`，说白了就是不要在 `sumRegion` 里面用 for 循环.

这是典型的「空间换时间」思路。



**前缀和主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和**。



### 代码

```java

/**
 * <p>
 * 二维区域和检索 - 矩阵不可变
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode304 {

    public static void main(String[] args) {

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        int sumRegion1 = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println(sumRegion1);

    }

    /**
     * 解法一：二维数组的前缀和
     */
    private static class NumMatrix {

        // 定义：preSum[i][j] 记录 matrix 中子矩阵 [0, 0, i-1, j-1] 的元素和
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            // 构造前缀和矩阵
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
                }
            }

        }

        /**
         * 计算子矩阵 [row1, col1, row2, col2] 的元素和
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 目标矩阵之和由四个相邻矩阵运算获得
            // 因为 row1, 是索引下标，而 preSum 的下标是表示前几个元素，
            // 所以，row/col + 1 才是对应的本来的 preSum, 而不 +1 就相当于减一了
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];

        }

    }

}

```

输出：

```sh
8
```





### 复杂度分析

- `sumRegion` 函数的时间复杂度也用前缀和技巧优化到了 O(1)





# THE END