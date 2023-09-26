# 目录

[toc]

# leetcode-516-最长回文子序列

- 时间：2023-08-28
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-palindromic-subsequence/description/
- 难度：中等

给你一个字符串 `s` ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。

 

**示例 1：**

```
输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
```

**示例 2：**

```
输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。
```

 

**提示：**

+ `1 <= s.length <= 1000`
+ `s` 仅由小写英文字母组成



# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

解题思路：
状态
`f[i][j]` 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少。

转移方程
如果 s 的第 i 个字符和第 j 个字符相同的话

`f[i][j] = f[i + 1][j - 1] + 2`

如果 s 的第 i 个字符和第 j 个字符不同的话

`f[i][j] = max(f[i + 1][j], f[i][j - 1])`

然后注意遍历顺序，i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了。

初始化
`f[i][i] = 1` 单个字符的最长回文序列是 1

结果
`f[0][n - 1]`





### 代码

```java

/**
 * <p>
 * 最长回文子序列
 * </p>
 *
 * @author admin
 * @date 2023/8/28
 */
public class leetcode516 {

    public static void main(String[] args) {
        String s = "bbbab";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestPalindromeSubseq(s));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            // dp[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少
            int[][] dp = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                // 初始化，单个字符的回文长度 1
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    // 状态转移
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[0][n - 1];
        }

    }

}

```





### 复杂度分析











# THE END