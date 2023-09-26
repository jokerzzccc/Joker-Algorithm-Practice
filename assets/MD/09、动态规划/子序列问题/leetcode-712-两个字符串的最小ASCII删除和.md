# 目录

[toc]

# leetcode-712-两个字符串的最小ASCII删除和

- 时间：2023-05-26
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/
- 难度：中等



给定两个字符串`s1` 和 `s2`，返回 *使两个字符串相等所需删除字符的 **ASCII** 值的最小和* 。



**示例 1:**

```
输入: s1 = "sea", s2 = "eat"
输出: 231
解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
在 "eat" 中删除 "t" 并将 116 加入总和。
结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
```

**示例 2:**

```
输入: s1 = "delete", s2 = "leet"
输出: 403
解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
```



**提示:**

+ `0 <= s1.length, s2.length <= 1000`
+ `s1` 和 `s2` 由小写英文字母组成

Related Topics

字符串

动态规划



# 2、题解

## 题目分析

这道题，和【583-连个字符串的删除操作】非常类似，这回不问我们删除的字符个数了，问我们删除的字符的 ASCII 码加起来是多少。

那就不能直接复用计算最长公共子序列的函数了，但是可以依照之前的思路，**稍微修改 base case 和状态转移部分即可直接写出解法代码**



## 解法一

### 算法分析



![image-20230526223947133](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230526223947133.png)

![image-20230526224003548](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230526224003548.png)

### 代码

```java
/**
 * <p>
 * 两个字符串的最小ASCII删除和
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/26
 */
public class leetcode712 {

    public static void main(String[] args) {
        String s1 = "sea", s2 = "eat";

        Solution01 solution01 = new Solution01();
        int minimumDeleteSum = solution01.minimumDeleteSum(s1, s2);
        System.out.println(minimumDeleteSum);

    }

    private static class Solution01 {

        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            // 其中 dp[i][j] 表示使 s1[0:i]和 s2[0:j] 相同的最小 ASCII 删除和
            // 其中 s1[0:i] 表示 s1 的长度为 i 的前缀。
            int[][] dp = new int[m + 1][n + 1];
            // 边界条件：j = 0
            for (int i = 1; i <= m; i++) {
                dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
            }
            // 边界条件：i = 0
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j - 1] + s2.codePointAt(j - 1);
            }

            for (int i = 1; i <= m; i++) {
                int code1 = s1.codePointAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    int code2 = s2.codePointAt(j - 1);
                    if (code1 == code2) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] + code1, dp[i][j - 1] + code2);
                    }
                }

            }

            return dp[m][n];
        }

    }

}
```



### 复杂度分析

- 时间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *s*1 和 *s*2 的长度。二维数组 *dp* 有 *m*+1 行和 *n*+1 列，需要对 *dp* 中的每个元素进行计算。

- 空间复杂度：*O*(*mn*)，其中 *m* 和 *n* 分别是字符串 *s*1 和 *s*2 的长度。创建了 *m*+1 行 *n*+1 列的二维数组 *dp*。









# THE END