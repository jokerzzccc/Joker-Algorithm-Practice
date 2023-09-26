# 目录

[toc]

# leetcode-59-螺旋矩阵 II

- 时间：2023-06-12

- 参考链接：
  - [二维数组的花式遍历技巧](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/er-wei-shu-150fb/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/spiral-matrix-ii/submissions/
- 难度：中等



给你一个正整数 `n` ，生成一个包含 `1` 到 `n2` 所有元素，且元素按顺时针顺序螺旋排列的 `n x n` 正方形矩阵 `matrix` 。



**提示：**

+ `1 <= n <= 20`







# 2、题解

## 题目分析



## 解法一: 按层模拟

### 算法分析

类比，leetcode-54，反过来就是了



### 代码

```java


/**
 * <p>
 * 螺旋矩阵 II
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode59 {

    public static void main(String[] args) {
        int n = 3;

        Solution01 solution01 = new Solution01();
        int[][] matrix01 = solution01.generateMatrix(n);
        for (int[] row : matrix01) {
            Arrays.stream(row).forEach(System.out::print);
            System.out.println("\n");
        }

    }

    /**
     * 解法一：类比，leetcode54
     */
    private static class Solution01 {

        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int upper_bound = 0, lower_bound = n - 1;
            int left_bound = 0, right_bound = n - 1;
            // 需要填入矩阵的数字
            int num = 1;
            while (num <= n * n) {
                if (upper_bound <= lower_bound) {
                    // 在顶部从左向右遍历
                    for (int j = left_bound; j <= right_bound; j++) {
                        matrix[upper_bound][j] = num++;
                    }
                    // 上边界下移
                    upper_bound++;
                }

                if (left_bound <= right_bound) {
                    // 在右侧从上向下遍历
                    for (int i = upper_bound; i <= lower_bound; i++) {
                        matrix[i][right_bound] = num++;
                    }
                    // 右边界左移
                    right_bound--;
                }

                if (upper_bound <= lower_bound) {
                    // 在底部从右向左遍历
                    for (int j = right_bound; j >= left_bound; j--) {
                        matrix[lower_bound][j] = num++;
                    }
                    // 下边界上移
                    lower_bound--;
                }

                if (left_bound <= right_bound) {
                    // 在左侧从下向上遍历
                    for (int i = lower_bound; i >= upper_bound; i--) {
                        matrix[i][left_bound] = num++;
                    }
                    // 左边界右移
                    left_bound++;
                }

            }

            return matrix;
        }

    }

}

```

输出：

```sh
123

894

765
```





### 复杂度分析

- 时间复杂度：O($n^2$),其中*n* 是给定的正整数。矩阵的大小是 n*×*n，需要填入矩阵中的每个元素。
- 空间复杂度：O(1)。除了返回的矩阵以外，空间复杂度是常数。



# THE END