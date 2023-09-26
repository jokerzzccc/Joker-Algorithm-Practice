# 目录

[toc]

# leetcode-54-螺旋矩阵

- 时间：2023-06-12

- 参考链接：
  - [二维数组的花式遍历技巧](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/er-wei-shu-150fb/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/spiral-matrix/
- 难度：中等



给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。



提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= `matrix[i][j]` <= 100







# 2、题解

## 题目分析



## 解法一: 按层模拟

### 算法分析

**解题的核心思路是按照右、下、左、上的顺序遍历数组，并使用四个变量圈定未遍历元素的边界**

随着螺旋遍历，相应的边界会收缩，直到螺旋遍历完整个数组：



### 代码

```java

/**
 * <p>
 * 螺旋矩阵
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode54 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        Solution01 solution01 = new Solution01();
        List<Integer> integers01 = solution01.spiralOrder(matrix);
        integers01.forEach(System.out::print);
    }

    /**
     * 解法一：按层模拟，定义四个顶点变量
     */
    private static class Solution01 {

        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int upper_bound = 0, lower_bound = m - 1;
            int left_bound = 0, right_bound = n - 1;
            List<Integer> res = new LinkedList<>();
            // res.size() == m * n 则遍历完整个数组
            // 即，四条边按上右下左的顺时针方向遍历
            while (res.size() < m * n) {
                // 上边界，在顶部从左向右遍历
                if (upper_bound <= lower_bound) {
                    for (int i = left_bound; i <= right_bound; i++) {
                        res.add(matrix[upper_bound][i]);
                    }
                    // 上边界下移
                    upper_bound++;
                }
                // 右边界，在右侧从上向下遍历
                if (left_bound <= right_bound) {
                    for (int i = upper_bound; i <= lower_bound; i++) {
                        res.add(matrix[i][right_bound]);
                    }
                    // 右边界左移
                    right_bound--;
                }

                // 下边界，在下侧从右向左遍历
                if (upper_bound <= lower_bound) {
                    for (int i = right_bound; i >= left_bound; i--) {
                        res.add(matrix[lower_bound][i]);
                    }
                    // 下边界上移
                    lower_bound--;
                }

                // 左边界，在左侧从下向上遍历
                if (left_bound <= right_bound) {
                    for (int i = lower_bound; i >= upper_bound; i--) {
                        res.add(matrix[i][left_bound]);
                    }
                    // 左边界右移
                    left_bound++;
                }
            }

            return res;
        }

    }

}

```

输出：

```sh
 123698745
```





### 复杂度分析

- 时间复杂度：O(mm),其中m和n分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
- 空间复杂度：O(1)。除了输出数组以外，空间复杂度是常数。



# THE END