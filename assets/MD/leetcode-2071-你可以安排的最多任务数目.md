# 目录

[toc]

# leetcode-2071-你可以安排的最多任务数目

- 时间：2023-08-10
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/description/
- 难度：困难

给你 `n` 个任务和 `m` 个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 **0** 开始的整数数组 `tasks` 中，第 `i` 个任务需要 `tasks[i]` 的力量才能完成。每个工人的力量值保存在下标从 **0** 开始的整数数组 `workers` 中，第 `j` 个工人的力量值为 `workers[j]` 。每个工人只能完成 **一个** 任务，且力量值需要 **大于等于** 该任务的力量要求值（即 `workers[j] >= tasks[i]` ）。

除此以外，你还有 `pills` 个神奇药丸，可以给 **一个工人的力量值** 增加 `strength` 。你可以决定给哪些工人使用药丸，但每个工人 **最多** 只能使用 **一片** 药丸。

给你下标从 **0** 开始的整数数组`tasks` 和 `workers` 以及两个整数 `pills` 和 `strength` ，请你返回 **最多** 有多少个任务可以被完成。

 

**示例 1：**

```
输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
输出：3
解释：
我们可以按照如下方案安排药丸：
- 给 0 号工人药丸。
- 0 号工人完成任务 2（0 + 1 >= 1）
- 1 号工人完成任务 1（3 >= 2）
- 2 号工人完成任务 0（3 >= 3）
```

**示例 2：**

```
输入：tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
输出：1
解释：
我们可以按照如下方案安排药丸：
- 给 0 号工人药丸。
- 0 号工人完成任务 0（0 + 5 >= 5）
```

**示例 3：**

```
输入：tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
输出：2
解释：
我们可以按照如下方案安排药丸：
- 给 0 号和 1 号工人药丸。
- 0 号工人完成任务 0（0 + 10 >= 10）
- 1 号工人完成任务 1（10 + 10 >= 15）
```

**示例 4：**

```
输入：tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
输出：3
解释：
我们可以按照如下方案安排药丸：
- 给 2 号工人药丸。
- 1 号工人完成任务 0（6 >= 5）
- 2 号工人完成任务 2（4 + 5 >= 8）
- 4 号工人完成任务 3（6 >= 5）
```

 

**提示：**

+ `n == tasks.length`
+ `m == workers.length`
+ `1 <= n, m <= 5 * 10^4`
+ `0 <= pills <= m`
+ `0 <= tasks[i], workers[j], strength <= 10^9`



# 2、题解

## 题目分析



## 解法一:二分查找 + 双端队列

### 算法分析

![image-20230810214658441](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230810214658441.png)



### 代码

```java
package com.joker.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <p>
 * 你可以安排的最多任务数目
 * </p>
 *
 * @author admin
 * @date 2023/8/10
 */
public class leetcode2071 {

    public static void main(String[] args) {
        int[] tasks = {3, 2, 1}, workers = {0, 3, 3};
        int pills = 1, strength = 1;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.maxTaskAssign(tasks, workers, pills, strength));

    }

    /**
     * 解法一：二分查找 + 双端队列
     */
    private static class Solution01 {

        public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
            Arrays.sort(tasks);
            Arrays.sort(workers);

            // 最少完成 min 个任务
            int min = 0;
            // 最多完成 max + 1 个任务
            int max = Math.min(tasks.length, workers.length) - 1;

            // 二分查找
            while (min <= max) {
                int mid = (min + max) / 2;
                if (check(tasks, workers, pills, strength, mid)) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }

            }

            return min;
        }

        /**
         * 从下标0到下标mid的任务(最小的mid+1个任务),
         * 是否都能被workers.length - 1 - mid到workers.length - 1下标的工人(力量最大的mid+1个工人)完成
         */
        private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
            // 工人队列(双端队列)
            // （在使用药丸的情况下）所有可以完成任务的工人
            Deque<Integer> workerDeque = new ArrayDeque<>();
            int workerIdxMax = workers.length - 1;
            int workerIdxMin = workers.length - 1 - mid;
            // 从大到小（从后向前）遍历任务
            for (int i = mid; i >= 0; i--) {
                // 工人力量：队首 -> 队尾，大 -> 小
                while (workerIdxMax >= workerIdxMin && workers[workerIdxMax] + strength >= tasks[i]) {
                    // 从力量最大的工人开始遍历,如果工人吃药丸后,能够完成当前任务,加入队尾
                    workerDeque.addLast(workers[workerIdxMax]);
                    workerIdxMax--;
                }
                if (workerDeque.size() == 0) {
                    // 情况一：没有工人能够完成当前任务
                    return false;
                }
                if (workerDeque.getFirst() >= tasks[i]) {
                    // 情况二：队头(队列中,力量最大)的工人可以完成当前任务,让他去完成当前任务.
                    workerDeque.removeFirst();
                } else {
                    // 情况三：队头(队列中,力量最大)的工人不能完成当前任务,需要吃药丸
                    if (pills == 0) {
                        // 没有药丸了
                        return false;
                    } else {
                        // 情况四：
                        // 让队里力量最小的工人吃了药丸去完成当前任务.
                        // 因为任务需要的力量是递减的,力量大的工人有可能不吃药丸完成后面的任务
                        pills--;
                        workerDeque.removeLast();
                    }
                }
            }

            return true;
        }

    }

}

```





### 复杂度分析











# THE END