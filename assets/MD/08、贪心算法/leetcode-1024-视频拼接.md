# 目录

[toc]

# leetcode-1024-视频拼接

- 时间：2023-06-24
- 参考链接：
  - [剪视频剪出一个贪心算法](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/jian-shi-p-4302c/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/video-stitching/
- 难度：中等



你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 endi 结束。

甚至可以对这些片段自由地再剪辑：

- 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。

我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。



示例 1：

```sh
输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
输出：3
解释：
选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
```



**提示：**

+ `1 <= clips.length <= 100`
+ `0 <= starti <= endi <= 100`
+ `1 <= time <= 100`



# 2、题解

## 题目分析

题目并不难理解，给定一个目标区间和若干小区间，如何通过裁剪和组合小区间拼凑出目标区间？最少需要几个小区间？

**前文多次说过，区间问题肯定按照区间的起点或者终点进行排序**。

因为排序之后更容易找到相邻区间之间的联系，如果是求最值的问题，可以使用贪心算法进行求解。

**区间问题**特别容易用贪心算法，公众号历史文章除了 [贪心算法之区间调度](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/tan-xin-su-c41e8/)，还有一篇 [贪心算法玩跳跃游戏](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/ru-he-yun--48a7c/)，其实这个跳跃游戏就相当于一个将起点排序的区间问题，你细品，你细品。

至于到底如何排序，这个就要因题而异了，我做这道题的思路是先按照起点升序排序，如果起点相同的话按照终点降序排序。

为什么这样排序呢，主要考虑到**这道题的以下两个特点**：

1、要用若干短视频凑出完成视频 `[0, T]`，至少得有一个短视频的起点是 0。

这个很好理解，如果没有一个短视频是从 0 开始的，那么区间 `[0, T]` 肯定是凑不出来的。

2、如果有几个短视频的起点都相同，那么一定应该选择那个最长（终点最大）的视频。

这一条就是贪心的策略，因为题目让我们计算**最少需要的短视频个数，如果起点相同，那肯定是越长越好**，不要白不要，多出来了大不了剪辑掉嘛。

基于以上两个特点，将 `clips` 按照起点升序排序，起点相同的按照终点降序排序，最后得到的区间顺序就像这样：

![image-20230624221800997](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624221800997.png)

这样我们就可以确定，如果 `clips[0]` 是的起点是 0，那么 `clips[0]` 这个视频一定会被选择。

![image-20230624221818206](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624221818206.png)

当我们确定 `clips[0]` 一定会被选择之后，就可以选出下一个会被选择的视频：

![image-20230624221833515](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624221833515.png)

**我们会比较所有起点小于 `clips[0][1]` 的区间，根据贪心策略，它们中终点最大的那个区间就是第二个会被选中的视频**。

然后可以通过第二个视频区间贪心选择出第三个视频，以此类推，直到覆盖区间 `[0, T]`，或者无法覆盖返回 -1。

以上就是这道题的解题思路，仔细想想，这题的核心和前文 [贪心算法玩跳跃游戏](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/ru-he-yun--48a7c/) 写的跳跃游戏是相同的，如果你能看出这两者的联系，就可以说理解贪心算法的奥义了。





实现上述思路需要我们用两个变量 `curEnd` 和 `nextEnd` 来进行，如下 GIF 所示：

![img](https://labuladong.gitee.io/algo/images/剪视频/5.gif)



## 解法一: 贪心

### 算法分析





### 代码

```java

/**
 * <p>
 * 视频拼接
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode1024 {

    public static void main(String[] args) {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int time = 10;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.videoStitching(clips, time));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int videoStitching(int[][] clips, int time) {
            if (time == 0) return 0;
            // 按起点升序排列，起点相同的降序排列
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });
            // 记录选择的短视频个数
            int res = 0;

            int curEnd = 0, nextEnd = 0;
            int i = 0, n = clips.length;
            while (i < n && clips[i][0] <= curEnd) {
                // 在第 res 个视频的区间内贪心选择下一个视频
                while (i < n && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    i++;
                }
                // 找到下一个视频，更新 curEnd
                res++;
                curEnd = nextEnd;
                if (curEnd >= time) {
                    // 已经可以拼出区间 [0, T]
                    return res;
                }
            }

            // 无法连续拼出区间 [0, T]
            return -1;
        }

    }

}

```





### 复杂度分析

这段代码的时间复杂度是多少呢？虽然代码中有一个嵌套的 while 循环，但这个嵌套 while 循环的时间复杂度是 `O(N)`。因为当 `i` 递增到 `n` 时循环就会结束，所以这段代码只会执行 `O(N)` 次。

但是别忘了我们对 `clips` 数组进行了一次排序，消耗了 `O(NlogN)` 的时间，所以本算法的**总时间复杂度是 `O(NlogN)`。**









# THE END