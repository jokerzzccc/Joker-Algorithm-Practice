# 目录

[toc]

# offer-04-二维数组中的查找

- 时间：2023-08-14
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/description/
- 难度：中等

在一个 n * m 的二维数组中，每一行都按照从左到右 **非递减** 的顺序排序，每一列都按照从上到下 **非递减** 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

**示例:**

现有矩阵 matrix 如下：

```sh
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

 

**限制：**

```sh
0 <= n <= 1000
0 <= m <= 1000
```



# 2、题解

## 题目分析



## 解法一：标志数

### 算法分析

- 参考链接：

如下图所示，我们将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。因此，通过从 “根节点” 开始搜索，遇到比 target 大的元素就向左，反之向右，即可找到目标值 target 。

![image-20230814205524411](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230814205524411.png)

“根节点” 对应的是矩阵的 “左下角” 和 “右上角” 元素，本文称之为 **标志数** ，以 matrix 中的 **左下角元素** 为标志数 flag ，则有:

- 若 `flag > target` ，则 target 一定在 flag 所在 行的上方 ，即 flag 所在行可被消去。
- 若 `flag < target` ，则 target 一定在 flag 所在 列的右方 ，即 flag 所在列可被消去。

**算法流程：**

1. 从矩阵 matrix 左下角元素（索引设为 (i, j) ）开始遍历，并与目标值对比：
   - 当 `matrix[i][j] > target` 时，执行 i-- ，即消去第 i 行元素；
   - 当 `matrix[i][j] < target` 时，执行 j++ ，即消去第 j 列元素；
   - 当 `matrix[i][j] = target` 时，返回 true，代表找到目标值。
2. 若行索引或列索引越界，则代表矩阵中无目标值，返回 false 。

> 每轮 i 或 j 移动后，相当于生成了“消去一行（列）的新矩阵”， 索引(i,j) 指向新矩阵的左下角元素（标志数），因此可重复使用以上性质消去行（列）。





### 代码

```java

/**
 * <p>
 * 二维数组中的查找
 * </p>
 *
 * @author admin
 * @date 2023/8/14
 */
public class offer04 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findNumberIn2DArray(matrix, target));

    }

    /**
     * 解法一：标志数：
     * 我们将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。
     */
    private static class Solution01 {

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 初始左下角坐标 matrix[i][j] 为标志数 flag
            int i = matrix.length - 1, j = 0;
            while (i >= 0 && j < matrix[0].length) {
                // 若 flag > target ，则 target 一定在 flag 所在 行的上方 ，即 flag 所在行可被消去。
                // 即消去第 i 行元素
                if (matrix[i][j] > target) {
                    i--;
                } else if (matrix[i][j] < target) {
                    // 若 flag < target ，则 target 一定在 flag 所在 列的右方 ，即 flag 所在列可被消去。
                    // 即消去第 j 列元素
                    j++;
                } else if (matrix[i][j] == target) {
                    return true;
                }
            }

            return false;
        }

    }

}

```





### 复杂度分析

- 时间复杂度 O(M+N)：其中，N 和 M 分别为矩阵行数和列数，此算法最多循环 M+N 次。
- 空间复杂度 O(1) : i, j 指针使用常数大小额外空间。





## 解法二：逐行二分查找

### 算法分析

由于矩阵 matrix 中每一行的元素都是升序排列的，因此我们可以对每一行都使用一次二分查找，判断 target 是否在该行中，从而判断 target 是否出现。



### 代码

```java

/**
 * <p>
 * 二维数组中的查找
 * </p>
 *
 * @author admin
 * @date 2023/8/14
 */
public class offer04 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.findNumberIn2DArray(matrix, target));

    }

    /**
     * 解法二：二分查找
     */
    private static class Solution02 {

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 逐行二分查找
            for (int[] row : matrix) {
                int index = binarySearch(row, target);
                if (index >= 0) {
                    return true;
                }
            }

            return false;
        }

        /**
         * 二分查找
         */
        private int binarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int tmp = nums[mid];
                if (tmp == target) {
                    return mid;
                } else if (tmp > target) {
                    high = mid - 1;
                } else if (tmp < target) {
                    low = mid + 1;
                }
            }

            return -1;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(nlog⁡m)。对一行使用二分查找的时间复杂度为 O(log⁡m)，最多需要进行 n 次二分查找。

- 空间复杂度：O(1)。







# THE END