# 目录

[toc]

# leetcode-31-下一个排列

- 时间：2023-07-08
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/next-permutation/
- 难度：中等

整数数组的一个 **排列**  就是将其所有成员以序列或线性顺序排列。

- 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。

整数数组的 **下一个排列** 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

- 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
- 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
- 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。

给你一个整数数组 nums ，找出 nums 的下一个排列。

**必须 原地 修改**，只允许使用额外常数空间。



**提示：**

+ `1 <= nums.length <= 100`
+ `0 <= nums[i] <= 100`



# 2、题解

## 题目分析



## 解法一

### 算法分析





### 代码

```java
package com.joker.algorithm;

import java.util.Arrays;

/**
 * <p>
 * 下一个排列
 * </p>
 *
 * @author admin
 * @date 2023/7/8
 */
public class leetcode31 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        Solution01 solution01 = new Solution01();
        solution01.nextPermutation(nums);

    }

    /**
     * 解法一：排序
     */
    private static class Solution01 {

        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            // 1. 从后向前（从右向左），找第一个升序的，即 左 < 右，即为较小数 nums[i]
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }

            // 2. 如果找到了，则在区间 [i + 1, n) 中从后向前查找第一个元素 满足 nums[i]  < nums[j]
            // 较大数即为 nums[j]
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                // 交换 nums[i] 与 nums[j]
                swap(nums, i, j);
            }

            // 3.  此时可以证明区间[i+1,n)必为降序。我们可以直接使用双指针反转区间[i+1,n)
            // 使其变为升序，而无需对该区间进行排序。
            reverse(nums, i + 1);
        }

        /**
         * 双指针法，从起点下标 start 原地反转 nums
         */
        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

    /**
     * 解法二：排序
     */
    private static class Solution02 {

        public void nextPermutation(int[] nums) {
            int len = nums.length;
            for (int i = len - 1; i > 0; i--) {
                // 找第一个升序的元素，即右 > 左
                // 因为要改动尽可能小，即右边动的越小越好，
                if (nums[i] > nums[i - 1]) {
                    // [i, len] 排升序后，找区间内第一个 > nums[i - 1] 的，并与之原地交换
                    Arrays.sort(nums, i, len);
                    for (int j = i; j < len; j++) {
                        if (nums[j] > nums[i - 1]) {
                            int tmp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = tmp;
                            return;
                        }
                    }
                }
            }

            // 找不到，就返回升序排列
            Arrays.sort(nums);
        }

    }

}

```





### 复杂度分析











# THE END