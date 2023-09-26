# 目录

[toc]

# leetcode-41-缺失的第一个正数

- 时间：2023-08-05
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/first-missing-positive/
- 难度：困难

给你一个未排序的整数数组 `nums` ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 `O(n)` 并且只使用常数级别额外空间的解决方案。

 

**示例 1：**

```
输入：nums = [1,2,0]
输出：3
```

**示例 2：**

```
输入：nums = [3,4,-1,1]
输出：2
```

**示例 3：**

```
输入：nums = [7,8,9,11,12]
输出：1
```

 

**提示：**

+ `1 <= nums.length <= 5 * 10^5`
+ `-2^31 <= nums[i] <= 2^31 - 1`



# 2、题解

## 题目分析



## 解法一：原地置换

### 算法分析

![image-20230805235304791](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230805235304791.png)



### 代码

```java


/**
 * <p>
 * 缺失的第一个正数
 * </p>
 *
 * @author admin
 * @date 2023/8/5
 */
public class leetcode41 {

    public static void main(String[] args) {
        int[] nums = {7, 8, 9, 11, 12};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.firstMissingPositive(nums));

    }

    /**
     * 解法一：原地置换
     * 类比 offer03
     */
    private static class Solution01 {

        public int firstMissingPositive(int[] nums) {
            int len = nums.length;

            // 对于 nums[i] 中的数，其属于[1...len] 的数，放到对应下标 nums[i] - 1 上
            for (int i = 0; i < len; i++) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                // 大于 len 的不用管，因为要找的是未出现的最小正整数
                while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                }
            }

            // 要找的数一定在 [1, len + 1] 左闭右闭这个区间里.
            // 第 1 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数
            for (int i = 0; i < len; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            // 都正确则返回数组长度 + 1
            return len + 1;
        }

        /**
         * 原地交换 nums 两个下标的元素
         */
        private void swap(int[] nums, int index1, int index2) {
            int tmp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = tmp;
        }

    }

}

```





### 复杂度分析

+ 时间复杂度：O(N)，这里 N 是数组的长度。
+ 空间复杂度：O(1)。









# THE END