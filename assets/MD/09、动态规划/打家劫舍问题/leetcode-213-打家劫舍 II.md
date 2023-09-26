# 目录

[toc]

# leetcode-213-打家劫舍 II

- 时间：2023-06-23
- 参考链接：
  - [一个方法团灭 LeetCode 打家劫舍问题](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/yong-dong--63ceb/yi-ge-fang-f3df7/)
  - [经典动态规划：打家劫舍系列问题](https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/house-robber-ii/
- 难度：中等



你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 **围成一圈** ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警** 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 **在不触动警报装置的情况下** ，今晚能够偷窃到的最高金额。



**提示：**

+ `1 <= nums.length <= 100`
+ `0 <= nums[i] <= 400`



# 2、题解

## 题目分析

- 类比 leetcdoe-198

这道题目和 leetcdoe-198 描述基本一样，强盗依然不能抢劫相邻的房子，输入依然是一个数组，但是告诉你**这些房子不是一排，而是围成了一个圈**。

也就是说，现在第一间房子和最后一间房子也相当于是相邻的，不能同时抢。比如说输入数组`nums=[2,3,2]`，算法返回的结果应该是 3 而不是 4，因为开头和结尾不能同时被抢。

这个约束条件看起来应该不难解决，我们前文 [单调栈 Monotonic Stack 的使用](http://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484525&idx=1&sn=3d2e63694607fec72455a52d9b15d4e5&chksm=9bd7fa65aca073734df90b45054448e09c14e6e35ad7b778bff62f9bd6c2b4f6e1ca7bc4f844&scene=21#wechat_redirect) 说过一种解决环形数组的方案，那么在这个问题上怎么处理呢？

首先，首尾房间不能同时被抢，那么只可能有三种不同情况：要么都不被抢；要么第一间房子被抢最后一间不抢；要么最后一间房子被抢第一间不抢。

![image-20230623233855824](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230623233855824.png)

那就简单了啊，这三种情况，哪种的结果最大，就是最终答案呗！不过，其实我们不需要比较三种情况，**只要比较情况二和情况三就行了，因为这两种情况对于房子的选择余地比情况一大呀，房子里的钱数都是非负数，所以选择余地大，最优决策结果肯定不会小**。

所以只需对之前的解法稍作修改即可：



## 解法一: 动态规划，自顶向下

### 算法分析





### 代码

```java
package com.joker.algorithm.dp;

import java.util.Arrays;

/**
 * <p>
 * 打家劫舍 II
 * </p>
 *
 * @author admin
 * @date 2023/6/23
 */
public class leetcode213 {

    public static void main(String[] args) {

        int[] nums = {2, 3, 2};

        Solution01 solution01 = new Solution01();
        int ro01 = solution01.rob(nums);
        System.out.println(ro01);
    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            return Math.max(robRange(nums, 0, n - 2),
                    robRange(nums, 1, n - 1));
        }

        // 仅计算闭区间 [start,end] 的最优结果
        int robRange(int[] nums, int start, int end) {
            int n = nums.length;
            int dp_i_1 = 0, dp_i_2 = 0;
            int dp_i = 0;
            for (int i = end; i >= start; i--) {
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
- 空间复杂度：O(n)









# THE END