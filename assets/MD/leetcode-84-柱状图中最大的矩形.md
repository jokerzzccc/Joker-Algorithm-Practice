# 目录

[toc]

# leetcode-84-柱状图中最大的矩形

- 时间：2023-08-12
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
- 难度：困难

给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

![image-20230812173619566](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230812173619566.png)

**提示：**

+ `1 <= heights.length <=10^5`
+ `0 <= heights[i] <= 10^4`



# 2、题解

## 题目分析



## 解法一：单调栈

### 算法分析

- 参考链接：
  - https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/142012/bao-li-jie-fa-zhan-by-liweiwei1419/



要搞清楚这个过程，请大家一定要在**纸上画图**，搞清楚一些细节，这样在编码的时候就不容易出错了。

记录什么信息呢？记录高度是不是可以呢？其实是不够的，因为计算矩形还需要计算宽度，很容易知道宽度是由下标确定的，记录了下标其实对应的高度就可以直接从输入数组中得出，因此，**应该记录的是下标**。



我们在遍历的时候，需要记录的是下标，如果当前的高度比它之前的高度严格小于的时候，就可以直接确定之前的那个高的柱形的最大矩形的面积，为了确定这个最大矩形的左边界，我们还要找到第一个严格小于它的高度的矩形，向左回退的时候，其实就可以当中间这些柱形不存在一样。

这是因为我们就是想确定 6 的宽度，6 的宽度确定完了，其实我们就不需要它了，这个 5 的高度和这个 5 的高度确定完了，我们也不需要它了。

**我们在缓存数据的时候，是从左向右缓存的，我们计算出一个结果的顺序是从右向左的，并且计算完成以后我们就不再需要了，符合后进先出的特点。因此，我们需要的这个作为缓存的数据结构就是栈。**

当确定了一个柱形的高度的时候，我们就将它从栈顶移出，所有的柱形在栈中进栈一次，出栈一次，一开始栈为空，最后也一定要让栈为空，表示这个高度数组里所有的元素都考虑完了。



### 代码

```java


/**
 * <p>
 * 柱状图中最大的矩形
 * </p>
 *
 * @author admin
 * @date 2023/8/11
 */
public class leetcode84 {

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.largestRectangleArea(heights));

    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

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





### 复杂度分析

+ 时间复杂度：O(N)，输入数组里的每一个元素入栈一次，出栈一次。
+ 空间复杂度：O(N)，栈的空间最多为 N。









# THE END