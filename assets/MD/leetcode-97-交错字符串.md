# 目录

[toc]

# leetcode-97-交错字符串

- 时间：2023-08-29
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/interleaving-string/
- 难度：中等

给定三个字符串 `s1`、`s2`、`s3`，请你帮忙验证 `s3` 是否是由 `s1` 和 `s2` **交错** 组成的。

两个字符串 `s` 和 `t` **交错** 的定义与过程如下，其中每个字符串都会被分割成若干 **非空** 子字符串：

+ `s = s1 + s2 + ... + sn`
+ `t = t1 + t2 + ... + tm`
+ `|n - m| <= 1`
+ **交错** 是 `s1 + t1 + s2 + t2 + s3 + t3 + ...` 或者 `t1 + s1 + t2 + s2 + t3 + s3 + ...`

**注意：**`a + b` 意味着字符串 `a` 和 `b` 连接。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/02/interleave.jpg)

```
输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出：true
```

**示例 2：**

```
输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出：false
```

**示例 3：**

```
输入：s1 = "", s2 = "", s3 = ""
输出：true
```

 

**提示：**

+ `0 <= s1.length, s2.length <= 100`
+ `0 <= s3.length <= 200`
+ `s1`、`s2`、和 `s3` 都由小写英文字母组成

 

**进阶：**您能否仅使用 `O(s2.length)` 额外的内存空间来解决它?









# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

![image-20230829215930852](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230829215930852.png)



### 代码

```java


/**
 * <p>
 * 交错字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/29
 */
public class leetcode97 {

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isInterleave(s1, s2, s3));

    }


    /**
     * 解法二：动态规划 + 滚动数组（空间优化）
     */
    private static class Solution02 {

        public boolean isInterleave(String s1, String s2, String s3) {
            int len1 = s1.length();
            int len2 = s2.length();
            int len3 = s3.length();
            if (len3 != len1 + len2) {
                return false;
            }

            boolean[] dp = new boolean[len2 + 1];

            dp[0] = true;

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    int p = i + j - 1;
                    if (i > 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                    }
                    if (j > 0) {
                        dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return dp[len2];
        }

    }

}

```





### 复杂度分析

时间复杂度和空间复杂度都是 O(nm)。





## 解法二：动态规划 + 滚动数组空间优化

### 算法分析

![image-20230829220019377](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java-img/image-20230829220019377.png)



### 代码

```java


/**
 * <p>
 * 交错字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/29
 */
public class leetcode97 {

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isInterleave(s1, s2, s3));


    }



    /**
     * 解法二：动态规划 + 滚动数组（空间优化）
     */
    private static class Solution02 {

        public boolean isInterleave(String s1, String s2, String s3) {
            int len1 = s1.length();
            int len2 = s2.length();
            int len3 = s3.length();
            if (len3 != len1 + len2) {
                return false;
            }

            boolean[] dp = new boolean[len2 + 1];

            dp[0] = true;

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    int p = i + j - 1;
                    if (i > 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                    }
                    if (j > 0) {
                        dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return dp[len2];
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(nm)，两重循环的时间代价为 O(nm)。
- 空间复杂度：O(m)，即 s2 的长度。









# THE END