# 目录

[toc]

# leetcode-151-反转字符串中的单词

- 时间：2023-06-12

- 参考链接：
  - [二维数组的花式遍历技巧](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/er-wei-shu-150fb/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/reverse-words-in-a-string/
- 难度：中等



给你一个字符串 s ，请你反转字符串中 单词 的顺序。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。

注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。



**提示：**

1 <= s.length <= 104
s 包含英文大小写字母、数字和空格 ' '
s 中 至少存在一个 单词

**进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。**







# 2、题解

## 题目分析



## 解法一: 原地解法

### 算法分析

常规的方式是把 `s` 按空格 `split` 成若干单词，然后 `reverse` 这些单词的顺序，最后把这些单词 `join` 成句子。但这种方式使用了额外的空间，并不是「原地反转」单词。

**正确的做法是，先将整个字符串 `s` 反转**：

**然后将每个单词分别反转**：

这样，就实现了原地反转所有单词顺序的目的。



### 代码

```java
package com.joker.algorithm.array;

/**
 * <p>
 * 反转字符串中的单词
 * </p>
 *
 * @author admin
 * @date 2023/6/12
 */
public class leetcode151 {

    public static void main(String[] args) {
        String s = "the sky is blue";

        Solution01 solution01 = new Solution01();
        String s1 = solution01.reverseWords(s);
        System.out.println(s1);
    }

    /**
     * 解法一：原地解法
     */
    private static class Solution01 {

        public String reverseWords(String s) {
            StringBuilder sb = trimSpaces(s);
            // 翻转字符串
            reverse(sb, 0, sb.length() - 1);

            // 翻转每个单词
            reverseEachWord(sb);

            return sb.toString();
        }

        public StringBuilder trimSpaces(String s) {
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                ++left;
            }

            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                --right;
            }

            // 将字符串间多余的空白字符去除
            StringBuilder sb = new StringBuilder();
            while (left <= right) {
                char c = s.charAt(left);

                if (c != ' ') {
                    sb.append(c);
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }

                ++left;
            }
            return sb;
        }

        public void reverse(StringBuilder sb, int left, int right) {
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, tmp);
            }
        }

        public void reverseEachWord(StringBuilder sb) {
            int n = sb.length();
            int start = 0, end = 0;

            while (start < n) {
                // 循环至单词的末尾
                while (end < n && sb.charAt(end) != ' ') {
                    ++end;
                }
                // 翻转单词
                reverse(sb, start, end - 1);
                // 更新start，去找下一个单词
                start = end + 1;
                ++end;
            }
        }

    }

}

```

输出：

```sh
blue is sky the
```





### 复杂度分析

- 时间复杂度：O(n),其中n为输入字符串的长度。
- 空间复杂度：Java和Python的方法需要O(n)的空间来存储字符串，而C++方法只需要O(1)的
  额外空间来存放若干变量。



# THE END