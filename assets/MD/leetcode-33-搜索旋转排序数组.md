# 目录

[toc]

# leetcode-33-搜索旋转排序数组

- 时间：2023-07-11
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/search-in-rotated-sorted-array/
- 难度：中等

整数数组 nums 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 **旋转**，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 **从 0 开始** 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 **旋转后** 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。



![image-20230711223851354](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230711223851354.png)







# 2、题解

## 题目分析

核心想法：

```yaml
将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环. 
```





## 解法一：二分查找

### 算法分析

![image-20230711234153968](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230711234153968.png)

![image-20230711234205662](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230711234205662.png)

### 代码

```java


/**
 * <p>
 * 搜索旋转排序数组
 * </p>
 *
 * @author admin
 * @date 2023/7/11
 */
public class leetcode33 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.search(nums, target));

    }

    /**
     * 解法一：二分查找
     * 这道题和平常二分法查找的不同就在于,把一个有序递增的数组分成了,两个递增的数组,我们需要做的就是判断这个数在哪一个递增的数组中,然后再去用常规的二分法去解决
     */
    private static class Solution01 {

        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            if (n == 1) {
                return nums[0] == target ? 0 : -1;
            }

            int left = 0;
            int right = n - 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                // 如果[mid，right] 是有序数组，且 target 的大小满足 (nums[mid + 1], mums[right]] ,
                // 否则我们应该将搜索范围缩小至[mid + 1，right], 否则在 [left, mid - 1] 中寻找。
                if (nums[mid] < nums[right]) { // 右边 [mid + 1, right]是递增数组，且 target 在右边
                    // 目标值在右边
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    }
                    // 目标值在左边
                    else {
                        right = mid - 1;
                    }
                }
                // 如果[left，mid-1] 是有序数组，且 target 的大小满足[nums[left], mums[mid]),
                // 则我们应该将搜索范围缩小至[left，mid-1], 否则在 [mid + 1, right] 中寻找。
                else { // 左边 [left, mid - 1] 是递增数组，且 target 在左边
                    // 目标值在左边
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    }
                    // 目标值在右边
                    else {
                        left = mid + 1;
                    }
                }
            }

            return -1;
        }

    }

}

```

输出：

```sh
4
```





### 复杂度分析

- 时间复杂度：O(logn).
- 空间复杂度：O(1s)









# THE END