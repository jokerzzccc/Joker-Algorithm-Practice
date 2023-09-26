# 目录

[toc]

# leetcode-53-最大子数组和

- 时间：2023-05-27
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-subarray/
- 难度：中等



给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23


提示：

1 <= nums.length <= $10^5$
$-10^4$ <= nums[i] <= $10^4$





# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

状态定义：

dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和。



状态方程：

dp[i] = Max{dp[i - 1] + nums[i], nums[i]}



### 代码

```java
/**
 * <p>
 * 最大子数组和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/27
 */
public class leetcode53 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Solution01 solution01 = new Solution01();
        int maxSubArraySum = solution01.maxSubArray(nums);
        System.out.println(maxSubArraySum);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int n = nums.length;

            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[n];
            dp[0] = nums[0];
            int maxSum = dp[0];
            for (int i = 1; i < n; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }

                maxSum = Math.max(maxSum, dp[i]);
            }

            return maxSum;
        }

    }

}
```



### 复杂度分析

- 时间复杂度：O(n),其中 n 为 nums 数组的长度。我们只需要遍历一遍数组即可求得答案。
- 空间复杂度：O(1)。我们只需要常数空间存放若干变量。





## 解法二：分治法

### 算法分析

- 参考：https://leetcode.cn/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/

定义一个操作：

```java
// 表示，查询 nums 序列 [l,r] 内的最大字段和。
etInfo(int[] nums, int left, int right)
```

对于一个区间 $[l,r]$ ，我们可以维护四个量：

- lSum表示$[l,r]$内以 $l$ 为左端点的最大子段和
- rSum表示 $[l,r]$ 内以r为右端点的最大子段和
- mSum表示 $[l,r]$ 内的最大子段和
- iSum表示  $[l,r]$ 的区间和





原文：

![image-20230527184349748](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230527184349748.png)

![image-20230527184402966](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230527184402966.png)



### 代码

```java
/**
 * <p>
 * 最大子数组和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/27
 */
public class leetcode53 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Solution02 solution02 = new Solution02();
        int maxSubArraySum02 = solution02.maxSubArray(nums);
        System.out.println(maxSubArraySum02);

    }

    /**
     * 方法二：分治法(线段树)
     */
    private static class Solution02 {

        public static class Status {

            // 对于一个区间 $[l,r]$ ，我们可以维护四个量：
            //
            //- lSum表示$[l,r]$内以 $l$ 为左端点的最大子段和
            //- rSum表示 $[l,r]$ 内以r为右端点的最大子段和
            //- mSum表示 $[l,r]$ 内的最大子段和
            //- iSum表示  $[l,r]$ 的区间和
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum, int rSum, int mSum, int iSum) {
                this.lSum = lSum;
                this.rSum = rSum;
                this.mSum = mSum;
                this.iSum = iSum;
            }

        }

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            return getInfo(nums, 0, nums.length - 1).mSum;
        }

        private Status getInfo(int[] nums, int left, int right) {
            if (left == right) {
                return new Status(nums[left], nums[left], nums[left],nums[left] );
            }
            int middle = (left + right) / 2;
            Status leftSub = getInfo(nums,left, middle);
            Status rightSub = getInfo(nums, middle + 1, right);
            return pushUp(leftSub, rightSub);

        }

        private Status pushUp(Status leftSub, Status rightSub) {
            int iSum = leftSub.iSum + rightSub.iSum;
            int lSum = Math.max(leftSub.lSum, leftSub.iSum + rightSub.lSum);
            int rSum = Math.max(rightSub.rSum, rightSub.iSum + leftSub.rSum);
            int mSum = Math.max(Math.max(leftSub.mSum, rightSub.mSum), leftSub.rSum + rightSub.lSum);
            return new Status(lSum, rSum, mSum, iSum);

        }

    }

}
```



### 复杂度分析

![image-20230527183956321](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230527183956321.png)





# THE END