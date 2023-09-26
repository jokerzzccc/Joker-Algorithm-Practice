# 目录

[toc]

# leetcode-4-寻找两个正序数组的中位数

- 时间：2023-06-30
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/median-of-two-sorted-arrays/
- 难度：困难



给定两个大小分别为 m 和 n 的**正序（从小到大）**数组 nums1 和 nums2。请你找出并返回这两个正序数组的 **中位数** 。

算法的时间复杂度应该为 O(log (m+n)) 。

**示例 1：**

```
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
```



提示：

- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -10^6 <= nums1[i], nums2[i] <= 10^6







# 2、题解

## 题目分析

注意题中的正序 + 时间要求O(log (m+n)) ：

- 肯定要利用有序，然后二分查找。
- 

给定两个有序数组，要求找到两个有序数组的中位数，最直观的思路有以下两种：

+ 使用归并的方式，合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数。
+ 不需要合并两个有序数组，只要找到中位数的位置即可。由于两个数组的长度已知，因此中位数对应的两个数组的下标之和也是已知的。维护两个指针，初始时分别指向两个数组的下标 0 的位置，每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置。

假设两个有序数组的长度分别为 *m* 和 *n*，上述两种思路的复杂度如何？

第一种思路的时间复杂度是 *O*(*m*+*n*)，空间复杂度是 *O*(*m*+*n*)。第二种思路虽然可以将空间复杂度降到 *O*(1)，但是时间复杂度仍是 *O*(*m*+*n*)。

如何把时间复杂度降低到 *O*(log(*m*+*n*)) 呢？如果对时间复杂度的要求有 log，通常都需要用到二分查找，这道题也可以通过**二分查找**实现。



根据中位数的定义，

- 当 *m*+*n* 是奇数时，中位数是两个有序数组中的第 (*m*+*n*)/2 个元素，
- 当 *m*+*n* 是偶数时，中位数是两个有序数组中的第 (*m*+*n*)/2 个元素和第 (*m*+*n*)/2+1 个元素的平均值。

因此，这道题可以转化成**寻找两个有序数组中的第 *k* 小的数，其中 *k* 为 (*m*+*n*)/2 或 (*m*+*n*)/2+1**。



假设两个有序数组分别是 A 和 B。要找到第 *k* 个元素，我们可以比较 A[*k*/2−1] 和 B[*k*/2−1]，其中 / 表示整数除法。由于 A[*k*/2−1] 和 B[*k*/2−1] 的前面分别有 A[0..*k*/2−2] 和 B[0..*k*/2−2]，即 *k*/2−1 个元素，**对于 A[*k*/2−1] 和 B[*k*/2−1] 中的较小值，最多只会有 (*k*/2−1)+(*k*/2−1)≤*k*−2 个元素比它小，那么它就不能是第 *k* 小的数了**。

因此我们可以归纳出**三种情况**：

+ 如果 A[*k*/2−1]<B[*k*/2−1]，则比 A[*k*/2−1] 小的数最多只有 A 的前 *k*/2−1 个数和 B 的前 *k*/2−1 个数，即比 A[*k*/2−1] 小的数最多只有 *k*−2 个，**因此 A[*k*/2−1] 不可能是第 *k* 个数，A[0] 到 A[*k*/2−1] 也都不可能是第 *k* 个数，可以全部排除**。
+ 如果 A[*k*/2−1]>B[*k*/2−1]，则可以**排除 B[0] 到 B[*k*/2−1]**。
+ 如果 A[*k*/2−1]=B[*k*/2−1]，则可以归入第一种情况处理。

![image-20230701191518435](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230701191518435.png)

可以看到，比较 A[*k*/2−1] 和 B[*k*/2−1] 之后，**可以排除 *k*/2 个不可能是第 *k* 小的数，查找范围缩小了一半**。同时，我们将在排除后的新数组上继续进行二分查找，并且根据我们排除数的个数，减少 *k* 的值，这是因为我们排除的数都不大于第 *k* 小的数。

- 如果 A[*k*/2−1] 或者 B[*k*/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们**必须根据排除数的个数减少 \*k\* 的值**，而不能直接将 *k* 减去 *k*/2。

- 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 *k* 小的元素。

- 如果 *k*=1，我们只要返回两个数组首元素的最小值即可。

## 解法一： 双指针+二分查找

### 算法分析





### 代码

```java

/**
 * <p>
 * 寻找两个正序数组的中位数
 * </p>
 *
 * @author admin
 * @date 2023/6/29
 */
public class leetcode4 {

    public static void main(String[] args) {
        int[] nums1 = {1, 3}, nums2 = {2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.findMedianSortedArrays(nums1, nums2));

    }

    /**
     * 解法一：双指针+二分查找
     */
    private static class Solution01 {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;

            int totalLen = len1 + len2;
            if (totalLen % 2 == 1) {
                // 奇数情况下，
                int midIndex = totalLen / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;

            } else {
                // 偶数情况下
                int midIndex1 = totalLen / 2 - 1;
                int midIndex2 = totalLen / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1)
                        + getKthElement(nums1, nums2, midIndex2 + 1)
                ) / 2.0;
                return median;
            }

        }

        /**
         * 寻找两个数组中第 k 小的数:
         * 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        private double getKthElement(int[] nums1, int[] nums2, int k) {
            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1];
                int pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }

        }

    }

}

```





### 复杂度分析



- 时间复杂度：*O*(log(*m*+*n*))，其中 *m* 和 *n* 分别是数组 *nums*1 和 *nums*2 的长度。初始时有 *k*=(*m*+*n*)/2 或 *k*=(*m*+*n*)/2+1，每一轮循环可以将查找范围减少一半，因此时间复杂度是 *O*(log(*m*+*n*))。

- 空间复杂度：*O*(1)。







# THE END