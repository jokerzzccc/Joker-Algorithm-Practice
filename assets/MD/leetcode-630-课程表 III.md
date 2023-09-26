# 目录

[toc]

# leetcode-630-课程表 III

- 时间：2023-09-11
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/course-schedule-iii/description/?envType=daily-question&envId=2023-09-11
- 难度：困难





# 2、题解

## 题目分析

这里有 `n` 门不同的在线课程，按从 `1` 到 `n` 编号。给你一个数组 `courses` ，其中 `courses[i] = [durationi, lastDayi]` 表示第 `i` 门课将会 **持续** 上 `durationi` 天课，并且必须在不晚于 `lastDayi` 的时候完成。

你的学期从第 `1` 天开始。且不能同时修读两门及两门以上的课程。

返回你最多可以修读的课程数目。

 

**示例 1：**

```
输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
输出：3
解释：
这里一共有 4 门课程，但是你最多可以修 3 门：
首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
```

**示例 2：**

```
输入：courses = [[1,2]]
输出：1
```

**示例 3：**

```
输入：courses = [[3,2],[4,3]]
输出：0
```

 

**提示:**

+ `1 <= courses.length <= 104`
+ `1 <= durationi, lastDayi <= 104`

## 解法一：贪心 + 优先队列

### 算法分析

参考链接：

- https://leetcode.cn/problems/course-schedule-iii/solutions/1156693/gong-shui-san-xie-jing-dian-tan-xin-yun-ghii2/?envType=daily-question&envId=2023-09-11



![image-20230911223502624](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230911223502624.png)



### 代码

```java

/**
 * <p>
 * 课程表 III
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/11
 */
public class leetcode630 {

    public static void main(String[] args) {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.scheduleCourse(courses));

    }

    /**
     * 解法一：贪心 + 优先队列
     */
    private static class Solution01 {
        public int scheduleCourse(int[][] courses) {
            // 首先我们按课程结束时间升序排列, 我们总是首先考虑当前最先结束的课程
            Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
            // 设置一个优先级队列，这里存储了当前已学课程的长度信息，所需时间最多的在堆顶（大顶堆）
            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            // 当前学习课程的总花费
            int sum = 0;
            for (int[] course : courses) {
                int duration = course[0], lastDay = course[1];
                sum += duration;
                queue.add(duration);
                if (sum > lastDay) {
                    sum -= queue.poll();
                }
            }

            return queue.size();
        }
    }

}

```





### 复杂度分析

+ 时间复杂度：O(nlog⁡n)
+ 空间复杂度：O(n)









# THE END