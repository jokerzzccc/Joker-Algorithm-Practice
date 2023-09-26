# 目录

[toc]

# leetcode-78-子集

- 时间：2023-05-15
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/subsets/
- 难度：中等

给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。



**示例 1：**

```
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

**示例 2：**

```
输入：nums = [0]
输出：[[],[0]]
```



**提示：**

+ `1 <= nums.length <= 10`
+ `-10 <= nums[i] <= 10`
+ `nums` 中的所有元素 **互不相同**

Related Topics

位运算

数组

回溯



# 2、题解

## 题目分析



## 解法一：递归+回溯



### 代码-debug 版

```java
/**
 * <p>
 * 子集
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/15
 */
public class leetcode78 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = solution.subsets(nums);
        System.out.println(subsets);

    }

    static class Solution {

        private List<List<Integer>> res = new ArrayList<>();

        /**
         * 用 track 记录根节点到每个节点的路径的值
         */
        private LinkedList<Integer> track = new LinkedList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backTrack(nums, 0);
            return res;
        }

        /**
         * @param start start 参数控制树枝的生长避免产生重复的子集
         */
        void backTrack(int[] nums, int start) {
            res.add(new ArrayList<>(track));

            for (int i = start; i < nums.length; i++) {
                track.add(nums[i]);

                backTrack(nums, i + 1);

                track.removeLast();
            }
        }

    }
}
```



## 解法二：二进制迭代+子集枚举

### 算法思想

记原序列中元素的总数为 *n*。原序列中的每个数字 *$a_i$* 的状态可能有两种，即「在子集中」和「不在子集中」。我们用 1 表示「在子集中」，0 表示不在子集中，那么每一个子集可以对应一个长度为 *n* 的 0/1 序列，第 *i* 位表示 *$a_i$* 是否在子集中。例如，*n*=3 ，*a*={5,2,9} 时：

| 0/1 序列 | 子集    | 0/1 序列对应的二进制数 |
| -------- | ------- | ---------------------- |
| 000      | {}      | 0                      |
| 001      | {9}     | 1                      |
| 010      | {2}     | 2                      |
| 011      | {2,9}   | 3                      |
| 100      | {5}     | 4                      |
| 101      | {5,9}   | 5                      |
| 110      | {5,2}   | 6                      |
| 111      | {5,2,9} | 7                      |

可以发现 0/1 序列对应的二进制数正好从 0 到$2^n$ −1。我们可以枚举 *mask*∈[0,$2^n$ −1]，*mask* 的二进制表示是一个 0/1 序列，我们可以按照这个 0/1 序列在原集合当中取数。当我们枚举完所有$2^n$  个 *mask*，我们也就能构造出所有的子集。



### 代码

```java
static class Solution02 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }
            res.add(sub);
        }
        return res;
    }

}
```

- mask 就是 i



### **复杂度分析**

+ 时间复杂度：$O(n×2^n)$。一共 $2^n$ 个状态，每种状态需要 *O*(*n*) 的时间来构造子集。
+ 空间复杂度：*O*(*n*)。即构造子集使用的临时数组 *t* 的空间代价。



# THE END