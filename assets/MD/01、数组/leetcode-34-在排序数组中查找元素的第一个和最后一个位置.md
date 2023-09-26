# 目录

[toc]

# leetcode-34-在排序数组中查找元素的第一个和最后一个位置

- 时间：2023-06-14

- 参考链接：
  - [我写了首诗，让你闭着眼睛也能写对二分搜索](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--9c7a4/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
- 难度：中等



给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。



提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109





# 2、题解

## 题目分析



## 解法一:  二分查找（左右边界版）

### 算法分析





### 代码

```java

/**
 * <p>
 * 在排序数组中查找元素的第一个和最后一个位置
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode34 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        Solution01 solution01 = new Solution01();
        int[] range01 = solution01.searchRange(nums, target);
        Arrays.stream(range01).forEach(System.out::println);

    }

    /**
     * 解法一：二分查找（左边界+右边界）
     */
    private static class Solution01 {

        public int[] searchRange(int[] nums, int target) {

            int leftBound = findLeft(nums, target);
            int rightBound = findRight(nums, target);

            return new int[]{leftBound, rightBound};

        }

        /**
         * 查询左边界
         */
        private int findLeft(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }

            // 如果索引越界，说明数组中无目标元素，返回 -1
            if (left < 0 || left >= nums.length) {
                return -1;
            }
            // 判断一下 nums[left] 是不是 target
            return nums[left] == target ? left : -1;
        }

        /**
         * 查询右边界
         */
        private int findRight(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }

            // right - 1 索引越界的话 target 肯定不存在
            if (right - 1 < 0 || right - 1 >= nums.length) {
                return -1;
            }
            // 判断一下 nums[right - 1] 是不是 target
            return nums[right - 1] == target ? (right - 1) : -1;
        }

    }

}

```

输出：

```sh
3
4
```





### 复杂度分析

- 时间复杂度：O(logn)
- 





# THE END