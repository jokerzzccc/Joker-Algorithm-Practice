# 目录

[toc]

# leetcode-198-打家劫舍

- 时间：2023-06-23
- 参考链接：
  - [一个方法团灭 LeetCode 打家劫舍问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-f3df7/)
  - [经典动态规划：打家劫舍系列问题](https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/house-robber/
- 难度：中等



你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。

给定一个代表每个房屋存放金额的非负整数数组，计算你 **不触动警报装置的情况下** ，一夜之内能够偷窃到的最高金额。



**提示：**

+ `1 <= nums.length <= 100`
+ `0 <= nums[i] <= 400`



# 2、题解

## 题目分析



## 解法一: 动态规划，自顶向下

### 算法分析

题目很容易理解，而且动态规划的特征很明显。我们前文 [动态规划详解](http://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=1&sn=f1db6dee2c8e70c42240aead9fd224e6&chksm=9bd7fb33aca07225bee0b23a911c30295e0b90f393af75eca377caa4598ffb203549e1768336&scene=21#wechat_redirect) 做过总结，**解决动态规划问题就是找「状态」和「选择」，仅此而已**。

假想你就是这个专业强盗，从左到右走过这一排房子，在每间房子前都有两种**选择**：抢或者不抢。

如果你抢了这间房子，那么你肯定不能抢相邻的下一间房子了，只能从**下下间**房子开始做选择。

如果你不抢这间房子，那么你可以走到**下一间**房子前，继续做选择。

当你走过了最后一间房子后，你就没得抢了，能抢到的钱显然是 0（**base case**）。

以上的逻辑很简单吧，其实已经明确了**「状态」和「选择」**：**你面前房子的索引就是状态，抢和不抢就是选择**。

![image-20230623231639003](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623231639003.png)

在两个选择中，每次都选更大的结果，最后得到的就是最多能抢到的 money：



### 代码

```java


/**
 * <p>
 * 打家劫舍
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode198 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};

        Solution01 solution01 = new Solution01();
        int ro01 = solution01.rob(nums);
        System.out.println(ro01);

    }

    /**
     * 解法一：动态规划，自顶向下
     */
    private static class Solution01 {

        // 备忘录
        private int[] memo;

        public int rob(int[] nums) {

            // 初始化备忘录
            memo = new int[nums.length];
            Arrays.fill(memo, -1);

            // 强盗从第 0 间房子开始抢劫
            return dp(nums, 0);
        }

        /**
         * 返回 dp[start..] 能抢到的最大值
         */
        private int dp(int[] nums, int start) {
            // base case
            if (start >= nums.length) {
                return 0;
            }
            // 避免重复计算
            if (memo[start] != -1) {
                return memo[start];
            }

            int res = Math.max(
                    // 不抢，去下家
                    dp(nums, start + 1),
                    // 抢，去下下家
                    nums[start] + dp(nums, start + 2)
            );
            // 记入备忘录
            memo[start] = res;
            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：O(n)



## 解法二：*动态规划，自底向上*

### 算法分析





### 代码

```java


/**
 * <p>
 * 打家劫舍
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode198 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};

        Solution02 solution02 = new Solution02();
        int rob02 = solution02.rob(nums);
        System.out.println(rob02);

    }

    /**
     * 解法二：动态规划，自底向上
     */
    private static class Solution02 {

        public int rob(int[] nums) {
            int n = nums.length;
            // dp[i] = x 表示：
            // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
            // base case: dp[n] = 0
            int[] dp = new int[n + 2];
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
            }
            return dp[0];
        }

    }

}

```





### 复杂度分析





## 解法三：*动态规划，自底向上（一维数组优化）*

### 算法分析

又发现状态转移只和`dp[i]`最近的两个状态有关，所以可以进一步优化，将空间复杂度降低到 O(1)。



### 代码

```java


/**
 * <p>
 * 打家劫舍
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode198 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};

        Solution03 solution03 = new Solution03();
        int rob03 = solution03.rob(nums);
        System.out.println(rob03);

    }



    /**
     * 解法三：动态规划，自底向上（一维数组优化）
     * 状态转移只和dp[i]最近的两个状态有关，所以可以进一步优化，将空间复杂度降低到 O(1)
     */
    private static class Solution03 {

        public int rob(int[] nums) {
            int n = nums.length;
            // 记录 dp[i+1] 和 dp[i+2]
            int dp_i_1 = 0, dp_i_2 = 0;
            // 记录 dp[i]
            int dp_i = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：O(1)







# THE END