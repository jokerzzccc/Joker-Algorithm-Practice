# 目录

[toc]

# leetcode-10-正则表达式匹配

- 时间：2023-06-06
- 参考链接：
  - ​	https://mp.weixin.qq.com/s/rnaFK05IcFWvNN1ppNf2ug
  - 




# 1、题目

- 题目：https://leetcode.cn/problems/regular-expression-matching/
- 难度：困难



给你一个字符串 `s` 和一个字符规律 `p`，请你来实现一个支持 `'.'` 和 `'*'` 的正则表达式匹配。

+ `'.'` 匹配任意单个字符
+ `'*'` 匹配零个或多个前面的那一个元素

所谓匹配，是要涵盖 **整个** 字符串 `s`的，而不是部分字符串。

**示例 1：**

```
输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3：**

```
输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```



**提示：**

+ `1 <= s.length <= 20`
+ `1 <= p.length <= 20`
+ `s` 只包含从 `a-z` 的小写字母。
+ `p` 只包含从 `a-z` 的小写字母，以及字符 `.` 和 `*`。
+ 保证每次出现字符 `*` 时，前面都匹配到有效的字符

Related Topics

递归

字符串

动态规划



# 2、题解

## 题目分析

- **正则表达算法问题只需要把住一个基本点：看两个字符是否匹配，一切逻辑围绕匹配/不匹配两种情况展开即可。**
- 两个字符串一般都是双指针法。
- 题目中：`保证每次出现字符 `*` 时，前面都匹配到有效的字符`
  - 就是说，不会出现 `**`这种搭配，即 * 前一个字符一定是 `.` 或字母。








## 解法一

### 算法分析

根据「状态」，我们可以定义一个`dp`函数：

```java
bool dp(String s, int i, String p, int j);
```

`dp`函数的定义如下：

**若`dp(s,i,p,j) = true`，则表示`s[i..]`可以匹配`p[j..]`；若`dp(s,i,p,j) = false`，则表示`s[i..]`无法匹配`p[j..]`**。

根据这个定义，我们想要的答案就是`i = 0,j = 0`时`dp`函数的结果，所以可以这样使用这个`dp`函数：

```
bool isMatch(string s, string p) {
    // 指针 i，j 从索引 0 开始移动
    return dp(s, 0, p, 0);
```

可以根据之前的代码



### 代码

```java

/**
 * <p>
 * 正则表达式匹配
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/6/6
 */
public class leetcode10 {

    public static void main(String[] args) {
        String s = "ab", p = ".*";

        Solution01 solution01 = new Solution01();
        boolean match01 = solution01.isMatch(s, p);
        System.out.println(match01);

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        /**
         * 备忘录
         */
        boolean[][] memo;

        public boolean isMatch(String s, String p) {
            memo = new boolean[s.length() + 1][p.length() + 1];
            char[] sCharArray = s.toCharArray();
            char[] pCharArray = p.toCharArray();

            return dp(sCharArray, 0, pCharArray, 0);
        }

        /**
         * dp函数的定义如下：
         * 若dp(s,i,p,j) = true，则表示s[i..]可以匹配p[j..]；
         * 若dp(s,i,p,j) = false，则表示s[i..]无法匹配p[j..]。
         */
        private boolean dp(char[] s, int i, char[] p, int j) {
            int m = s.length;
            int n = p.length;

            // base case
            if (j == n) {
                return i == m;
            }
            if (i == m) {
                // 如果能匹配空串，一定是字符和 * 成对儿出现
                if ((n - j) % 2 == 1) {
                    return false;
                }
                // 检查是否为 x*y*z* 这种形式
                for (; j + 1 < p.length; j += 2) {
                    if (p[j + 1] != '*') {
                        return false;
                    }
                }
                return true;
            }

            // 记录状态 (i, j)，消除重叠子问题
            if (memo[i][j]) {
                return memo[i][j];
            }

            // 核心逻辑
            boolean res = false;
            if (s[i] == p[j] || p[j] == '.') {
                // 匹配
                if (j < n - 1 && p[j + 1] == '*') {
                    // 1.1 通配符匹配 0 次或多次
                    res = dp(s, i, p, j + 2)
                            || dp(s, i + 1, p, j);
                } else {
                    // 1.2 常规匹配 1 次
                    res = dp(s, i + 1, p, j + 1);
                }
            } else {
                // 不匹配
                if (j < n - 1 && p[j + 1] == '*') {
                    // 2.1 通配符匹配 0 次
                    res = dp(s, i, p, j + 2);
                } else {
                    // 2.2 无法继续匹配
                    res = false;
                }
            }

            memo[i][j] = res;
            return res;
        }

    }

}
```





### 复杂度分析











# THE END