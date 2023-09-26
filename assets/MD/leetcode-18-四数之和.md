# 目录

[toc]

# leetcode-18-四数之和

- 时间：2023-06-25
- 参考链接：
  - [一个方法团灭 nSum 问题](https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/yi-ge-fang-894da/)
  - [一个函数秒杀 2Sum 3Sum 4Sum 问题](https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/4sum/
- 难度：中等



给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且**不重复**的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

- 0 <= a, b, c, d < n
- a、b、c 和 d **互不相同**
- nums[a] + nums[b] + nums[c] + nums[d] == target



你可以按 **任意顺序** 返回答案 。

示例 1：

```sh
输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
```



**提示：**

+ `1 <= nums.length <= 200`
+ `-10^9 <= nums[i] <= 10^9`
+ `-10^9 <= target <= 10^9`



# 2、题解

## 题目分析



## 解法一: 排序 + 双指针

### 算法分析

最朴素的方法是使用四重循环枚举所有的四元组，然后使用哈希表进行去重操作，得到不包含重复四元组的最终答案。假设数组的长度是 *n*，则该方法中，枚举的时间复杂度为 *O*(n^4)，去重操作的时间复杂度和空间复杂度也很高，因此需要换一种思路。

**为了避免枚举到重复**四元组，则需要保证每一重循环枚举到的元素不小于其上一重循环枚举到的元素，且在同一重循环中不能多次枚举到相同的元素。

为了实现上述要求，可以对**数组进行排序**，并且在循环过程中遵循以下两点：

+ **每一种循环枚举到的下标必须大于上一重循环枚举到的下标**；
+ **同一重循环中，如果当前元素与上一个元素相同，则跳过当前元素**。

使用上述方法，可以避免枚举到重复四元组，但是由于仍使用四重循环，时间复杂度仍是 *O*(n^4)。注意到数组已经被排序，因此可以使用双指针的方法去掉一重循环。

使用两重循环分别枚举前两个数，然后在两重循环枚举到的数之后使用双指针枚举剩下的两个数。假设两重循环枚举到的前两个数分别位于下标 *i* 和 *j*，其中 *i*<*j*。初始时，左右指针分别指向下标 *j*+1 和下标 *n*−1。每次计算四个数的和，并进行如下操作：

+ 如果和等于 *target*，则将枚举到的四个数加到答案中，然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数；
+ 如果和小于 *target*，则将左指针右移一位；
+ 如果和大于 *target*，则将右指针左移一位。

使用双指针枚举剩下的两个数的时间复杂度是 *O*(*n*)，因此总时间复杂度是 *O*(n^3)，低于 *O*(n^4)。

具体实现时，还可以进行一些剪枝操作：

+ 在确定第一个数之后，如果 *nums*[*i*]+*nums*[*i*+1]+*nums*[*i*+2]+*nums*[*i*+3]>*target*，说明此时剩下的三个数无论取什么值，四数之和一定大于 *target*，因此退出第一重循环；
+ 在确定第一个数之后，如果 *nums*[*i*]+*nums*[*n*−3]+*nums*[*n*−2]+*nums*[*n*−1]<*target*，说明此时剩下的三个数无论取什么值，四数之和一定小于 *target*，因此第一重循环直接进入下一轮，枚举 *nums*[*i*+1]；
+ 在确定前两个数之后，如果 *nums*[*i*]+*nums*[*j*]+*nums*[*j*+1]+*nums*[*j*+2]>*target*，说明此时剩下的两个数无论取什么值，四数之和一定大于 *target*，因此退出第二重循环；
+ 在确定前两个数之后，如果 *nums*[*i*]+*nums*[*j*]+*nums*[*n*−2]+*nums*[*n*−1]<*target*，说明此时剩下的两个数无论取什么值，四数之和一定小于 *target*，因此第二重循环直接进入下一轮，枚举 *nums*[*j*+1]。





### 代码

```java

/**
 * <p>
 * 四数之和
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode18 {

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;

        Solution01 solution01 = new Solution01();
        List<List<Integer>> fourSum01 = solution01.fourSum(nums, target);
        for (List<Integer> list : fourSum01) {
            System.out.println(list);
        }

    }

    /**
     * 解法一：排序 + 双指针
     */
    private static class Solution01 {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) {
                return quadruplets;
            }
            Arrays.sort(nums);
            int n = nums.length;
            for (int first = 0; first < n - 3; first++) {
                // 避免重复
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // 一些剪枝条件，最小的都比 target 大
                if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                    break;
                }
                // 最大的都比 target 小
                if ((long) nums[first] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }

                for (int second = first + 1; second < n - 2; second++) {
                    // 避免重复
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 一些剪枝条件，最小的都比 target 大
                    if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                        break;
                    }
                    // 最大的都比 target 小
                    if ((long) nums[first] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                        continue;
                    }

                    // 双指针法
                    int third = second + 1, fourth = n - 1;
                    while (third < fourth) {
                        int curSum = nums[first] + nums[second] + nums[third] + nums[fourth];
                        if (curSum < target) {
                            third++;
                        } else if (curSum > target) {
                            fourth--;
                        } else if (curSum == target) {
                            // 找到一个答案
                            // 如果和等于 target, 则将枚举到的四个数加到答案中，
                            // 然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数；
                            quadruplets.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                            while (third < fourth && nums[third] == nums[third + 1]) {
                                third++;
                            }
                            third++;
                            while (third < fourth && nums[fourth] == nums[fourth - 1]) {
                                fourth--;
                            }
                            fourth--;
                        }
                    }
                }
            }

            return quadruplets;
        }

    }

}

```

输出如下：

```sh
[-2, -1, 1, 2]
[-2, 0, 0, 2]
[-1, 0, 0, 1]

```



### 复杂度分析

- 时间复杂度：O(n^3),其中n是数组的长度。排序的时间复杂度是O(n log n),枚举四元组的时间复杂
  度是O(n^3),因此总时间复杂度为O(n^3+n log n)=O(n^3)。
- 空间复杂度：O(Iogn),其中n是数组的长度。空间复杂度主要取决于排序额外使用的空间。此外排序
  修改了输入数组ms,实际情况中不一定允许，因此也可以看成使用了一个额外的数组存储了数组
  nums的副本并排序，空间复杂度为O(n)。









# THE END