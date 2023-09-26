# 目录

[toc]

# leetcode-452-用最少数量的箭引爆气球

- 时间：2023-06-24
- 参考链接：
  - [贪心算法之区间调度问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/tan-xin-su-c41e8/)



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
- 难度：中等



有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。

一支弓箭可以沿着 x 轴从不同点 **完全垂直** 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 **引爆** 。可以射出的弓箭的数量 **没有限制** 。 弓箭一旦被射出之后，可以无限地前进。

给你一个数组 points ，返回引爆所有气球所必须射出的 **最小** 弓箭数 。

示例 1：

```sh
输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：气球可以用2支箭来爆破:
-在x = 6处射出箭，击破气球[2,8]和[1,6]。
-在x = 11处发射箭，击破气球[10,16]和[7,12]。
```



提示:

- 1 <= points.length <= 10^5
- points[i].length == 2
- -2^31 <= xstart < xend <= 2^31 - 1







# 2、题解

## 题目分析

给你很多形如 `[start, end]` 的闭区间，请你设计一个算法，**算出这些区间中最多有几个互不相交的区间**。

```java
int intervalSchedule(int[][] intvs);
```

举个例子，`intvs = [[1,3], [2,4], [3,6]]`，这些区间最多有 2 个区间互不相交，即 `[[1,3], [3,6]]`，你的算法应该返回 2。注意边界相同并不算相交。

这个问题在生活中的应用广泛，比如你今天有好几个活动，每个活动都可以用区间 `[start, end]` 表示开始和结束的时间，请问你今天**最多能参加几个活动呢**？显然你一个人不能同时参加两个活动，所以说这个问题就是求这些时间区间的最大不相交子集。

**正确的思路**其实很简单，可以分为以下三步：

1、从区间集合 `intvs` 中选择一个区间 `x`，这个 `x` 是在当前所有区间中**结束最早的**（`end` 最小）。

2、把所有与 `x` 区间相交的区间从区间集合 `intvs` 中删除。

3、重复步骤 1 和 2，直到 `intvs` 为空为止。之前选出的那些 `x` 就是最大不相交子集。

把这个思路实现成算法的话，可以按每个区间的 `end` 数值升序排序，因为这样处理之后实现步骤 1 和步骤 2 都方便很多，

![img](https://labuladong.gitee.io/algo/images/interval/1.gif)

现在来实现算法，对于步骤 1，由于我们预先按照 `end` 排了序，所以选择 `x` 是很容易的。关键在于，如何去除与 `x` 相交的区间，选择下一轮循环的 `x` 呢？

**由于我们事先排了序**，不难发现所有与 `x` 相交的区间必然会与 `x` 的 `end` 相交；如果一个区间不想与 `x` 的 `end` 相交，它的 `start` 必须要大于（或等于）`x` 的 `end`

![img](https://labuladong.gitee.io/algo/images/interval/2.jpg)







## 解法一: 贪心

### 算法分析

其实稍微思考一下，这个问题和 leetcode-435 区间调度算法一模一样！如果最多有 `n` 个不重叠的区间，那么就至少需要 `n` 个箭头穿透所有区间：

只是有一点不一样，在 `intervalSchedule` 算法中，如果两个区间的边界触碰，不算重叠；而按照这道题目的描述，箭头如果碰到气球的边界气球也会爆炸，所以说相当于**区间的边界触碰也算重叠**：

所以只要将 leetcode435 的算法稍作修改，就是这道题目的答案：

### 代码

```java

/**
 * <p>
 * 用最少数量的箭引爆气球
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode452 {

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findMinArrowShots(points));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int findMinArrowShots(int[][] points) {
            if (points.length == 0) {
                return 0;
            }
            // 按 end 升序排序
            Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

            // 至少有一个区间不相交
            int count = 1;
            // 排序后，第一个区间就是 x
            int x_end = points[0][1];
            for (int[] point : points) {
                int start = point[0];
                // 类比，leetcode435 : 把 >= 改成 > 就行了
                if (start > x_end) {
                    count++;
                    x_end = point[1];
                }
            }

            return count;
        }

    }

}

```





### 复杂度分析









# THE END