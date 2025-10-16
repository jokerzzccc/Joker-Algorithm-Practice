# 目录

[toc]

# leetcode-303-区域和检索 - 数组不可变

- 时间：2023-06-11

- 参考链接：
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/range-sum-query-immutable/
- 难度：简单



给定一个整数数组  nums，处理以下类型的多个查询:

计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
实现 NumArray 类：

- NumArray(int[] nums) 使用数组 nums 初始化对象

- int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )









# 2、题解

## 题目分析



## 解法一: 一维数组的前缀和

### 算法分析

这道题的最优解法是使用**前缀和**技巧，将 `sumRange` 函数的时间复杂度降为 `O(1)`，说白了就是不要在 `sumRange` 里面用 for 循环



**前缀和主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和**。



### 代码

```java

/**
 * <p>
 * 区域和检索 - 数组不可变
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode303 {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        int left = 2;
        int right = 5;

        NumArray numArray = new NumArray(nums);
        int sumRange = numArray.sumRange(left, right);
        System.out.println(sumRange);

    }

    /**
     * 解法一：一维数组的前缀和
     */
    private static class NumArray {

        /**
         * 前缀和数组
         */
        private int[] preSum;

        public NumArray(int[] nums) {
            // preSum[0] = 0，便于计算累加和
            preSum = new int[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        /**
         * 查询闭区间 [left, right] 的累加和
         */
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }

    }

}


```

输出：

```sh
-1
```





### 复杂度分析

- 时间复杂度：O(1). 
- 空间复杂度：O(N). 







# THE END