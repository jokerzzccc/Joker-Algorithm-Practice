# 目录

[toc]

# leetcode-55-跳跃游戏

- 时间：2023-06-24
- 参考链接：
  - [如何运用贪心思想玩跳跃游戏](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/tan-xin-le-9bedf/ru-he-yun--48a7c/)



# 1、题目

- 题目：https://leetcode.cn/problems/jump-game/
- 难度：中等



给定一个非负整数数组 `nums` ，你最初位于数组的 **第一个下标** 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。

示例 1：

```sh
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
```



**提示：**

+ `1 <= nums.length <= 3 * 10^4`
+ `0 <= nums[i] <= 10^5`





# 2、题解

## 题目分析

**不知道读者有没有发现，有关动态规划的问题，大多是让你求最值的**，比如最长子序列，最小编辑距离，最长公共子串等等等。这就是规律，因为动态规划本身就是运筹学里的一种求最值的算法。

那么贪心算法作为特殊的动态规划也是一样，也一定是让你求个最值。这道题表面上不是求最值，但是可以改一改：

**请问通过题目中的跳跃规则，最多能跳多远**？如果能够越过最后一格，返回 true，否则返回 false。



## 解法一: 贪心

### 算法分析

每一步都计算一下从当前位置最远能够跳到哪里，然后和一个全局最优的最远位置 `farthest` 做对比，通过每一步的最优解，更新全局最优解，这就是贪心。

### 代码

```java

/**
 * <p>
 * 跳跃游戏
 * </p>
 *
 * @author admin
 * @date 2023/6/24
 */
public class leetcode55 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.canJump(nums));
    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public boolean canJump(int[] nums) {
            int n = nums.length;
            int farthest = 0;
            for (int i = 0; i < n - 1; i++) {
                // 不断计算能跳到的最远距离
                farthest = Math.max(farthest, i + nums[i]);
                // 可能碰到了 0，卡住跳不动了
                if (farthest <= i) {
                    return false;
                }
            }

            return farthest >= n - 1;
        }

    }

}

```





### 复杂度分析









# THE END