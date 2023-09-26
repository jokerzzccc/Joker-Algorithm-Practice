# 目录

[toc]

# leetcode-1144-递减元素使数组呈锯齿状

- 时间：2023-08-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/
- 难度：中等

给你一个整数数组 `nums`，每次 **操作** 会从中选择一个元素并 **将该元素的值减少 1**。

如果符合下列情况之一，则数组 `A` 就是 **锯齿数组**：

+ 每个偶数索引对应的元素都大于相邻的元素，即 `A[0] > A[1] < A[2] > A[3] < A[4] > ...`
+ 或者，每个奇数索引对应的元素都大于相邻的元素，即 `A[0] < A[1] > A[2] < A[3] > A[4] < ...`

返回将数组 `nums` 转换为锯齿数组所需的最小操作次数。

 

**示例 1：**

```
输入：nums = [1,2,3]
输出：2
解释：我们可以把 2 递减到 0，或把 3 递减到 1。
```

**示例 2：**

```
输入：nums = [9,6,1,6,2]
输出：4
```

 

**提示：**

+ `1 <= nums.length <= 1000`
+ `1 <= nums[i] <= 1000`



# 2、题解

## 题目分析



## 解法一：贪心

### 算法分析





### 代码

```java


/**
 * <p>
 * 递减元素使数组呈锯齿状
 * </p>
 *
 * @author admin
 * @date 2023/8/17
 */
public class leetcode1144 {

    public static void main(String[] args) {
        int[] nums = {9, 6, 1, 6, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.movesToMakeZigzag(nums));

    }

    /**
     * 解法一：贪心
     */
    private static class Solution01 {

        public int movesToMakeZigzag(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            // 总共有两种，偶数位大或奇数位大，取最小
            return Math.min(help(nums, 0), help(nums, 1));
        }

        private int help(int[] nums, int index) {
            int res = 0;

            // 因为具有单调性，挨着贪心取最大即可
            for (int i = index; i < nums.length; i += 2) {
                int tmpCnt = 0;

                // 取相邻元素，满足条件时的较大操作次数
                if (i - 1 >= 0) {
                    tmpCnt = Math.max(tmpCnt, nums[i] - nums[i - 1] + 1);
                }
                if (i + 1 < nums.length) {
                    tmpCnt = Math.max(tmpCnt, nums[i] - nums[i + 1] + 1);
                }

                res += tmpCnt;
            }

            return res;
        }

    }

}

```





### 复杂度分析











# THE END