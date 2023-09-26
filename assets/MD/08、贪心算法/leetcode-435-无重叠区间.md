# 目录

[toc]

# leetcode-435-无重叠区间

- 时间：2023-06-24
- 参考链接：
  - [贪心算法之区间调度问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/tan-xin-su-c41e8/)




# 1、题目

- 题目：https://leetcode.cn/problems/non-overlapping-intervals/
- 难度：中等

给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 **需要移除区间的最小数量**，使剩余区间互不重叠 。

示例 1:

```sh
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。
```



提示:

- 1 <= intervals.length <= 10^5
- intervals[i].length == 2
- -5 * 10^4 <= starti < endi <= 5 * 10^4





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

我们已经会求最多有几个区间不会重叠了，那么剩下的不就是至少需要去除的区间吗？

### 代码

```java

/**
 * <p>
 * 无重叠区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode435 {

    public static void main(String[] args) {

        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};

        Solution01 solution01 = new Solution01();
        int erased01 = solution01.eraseOverlapIntervals(intervals);
        System.out.println(erased01);

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int eraseOverlapIntervals(int[][] intervals) {
            int n = intervals.length;
            return n - intervalSchedule(intervals);
        }

        /**
         * 算出这些区间中最多有几个互不相交的区间
         */
        private int intervalSchedule(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            // 按 end 升序排序
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

            // 至少有一个区间不相交
            int count = 1;
            // 排序后，第一个区间就是 x
            int x_end = intervals[0][1];
            for (int[] interval : intervals) {
                int start = interval[0];
                if (start >= x_end) {
                    count++;
                    x_end = interval[1];
                }
            }

            return count;
        }

    }

}

```





### 复杂度分析

![image-20230624174826750](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624174826750.png)







# THE END