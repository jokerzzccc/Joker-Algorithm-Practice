# 目录

[toc]

# leetcode-1143-最长公共子序列

- 时间：2023-05-25
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-common-subsequence/
- 难度：中等



给定两个字符串 `text1` 和 `text2`，返回这两个字符串的最长 **公共子序列** 的长度。如果不存在 **公共子序列** ，返回 `0` 。

一个字符串的 **子序列** 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

+ 例如，`"ace"` 是 `"abcde"` 的子序列，但 `"aec"` 不是 `"abcde"` 的子序列。

两个字符串的 **公共子序列** 是这两个字符串所共同拥有的子序列。



**示例 1：**

```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。
```

**示例 2：**

```
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
```

**示例 3：**

```
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。
```



**提示：**

+ `1 <= text1.length, text2.length <= 1000`
+ `text1` 和 `text2` 仅由小写英文字符组成。

Related Topics

字符串

动态规划



# 2、题解

## 题目分析



## 解法一：自顶向下+备忘录

### 算法分析

- 参考链接：https://mp.weixin.qq.com/s/ZhPEchewfc03xWv9VP3msg



正确的思路是不要考虑整个字符串，而是细化到`s1`和`s2`的每个字符。前文 子序列解题模板 中总结的一个规律：

**对于两个字符串求子序列的问题，都是用两个指针`i`和`j`分别在两个字符串上移动，大概率是动态规划思路**。



**函数状态定义**：

定义一个状态函数：

```java
// 定义：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
int dp(String s1, int i, String s2, int j)
```

这个`dp`函数的定义是：**`dp(s1, i, s2, j)`计算`s1[i..]`和`s2[j..]`的最长公共子序列长度**。

根据这个定义，那么我们**想要的答案就是`dp(s1, 0, s2, 0)`**，且 **base case** 就是`i == len(s1)`或`j == len(s2)`时，因为这时候`s1[i..]`或`s2[j..]`就相当于空串了，最长公共子序列的长度显然是 0：



存在几种情况：

- `s1[i]`和`s2[j]`，**如果`s1[i] == s2[j]`，说明这个字符一定在`lcs`中**

- **`s1[i] != s2[j]`意味着，`s1[i]`和`s2[j]`中至少有一个字符不在`lcs`中**，总共可能有三种情况，把这三种情况的答案都算出来，取其中结果最大的那个呗。

  - 情况一：S1[i] 不在 lcs 中

  - 情况二：S2[j] 不在 lcs 中

  - **情况三「`s1[i]`和`s2[j]`都不在 lcs 中」**

    - **还有一个小的优化，情况三「`s1[i]`和`s2[j]`都不在 lcs 中」其实可以直接忽略**。

    - 因为我们在求最大值嘛，情况三在计算`s1[i+1..]`和`s2[j+1..]`的`lcs`长度，这个长度肯定是小于等于情况二`s1[i..]`和`s2[j+1..]`中的`lcs`长度的，因为`s1[i+1..]`比`s1[i..]`短嘛，那从这里面算出的`lcs`当然也不可能更长嘛。

      同理，情况三的结果肯定也小于等于情况一。**说白了，情况三被情况一和情况二包含了**，所以我们可以直接忽略掉情况三



**加 memo 备忘录的原因：**

首先抽象出我们核心`dp`函数的递归框架：

```
int dp(int i, int j) {
    dp(i + 1, j + 1); // #1
    dp(i, j + 1);     // #2
    dp(i + 1, j);     // #3
}
```

- 假设我想从`dp(i, j)`转移到`dp(i+1, j+1)`，有不止一种方式，可以直接走`#1`，也可以走`#2 -> #3`，也可以走`#3 -> #2`。

- 这就是重叠子问题，如果我们不用`memo`备忘录消除子问题，那么`dp(i+1, j+1)`就会被多次计算，这是没有必要的。



### 代码

```java
/**
 * <p>
 * 最长公共子序列
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/25
 */
public class leetcode1143 {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        Solution01 solution01 = new Solution01();
        int subsequenceLen = solution01.longestCommonSubsequence(text1, text2);
        System.out.println(subsequenceLen);
    }
    /**
     * 自顶向下：从后到前 LCS
     */
    private static class Solution01 {

        // 备忘录，消除重叠子问题
        // memo[i][j]: text1[i...] 与 text2[j...] 的最长公共子序列
        // memo[0][0] 表示 text1.substring(0, text1.length()) 与 text2.substring(0, text2.length()) 的 LCS
        // i == 0 时，就表示，text1 为空串；j == 0 时，表示text2为空串
        private int[][] memo;

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();

            memo = new int[m][n];
            // 备忘录值为 -1 代表未曾计算
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            // 计算 s1[0..] 和 s2[0..] 的 lcs 长度
            int dp = dp(text1, 0, text2, 0);
            Arrays.stream(memo).forEach(ints -> System.out.println(Arrays.toString(ints)));
            return dp;
        }

        int dp(String s1, int i, String s2, int j) {
            // base case
            if (i == s1.length() || j == s2.length()) {
                return 0;
            }

            // 如果之前计算过，则直接返回备忘录中的答案
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            if (s1.charAt(i) == s2.charAt(j)) {
                // s1[i] 和 s2[j] 必然在 lcs 中，
                // 加上 s1[i+1..] 和 s2[j+1..] 中的 lcs 长度，就是答案
                memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            } else {
                // s1[i] 和 s2[j] 至少有一个不在 lcs 中
                memo[i][j] = Math.max(
                        dp(s1, i + 1, s2, j),
                        dp(s1, i, s2, j + 1)
                );
                System.out.println("memo[i-" + i + " j-" + j + "]: " + memo[i][j]);
            }

            return memo[i][j];
        }

    }
}
```

输出如下：

```sh
memo[i-4 j-2]: 1
memo[i-3 j-2]: 1
memo[i-2 j-1]: 2
memo[i-2 j-2]: 1
memo[i-1 j-2]: 1
memo[i-1 j-1]: 2
memo[i-0 j-0]: 3
[3, -1, -1]
[-1, 2, 1]
[-1, 2, 1]
[-1, -1, 1]
[-1, -1, 1]
3
```





### 复杂度分析

- 







## 解法二：自底向上-较好理解

### 算法分析

- 参考：https://leetcode.cn/problems/longest-common-subsequence/solution/zui-chang-gong-gong-zi-xu-lie-by-leetcod-y7u0/

![image-20230525235011551](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230525235011551.png)

![image-20230525235106047](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230525235106047.png)



### 代码

```java
/**
 * <p>
 * 最长公共子序列
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/25
 */
public class leetcode1143 {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        Solution02 solution02 = new Solution02();
        int subsequenceLen02 = solution02.longestCommonSubsequence(text1, text2);
        System.out.println(subsequenceLen02);

    }

    /**
     * 自底向上：从前到后 LCS
     */
    private static class Solution02 {

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
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                        System.out.println("dp[i-" + i + " j-" + j + "]: " + dp[i][j]);
                    }
                }
            }

            // debug
            System.out.println("========");
            Arrays.stream(dp).forEach(ints -> System.out.println(Arrays.toString(ints)));

            return dp[m][n];
        }

    }

}
```



输出：

```sh
dp[i-1 j-1]: 1
dp[i-1 j-2]: 1
dp[i-1 j-3]: 1
dp[i-2 j-1]: 1
dp[i-2 j-2]: 1
dp[i-2 j-3]: 1
dp[i-3 j-1]: 1
dp[i-3 j-2]: 2
dp[i-3 j-3]: 2
dp[i-4 j-1]: 1
dp[i-4 j-2]: 2
dp[i-4 j-3]: 2
dp[i-5 j-1]: 1
dp[i-5 j-2]: 2
dp[i-5 j-3]: 3
========
[0, 0, 0, 0]
[0, 1, 1, 1]
[0, 1, 1, 1]
[0, 1, 2, 2]
[0, 1, 2, 2]
[0, 1, 2, 3]
3
```



### 复杂度分析

- 时间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *text*1 和 *text*2 的长度。二维数组 *dp* 有 *m*+1 行和 *n*+1 列，需要对 *dp* 中的每个元素进行计算。

- 空间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *text*1 和 *text*2 的长度。创建了 *m*+1 行 *n*+1 列的二维数组 *dp*。







# THE END