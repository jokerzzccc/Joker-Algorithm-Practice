# 目录

[toc]

# leetcode-912-排序数组

- 时间：2023-06-21
- 参考链接：
  - [归并排序详解及应用](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/gui-bing-p-1387f/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/sort-an-array/
- 难度：中等

给你一个整数数组 `nums`，请你将该数组升序排列。



**提示：**

+ `1 <= nums.length <= 5 * 104`
+ `-5 * 104 <= nums[i] <= 5 * 104`



# 2、题解

## 题目分析





## 解法一: 归并排序

### 算法分析

- 参考链接：
  - [归并排序详解及应用](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/gui-bing-p-1387f/)
  - 



### 代码

```java

/**
 * <p>
 * 排序数组
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode912 {

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};

        Solution01 solution01 = new Solution01();
        int[] sortArray = solution01.sortArray(nums);
        Arrays.stream(sortArray).forEach(System.out::print);
        System.out.println("\n");

    }

    /**
     * 解法一：归并排序
     */
    private static class Solution01 {

        /**
         * 临时辅助数组
         */
        int[] tmp;

        public int[] sortArray(int[] nums) {
            tmp = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 定义mergeSort(nums,l,r) 函数表示对 nums 数组里[l, r]的部分进行排序，整个函数流程如下：
         */
        private void mergeSort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            int i = left, j = mid + 1;
            int cnt = 0;
            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
                    tmp[cnt++] = nums[i++];
                } else {
                    tmp[cnt++] = nums[j++];
                }
            }
            while (i <= mid) {
                tmp[cnt++] = nums[i++];
            }
            while (j <= right) {
                tmp[cnt++] = nums[j++];
            }
            for (int k = 0; k < right - left + 1; ++k) {
                nums[k + left] = tmp[k];
            }
        }

    }

}

```

输出如下：

```java
```





### 复杂度分析

- 时间复杂度：$O(nlogn)$
- 空间复杂度：$O(n)$





## 解法二: 快速排序

### 算法分析

- 参考链接：
  - https://mp.weixin.qq.com/s/8ZTMhvHJK_He48PpSt_AmQ



### 代码

```java
package com.joker.algorithm.array;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 排序数组
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode912 {

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};

        // 快速排序
        Solution02 solution02 = new Solution02();
        int[] sortArray02 = solution02.sortArray(nums);
        Arrays.stream(sortArray02).forEach(System.out::print);
        System.out.println("\n");

    }


    /**
     * 解法二：快速排序
     */
    private static class Solution02 {

        public int[] sortArray(int[] nums) {

            // 为了避免出现耗时的极端情况，先随机打乱
            shuffle(nums);

            // 排序整个数组（原地修改）
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 快排算法
         */
        private void quickSort(int[] nums, int low, int high) {
            if (low >= high) {
                return;
            }
            // 对 nums[low..high] 进行切分
            // 使得 nums[low..pivot-1] <= nums[pivot] < nums[pivot+1..high]
            // pivot 为每次确定那个元素的最终下标
            int pivot = partition(nums, low, high);

            quickSort(nums, low, pivot - 1);
            quickSort(nums, pivot + 1, high);
        }

        /**
         * 对 nums[low..high] 进行切分, 使得 nums[low..pivot-1] <= nums[pivot] < nums[pivot+1..high]
         *
         * @return pivot 每次排序确定元素位置的下标
         */
        private int partition(int[] nums, int low, int high) {
            int pivot = nums[low];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [low, i) <= pivot；(j, high] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = low + 1, j = high;
            // 当 i > j 时结束循环，以保证区间 [low, high] 都被覆盖
            while (i <= j) {
                while (i < high && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > low && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [low, i) <= pivot && (j, high] > pivot

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, low, j);
            return j;
        }

        /**
         * 洗牌算法，将输入的数组随机打乱
         * 防止极端情况（原数组基本有序，导致最后成链表装），导致，时间复杂度过高
         */
        private void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }

        /**
         * 原地交换两个元素
         */
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

}

```

输出如下：

```java
1235
```





### 复杂度分析

![image-20230622170153042](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230622170153042.png)







# THE END