# 目录

[toc]

# leetcode-1-两数之和

- 时间：2023-06-25
- 参考链接：
  - [一个方法团灭 nSum 问题](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/yi-ge-fang-894da/)
  - [一个函数秒杀 2Sum 3Sum 4Sum 问题](https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/two-sum/
- 难度：简单

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 **和为目标值** target  的那 **两个** 整数，并**返回它们的数组下标**。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

示例 1：

```sh
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```



提示：

- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- **只会存在一个有效答案**






# 2、题解

## 题目分析



## 解法一: 缓存

### 算法分析



### 代码

```java

/**
 * <p>
 * 两数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;

        Solution01 solution01 = new Solution01();
        int[] twoSum = solution01.twoSum(nums, target);
        System.out.println(Arrays.toString(twoSum));

    }

    /**
     * 解法一：缓存
     */
    private static class Solution01 {

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }

            return new int[]{};
        }

    }

}

```

输出如下：

```sh
[1, 2]
```



### 复杂度分析

- 时间复杂度：O(N),其中N是数组中的元素数量。对于每一个元素 x,我们可以O(1)地寻找
  `target - x`
- 空间复杂度：O(N),其中N是数组中的元素数量。主要为哈希表的开销。









# THE END