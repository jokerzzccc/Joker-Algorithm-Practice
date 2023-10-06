# 目录

[toc]

# leetcode-973-最接近原点的 K 个点

- 时间：2023-10-06
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/k-closest-points-to-origin/
- 难度：中等



给定一个数组 `points` ，其中 `points[i] = [xi, yi]` 表示 **X-Y** 平面上的一个点，并且是一个整数 `k` ，返回离原点 `(0,0)` 最近的 `k` 个点。

这里，平面上两点之间的距离是 **欧几里德距离**（ $`√(x1 - x2)^2 + (y1 - y2)^2`$ ）。

你可以按 **任何顺序** 返回答案。除了点坐标的顺序之外，答案 **确保** 是 **唯一** 的。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/03/03/closestplane1.jpg)

```
输入：points = [[1,3],[-2,2]], k = 1
输出：[[-2,2]]
解释： 
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
```

**示例 2：**

```
输入：points = [[3,3],[5,-1],[-2,4]], k = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
```

 

**提示：**

- `1 <= k <= points.length <= 10^4`
- $-10^4 < x_i, y_i < 10^4$



# 2、题解

## 题目分析



## 解法一：大根堆

### 算法分析





### 代码

```java


/**
 * <p>
 * 最接近原点的 K 个点
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/10/6
 **/
public class leetcode973 {

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}};
        int k = 1;

        Solution01 solution01 = new Solution01();
        int[][] res1 = solution01.kClosest(points, k);
        System.out.println(Arrays.deepToString(res1));

    }

    /**
     * 解法一：大根堆
     */
    private static class Solution01 {
        public int[][] kClosest(int[][] points, int k) {
            // 大根堆：坐标到原点的距离为排序内容
            PriorityQueue<int[]> queue = new PriorityQueue<>((array1, array2) -> array2[0] - array1[0]);

            // 队列里最多只有 K 个元素
            for (int i = 0; i < k; i++) {
                queue.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
            }

            int len = points.length;
            for (int i = k; i < len; i++) {
                int tempDist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                if (tempDist < queue.peek()[0]) {
                    queue.poll();
                    queue.offer(new int[]{tempDist, i});
                }
            }

            // 返回答案
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; i++) {
                ans[i] = points[queue.poll()[1]];
            }

            return ans;


        }
    }
}

```





### 复杂度分析

- 时间复杂度：O(nlog⁡k)，其中 n 是数组 points 的长度。由于大根堆维护的是前 k 个距离最小的点，因此弹出和插入操作的单次时间复杂度均为 O(log⁡k)。在最坏情况下，数组里 n 个点都会插入，因此时间复杂度为 O(nlog⁡k)。

- 空间复杂度：O(k)，因为大根堆中最多有 k 个点。











# THE END