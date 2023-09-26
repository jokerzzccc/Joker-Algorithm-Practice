# 目录

[toc]

# leetcode-2560-打家劫舍 IV

- 时间：2023-09-26
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/house-robber-iv/description/
- 难度：中等



沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。

由于相邻的房屋装有相互连通的防盗系统，所以小偷 **不会窃取相邻的房屋** 。

小偷的 **窃取能力** 定义为他在窃取过程中能从单间房屋中窃取的 **最大金额** 。

给你一个整数数组 `nums` 表示每间房屋存放的现金金额。形式上，从左起第 `i` 间房屋中放有 `nums[i]` 美元。

另给你一个整数 `k` ，表示窃贼将会窃取的 **最少** 房屋数。小偷总能窃取至少 `k` 间房屋。

返回小偷的 **最小** 窃取能力。

 

**示例 1：**

```
输入：nums = [2,3,5,9], k = 2
输出：5
解释：
小偷窃取至少 2 间房屋，共有 3 种方式：
- 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
- 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
- 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
因此，返回 min(5, 9, 9) = 5 。
```

**示例 2：**

```
输入：nums = [2,7,9,3,1], k = 2
输出：2
解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
```

 

**提示：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^9`
- `1 <= k <= (nums.length + 1)/2`





# 2、题解

## 题目分析

参考链接：

- https://leetcode.cn/problems/house-robber-iv/solutions/2093952/er-fen-da-an-dp-by-endlesscheng-m558/
- 其实就是**最小化最大值**的模板，



![image-20230926095432281](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230926095432281.png)

## 解法一: 二分 + 动态规划

### 算法分析

![image-20230926095532161](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230926095532161.png)



### 代码

```java
package com.joker.algorithm.dp;

import java.util.Arrays;

/**
 * <p>
 * 打家劫舍 IV
 * </p>
 *
 * @author jokerzzccc
 * @since 2023/9/19
 */
public class leetcode2560 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 9};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.minCapability(nums, k));
    }

    /**
     * 解法一：二分 + DP
     * 最小化最大值
     */
    private static class Solution01 {
        public int minCapability(int[] nums, int k) {
            int left = 0;
            int right = Arrays.stream(nums)
                    .max().getAsInt();
            // 二分查找，
            // 开区间：(left, right)
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;
                if (check(nums, k, mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }

            return right;

        }

        /**
         * dp (滚动变量空间优化)。
         * 当小偷的窃取能力为 midNum 时，是否可以窃取至少 k 个房子 :
         * f1 代表不考虑当前房屋时，小偷窃取的最多的房屋数;
         * f2 代表考虑当前房屋时 ，小偷窃取的最多的房屋数
         */
        private boolean check(int[] nums, int k, int midNum) {
            int f1 = 0, f2 = 0;
            for (int num : nums) {
                // 做选择：不偷与偷
                // 当前的金额超过了小偷的能力，则不能窃取
                if (num > midNum) {
                    f1 = f2;
                } else { // 如果当前的金额小于等于小偷的能力 ，小偷可以选择窃取
                    int tmp = f2;
                    f2 = Math.max(f2, f1 + 1);
                    f1 = tmp;
                }
            }

            return f2 >= k;
        }
    }
}

```





### 复杂度分析

![image-20230926102736079](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230926102736079.png)









# THE END