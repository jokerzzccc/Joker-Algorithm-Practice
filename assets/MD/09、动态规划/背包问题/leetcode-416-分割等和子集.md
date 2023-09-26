# 目录

[toc]

# leetcode-416-分割等和子集

- 时间：2023-05-29
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/partition-equal-subset-sum/
- 难度：中等



给你一个 **只包含正整数** 的 **非空** 数组 `nums` 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。



**示例 1：**

```
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
```

**示例 2：**

```
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
```



**提示：**

+ `1 <= nums.length <= 200`
+ `1 <= nums[i] <= 100`

Related Topics

数组

动态规划





# 2、题解

## 题目分析

- 题解参考：
  - https://labuladong.gitee.io/algo/di-er-zhan-a01c6/bei-bao-le-34bd4/jing-dian--43be3/
  - https://leetcode.cn/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
- 可以转换为 【0-1背包问题】

对于这个问题，我们可以先对集合求和，得出 `sum`，把问题转化为背包问题：

**给一个可装载重量为 `sum / 2` 的背包和 `N` 个物品，每个物品的重量为 `nums[i]`。现在让你装物品，是否存在一种装法，能够恰好将背包装满**？



## 解法一：动态规划+二维

### 算法分析

**函数定义：**

- **`dp[i][j] = x` 表示，对于前 `i` 个物品（`i` 从 1 开始计数），当前背包的容量为 `j` 时，若 `x` 为 `true`，则说明可以恰好将背包装满，若 `x` 为 `false`，则说明不能恰好将背包装满**。
  - 比如说，如果 `dp[4][9] = true`，其含义为：对于容量为 9 的背包，若只是在前 4 个物品中进行选择，可以有一种方法把背包恰好装满。
- 想求的最终答案就是 `dp[N][sum/2]`
- **base case** 就是 `dp[..][0] = true` 和 `dp[0][..] = false`，因为背包没有空间的时候，就相当于装满了，而当没有物品可选择的时候，肯定没办法装满背包。



**状态转移方程：**

- 如果不把 `nums[i]` 算入子集，**或者说你不把这第 `i` 个物品装入背包**，那么是否能够恰好装满背包，取决于上一个状态 `dp[i-1][j]`，继承之前的结果。
- 如果把 `nums[i]` 算入子集，**或者说你把这第 `i` 个物品装入了背包**，那么是否能够恰好装满背包，取决于状态 `dp[i-1][j-nums[i-1]]`。
  - 注意：由于 `dp` 数组定义中的 `i` 是从 1 开始计数，而数组索引是从 0 开始的，所以第 `i` 个物品的重量应该是 `nums[i-1]`，这一点不要搞混。

`dp[i - 1][j-nums[i-1]]` 也很好理解：你如果装了第 `i` 个物品，就要看背包的剩余重量 `j - nums[i-1]` 限制下是否能够被恰好装满。

换句话说，如果 `j - nums[i-1]` 的重量可以被恰好装满，那么只要把第 `i` 个物品装进去，也可恰好装满 `j` 的重量；否则的话，重量 `j` 肯定是装不满的。

```java
dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]]
```



初始化条件：

![image-20230529225631538](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230529225631538.png)





### 代码

```java
/**
 * <p>
 * 分割等和子集
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/29
 */
public class leetcode416 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        Solution01 solution01 = new Solution01();
        boolean canPartition01 = solution01.canPartition(nums);
        System.out.println(canPartition01);

    }

    /**
     * 解法一：动态规划 + 二维数组
     */
    private static class Solution01 {

        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            // 特判：如果是奇数，就不符合要求
            if (sum % 2 != 0) {
                return false;
            }

            int target = sum / 2;
            // 创建二维状态数组，行：第几个物品（对应数组下标为 i - 1），列：容量（包括 0）
            boolean[][] dp = new boolean[n + 1][target + 1];

            // base case
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= target; j++) {
                    if (j - nums[i - 1] < 0) {
                        // 背包容量不足，不能装入第 i 个物品
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // 第 i 个物品（对应的种来为 nums[i - 1]） 装入或不装入背包
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            // debug
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));

            return dp[n][target];
        }

    }

}
```

输出如下：

```sh
[true, false, false, false, false, false, false, false, false, false, false, false]
[true, true, false, false, false, false, false, false, false, false, false, false]
[true, true, false, false, false, true, true, false, false, false, false, false]
[true, true, false, false, false, true, true, false, false, false, false, true]
[false, true, false, false, false, true, true, false, false, false, true, true]
true

```



### 复杂度分析

- 时间复杂度：O(NC):这里 N 是数组元素的个数，C 是数组元素的和的一半。
- 空间复杂度：O(NC)。



## 解法二：动态规划+一维数组（空间优化）

### 算法分析

- **注意到 `dp[i][j]` 都是通过上一行 `dp[i-1][..]` 转移过来的**，之前的数据都不会再使用了。
- 



> 友情提示：这一点在刚开始学习的时候，可能会觉得很奇怪。理解的办法是：拿题目中的示例，画一个表格，自己模拟一遍程序是如何「填表」的行为，就很清楚为什么状态数组降到 1 行的时候，需要「从后前向」填表。



### 代码

```java
/**
 * <p>
 * 分割等和子集
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/29
 */
public class leetcode416 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        Solution01 solution01 = new Solution01();
        boolean canPartition01 = solution01.canPartition(nums);
        System.out.println(canPartition01);

        Solution02 solution02 = new Solution02();
        boolean canPartition02 = solution02.canPartition(nums);
        System.out.println(canPartition02);

    }

    /**
     * 解法二：动态规划 + 一维数组（空间优化）
     */
    private static class Solution02 {

        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            // 特判：如果是奇数，就不符合要求
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];

            // base case
            dp[0] = true;

            for (int i = 0; i < n; i++) {
                for (int j = target; j >= 0; j--) {
                    if (j - nums[i] >= 0) {
                        // 背包容量充足的情况下
                        dp[j] = dp[j] || dp[j - nums[i]];
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[j]);
                    }
                }
            }
            System.out.println(Arrays.toString(dp));

            return dp[target];
        }

    }

    /**
     * 解法一：动态规划 + 二维数组
     */
    private static class Solution01 {

        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return false;
            }
            int sum = Arrays.stream(nums).sum();
            // 特判：如果是奇数，就不符合要求
            if (sum % 2 != 0) {
                return false;
            }

            int target = sum / 2;
            // 创建二维状态数组，行：第几个物品（对应数组下标为 i - 1），列：容量（包括 0）
            boolean[][] dp = new boolean[n + 1][target + 1];

            // base case
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= target; j++) {
                    if (j - nums[i - 1] < 0) {
                        // 背包容量不足，不能装入第 i 个物品
                        dp[i][j] = dp[i - 1][j];

                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    } else {
                        // 第 i 个物品（对应的种来为 nums[i - 1]） 装入或不装入背包
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    }
                }
            }

            // debug
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));

            return dp[n][target];
        }

    }

}
```

输出如下：

```sh
dp[i-0 j-11]: false
dp[i-0 j-10]: false
dp[i-0 j-9]: false
dp[i-0 j-8]: false
dp[i-0 j-7]: false
dp[i-0 j-6]: false
dp[i-0 j-5]: false
dp[i-0 j-4]: false
dp[i-0 j-3]: false
dp[i-0 j-2]: false
dp[i-0 j-1]: true
dp[i-1 j-11]: false
dp[i-1 j-10]: false
dp[i-1 j-9]: false
dp[i-1 j-8]: false
dp[i-1 j-7]: false
dp[i-1 j-6]: true
dp[i-1 j-5]: true
dp[i-2 j-11]: true
dp[i-3 j-11]: true
dp[i-3 j-10]: true
dp[i-3 j-9]: false
dp[i-3 j-8]: false
dp[i-3 j-7]: false
dp[i-3 j-6]: true
dp[i-3 j-5]: true
[true, true, false, false, false, true, true, false, false, false, true, true]
true

```



其实这段代码和之前的解法思路完全相同，只在一行 `dp` 数组上操作，`i` 每进行一轮迭代，`dp[j]` 其实就相当于 `dp[i-1][j]`，所以只需要一维数组就够用了。

**唯一需要注意的是 `j` 应该从后往前反向遍历，因为每个物品（或者说数字）只能用一次，以免之前的结果影响其他的结果**。



### 复杂度分析

- 时间复杂度：O(NC): 这里 N 是数组元素的个数，C 是数组元素的和的一半；
- 空间复杂度：O(C): 减少了物品那个维度，无论来多少个数，用一行表示状态就够了。



# THE END