# 目录

[toc]

# leetcode-32-最长有效括号

- 时间：2023-07-15
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-valid-parentheses/
- 难度：困难

![image-20230715182948621](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230715182948621.png)



# 2、题解

## 题目分析



## 解法一: 动态规划

### 算法分析

**函数定义：**

我们定义 `dp[i]` 表示以下标 i 字符结尾的最长有效括号的长度。

我们将dp数组全部初始化为0。显然有效的子串一定以)'结尾，因此我们可以知道以`‘('`结尾的子串对应的dp值必定为0，我们只需要求解`')'`在p数组中对应位置的值。



**状态转移：**

1. 当 `s[i]` 为 `(` ,`dp[i]` 必然等于 0，因为不可能组成有效的括号；

2. 那么 `s[i]` 为 `)`

   2.1 当 `s[i-1]` 为 `(`，形如，`....()` ,那么 `dp[i] = dp[i-2] + 2`；

   2.2 当 `s[i-1]` 为 `)` , 形如 `......))` , **并且** `s[i-dp[i-1] - 1]` 为 `(`， 那么 ；

   ```sh
   dp[i] = dp[i - 1] + 2 + dp[i - (dp[i - 1] + 2)]
   	  = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
   ```

3. 画图就可以理解了。





### 代码

```java


/**
 * <p>
 * 最长有效括号
 * </p>
 *
 * @author admin
 * @date 2023/7/15
 */
public class leetcode32 {

    public static void main(String[] args) {
        String s = ")()())";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.longestValidParentheses(s));
    }

    /**
     * 解法一：动态规划
     */
    private static class Solution01 {

        public int longestValidParentheses(String s) {
            int n = s.length();
            int res = 0;

            // dp[i] 表示，以 s.charAt(i) 结尾的最长有效括号的长度
            int[] dp = new int[n];
            for (int i = 1; i < n; i++) {
                // 只有以 ')' 结尾，才可能是有效括号
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }

                    res = Math.max(res, dp[i]);
                }
            }

            return res;
        }

    }

}

```





### 复杂度分析

- 时间复杂度：O(n),其中 n 为字符串的长度。我们只需遍历整个字符串一次，即可将 dp 数组求出
  来。
- 空间复杂度：O(n)。我们需要一个大小为 n 的 dp 数组。









# THE END