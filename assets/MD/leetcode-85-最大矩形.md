# 目录

[toc]

# leetcode-85-最大矩形

- 时间：2023-08-12
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximal-rectangle/description/
- 难度：困难

![image-20230812183938995](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230812183938995.png)

**提示：**

+ `rows == matrix.length`
+ `cols == matrix[0].length`
+ `1 <= row, cols <= 200`
+ `matrix[i][j]` 为 `'0'` 或 `'1'`



# 2、题解

## 题目分析

- 参考链接：
  - https://leetcode.cn/problems/maximal-rectangle/solutions/9535/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/



## 解法一：单调栈

### 算法分析

算法有了，就是求出每一层的 heights[] 然后传给 leetcode84 的函数就可以了



### 代码

```java

/**
 * <p>
 * 最大矩形
 * </p>
 *
 * @author admin
 * @date 2023/8/11
 */
public class leetcode85 {

    public static void main(String[] args) {
        char[][] martix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.maximalRectangle(martix));

    }


    /**
     * 解法二：单调栈（更优）
     * 类比 leetcode84
     */
    private static class Solution02 {

        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }

            int[] heights = new int[matrix[0].length];
            int maxArea = 0;

            // 遍历每一行，统计柱形的高度，传递给 largestRectangleArea
            for (int row = 0; row < matrix.length; row++) {
                // 遍历每一列，更新高度
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] == '1') {
                        heights[col] += 1;
                    } else {
                        heights[col] = 0;
                    }
                }
                // 调用 leetcode84 的解法，更新函数
                maxArea = Math.max(maxArea, largestRectangleArea(heights));
            }

            return maxArea;
        }

        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) {
                return 0;
            }
            if (len == 1) {
                return heights[0];
            }

            int maxArea = 0;
            // 新数组两端加上两个高度为 0 的柱形(两哨兵)，便于计算
            int[] newHeights = new int[len + 2];
            System.arraycopy(heights, 0, newHeights, 1, len);
            len += 2;
            heights = newHeights;

            // 单调栈：存储下标
            // 下标对应的柱形的高度一定是递增(头 -> 尾)的
            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);

            for (int i = 1; i < len; i++) {
                // 确定一个柱形高度的最大面积（即找宽度）
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    maxArea = Math.max(maxArea, curHeight * curWidth);
                }

                stack.addLast(i);
            }

            return maxArea;
        }

    }

}



```

输出：

```sh
6
```





### 复杂度分析

- 时间复杂度：O（mn）。

- 空间复杂度：O（n）。



## 解法二：动态规划

### 算法分析





### 代码

```java

```





### 复杂度分析

- 









# THE END