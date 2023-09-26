# 目录

[toc]

# leetcode-528-按权重随机选择

- 时间：2023-06-14

- 参考链接：
  - [带权重的随机选择算法](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/dai-quan-z-585d6/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/random-pick-with-weight/
- 难度：中等



给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。

请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。



**提示：**

+ `1 <= w.length <= 104`
+ `1 <= w[i] <= 105`
+ `pickIndex` 将被调用不超过 `104` 次



# 2、题解

## 题目分析



## 解法一:  前缀和 + 二分查找（左侧边界）

### 算法分析

到这里，这道题的核心思路就说完了，主要分几步：

1、根据权重数组 `w` 生成前缀和数组 `preSum`。

2、生成一个取值在 `preSum` 之内的随机数，用二分搜索算法寻找大于等于这个随机数的最小元素索引。

3、最后对这个索引减一（因为前缀和数组有一位索引偏移），就可以作为权重数组的索引，即最终答案:



上述思路应该不难理解，但是写代码的时候坑可就多了。

要知道涉及开闭区间、索引偏移和二分搜索的题目，需要你对算法的细节把控非常精确，否则会出各种难以排查的 bug。



就比如这个 `preSum` 数组，你觉得随机数 `target` 应该在什么范围取值？闭区间 `[0, 7]` 还是左闭右开 `[0, 7)`？

都不是，应该在闭区间 `[1, 7]` 中选择，**因为前缀和数组中 0 本质上是个占位符**，仔细体会一下.



接下来，在 `preSum` 中寻找大于等于 `target` 的最小元素索引，应该用什么品种的二分搜索？搜索左侧边界的还是搜索右侧边界的？

实际上应该使用搜索左侧边界的二分搜索



**当目标元素 `target` 不存在数组 `nums` 中时，搜索左侧边界的二分搜索的返回值可以做以下几种解读**：

1、返回的这个值是 `nums` 中大于等于 `target` 的最小元素索引。

2、返回的这个值是 `target` 应该插入在 `nums` 中的索引位置。

3、返回的这个值是 `nums` 中小于 `target` 的元素个数。

比如在有序数组 `nums = [2,3,5,7]` 中搜索 `target = 4`，搜索左边界的二分算法会返回 2，你带入上面的说法，都是对的。



### 代码

```java

/**
 * <p>
 * 按权重随机选择
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode528 {

    public static void main(String[] args) {
        int[] w = {1, 3};

        Solution01 solution01 = new Solution01(w);
        int count01 = 5;
        for (int i = 0; i < count01; i++) {
            int index = solution01.pickIndex();
            System.out.println(index);
        }

    }

    /**
     * 解法一：前缀和 + 二分查找（左侧边界）
     */
    private static class Solution01 {

        /**
         * 前缀和数组
         */
        private int[] preSum;
        private Random random = new Random();

        public Solution01(int[] w) {
            int n = w.length;
            // 构建前缀和数组，偏移一位留给 preSum[0]
            preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }

        }

        public int pickIndex() {
            int n = preSum.length;
            // 方法在 [0, n) 中生成一个随机整数
            // 再加一就是在闭区间 [1, preSum[n - 1]] 中随机选择一个数字
            int target = random.nextInt(preSum[n - 1]) + 1;
            // 获取 target 在前缀和数组 preSum 中的索引（返回的这个值是 preSum 中大于等于 target 的最小元素索引）
            // 别忘了前缀和数组 preSum 和原始数组 w 有一位索引偏移
            return left_bound(preSum, target) - 1;
        }

        /**
         * 二分查找，左侧边界
         */
        private int left_bound(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 别返回，锁定左侧边界
                    right = mid - 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }

            return left;
        }

    }

}
```

输出：

```sh
1
1
1
0
1

```





### 复杂度分析

- 时间复杂度：初始化的时间复杂度为O(m),每次选择的时间复杂度为O(Iogn),其中n是数组w的长度。
- 空间复杂度：O(n),即为前缀和数组pr需要使用的空间。



# THE END