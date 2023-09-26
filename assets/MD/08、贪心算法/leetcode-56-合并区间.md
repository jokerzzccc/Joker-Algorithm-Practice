# 目录

[toc]

# leetcode-56-合并区间

- 时间：2023-06-24
- 参考链接：
  - [一文秒杀所有区间相关问题](https://mp.weixin.qq.com/s/Eb6ewVajH56cUlY9LetRJw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/merge-intervals/
- 难度：中等



以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。



示例 1：

```sh
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```



提示：

- 1 <= intervals.length <= 10^4
- intervals[i].length == 2
- 0 <= starti <= endi <= 10^4



# 2、题解

## 题目分析

所谓区间问题，就是线段问题，让你合并所有线段、找出线段的交集等等。主要有两个技巧：

**1、排序**。常见的排序方法就是按照区间起点排序，或者先按照起点升序排序，若起点相同，则按照终点降序排序。当然，如果你非要按照终点排序，无非对称操作，本质都是一样的。

**2、画图**。就是说不要偷懒，勤动手，两个区间的相对位置到底有几种可能，不同的相对位置我们的代码应该怎么去处理。



本体为，**区间合并问题**。

我们解决区间问题的一般思路是先排序，然后观察规律。

一个区间可以表示为`[start, end]`，前文聊的区间调度问题，需要按`end`排序，以便满足贪心选择性质。而对于区间合并问题，其实按`end`和`start`排序都可以，不过为了清晰起见，我们选择按`start`排序。

![image-20230624230728458](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624230728458.png)

**显然，对于几个相交区间合并后的结果区间`x`，`x.start`一定是这些相交区间中`start`最小的，`x.end`一定是这些相交区间中`end`最大的。**

![image-20230624230750166](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624230750166.png)

由于已经排了序，`x.start`很好确定，求`x.end`也很容易，可以类比在数组中找最大值的过程：

```
int max_ele = arr[0];
for (int i = 1; i < arr.length; i++) 
    max_ele = max(max_ele, arr[i]);
return max_ele;
```

然后就可以写出完整代码

![图片](https://mmbiz.qpic.cn/sz_mmbiz_gif/gibkIz0MVqdHvEm6Hfybxj5tHaDLbHmMwrrgDsg5Y7ic7EjSTldjUWqf9bhg6RibZFmQkU44d4MEEZCaRfqptH6JQ/640?wx_fmt=gif&wxfrom=5&wx_lazy=1&wx_co=1)

## 解法一

### 算法分析





### 代码

```java

/**
 * <p>
 * 合并区间
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode56 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        Solution01 solution01 = new Solution01();
        int[][] result = solution01.merge(intervals);
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            // 按区间的 start 升序排列
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            LinkedList<int[]> merged = new LinkedList<>();
            merged.add(intervals[0]);
            for (int i = 1; i < intervals.length; i++) {
                int[] curInterval = intervals[i];
                int[] last = merged.getLast();
                if (curInterval[0] <= last[1]) {
                    // 是相交区间，可以合并为更大的区间
                    // 找到最大的 end
                    last[1] = Math.max(last[1], curInterval[1]);
                } else {
                    // 是不相交的
                    // 处理下一个待合并区间，添加到结果集
                    merged.add(curInterval);
                }

            }

            return merged.toArray(new int[merged.size()][]);
        }

    }

}


```





### 复杂度分析

![image-20230624232121259](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624232121259.png)









# THE END