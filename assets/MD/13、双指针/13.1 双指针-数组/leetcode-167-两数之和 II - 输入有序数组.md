# 目录

[toc]

# leetcode-167-两数之和 II - 输入有序数组

- 时间：2023-06-11

- 参考链接：
  - 
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
- 难度：中等



给你一个下标从 1 开始的整数数组 numbers ，该数组已按 **非递减顺序排列**  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。

以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。

你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

你所设计的解决方案必须只使用常量级的额外空间



提示：

2 <= numbers.length <= 3 * 104
-1000 <= numbers[i] <= 1000
numbers 按 非递减顺序 排列
-1000 <= target <= 1000
仅存在一个有效答案







# 2、题解

## 题目分析



## 解法一: 双指针	

### 算法分析

只要数组有序，就应该想到双指针技巧



### 代码

```java


/**
 * <p>
 * 两数之和 II - 输入有序数组
 * </p>
 *
 * @author admin
 * @date 2023/6/11
 */
public class leetcode167 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Solution01 solution01 = new Solution01();
        int[] ints = solution01.twoSum(nums, target);
        Arrays.stream(ints).forEach(System.out::println);

    }

    /**
     * 解法一：双指针
     * 只要数组有序，就应该想到双指针技巧
     */
    private static class Solution01 {

        public int[] twoSum(int[] numbers, int target) {
            // 一左一右两个指针相向而行
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    // 题目要求的索引是从 1 开始的
                    return new int[]{left + 1, right + 1};
                } else if (sum < target) {
                    // 因为数组非递减
                    left++;
                } else if (sum > target) {
                    right--;
                }
            }

            return new int[]{-1, -1};
        }

    }

}
```

输出：

```sh
1
2
```





### 复杂度分析

- 时间复杂度：O(N). 
- 空间复杂度：O(1). 







# THE END