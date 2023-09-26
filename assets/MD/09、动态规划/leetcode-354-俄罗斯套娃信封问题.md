# 目录

[toc]

# leetcode-354-俄罗斯套娃信封问题

- 时间：2023-05-22
- 参考链接：
  - [动态规划设计：最长递增子序列](https://labuladong.gitee.io/algo/di-er-zhan-a01c6/dong-tai-g-a223e/dong-tai-g-6ea57/)
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/russian-doll-envelopes/
- 难度：困难



给你一个二维整数数组 `envelopes` ，其中 `envelopes[i] = [wi, hi]` ，表示第 `i` 个信封的宽度和高度。

当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算 **最多能有多少个** 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

**注意**：不允许旋转信封。

**示例 1：**

```
输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出：3
解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
```

**示例 2：**

```
输入：envelopes = [[1,1],[1,1],[1,1]]
输出：1
```



**提示：**

+ `1 <= envelopes.length <= 105`
+ `envelopes[i].length == 2`
+ `1 <= wi, hi <= 105`

Related Topics

数组

二分查找

动态规划

排序

# 2、题解

## 题目分析

- 类比 leetcode300-最长递增子序列
- 假设 信封是由 `(w, h)` 这样的二维数对形式表示的
- 解法：**先对宽度 `w` 进行升序排序，如果遇到 `w` 相同的情况，则按照高度 `h` 降序排序；之后把所有的 `h` 作为一个数组，在这个数组上计算 LIS 的长度就是答案**。





## 解法一：动态规划





### 代码

```java
// 动态规划版(会超出时间限制)
class Solution {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}
```





## 解法二：动态规划 + 	二分查找





### 代码

```java
public class leetcode354 {

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Solution02 solution02 = new Solution02();
        int max = solution02.maxEnvelopes(envelopes);
        System.out.println(max);
    }

    // 动态规划版 + 二分查找
    private static class Solution02 {

        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes.length == 0) {
                return 0;
            }

            int n = envelopes.length;
            Arrays.sort(envelopes, (e1, e2) -> {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            });

            // 对高度数组寻找 LIS（最长递增子序列）
            int[] heights = new int[n];
            for (int i = 0; i < n; i++) {
                heights[i] = envelopes[i][1];
            }

            return lengthOfLIS(heights);
        }

        private int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] tails = new int[nums.length];
            int maxRes = 0;
            for (int num : nums) {
                // 二分查找
                // 每个元素开始遍历 看能否插入到之前的 tails数组的位置 如果能 是插到哪里
                int left = 0, right = maxRes, pos = 0;
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (tails[middle] < num) {
                        left = middle + 1;
                        pos = left;
                    } else {
                        right = middle;
                    }
                }
                tails[pos] = num;
                // maxRes == right 说明目前tail数组的元素都比当前的num要小 因此最长子序列的长度可以增加了
                if (maxRes == right) {
                    maxRes++;
                }
            }
            return maxRes;
        }

    }
}
```





# THE END