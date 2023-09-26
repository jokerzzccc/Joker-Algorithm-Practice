# 目录

[toc]

# leetcode-48-旋转图像

- 时间：2023-06-12

- 参考链接：
  - [二维数组的花式遍历技巧](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/er-wei-shu-150fb/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/rotate-image/
- 难度：中等



给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 **原地** 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。



提示：

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000





# 2、题解

## 题目分析



## 解法一: 

### 算法分析

常规的思路就是去寻找原始坐标和旋转后坐标的映射规律，但我们是否可以让思维跳跃跳跃，尝试把矩阵进行反转、镜像对称等操作，可能会出现新的突破口。

**我们可以先将 `n x n` 矩阵 `matrix` 按照左上到右下的对角线进行镜像对称**：

**然后再对矩阵的每一行进行反转**：

**发现结果就是 `matrix` 顺时针旋转 90 度的结果**：



### 代码

```java

/**
 * <p>
 * 旋转图像
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode48 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution01 solution01 = new Solution01();
        solution01.rotate(matrix);
    }

    /**
     * 解法一：
     * 我们可以先将 `n x n` 矩阵 `matrix` 按照左上到右下的对角线进行镜像对称；
     * 然后再对矩阵的每一行进行反转**：
     * 发现结果就是 `matrix` 顺时针旋转 90 度的结果：
     */
    private static class Solution01 {

        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 1、先沿对角线镜像对称二维矩阵
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    // swap(matrix[i][j], matrix[j][i]);
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // 2、然后反转二维矩阵的每一行
            for (int[] row : matrix) {
                reverse(row);
            }

            // debug
            for (int[] row : matrix) {
                for (int element : row) {
                    System.out.print(element + " ");
                }
                System.out.println("\n");
            }

        }

        /**
         * 反转一维数组
         */
        private void reverse(int[] arr) {
            int i = 0, j = arr.length - 1;
            while (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

    }

}

```

输出：

```sh
7 4 1 

8 5 2 

9 6 3 
```





### 复杂度分析

- 时间复杂度：O(N2),其中 N 是 matrix 的边长。对于每一次翻转操作，我们都需要枚举矩阵中一半的
  元素。
- 空间复杂度：O(1)。为原地翻转得到的原地旋转。





# THE END