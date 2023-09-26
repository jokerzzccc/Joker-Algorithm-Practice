# 目录

[toc]

# leetcode-986-区间列表的交集

- 时间：2023-06-24
- 参考链接：
  - [一文秒杀所有区间相关问题](https://mp.weixin.qq.com/s/Eb6ewVajH56cUlY9LetRJw)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/interval-list-intersections/
- 难度：中等



给定两个由一些 **闭区间** 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 **不相交** 的，并且 **已经排序** 。

返回这 **两个区间列表的交集** 。

形式上，**闭区间** [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。

两个闭区间的 **交集** 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。



示例 1：

![image-20230624232434695](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624232434695.png)

```sh
输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```



提示：

- 0 <= firstList.length, secondList.length <= 1000
- firstList.length + secondList.length >= 1
- 0 <= starti < endi <= 10^9
- endi < starti+1
- 0 <= startj < endj <= 10^9
- endj < startj+1



# 2、题解

## 题目分析

题目很好理解，就是让你找**交集**，注意区间都是闭区间。

解决区间问题的思路一般是先排序，以便操作，不过题目说已经排好序了，那么可以用两个索引指针在`A`和`B`中游走，把交集找出来，代码大概是这样的：

```python
# A, B 形如 [[0,2],[5,10]...]
def intervalIntersection(A, B):
    i, j = 0, 0
    res = []
    while i < len(A) and j < len(B):
        # ...
        j += 1
        i += 1
    return res
```

不难，我们先老老实实分析一下各种情况。

首先，**对于两个区间**，我们用`[a1,a2]`和`[b1,b2]`表示在`A`和`B`中的两个区间，那么什么情况下这两个区间**没有交集**呢：

![image-20230624232720451](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624232720451.png)

只有这两种情况，写成代码的条件判断就是这样：

```python
if b2 < a1 or a2 < b1:
    [a1,a2] 和 [b1,b2] 无交集
```

那么，什么情况下，两个区间存在交集呢？根据命题的否定，上面逻辑的否命题就是存在交集的条件：

```python
# 不等号取反，or 也要变成 and
if b2 >= a1 and a2 >= b1:
    [a1,a2] 和 [b1,b2] 存在交集
```

接下来，两个区间存在交集的情况有哪些呢？穷举出来：

![image-20230624232810475](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624232810475.png)

这很简单吧，就这四种情况而已。那么接下来思考，这几种情况下，交集是否有什么共同点呢？

![image-20230624232834227](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624232834227.png)

我们惊奇地发现，交集区间是有规律的！如果交集区间是`[c1,c2]`，那么`c1=max(a1,b1)`，`c2=min(a2,b2)`！这一点就是寻找交集的核心，我们把代码更进一步：

```python
while i < len(A) and j < len(B):
    a1, a2 = A[i][0], A[i][1]
    b1, b2 = B[j][0], B[j][1]
    if b2 >= a1 and a2 >= b1:
        res.append([max(a1, b1), min(a2, b2)])
    # ...
```

最后一步，我们的指针`i`和`j`肯定要前进（递增）的，什么时候应该前进呢？

![图片](https://mmbiz.qpic.cn/sz_mmbiz_gif/gibkIz0MVqdHvEm6Hfybxj5tHaDLbHmMwjLGcG66rnUlIrDoKO3rLa90eXlUtjIAS8enYDficptT6Sryjtpb8UpA/640?wx_fmt=gif&wxfrom=5&wx_lazy=1&wx_co=1)

结合动画示例就很好理解了，是否前进，只取决于`a2`和`b2`的大小关系：

```python
while i < len(A) and j < len(B):
    # ...
    if b2 < a2:
        j += 1
    else:
        i += 1
```

以此思路写出代码：



## 解法一

### 算法分析





### 代码

```java

/**
 * <p>
 * 区间列表的交集
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode986 {

    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        Solution01 solution01 = new Solution01();
        int[][] intervalIntersection01 = solution01.intervalIntersection(firstList, secondList);
        Arrays.deepToString(intervalIntersection01);

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            // 双指针，分别游走在 firstList, secondList
            int first_i = 0;
            int second_j = 0;

            LinkedList<int[]> res = new LinkedList<>();
            while (first_i < firstList.length && second_j < secondList.length) {
                int a_left = firstList[first_i][0];
                int a_right = firstList[first_i][1];
                int b_left = secondList[second_j][0];
                int b_right = secondList[second_j][1];
                // 两个区间存在交集
                if (b_right >= a_left && a_right >= b_left) {
                    // 计算出交集，加入 res
                    res.addLast(new int[]{Math.max(a_left, b_left), Math.min(a_right, b_right)});
                }
                // 指针前进
                if (b_right < a_right) {
                    second_j++;
                } else {
                    first_i++;
                }
            }

            return res.toArray(new int[res.size()][]);
        }

    }

}



```





### 复杂度分析

![image-20230624234351799](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230624234351799.png)









# THE END