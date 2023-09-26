# 目录

[toc]

# offer-04-二维数组中的查找

- 时间：2023-04-20





# 1、题目

- 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。



# 2、题解

## 法一

**思路：**

- 如下图所示，我们将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。因此，通过从 “根节点” 开始搜索，遇到比 target 大的元素就向左，反之向右，即可找到目标值 target 。
- 同时，也只能以**左下角，或者，右上角**元素为起始遍历点，因为这样才可以达到缩小搜查范围的目的。





**算法：**

1. 从矩阵 matrix 左下角元素（索引设为 (i, j) ）开始遍历，并与目标值对比：
   - 当 `matrix[i][j] > target` 时，执行 i-- ，即消去第 i 行元素；
   - 当 `matrix[i][j] < target` 时，执行 j++ ，即消去第 j 列元素；
   - 当 `matrix[i][j] = target` 时，返回 true ，代表找到目标值。
2. 若行索引或列索引越界，则代表矩阵中无目标值，返回 f**a**l**s**e。



**代码：**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}

```

- 时间复杂度：O（M + N）, M 与 N 分别为矩阵行数和列数
- 空间复杂度：O（1）。i,j 使用常熟大小额外空间。





