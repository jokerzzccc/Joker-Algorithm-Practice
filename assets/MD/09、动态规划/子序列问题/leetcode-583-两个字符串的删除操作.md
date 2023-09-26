

# 目录

[toc]

# leetcode-583-两个字符串的删除操作

- 时间：2023-05-26
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/delete-operation-for-two-strings/
- 难度：中等



给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。

每步 可以删除任意一个字符串中的一个字符。

 

示例 1：

```sh
输入: word1 = "sea", word2 = "eat"
输出: 2
解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
```


示例  2:

```sh
输入：word1 = "leetcode", word2 = "etco"
输出：4
```



提示：

1 <= word1.length, word2.length <= 500
word1 和 word2 只包含小写英文字母



# 2、题解

## 题目分析

- 基本等价于求 【最长公共子序列】

## 解法一

### 算法分析

给定两个字符串 word1 和 word2，分别删除若干字符之后使得两个字符串相同，则剩下的字符为两个字符串的公共子序列。为了使删除操作的次数最少，剩下的字符应尽可能多。当剩下的字符为两个字符串的**最长公共子序列**时，删除操作的次数最少。因此，可以计算两个字符串的最长公共子序列的长度，然后分别计算两个字符串的长度和最长公共子序列的长度之差，即为两个字符串分别需要删除的字符数，两个字符串各自需要删除的字符数之和即为最少的删除操作的总次数。

假设字符串 word1 和 word2 边的长度分别为 m 和 n ,计算字符串 word1 和 word2 的最长公共子序列的长度，
记为 lcs,则**最少删除操作次数为 m-lcs+n-lcs**。



### 代码

```java
/**
 * <p>
 * 两个字符串的删除操作
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode583 {

    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";

        Solution01 solution01 = new Solution01();
        int minDistance = solution01.minDistance(word1, word2);
        System.out.println(minDistance);

    }

    private static class Solution01 {

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            int lcs = longestCommonSubsequence(word1, word2);

            return m - lcs + n - lcs;
        }

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();

            // 其中 dp[i][j] 表示 text1[0:i]和text[0:j] 的最长公共子序列的长度。
            // test[0:i] 表示 text1 长度为 i 的前缀。
            int[][] dp = new int[m + 1][n + 1];

            // 从 1 开始，是因为，text[0:0] 表示空串，空串和任何字符串的最长公共子序列都为空。
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            return dp[m][n];
        }

    }

}
```



### 复杂度分析

- 时间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *text*1 和 *text*2 的长度。二维数组 *dp* 有 *m*+1 行和 *n*+1 列，需要对 *dp* 中的每个元素进行计算。

- 空间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *text*1 和 *text*2 的长度。创建了 *m*+1 行 *n*+1 列的二维数组 *dp*。











# THE END