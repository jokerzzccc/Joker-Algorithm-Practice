# 目录

[toc]

# leetcode-16-最接近的三数之和

- 时间：2023-07-07
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/3sum-closest/
- 难度：中等



给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。



**提示：**

+ `3 <= nums.length <= 1000`
+ `-1000 <= nums[i] <= 1000`
+ `-10^4 <= target <= 10^4`



# 2、题解

## 题目分析



## 解法一:排序 + 双指针

### 算法分析





### 代码

```java


/**
 * <p>
 * 最接近的三数之和
 * </p>
 *
 * @author admin
 * @date 2023/7/7
 */
public class leetcode16 {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.threeSumClosest(nums, target));

    }

    /**
     * 解法一：排序 + 双指针
     * 类比 leetcode15
     */
    private static class Solution01 {

        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            int best = Integer.MAX_VALUE;

            // 枚举 a, 对应指针 first
            for (int first = 0; first < n; first++) {
                // 保证和上一次枚举的元素不相等,避免重复结果
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }

                // 枚举 b, 对应指针 second
                int second = first + 1;
                // c 对应的指针 third,初始指向数组的最右端
                int third = n - 1;

                while (second < third) {
                    int curSum = nums[first] + nums[second] + nums[third];

                    // 找到完全相等的，直接返回
                    if (curSum == target) {
                        return target;
                    }

                    // 因为是最接近的，这里就区别于 leetcode15
                    if (Math.abs(curSum - target) < Math.abs(best - target)) {
                        best = curSum;
                    }

                    // 如果和大于 target，移动 c 对应的指针
                    if (curSum > target) {
                        // 移动到下一个不相等的元素
                        while (second < --third && nums[third + 1] == nums[third]) {
                            --third;
                        }
                    } else {
                        // 如果和小于 target，移动 b 对应的指针
                        while (++second < third && nums[second - 1] == nums[second]) {
                            ++second;
                        }
                    }
                }

            }

            return best;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(N^2),其中 N 是数组 nums 的长度。我们首先需要 O(N1ogN) 的时间对数组进行排
  序，随后在枚举的过程中，使用一重循环O(N)枚举a,双指针O(N)枚举b和c,故一共是O(N^2)。
- 空间复杂度：O(IogN)。排序需要使用O(IogN)的空间。然而我们修改了输入的数组nums,在实际
  情况下不一定允许，因此也可以看成使用了一个额外的数组存储了ums的副本并进行排序，比时空间
  复杂度为O(N)。









# THE END