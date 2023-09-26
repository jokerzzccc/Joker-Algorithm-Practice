# 目录

[toc]

# leetcode-253-会议室 II

- 时间：2023-06-24
- 参考链接：
  - [扫描线技巧：安排会议室](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/sao-miao-x-2e810/)



# 1、题目

- 题目：https://leetcode.cn/problems/meeting-rooms-ii/
- 难度：中等



给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。



**示例 1：**

```
输入：intervals = [[0,30],[5,10],[15,20]]
输出：2
```



**提示：**

+ `1 <= intervals.length <= 10^4`
+ `0 <= starti < endi <= 10^6`

# 2、题解

## 题目分析

如果会议之间的时间有重叠，那就得额外申请会议室来开会，想求至少需要多少间会议室，就是让你计算同一时刻最多有多少会议在同时进行。

换句话说，**如果把每个会议的起始时间看做一个线段区间，那么题目就是让你求最多有几个重叠区间**，仅此而已。

对于这种时间安排的问题，本质上讲就是区间调度问题，十有八九得排序，然后找规律来解决。



重复一下**题目的本质**：

**给你输入若干时间区间，让你计算同一时刻「最多」有几个区间重叠**。

题目的关键点在于，给你任意一个时刻，你是否能够说出这个时刻有几个会议？

如果可以做到，那我遍历所有的时刻，找个最大值，就是需要申请的会议室数量。



## 解法一: 贪心

### 算法分析

**基于差分数组的思路，我们可以推导出一种更高效，更优雅的解法**。

我们首先把这些会议的时间区间进行投影：

![image-20230624215756478](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624215756478.png)

红色的点代表每个会议的开始时间点，绿色的点代表每个会议的结束时间点。

现在假想有一条带着计数器的线，在时间线上从左至右进行扫描，每遇到红色的点，计数器 `count` 加一，每遇到绿色的点，计数器 `count` 减一：

![image-20230624215820394](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624215820394.png)

**这样一来，每个时刻有多少个会议在同时进行，就是计数器 `count` 的值，`count` 的最大值，就是需要申请的会议室数量**。

对差分数组技巧熟悉的读者一眼就能看出来了，这个扫描线其实就是差分数组的遍历过程，所以我们说这是差分数组技巧衍生出来的解法。



那么，如何写代码实现这个扫描的过程呢？

首先，对区间进行投影，就相当于对每个区间的起点和终点分别进行排序：

![image-20230624220014660](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624220014660.png)



### 代码

```java

/**
 * <p>
 * 会议室 II
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode253 {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        Solution01 solution01 = new Solution01();
        int meetingRooms01 = solution01.minMeetingRooms(intervals);
        System.out.println(meetingRooms01);

    }

    /**
     * 解法一：贪心 + 差分数组
     */
    private static class Solution01 {

        int minMeetingRooms(int[][] meetings) {
            int n = meetings.length;
            int[] begin = new int[n];
            int[] end = new int[n];
            for (int i = 0; i < n; i++) {
                begin[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }
            Arrays.sort(begin);
            Arrays.sort(end);

            // 扫描过程中的计数器
            int count = 0;
            // 双指针技巧
            int res = 0, i = 0, j = 0;
            while (i < n && j < n) {
                if (begin[i] < end[j]) {
                    // 扫描到一个红点
                    count++;
                    i++;
                } else {
                    // 扫描到一个绿点
                    count--;
                    j++;
                }
                // 记录扫描过程中的最大值
                res = Math.max(res, count);
            }

            return res;
        }

    }

}

```





### 复杂度分析

![image-20230624220552917](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624220552917.png)





# THE END