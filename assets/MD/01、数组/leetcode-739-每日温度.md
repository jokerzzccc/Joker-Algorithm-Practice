# 目录

[toc]

# leetcode-496-下一个更大元素 I

- 时间：2023-07-01
- 参考链接：
  - [单调栈结构解决三道算法题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/dan-diao-z-1bebe/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/next-greater-element-i/
- 难度：简单



给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。



**提示：**

+ `1 <= temperatures.length <= 10^5`
+ `30 <= temperatures[i] <= 100`





# 2、题解

## 题目分析

- 类比 leetcode-496,使用单调栈模板



## 解法一：单调栈

### 算法分析





### 代码

```java

package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>
 * 每日温度
 * </p>
 *
 * @author admin
 * @date 2023/7/1
 */
public class leetcode739 {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        Solution01 solution01 = new Solution01();
        int[] result = solution01.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 解法一：单调栈
     */
    private static class Solution01 {

        public int[] dailyTemperatures(int[] temperatures) {
            int[] greater = nextGreaterElement(temperatures);
            int[] res = new int[greater.length];
            for (int i = 0; i < greater.length; i++) {
                res[i] = greater[i] == 0 ? 0 : greater[i] - i;
            }

            return res;
        }

        /**
         * 返回 nums 所有的下一个最大元素的 下标 数组 res
         */
        public int[] nextGreaterElement(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                    stack.pop();
                }

                res[i] = stack.isEmpty() ? 0 : stack.peek();
                stack.push(i);
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n)
- 空间复杂度：O(n)









# THE END