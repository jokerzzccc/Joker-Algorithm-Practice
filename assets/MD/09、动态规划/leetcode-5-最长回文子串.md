# 目录

[toc]

# leetcode-5-最长回文子串

- 时间：2023-05-23
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/longest-palindromic-substring/
- 难度：中等



给你一个字符串 `s`，找到 `s` 中最长的回文子串。

如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。



**示例 1：**

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```

**示例 2：**

```
输入：s = "cbbd"
输出："bb"
```



**提示：**

+ `1 <= s.length <= 1000`
+ `s` 仅由数字和英文字母组成

Related Topics

字符串

动态规划



# 2、题解

## 题目分析



## 解法一: 动态规划

### 算法分析

**状态定义**：

用 *P*(*i*,*j*) 表示字符串 *s* 的第 *i* 到 *j* 个字母组成的串（下文表示成 *s*[*i*:*j*]）是否为回文串：

- *P*(*i*,*j*)=true, 如果子串 *$S_i$*…*$S_j$* 是回文串

- *P*(*i*,*j*)=false, 其它情况

这里的「其它情况」包含两种可能性：

+ *s*[*i*,*j*] 本身不是一个回文串；
+ *i*>*j*，此时 *s*[*i*,*j*] 本身不合法。



**状态转移方程**：

那么我们就可以写出动态规划的**状态转移方程**：

*P*(*i*,*j*)=*P*(*i*+1,*j*−1)∧(*$S_i$*==*$S_j$*)

也就是说，只有 *s*[*i*+1:*j*−1] 是回文串，并且 *s* 的第 *i* 和 *j* 个字母相同时，*s*[*i*:*j*] 才会是回文串。

上文的所有讨论是建立在子串长度大于 2 的前提之上的，我们还需要考虑动态规划中的边界条件，即子串的长度为 1 或 2。对于长度为 1 的子串，它显然是个回文串；对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文串。因此我们就可以写出**动态规划的边界条件**：

- *P*(*i*,*i*)=true

- *P*(*i*,*i*+1)=(*$S_i$*==*$S_j$*+1)



**返回值**：

根据这个思路，我们就可以完成动态规划了，最终的答案即为所有 *P*(*i*,*j*)=true 中 *j*−*i*+1（即子串长度）的最大值。**注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。**





### 代码

```java
/**
 * <p>
 * 最长回文子串
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/24
 */
public class leetcode005 {

    public static void main(String[] args) {
        String s = "babad";

        Solution01 solution01 = new Solution01();
        String longestPalindrome01 = solution01.longestPalindrome(s);
        System.out.println(longestPalindrome01);

    }


    /**
     * 解法一：动态规划版
     */
    private static class Solution01 {

        public String longestPalindrome(String s) {
            int length = s.length();
            if (length < 2) {
                return s;
            }
            // dp[i][j]: 表示 S[i...j] 是回文子串。
            boolean[][] dp = new boolean[length][length];

            // 单个字符都是回文子串
            for (int i = 0; i < length; i++) {
                dp[i][i] = true;
            }

            char[] chars = s.toCharArray();
            int maxLen = 1;
            int begin = 0;
            // 枚举子串长度
            for (int subLen = 2; subLen <= length; subLen++) {
                // 枚举左边界 i
                for (int i = 0; i < length; i++) {
                    // 右边界 j
                    int j = subLen + i - 1;
                    // 右边界越界
                    if (j >= length) {
                        break;
                    }

                    if (chars[i] != chars[j]) {
                        dp[i][j] = false;
                    } else {
                        // 小于等于三个，且头尾相等，则一定是回文字符串，比如 "aba"
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            // 当头尾两个相等，则由子串决定
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    // 比对，是否更新最长回文子串
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }

            return s.substring(begin, begin + maxLen);

        }

    }

}

```





### 复杂度分析



- 时间复杂度：*O*($n^2$)，其中 *n* 是字符串的长度。动态规划的状态总数为 *O*(*n*2)，对于每个状态，我们需要转移的时间为 *O*(1)。

- 空间复杂度：*O*($n^2$)，即存储动态规划状态需要的空间。



## 解法二：中心扩散法

### 算法分析

我们仔细观察一下方法一中的状态转移方程：

- P(i,j) = true
- p(i, i + 1) = (Si == Si+1)
- *P*(*i*,*j*)=*P*(*i*+1,*j*−1)∧(*$S_i$*==*$S_j$*)

找出其中的状态转移链：

*P*(*i*,*j*)←*P*(*i*+1,*j*−1)←*P*(*i*+2,*j*−2)←⋯←某一边界情况

可以发现，**所有的状态在转移的时候的可能性都是唯一的**。也就是说，我们可以从每一种边界情况开始「扩展」，也可以得出所有的状态对应的答案。

边界情况即为子串长度为 1 或 2 的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。如果两边的字母相同，我们就可以继续扩展，例如从 *P*(*i*+1,*j*−1) 扩展到 *P*(*i*,*j*)；如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。

聪明的读者此时应该可以发现，「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。我们对所有的长度求出最大值，即可得到最终的答案。



### 代码



```java
/**
 * <p>
 * 最长回文子串
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/5/24
 */
public class leetcode005 {

    public static void main(String[] args) {
        String s = "babad";

        Solution02 solution02 = new Solution02();
        String longestPalindrome02 = solution02.longestPalindrome(s);
        System.out.println(longestPalindrome02);
    }

    /**
     * 中心扩展法
     */
    private static class Solution02 {

        // 中心扩展法
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int start = 0, end = 0;
            // 遍历每一个字符串，每一个字符都是中心
            for (int i = 0; i < s.length(); i++) {
                // 两种边界条件，len1: 中心子串长度为1；len2: 中心子串长度为2；
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int subLen = Math.max(len1, len2);
                if (subLen > end - start + 1) {
                    start = i - (subLen - 1) / 2;
                    end = i + subLen / 2;
                }
            }

            return s.substring(start, end + 1);
        }

        /**
         * 由中心向两边扩展
         *
         * @param s 输入字符串
         * @param left 左下标
         * @param right 右下标
         * @return 以当前字符为中心的最长回文字符串的长度。
         */
        public int expandAroundCenter(String s, int left, int right) {
            // 不越界，且头尾相等，才是回文。
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return right - left - 1;
        }

    }

}

```



### 复杂度分析

- 时间复杂度：*O*($n^2$)，其中 *n* 是字符串的长度。长度为 1 和 2 的回文中心分别有 *n* 和 *n*−1 个，每个回文中心最多会向外扩展 *O*(*n*) 次。

- 空间复杂度：*O*(1)。



# THE END