# 目录

[toc]

# leetcode-560-和为 K 的子数组

- 时间：2023-07-22
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/subarray-sum-equals-k/description/
- 难度：中等

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的连续子数组的个数* 。

 

**示例 1：**

```
输入：nums = [1,1,1], k = 2
输出：2
```

**示例 2：**

```
输入：nums = [1,2,3], k = 3
输出：2
```

 

**提示：**

+ `1 <= nums.length <= 2 * 10^4`
+ `-1000 <= nums[i] <= 1000`
+ `-10^7 <= k <= 10^7`



# 2、题解

## 题目分析



## 解法一：暴力枚举

### 算法分析

![image-20230722163441797](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722163441797.png)



### 代码

```java


/**
 * <p>
 * 和为 K 的子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode560 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.subarraySum(nums, k));

    }

    /**
     * 解法一：暴力枚举
     */
    private static class Solution01 {

        public int subarraySum(int[] nums, int k) {
            int n = nums.length;
            int resCount = 0;

            for (int i = 0; i < n; i++) {
                int tmpSum = 0;
                // 统计 [0,i] 里有多少个符合答案
                // 因为 nums[i] 可能为负数，所以，不能用双指针
                for (int j = i; j >= 0; j--) {
                    tmpSum += nums[j];
                    if (tmpSum == k) {
                        resCount++;
                    }
                }
            }

            return resCount;
        }

    }

}

```





### 复杂度分析

![image-20230722163515713](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722163515713.png)



## 解法二：前缀和 + 哈希表优化

### 算法分析

![image-20230722163803797](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722163803797.png)

![image-20230722163812720](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722163812720.png)



### 代码

```java


/**
 * <p>
 * 和为 K 的子数组
 * </p>
 *
 * @author admin
 * @date 2023/7/22
 */
public class leetcode560 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.subarraySum(nums, k));

    }

    /**
     * 解法二：前缀和 + 哈希表优化
     */
    private static class Solution02 {

        public int subarraySum(int[] nums, int k) {
            int n = nums.length;

            int resCount = 0;
            // pre 变量用来记录当前数以前的所有数的和
            // 因为 pre[i] 只与 pre[i - 1] 有关，所以用变量代替数组
            int pre = 0;
            // K-以下标 i 结尾的连续子数组的和；V-出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            // base case, 第一个数的前缀和，
            map.put(0, 1);

            for (int i = 0; i < n; i++) {
                // 前缀和
                pre += nums[i];
                // pre[j - 1] = pre[i] - k;
                // 统计有多少个j 使 [j, i] 的和为k 的问题转换为 统计有多少个前缀和为 pre[i] - k
                if (map.containsKey(pre - k)) {
                    resCount += map.get(pre - k);
                }
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }

            return resCount;
        }

    }

}

```





### 复杂度分析

![image-20230722165006437](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230722165006437.png)







# THE END