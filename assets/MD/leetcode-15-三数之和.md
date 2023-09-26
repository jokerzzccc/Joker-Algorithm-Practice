# 目录

[toc]

# leetcode-15-三数之和

- 时间：2023-06-25
- 参考链接：
  - [一个方法团灭 nSum 问题](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/yi-ge-fang-894da/)
  - [一个函数秒杀 2Sum 3Sum 4Sum 问题](https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/3sum/
- 难度：中等

给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请

你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。



**提示：**

+ `3 <= nums.length <= 3000`
+ `-10^5 <= nums[i] <= 10^5`





# 2、题解

## 题目分析



## 解法一: 排序 + 双指针

### 算法分析

这个方法就是我们常说的「**双指针**」，当我们需要枚举数组中的两个元素时，如果我们发现**随着第一个元素**
**的递增，第二个元素是递减的，那么就可以使用双指针的方法**，将枚举的时间复杂度从O(N^2)减少至O(N)
。为什么是O(N)呢？这是因为在枚举的过程每一步中，「左指针」会向右移动一个位置（也就是题目中的
b),而「右指针」会向左移动若干个位置，这个与数组的元素有关，但我们知道它一共会移动的位置数为
O(N),均摊下来，每次也向左移动一个位置，因此时间复杂度为O(N)。



### 代码

```java


/**
 * <p>
 * 三数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode35 {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};

        Solution01 solution01 = new Solution01();
        List<List<Integer>> threeSum01 = solution01.threeSum(nums);
        threeSum01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：排序 + 双指针
     */
    private static class Solution01 {

        public List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            // 枚举 a, 对应指针 first
            for (int first = 0; first < n; first++) {
                // 需要和上一次枚举的数不相同，避免重复结果
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // c 对应的指针 third,初始指向数组的最右端
                int third = n - 1;
                int target = -nums[first];

                // 枚举 b, 对应指针 second
                for (int second = first + 1; second < n; second++) {
                    // 需要和上一次枚举的数不相同
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 需要保证 b 的指针在 c 的指针的左侧
                    // 向左移动指针 c，直到 a+b+c 不大于 0
                    while (second < third && nums[second] + nums[third] > target) {
                        --third;
                    }
                    // 如果指针重合，随着 b 后续的增加
                    // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                    if (second == third) {
                        break;
                    }

                    // 找到一个结果,即 a+b+c==0
                    if (nums[second] + nums[third] == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        res.add(list);
                    }
                }
            }

            return res;
        }

    }

}

```

输出如下：

```sh
[-1, -1, 2]
[-1, 0, 1]
```



### 复杂度分析

- 时间复杂度：O(N^2),其中 N 是数组 nums 的长度。
- 空间复杂度：O(IogN)。我们忽略存储答案的空间，额外的排序的空间复杂度为O(IogN)。然而我们
  修改了输入的数组 nums,在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了
  nums的副本并进行排序，空间复杂度为O(N)。









# THE END