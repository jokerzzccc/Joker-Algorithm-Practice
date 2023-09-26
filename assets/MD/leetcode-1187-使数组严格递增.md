# 目录

[toc]

# leetcode-1187-使数组严格递增

- 时间：2023-08-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/make-array-strictly-increasing/
- 难度：困难

给你两个整数数组 `arr1` 和 `arr2`，返回使 `arr1` 严格递增所需要的最小「操作」数（可能为 0）。

每一步「操作」中，你可以分别从 `arr1` 和 `arr2` 中各选出一个索引，分别为 `i` 和 `j`，`0 <= i < arr1.length` 和 `0 <= j < arr2.length`，然后进行赋值运算 `arr1[i] = arr2[j]`。

如果无法让 `arr1` 严格递增，请返回 `-1`。

 

**示例 1：**

```
输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
输出：1
解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。
```

**示例 2：**

```
输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1]
输出：2
解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。
```

**示例 3：**

```
输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
输出：-1
解释：无法使 arr1 严格递增。
```

 

**提示：**

+ `1 <= arr1.length, arr2.length <= 2000`
+ `0 <= arr1[i], arr2[i] <= 10^9`

 



# 2、题解

## 题目分析



## 解法一：*动态规划* *+ DFS +* *贪心* *+* *二分查找*

### 算法分析

![image-20230818215047600](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230818215047600.png)

![image-20230818215103907](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230818215103907.png)



### 代码

```java

/**
 * <p>
 * 使数组严格递增
 * </p>
 *
 * @author admin
 * @date 2023/8/18
 */
public class leetcode1187 {

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {4, 3, 1};

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.makeArrayIncreasing(arr1, arr2));

    }

    /**
     * 解法一：动态规划 + DFS + 贪心 + 二分查找
     * 思路：选与不选
     * 可以使用 leetcode300 最长递增子序列的思路
     */
    private static class Solution01 {

        /**
         * 入参数组
         */
        private int[] nums1, nums2;

        /**
         * 备忘录优化:
         * map 数组下标为 nums1 下标（0...n-1）
         * key：pre, value: 操作数
         */
        private Map<Integer, Integer>[] memo;

        public int makeArrayIncreasing(int[] arr1, int[] arr2) {
            this.nums1 = arr1;
            this.nums2 = arr2;
            Arrays.sort(nums2);
            int n = nums1.length;
            memo = new HashMap[n];
            Arrays.setAll(memo, HashMap::new);

            // 假设 nums1[n-1] 右侧有个无穷大的数
            int ans = dfs(n - 1, Integer.MAX_VALUE);

            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }

        /**
         * 把从 nums1[0] 到 nums1[i] 的这段前缀替换成严格递增数组，且数组的最后一个数小于 pre，
         * 所需要的最小操作数。记为 dfs(i,pre)
         *
         * @param index nums1 下标
         * @param pre 从后向前算的
         */
        private int dfs(int index, int pre) {
            // base case: i<0 数组没有元素了，操作数自然就是0
            if (index < 0) {
                return 0;
            }
            // 备忘录优化
            if (memo[index].containsKey(pre)) {
                return memo[index].get(pre);
            }

            // 不替换 nums1[i]
            int res = nums1[index] < pre ? dfs(index - 1, nums1[index]) : Integer.MAX_VALUE / 2;

            // 二分查找 nums2 中小于 pre 的最大数的下标
            int k = lowerBound(nums2, pre) - 1;
            // 状态转移
            // nums1[i] 替换成小于 pre 的最大数
            if (k >= 0) {
                res = Math.min(res, dfs(index - 1, nums2[k]) + 1);
            }

            // 存入备忘录
            memo[index].put(pre, res);

            return res;
        }

        /**
         * 二分查找 nums
         */
        private int lowerBound(int[] nums, int target) {
            // 区间 (left,right)
            int left = -1, right = nums.length;
            while (left + 1 < right) {
                int mid = (right + left) >>> 1;
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            return right;
        }

    }

    /**
     * 解法二：动态规划 + DFS + 贪心 + 二分查找
     * 思路：枚举选哪个
     */
    private static class Solution02 {

        /**
         * 入参数组
         */
        private int[] nums1, nums2;

        /**
         * 备忘录优化:
         * map 数组下标为 nums1 下标（0...n-1）
         * key：pre, value: 操作数
         */
        private Map<Integer, Integer>[] memo;

        public int makeArrayIncreasing(int[] arr1, int[] arr2) {
            this.nums1 = arr1;
            this.nums2 = arr2;
            Arrays.sort(nums2);
            int n = nums1.length;
            memo = new HashMap[n];
            Arrays.setAll(memo, HashMap::new);

            // 假设 nums1[n-1] 右侧有个无穷大的数
            int ans = dfs(n - 1, Integer.MAX_VALUE);

            return ans < Integer.MAX_VALUE / 2 ? ans : -1;
        }

        /**
         * 把从 nums1[0] 到 nums1[i] 的这段前缀替换成严格递增数组，且数组的最后一个数小于 pre，
         * 所需要的最小操作数。记为 dfs(i,pre)
         *
         * @param index nums1 下标
         * @param pre 从后向前算的
         */
        private int dfs(int index, int pre) {
            // base case: i<0 数组没有元素了，操作数自然就是0
            if (index < 0) {
                return 0;
            }
            // 备忘录优化
            if (memo[index].containsKey(pre)) {
                return memo[index].get(pre);
            }

            // 不替换 nums1[i]
            int res = nums1[index] < pre ? dfs(index - 1, nums1[index]) : Integer.MAX_VALUE / 2;

            // 二分查找 nums2 中小于 pre 的最大数的下标
            int k = lowerBound(nums2, pre) - 1;
            // 状态转移
            // nums1[i] 替换成小于 pre 的最大数
            if (k >= 0) {
                res = Math.min(res, dfs(index - 1, nums2[k]) + 1);
            }

            // 存入备忘录
            memo[index].put(pre, res);

            return res;
        }

        /**
         * 二分查找 nums
         */
        private int lowerBound(int[] nums, int target) {
            // 区间 (left,right)
            int left = -1, right = nums.length;
            while (left + 1 < right) {
                int mid = (right + left) >>> 1;
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            return right;
        }

    }

}

```





### 复杂度分析

![image-20230818215135448](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230818215135448.png)









# THE END