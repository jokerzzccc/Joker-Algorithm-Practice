# 目录

[toc]

# leetcode-454-四数相加 II

- 时间：2023-07-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/4sum-ii/
- 难度：中等

给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

- `0 <= i, j, k, l < n`
- `nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0`



示例 1：

```sh
输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：

1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
```



示例 2：

```sh
输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
输出：1
```




  提示：

- n == nums1.length
- n == nums2.length
- n == nums3.length
- n == nums4.length
- 1 <= n <= 200
- -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28





# 2、题解

## 题目分析



## 解法一：分组 *+* 哈希表

### 算法分析

我们可以将四个数组分成两部分，A和B为一组，C和D为另外一组。

- 对于A和B,我们使用二重循环对它们进行遍历，得到所有`A[i]+B[j]`的值并存入哈希映射中。对于哈希映射中的每个键值对，每个键表示一种`A[i]+B[j]`,对应的值为`A[i]+B[j]`出现的次数。
- 对于C和D,我们同样使用二重循环对它们进行遍历。当遍历到`C[k] + D[l]`时，如果`-(C[k] + D[l])`出现在哈希映射中，那么将`-(C[k] + D[l])`对应的值累加进答案中。

最终即可得到满足 `A[i]+B[j]+C[k] + D[l]` 的四元组数目。



### 代码

```java


/**
 * <p>
 * 四数相加 II
 * </p>
 *
 * @author admin
 * @date 2023/7/13
 */
public class leetcode454 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2}, nums2 = {-2, -1}, nums3 = {-1, 2}, nums4 = {0, 2};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.fourSumCount(nums1, nums2, nums3, nums4));

    }

    /**
     * 解法一：分组 + 哈希表
     */
    private static class Solution01 {

        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> countAB = new HashMap<>();
            for (int a : nums1) {
                for (int b : nums2) {
                    int cur = a + b;
                    countAB.put(cur, countAB.getOrDefault(cur, 0) + 1);
                }
            }

            int target = 0;
            int ret = 0;
            for (int c : nums3) {
                for (int d : nums4) {
                    if (countAB.containsKey(target - (c + d))) {
                        ret += countAB.get(target - (c + d));
                    }
                }
            }

            return ret;
        }

    }

}

```

输出：

```sh
2
```





### 复杂度分析

- 时间复杂度：O(n^2)。我们使用了两次二重循环，时间复杂度均为O(n^2)。在循环中对哈希映射进行的
  修改以及查询操作的期望时间复杂度均为O(1),因此总时间复杂度为O(n^2)。
- 空间复杂度：O(n^2),即为哈希映射需要使用的空间。在最坏的情况下，`A[i]+B[j]`的值均不相同，因
  此值的个数为n^2,也就需要O(n^2)的空间。









# THE END