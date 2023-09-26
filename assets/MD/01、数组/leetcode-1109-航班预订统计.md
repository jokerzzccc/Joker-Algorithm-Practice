# 目录

[toc]

# leetcode-1109-航班预订统计

- 时间：2023-06-11

- 参考链接：
  - [小而美的算法技巧：差分数组](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/xiao-er-me-c304e/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/range-addition/
- 难度：中等



这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。



提示：

1 <= n <= 2 * 104
1 <= bookings.length <= 2 * 104
bookings[i].length == 3
1 <= firsti <= lasti <= n
1 <= seatsi <= 104



# 2、题解

## 题目分析

这个题目就在那绕弯弯，其实它就是个差分数组的题，我给你翻译一下：

给你输入一个长度为 `n` 的数组 `nums`，其中所有元素都是 0。再给你输入一个 `bookings`，里面是若干三元组 `(i, j, k)`，每个三元组的含义就是要求你给 `nums` 数组的闭区间 `[i-1,j-1]` 中所有元素都加上 `k`。请你返回最后的 `nums` 数组是多少？



因为题目说的 `n` 是从 1 开始计数的，而数组索引从 0 开始，所以对于输入的三元组 `(i, j, k)`，数组区间应该对应 `[i-1,j-1]`。

这么一看，不就是一道标准的差分数组题嘛？

## 解法一: 差分数组

### 算法分析

- 方法，差分数组，类比 leetcode370



### 代码

```java

/**
 * <p>
 * 航班预订统计
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode1109 {

    public static void main(String[] args) {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        Solution01 solution01 = new Solution01();
        int[] flightBookings = solution01.corpFlightBookings(bookings, n);
        Arrays.stream(flightBookings).forEach(System.out::println);
    }

    private static class Solution01 {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            // nums 初始化为全 0
            int[] nums = new int[n];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] booking : bookings) {
                // 注意转成数组索引要减一哦
                int i = booking[0] - 1;
                int j = booking[1] - 1;
                int val = booking[2];
                // 对区间 nums[i..j] 增加 val
                df.increment(i, j, val);
            }
            // 返回最终的结果数组
            return df.result();
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
10
55
45
25
25
```





### 复杂度分析

- 时间复杂度：O(k + n),每个操作值修改两个元素，即修改操作是O(1)的，最后遍历差分数组是
  O(n)的。
- 空间复杂度：O(n)。







# THE END