# 目录

[toc]

# leetcode-3-无重复字符的最长子串

- 时间：2023-06-14

- 参考链接：
  - [我写了首诗，把滑动窗口算法算法变成了默写题](https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--f7a92/)
  - 



# 1、题目

- 题目：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
- 难度：中等

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。



**提示：**

+ `0 <= s.length <= 5 * 104`
+ `s` 由英文字母、数字、符号和空格组成



# 2、题解

## 题目分析



## 解法一:  滑动窗口

### 算法分析





### 代码

```java

/**
 * <p>
 * 无重复字符的最长子串
 * </p>
 *
 * @author admin
 * @date 2023/6/14
 */
public class leetcode3 {

    public static void main(String[] args) {
        String s = "abcabcbb";

        Solution01 solution01 = new Solution01();
        int length01 = solution01.lengthOfLongestSubstring(s);
        System.out.println(length01);

    }

    /**
     * 解法一：滑动窗口
     */
    private static class Solution01 {

        Map<Character, Integer> window = new HashMap<>();

        public int lengthOfLongestSubstring(String s) {
            int left = 0;
            int right = 0;

            int res = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                window.put(c, window.getOrDefault(c, 0) + 1);
                while (window.get(c) > 1) {
                    char d = s.charAt(left);
                    left++;
                    window.put(d, window.get(d) - 1);
                }
                res = Math.max(res, right - left);
            }

            return res;
        }

    }

}

```

输出：

```sh
3
```





### 复杂度分析

- 时间复杂度：O(N),其中N是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
- 空间复杂度：O(∑),其中∑表示字符集（即字符串中可以出现的字符），表示字符集的大小。在
  本题中没有明确说明字符集，因此可以默认为所有ASC川码在「0,128)内的字符，即=128。我们
  需要用到哈希集合来存储出现过的字符，而字符最多有∑个，因此空间复杂度为O(∑)。





# THE END