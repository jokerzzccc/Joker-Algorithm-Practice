# 目录

[toc]

# offer-03-数组中重复的数字

- 时间：2023-04-19





# 1、题目

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。



# 2、题解

## 法一：

算法流程：

1. 遍历数组 `nums`,设索引初始值为i=0:
   1. 若 `nums[i]=i`, 说明比数字已在对应索引位置，无需交换，因比跳过；
   2. 若 `nums[nums[i]] = nums[i]`：代表索引 `nums[i]` 处和索引 `i` 处的元素值都为 `nums[i]`, 即找到一组重复值，返回此值 `nums[i]`
   3. 否则：交换索引为 `i` 和 `nums[i]` 的元素值，将此数字交换至对应索引位置。
2. 若遍历完毕尚未返回，则返回一1。

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
}

```

复杂度分析：

- 时间复杂度O(N):遍历数组使用O(N),每轮遍历的判断和交换操作使用O(1)。
- 空间复杂度O(1):使用常数复杂度的额外空间。