# 目录

[toc]

# leetcode-128-最长连续序列

- 时间：2023-07-09
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-consecutive-sequence/
- 难度：中等

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。



**示例 1：**

```
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
```



**提示：**

+ `0 <= nums.length <= 10^5`
+ `-10^9 <= nums[i] <= 10^9`



# 2、题解

## 题目分析



## *解法一：哈希表* (HashSet)

### 算法分析

![image-20230709161302887](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230709161302887.png)

![image-20230709161338951](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230709161338951.png)

### 代码

```java

/**
 * <p>
 * 最长连续序列
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode128 {

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestConsecutive(nums));

    }

    /**
     * 解法一：哈希表 (HashSet)
     */
    private static class Solution01 {

        public int longestConsecutive(int[] nums) {
            // 存储每个端点值，并去重，两个相等的，表示不连续
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            HashMap<Integer, Integer> pointToLongest = new HashMap<>();

            // 最长连续子序列的长度
            int longest = 0;
            for (Integer num : set) {
                // 因为，如果 num 存在前驱 num - 1, 则肯定会在 num - 1 开始计算，而不会在 num 计算，
                // 所以可以直接跳过
                if (!set.contains(num - 1)) {
                    int curNum = num;
                    int curLongest = 1;

                    while (set.contains(curNum + 1)) {
                        curNum += 1;
                        curLongest += 1;
                    }

                    longest = Math.max(longest, curLongest);
                }
            }

            return longest;
        }

    }



}

```





### 复杂度分析

- 时间复杂度：O(n),其中 n 为数组的长度。具体分析已在上面正文中给出。
- 空间复杂度：O(n)。哈希表存储数组中所有的数需要O(n)的空间。



## 解法二：哈希表（**HashMap ,**没有自动排序）

### 算法分析





### 代码

```java


/**
 * <p>
 * 最长连续序列
 * </p>
 *
 * @author admin
 * @date 2023/7/9
 */
public class leetcode128 {

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        Solution02 solution02 = new Solution02();
        System.out.println(solution02.longestConsecutive(nums));

    }


    /**
     * 解法二：哈希表（HashMap ,没有自动排序）
     */
    private static class Solution02 {

        public int longestConsecutive(int[] nums) {
            // 端点值（nums[i]） -> 对应的连续区间的长度
            HashMap<Integer, Integer> pointToLongest = new HashMap<>();

            // 最长连续子序列的长度
            int longest = 0;

            for (Integer num : nums) {
                if (!pointToLongest.containsKey(num)) {
                    int left = pointToLongest.getOrDefault(num - 1, 0);
                    int right = pointToLongest.getOrDefault(num + 1, 0);
                    int curLen = 1 + left + right;

                    // 更新答案
                    longest = Math.max(longest, curLen);

                    // 更新当前以及区间端点值
                    pointToLongest.put(num, curLen);
                    pointToLongest.put(num - left, curLen);
                    pointToLongest.put(num + right, curLen);
                }
            }

            return longest;
        }

    }

}

```





### 复杂度分析







# THE END