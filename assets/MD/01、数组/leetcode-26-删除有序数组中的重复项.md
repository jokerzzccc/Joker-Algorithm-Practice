# 目录

[toc]

# leetcode-26-删除有序数组中的重复项

- 时间：2023-06.-10
- 参考链接：
  - 
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
- 难度：简单



给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。

考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：

- 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
- 返回 k 。



# 2、题解

## 题目分析



## 解法一: 快慢指针	

### 算法分析





### 代码

```java

/**
 * <p>
 * 删除有序数组中的重复项
 * </p>
 *
 * @author admin
 * @date 2023/6/10
 */
public class leetcode26 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        Solution01 solution01 = new Solution01();
        int count = solution01.removeDuplicates(nums);
        // 5
        System.out.println(count);
    }

    /**
     * 解法一：快慢指针
     */
    private static class Solution01 {

        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    // 维护 nums[0..slow] 无重复
                    nums[slow] = nums[fast];
                }
                fast++;
            }
            // 数组长度为索引 + 1
            return slow + 1;

        }

    }

}

```

输出：

```sh
5
```





### 复杂度分析

- 时间复杂度：O(N). 其中*n* 指的是链表的大小
- 空间复杂度：O(1). 我们只会修改原本链表中节点的指向，而在堆栈上的堆栈帧不超过 O*(1)。









# THE END