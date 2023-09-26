# 目录

[toc]

# leetcode-593-有效的正方形

- 时间：2023-07-03
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/maximum-sum-circular-subarray/?company_slug=alibaba
- 难度：中等



给定一个长度为 n 的**环形整数数组** nums ，返回 nums 的非空 **子数组** 的最大可能和 。

**环形数组** 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。

**子数组** 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。



**示例 1：**

```
输入：nums = [1,-2,3,-2]
输出：3
解释：从子数组 [3] 得到最大和 3
```



**提示：**

+ `n == nums.length`
+ `1 <= n <= 3 * 10^4`
+ `-3 * 10^4 <= nums[i] <= 3 * 10^4`





# 2、题解

## 题目分析



## 解法一：*前缀和数组* *+* *单调队列*

### 算法分析



![image-20230703220859583](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230703220859583.png)

### 代码

```java


/**
 * <p>
 * 环形子数组的最大和
 * </p>
 *
 * @author admin
 * @date 2023/7/3
 */
public class leetcode918 {

    public static void main(String[] args) {
        int[] nums = {1, -2, 3, -2};

        Solution01 solution01 = new Solution01();
        int maxed01 = solution01.maxSubarraySumCircular(nums);
        System.out.println(maxed01);

    }

    /**
     * 解法一：前缀和数组 + 单调队列
     */
    private static class Solution01 {

        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;

            // 前缀和数组，且将 nums 拼成两个
            int[] preSum = new int[n * 2 + 1];
            for (int i = 1; i <= n * 2; i++) {
                preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
            }

            int ans = nums[0];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offer(0);

            // 单调队列的维护
            for (int j = 1; j <= 2 * n; j++) {
                if (deque.peekFirst() < j - n)
                    deque.pollFirst();

                // The optimal i is deque[0], for cand. answer P[j] - P[i].
                ans = Math.max(ans, preSum[j] - preSum[deque.peekFirst()]);

                // Remove any i1's with P[i2] <= P[i1].
                while (!deque.isEmpty() && preSum[j] <= preSum[deque.peekLast()])
                    deque.pollLast();

                deque.offerLast(j);

            }

            return ans;
        }

    }

}


```





### 复杂度分析

- 时间复杂度：O(N)
- 空间复杂度：O(1)









# THE END