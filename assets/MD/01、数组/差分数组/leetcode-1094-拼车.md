# 目录

[toc]

# leetcode-1094-拼车

- 时间：2023-06-11

- 参考链接：
  - [小而美的算法技巧：差分数组](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/xiao-er-me-c304e/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/car-pooling/
- 难度：中等



车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）

给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。

当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。



提示：

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105



# 2、题解

## 题目分析



## 解法一: 差分数组

### 算法分析

- 方法，差分数组，类比 leetcode1109

联想到差分数组技巧了：**`trips[i]` 代表着一组区间操作，旅客的上车和下车就相当于数组的区间加减；只要结果数组中的元素都小于 `capacity`，就说明可以不超载运输所有旅客**。

但问题是，差分数组的长度（车站的个数）应该是多少呢？题目没有直接给，但给出了数据取值范围：

0 <= trips[i][1] < trips[i][2] <= 1000

车站编号从 0 开始，最多到 1000，也就是最多有 1001 个车站，那么我们的差分数组长度可以直接设置为 1001，这样索引刚好能够涵盖所有车站的编号



### 代码

```java

/**
 * <p>
 * 拼车
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode1094 {

    public static void main(String[] args) {
        int[][] bookings = {{2, 1, 5}, {3, 3, 7}};
        int n = 4;

        Solution01 solution01 = new Solution01();
        boolean carPooling01 = solution01.carPooling(bookings, n);
        System.out.println(carPooling01);

        Solution02 solution02 = new Solution02();
        boolean carPooling02 = solution02.carPooling(bookings, n);
        System.out.println(carPooling02);
    }

    /**
     * 解法二：迭代
     */
    private static class Solution02 {

        public boolean carPooling(int[][] trips, int capacity) {
            int sites[] = new int[1001];
            for (int[] trip : trips) {
                // 上车加
                sites[trip[1]] += trip[0];
                // 下车减
                sites[trip[2]] -= trip[0];
            }
            // 从始发站计数，超过capacity则超载
            int total = 0;
            for (int passengerChanges : sites) {
                total += passengerChanges;
                if (total > capacity) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * 解法一：差分数组
     */
    private static class Solution01 {

        public boolean carPooling(int[][] trips, int capacity) {
            // 最多有 1001 个车站
            int[] nums = new int[1001];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] trip : trips) {
                // 乘客数量
                int val = trip[0];
                // 第 trip[1] 站乘客上车
                int i = trip[1];
                // 第 trip[2] 站乘客已经下车，
                // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
                int j = trip[2] - 1;
                // 进行区间操作
                df.increment(i, j, val);
            }

            int[] res = df.result();

            // 客车自始至终都不应该超载
            for (int i = 0; i < res.length; i++) {
                if (capacity < res[i]) {
                    return false;
                }
            }

            return true;
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
false
false
```





### 复杂度分析

差分数组：

- 时间复杂度：O(k + n),每个操作值修改两个元素，即修改操作是O(1)的，最后遍历差分数组是
  O(n)的。
- 空间复杂度：O(n)。







# THE END