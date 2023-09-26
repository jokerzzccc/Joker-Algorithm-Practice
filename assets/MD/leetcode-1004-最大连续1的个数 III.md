# 目录

[toc]

# leetcode-1004-最大连续1的个数 III

- 时间：2023-08-05
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/max-consecutive-ones-iii/description/
- 难度：中等

给定一个二进制数组 `nums` 和一个整数 `k`，如果可以翻转最多 `k` 个 `0` ，则返回 *数组中连续 `1` 的最大个数* 。

 

**示例 1：**

```
输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
```

**示例 2：**

```
输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。
```

 

**提示：**

+ `1 <= nums.length <= 10^5`
+ `nums[i]` 不是 `0` 就是 `1`
+ `0 <= k <= nums.length`



# 2、题解

## 题目分析

+ **重点：题意转换。把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」。**



## 解法一：滑动窗口

### 算法分析

![image-20230805173844246](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230805173844246.png)



### 代码

```java

public class leetcode1004 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestOnes(nums, k));
    }

    /**
     * 解法一：滑动窗口
     * 题意转换。把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」
     * 转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」
     */
    private static class Solution01 {

        public int longestOnes(int[] nums, int k) {
            int n = nums.length;
            // 维护滑动窗口[left, right]的左右指针
            int left = 0, right = 0;
            // 窗口内 0 的个数
            int zeroCounts = 0;
            // 窗口长度
            int res = 0;

            while (right < n) {
                if (nums[right] == 0) {
                    zeroCounts++;
                }
                // 左指针右移，知道窗口内的0的个数 <= k
                while (zeroCounts > k) {
                    if (nums[left] == 0) {
                        zeroCounts--;

                    }
                    left++;
                }
                res = Math.max(res, right - left + 1);
                right++;
            }

            return res;
        }

    }

}

```





### 复杂度分析

+ 时间复杂度：O(N)，因为每个元素只遍历了一次。
+ 空间复杂度：O(1)，因为使用了常数个空间。









# THE END