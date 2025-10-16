# 目录

[toc]

# leetcode-370-区间加法

- 时间：2023-06-11

- 参考链接：
  - [小而美的算法技巧：差分数组](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/xiao-er-me-c304e/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/range-addition/
- 难度：中等



假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。

其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。

请你返回 k 次操作后的数组。





# 2、题解

## 题目分析



## 解法一: 差分数组

### 算法分析

**差分数组的主要适用场景是频繁对原始数组的某个区间的元素进行增减**。

常规的思路很容易，你让我给区间 `nums[i..j]` 加上 `val`，那我就一个 for 循环给它们都加上呗，还能咋样？这种思路的时间复杂度是 O(N)，由于这个场景下对 `nums` 的修改非常频繁，所以效率会很低下。

这里就需要差分数组的技巧，类似前缀和技巧构造的 `preSum` 数组，我们先对 `nums` 数组构造一个 `diff` 差分数组，**`diff[i]` 就是 `nums[i]` 和 `nums[i-1]` 之差**.



通过这个 `diff` 差分数组是可以反推出原始数组 `nums` 的.

**这样构造差分数组 `diff`，就可以快速进行区间增减的操作**，如果你想对区间 `nums[i..j]` 的元素全部加 3，那么只需要让 `diff[i] += 3`，然后再让 `diff[j+1] -= 3` 即可

- **原理很简单，回想 `diff` 数组反推 `nums` 数组的过程，`diff[i] += 3` 意味着给 `nums[i..]` 所有的元素都加了 3，然后 `diff[j+1] -= 3` 又意味着对于 `nums[j+1..]` 所有元素再减 3，那综合起来，是不是就是对 `nums[i..j]` 中的所有元素都加 3 了**？
- 只要花费 O(1) 的时间修改 `diff` 数组，就相当于给 `nums` 的整个区间做了修改。多次修改 `diff`，然后通过 `diff` 数组反推，即可得到 `nums` 修改后的结果。



### 代码

```java

/**
 * <p>
 * 区间加法
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode370 {

    public static void main(String[] args) {
        int length = 5;
        int[][] updates = {
                {1, 3, 2},
                {2, 4, 3},
                {0, 2, -2}
        };
        Solution01 solution01 = new Solution01();
        int[] modifiedArray = solution01.getModifiedArray(length, updates);
        Arrays.stream(modifiedArray).forEach(System.out::println);

    }

    /**
     * 解法一：差分数组
     */
    private static class Solution01 {

        public int[] getModifiedArray(int length, int[][] updates) {
            // nums 初始化为全 0
            int[] nums = new int[length];
            // 构造差分解法
            Difference difference = new Difference(nums);

            for (int[] update : updates) {
                difference.increment(update[0], update[1], update[2]);
            }

            return difference.result();

        }

    }

    /**
     * 差分数组工具类
     */
    private static class Difference {

        /**
         * 差分数组:
         * diff[i] 就是 nums[i] 和 nums[i-1] 之差
         */
        private int[] diff;

        public Difference(int[] nums) {
            if (nums.length <= 0) {
                return;
            }
            diff = new int[nums.length];
            // 根据初始数组构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * 给闭区间 [i, j] 增加 val（可以是负数）
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;
            // 即，nums[j] 之后如果还有元素，就要减去加上的 val，因为，保证之操作区间 [i, j] 的数。
            // 当 j+1 >= diff.length 时，说明是对 nums[i] 及以后的整个数组都进行修改，那么就不需要再给 diff 数组减 val 了。
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * 返回结果数组:
         * diff 差分数组是可以反推出原始数组 nums
         */
        public int[] result() {
            int[] res = new int[diff.length];
            // 根据差分数组构造结果数组
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }

    }

}


```

输出：

```sh
-2
0
3
5
3

```





### 复杂度分析

- 时间复杂度：O(k + n),每个操作值修改两个元素，即修改操作是O(1)的，最后遍历差分数组是
  O(n)的。
- 空间复杂度：O(n)。







# THE END