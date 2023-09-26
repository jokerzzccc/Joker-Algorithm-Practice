# 目录

[toc]

# leetcode-1288-删除被覆盖区间

- 时间：2023-06-24
- 参考链接：
  - [一文秒杀所有区间相关问题](https://mp.weixin.qq.com/s/Eb6ewVajH56cUlY9LetRJw)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/remove-covered-intervals/
- 难度：中等



给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。

只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。

在完成所有删除操作后，请你返回列表中剩余区间的数目。

 

示例：

```sh
输入：intervals = [[1,4],[3,6],[2,8]]
输出：2
解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
```




提示：

- 1 <= intervals.length <= 1000
- 0 <= intervals[i][0] < intervals[i][1] <= 10^5
- 对于所有的 i != j：intervals[i] != intervals[j]





# 2、题解

## 题目分析

所谓区间问题，就是线段问题，让你合并所有线段、找出线段的交集等等。主要有两个技巧：

**1、排序**。常见的排序方法就是按照区间起点排序，或者先按照起点升序排序，若起点相同，则按照终点降序排序。当然，如果你非要按照终点排序，无非对称操作，本质都是一样的。

**2、画图**。就是说不要偷懒，勤动手，两个区间的相对位置到底有几种可能，不同的相对位置我们的代码应该怎么去处理。



本体为，**区间覆盖问题**。

题目问我们，去除被覆盖区间之后，还剩下多少区间，**那么我们可以先算一算，被覆盖区间有多少个，然后和总数相减就是剩余区间数**。

对于这种区间问题，如果没啥头绪，首先排个序看看，比如我们按照区间的起点进行升序排序：

![image-20230624225532774](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624225532774.png)

排序之后，两个相邻区间可能有如下三种相对位置：

![image-20230624225549228](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624225549228.png)

对于这三种情况，我们应该这样处理：

对于情况一，找到了覆盖区间。

对于情况二，两个区间可以合并，成一个大区间。

对于情况三，两个区间完全不相交。

依据几种情况，我们可以写出如下代码：



起点升序排列，终点降序排列的目的是防止如下情况：

![image-20230624225623381](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624225623381.png)

对于这两个起点相同的区间，我们需要保证长的那个区间在上面（按照终点降序），这样才会被判定为覆盖，否则会被错误地判定为相交，少算一个覆盖区间。

## 解法一

### 算法分析





### 代码

```java
package com.joker.algorithm.dp.greedy;

import java.util.Arrays;

/**
 * <p>
 * 删除被覆盖区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode1288 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};

        Solution01 solution01 = new Solution01();
        int removeCoveredIntervals01 = solution01.removeCoveredIntervals(intervals);
        System.out.println(removeCoveredIntervals01);

    }

    /**
     * 解法一：
     */
    private static class Solution01 {

        public int removeCoveredIntervals(int[][] intervals) {
            // 按照起点升序排列，起点相同时降序排列
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });

            // 记录合并区间的起点和终点，初始为排序后的第一个区间
            int left = intervals[0][0];
            int right = intervals[0][1];

            // 被覆盖的区间数
            int res = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] curInterval = intervals[i];
                // 情况一，找到覆盖区间
                if (left <= curInterval[0] && right >= curInterval[1]) {
                    res++;
                }

                // 情况二，找到相交区间，合并
                if (right >= curInterval[0] && right <= curInterval[1]) {
                    right = curInterval[1];
                }

                // 情况三，完全不相交，更新起点和终点
                if (right < curInterval[0]) {
                    left = curInterval[0];
                    right = curInterval[1];
                }
            }

            return intervals.length - res;
        }

    }

}

```





### 复杂度分析











# THE END