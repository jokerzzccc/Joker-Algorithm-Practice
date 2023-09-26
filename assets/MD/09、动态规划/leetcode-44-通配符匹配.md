# 目录

[toc]

# leetcode-44-通配符匹配

- 时间：2023-07-02
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/wildcard-matching/?company_slug=alibaba
- 难度：困难



给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：

- ' ?' 可以匹配任何单个字符。
- '*' 可以匹配任意字符序列（包括空字符序列）。

判定匹配成功的充要条件是：字符模式必须能够 **完全匹配** 输入字符串（而不是部分匹配）。



 

**示例 1：**

```
输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
```



**提示：**

+ `0 <= s.length, p.length <= 2000`
+ `s` 仅由小写英文字母组成
+ `p` 仅由小写英文字母、`'?'` 或 `'*'` 组成





# 2、题解

## 题目分析



## 解法一：动态规划

### 算法分析

1、函数定义：

`dp[i][j]` 表示：字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配

![image-20230702162458528](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230702162458528.png)

![image-20230702162518576](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230702162518576.png)

![image-20230702162535054](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230702162535054.png)





### 代码

```java


/**
 * <p>
 * 通配符匹配
 * </p>
 *
 * @author admin
 * @date 2023/7/2
 */
public class leetcode44 {

    public static void main(String[] args) {
        String s = "aa", p = "a";

        System.out.println(new Solution01().isMatch(s, p));

    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            // `dp[i][j]` 表示：字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配
            boolean[][] dp = new boolean[m + 1][n + 1];

            // base case
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                if (p.charAt(i - 1) == '*') {
                    dp[0][i] = true;
                } else {
                    break;
                }
            }

            // 状态转移计算
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }

            return dp[m][n];
        }

    }

}

```





### 复杂度分析

![image-20230702163305725](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230702163305725.png)







# THE END