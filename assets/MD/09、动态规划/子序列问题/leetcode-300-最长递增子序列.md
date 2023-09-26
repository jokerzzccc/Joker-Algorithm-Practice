# 目录

[toc]

# leetcode-300-最长递增子序列

- 时间：2023-05-22
- 参考链接：
  - [动态规划设计：最长递增子序列](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/dong-tai-g-a223e/dong-tai-g-6ea57/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/longest-increasing-subsequence/
- 难度：中等



给你一个整数数组 `nums` ，找到其中最长严格递增子序列的长度。

**子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。

**示例 1：**

```
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
```

**示例 2：**

```
输入：nums = [0,1,0,3,2,3]
输出：4
```

**示例 3：**

```
输入：nums = [7,7,7,7,7,7,7]
输出：1
```



**提示：**

+ `1 <= nums.length <= 2500`
+ `-104 <= nums[i] <= 104`



**进阶：**

+ 你能将算法的时间复杂度降低到 `O(n log(n))` 吗?

Related Topics

数组

二分查找

动态规划



# 2、题解

## 题目分析



## 解法一：动态规划

### 算法

**状态定义：**

+ *dp*[*i*] 的值代表 `nums` 以 *nums*[*i*] 结尾的最长子序列长度。

**转移方程：** 设 *j*∈[0,*i*)，考虑每轮计算新 *dp*[*i*] 时，遍历 [0,*i*) 列表区间，做以下判断：

1. **当 nums[i] > nums[j] 时：** nums[i] 可以接在nums[j]之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j] + 1 ；
2. **当nums[i] < nums[j]时：**  nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。

+ 上述所有 **`1.` 情况** 下计算出的 dp[j]+1 的最大值，为直到 *i* 的最长上升子序列长度（即 *dp*[*i*] ）。实现方式为遍历 *j* 时，每轮执行 *dp*[*i*]=*max*(*dp*[*i*],*dp*[*j*]+1)。
+ **转移方程：** `dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)`。

**初始状态：**

+ *dp*[*i*] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。

**返回值：**

+ 返回 *dp* 列表最大值，即可得到全局最长上升子序列长度。

### 代码

```java
/**
 * <p>
 * 最长递增子序列
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/22
 */
public class leetcode300 {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        Solution01 solution01 = new Solution01();
        int maxLength = solution01.lengthOfLIS(nums);
        System.out.println(maxLength);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            // 表示，最少都有自身一个子序列的长度
            Arrays.fill(dp, 1);
            int maxRes = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxRes = Math.max(maxRes, dp[i]);
            }

            return maxRes;
        }

    }

}
```

### 复杂度分析

- 时间复杂度：*O*($n^2$)，其中 *n* 为数组 *nums* 的长度。动态规划的状态数为 *n*，计算状态 *dp*[*i*] 时，需要 *O*(*n*) 的时间遍历 *dp*[0…*i*−1] 的所有状态，所以总时间复杂度为 *O*(*n*2)。

- 空间复杂度：*O*(*n*)，需要额外使用长度为 *n* 的 *dp* 数组。



## 解法二：贪心 + 二分查找（优化解法）

### 算法

#### 解题思路：

+ **降低复杂度切入点：** 解法一中，遍历计算 *dp* 列表需 *O*(*N*)，计算每个 *dp*[*k*] 需 *O*(*N*)。

  1. 动态规划中，通过线性遍历来计算 *dp* 的复杂度无法降低；
  2. 每轮计算中，需要通过线性遍历 [0,*k*) 区间元素来得到 *dp*[*k*] 。我们考虑：是否可以通过重新设计**状态定义**，使整个 *dp* 为一个**排序列表**；这样在计算每个 *dp*[*k*] 时，就可以通过二分法遍历 [0,*k*) 区间元素，将此部分复杂度由 *O*(*N*) 降至 *O*(*logN*)。

+ **设计思路：**

  + 新的状态定义：

    + 我们考虑维护一个列表 tails，其中每个元素 *tails*[*k*] 的值代表 **长度为 k+1 的子序列尾部元素的值**。
    + 如 [1,4,6] 序列，长度为 1,2,3 的子序列尾部元素值分别为 *tails*=[1,4,6]。

  + 状态转移设计：

    + 设常量数字 *N*，和随机数字 *x*，我们可以容易推出：当 *N* 越小时，*N*<*x* 的几率越大。例如： *N*=0 肯定比 *N*=1000 更可能满足 *N*<*x*。
    + 在遍历计算每个 *tails*[*k*]，不断更新长度为 [1,*k*] 的子序列尾部元素值，**始终保持每个尾部元素值最小** （例如 [1,5,3]]， 遍历到元素 5 时，长度为 2 的子序列尾部元素值为 5；当遍历到元素 3 时，尾部元素值应更新至 3，因为 3 遇到比它大的数字的几率更大）。

  + *tails* 列表一定是严格递增的：

    即当尽可能使每个子序列尾部元素值最小的前提下，子序列越长，其序列尾部元素值一定更大。

    + **反证法证明：** 当 *k*<*i*，若 *t**ai**l**s*[*k*]>=*t**ai**l**s*[*i*]，代表较短子序列的尾部元素的值 > 较长子序列的尾部元素的值。这是不可能的，因为从长度为 *i* 的子序列尾部倒序删除 *i*−1 个元素，剩下的为长度为 *k* 的子序列，设此序列尾部元素值为 *v*，则一定有 *v*<*t**ai**l**s*[*i*] （即长度为 *k* 的子序列尾部元素值一定更小）， 这和 *t**ai**l**s*[*k*]>=*t**ai**l**s*[*i*] 矛盾。
    + 既然严格递增，每轮计算 *t**ai**l**s*[*k*] 时就可以使用二分法查找需要更新的尾部元素值的对应索引 *i*。

+ **算法流程：**

  + **状态定义：**
    + *tails*[*k*] 的值代表 长度为 *k*+1 子序列 的尾部元素值。
  + **转移方程：** 设 *res* 为 *tails* 当前长度，代表直到当前的最长上升子序列长度。设 *j*∈[0,*res*)，考虑每轮遍历 *nums*[*k*] 时，通过二分法遍历 [0,*res*) 列表区间，找出 *nums*[*k*] 的大小分界点，会出现两种情况：
    + **区间中存在 tails[i] > nums[k]：** 将第一个满足 tails[*i*]>*nums*[*k*] 执行 *tails*[*i*]=*nums*[*k*] ；因为更小的 *nums*[*k*] 后更可能接一个比它大的数字（前面分析过）。
    + **区间中不存在tails[i] > nums[k]：** 意味着 *nums*[*k*] 可以接在前面所有长度的子序列之后，因此肯定是接到最长的后面（长度为 *res* ），新子序列长度为 *res*+1。
  + **初始状态：**
    + 令 tails 列表所有值 =0。
  + **返回值：**
    + 返回 *res* ，即最长上升子子序列长度。

### 代码

```java

    /**
     * 解法二：贪心 + 二分查找
     */
    private static class Solution02 {

        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] tails = new int[nums.length];
            int maxRes = 0;
            for (int num : nums) {
                // 二分查找
                // 每个元素开始遍历 看能否插入到之前的 tails数组的位置 如果能 是插到哪里
                int left = 0, right = maxRes, pos = 0;
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (tails[middle] < num) {
                        left = middle + 1;
                        pos = left;
                    } else {
                        right = middle;
                    }
                }
                tails[pos] = num;
                // maxRes == right 说明目前tail数组的元素都比当前的num要小 因此最长子序列的长度可以增加了
                if (maxRes == right) {
                    maxRes++;
                }
                // debug
                Arrays.stream(tails).mapToObj(x -> x + ",").forEach(System.out::print);
                System.out.println("\n");
            }
            return maxRes;
        }

    }
```

输出结果：

```sh
10,0,0,0,0,0,0,0,

9,0,0,0,0,0,0,0,

2,0,0,0,0,0,0,0,

2,5,0,0,0,0,0,0,

2,3,0,0,0,0,0,0,

2,3,7,0,0,0,0,0,

2,3,7,101,0,0,0,0,

2,3,7,18,0,0,0,0,

```



### 复杂度分析

- **时间复杂度O(NlogN) ：** 遍历 nums 列表需 *O*(*N*)，在每个 *nums*[*i*] 二分法需 *O*(*logN*)。

- **空间复杂度O(N) **：tails 列表占用线性大小额外空间。











# THE END
