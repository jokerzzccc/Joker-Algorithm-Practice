# 目录

[toc]

# leetcode-1605-给定行和列的和求可行矩阵

- 时间：2023-08-22
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums/description/
- 难度：中等

给你两个非负整数数组 `rowSum` 和 `colSum` ，其中 `rowSum[i]` 是二维矩阵中第 `i` 行元素的和， `colSum[j]` 是第 `j` 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。

请找到大小为 `rowSum.length x colSum.length` 的任意 **非负整数** 矩阵，且该矩阵满足 `rowSum` 和 `colSum` 的要求。

请你返回任意一个满足题目要求的二维矩阵，题目保证存在 **至少一个** 可行矩阵。

 

**示例 1：**

```
输入：rowSum = [3,8], colSum = [4,7]
输出：[[3,0],
      [1,7]]
解释：
第 0 行：3 + 0 = 3 == rowSum[0]
第 1 行：1 + 7 = 8 == rowSum[1]
第 0 列：3 + 1 = 4 == colSum[0]
第 1 列：0 + 7 = 7 == colSum[1]
行和列的和都满足题目要求，且所有矩阵元素都是非负的。
另一个可行的矩阵为：[[1,2],
                  [3,5]]
```

**示例 2：**

```
输入：rowSum = [5,7,10], colSum = [8,6,8]
输出：[[0,5,0],
      [6,1,0],
      [2,0,8]]
```

**示例 3：**

```
输入：rowSum = [14,9], colSum = [6,9,8]
输出：[[0,9,5],
      [6,0,3]]
```

**示例 4：**

```
输入：rowSum = [1,0], colSum = [1]
输出：[[1],
      [0]]
```

**示例 5：**

```
输入：rowSum = [0], colSum = [0]
输出：[[0]]
```

 

**提示：**

+ `1 <= rowSum.length, colSum.length <= 500`
+ `0 <= rowSum[i], colSum[i] <= 10^8`
+ `sum(rowSum) == sum(colSum)`



签名：

```java
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {

    }
}
```



# 2、题解

## 题目分析

隐藏推理：

- 因为题目保证有一个解，则，`sum(rowSum) == sum(colSum)`





## 解法一：贪心

### 算法分析





### 代码

```java


/**
 * <p>
 * 给定行和列的和求可行矩阵
 * </p>
 *
 * @author admin
 * @date 2023/8/22
 */
public class leetcode1065 {

    public static void main(String[] args) {
        int[] rowSum = {3, 8}, colSum = {4, 7};

        Solution01 solution01 = new Solution01();
        System.out.println(Arrays.deepToString(solution01.restoreMatrix(rowSum, colSum)));

        Solution02 solution02 = new Solution02();
        System.out.println(Arrays.deepToString(solution02.restoreMatrix(rowSum, colSum)));

    }

    /**
     * 解法一：贪心
     * 因为题目保证有一个解，就可以贪心，每次填入矩阵能填的 Math.min(rowSum[i], colSum[j])
     */
    private static class Solution01 {

        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int m = rowSum.length;
            int n = colSum.length;
            int[][] matrix = new int[m][n];

            // 遍历行
            for (int i = 0; i < m; i++) {
                // 遍历列
                for (int j = 0; j < n; j++) {
                    // 每次取较小的值填入矩阵
                    matrix[i][j] = Math.min(rowSum[i], colSum[j]);
                    rowSum[i] -= matrix[i][j];
                    colSum[j] -= matrix[i][j];
                }
            }

            return matrix;

        }

    }

    /**
     * 解法二：贪心 + 时间优化
     * 从左上角出发，每次要么去掉一行，要么去掉一列。
     */
    private static class Solution02 {

        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int m = rowSum.length;
            int n = colSum.length;
            int[][] matrix = new int[m][n];

            // 从左上角开始遍历
            for (int i = 0, j = 0; i < m && j < n; ) {
                int rs = rowSum[i], cs = colSum[j];
                // 取 min 值的时候，如果 rowSum 更小，那么该行右边的就全是0了，就可以跳过该行设值了
                if (rs < cs) { // 去掉第 i 行，往下走
                    colSum[j] -= rs;
                    matrix[i++][j] = rs;
                } else { // 去掉第 j 列，往右走
                    // 取 min 值的时候，如果 colSum 更小，那么该列下边的就全是0了，就可以跳过该列设值了
                    rowSum[i] -= cs;
                    matrix[i][j++] = cs;
                }
            }

            return matrix;

        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(mn)，其中 m 为矩阵行数，即 rowSum 的长度；n 为矩阵列数，即 colSum 的长度。如果忽略创建二维数组的时间，时间复杂度为 O(m+n)。
- 空间复杂度：O(1)。返回值不计入。







# THE END